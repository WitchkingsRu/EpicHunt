package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.entity.SurfaceSwimGoal;
import net.epichunt.item.ModItem;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GooseEntity extends Animal implements NeutralMob{
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(
            GooseEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SWIMMING;
    public static final Ingredient FOOD_ITEMS;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private boolean isGliding = false;
    private int warningSoundTicks;
    private static final UniformInt PERSISTENT_ANGER_TIME;
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    private static final int AGGRESSION_RADIUS = 5;
    private static final int AGGRESSION_THRESHOLD = 100;
    public int eggTime = 9999;
    public static boolean isAngry = false;

    private int proximityTimer = 0;

    public GooseEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_OPEN, -1.0F);
        this.moveControl = new DuckMoveControl(this);
        this.setMaxUpStep(1.0F);

    }

    public static final Supplier<EntityType<GooseEntity>> GOOSE = Suppliers.memoize(() -> EntityType.Builder.of(GooseEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("goose"));

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.4f);
    }

    private void updateProximityTimer() {
        if (!this.level().isClientSide) {
            List<Player> nearbyPlayers = this.level().getEntitiesOfClass(
                    Player.class,
                    this.getBoundingBox().inflate(AGGRESSION_RADIUS)
            );

            if (!nearbyPlayers.isEmpty()) {
                proximityTimer++;
                if (proximityTimer >= AGGRESSION_THRESHOLD) {
                    this.becomeAggressive(nearbyPlayers.get(0));
                    this.isAngry = true;
                }
            } else {
                proximityTimer = 0;
                this.isAngry = false;
            }
        }
    }

    private void becomeAggressive(Player target) {
        this.setPersistentAngerTarget(target.getUUID());
        this.startPersistentAngerTimer();
    }


    @Override
    public void tick() {
        super.tick();
        if(!this.isInWater()&&!this.onGround()) {
            this.resetFallDistance();
        }
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        if (this.warningSoundTicks > 0) {
            --this.warningSoundTicks;
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
        updateProximityTimer();
    }

    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;


    private void setupAnimationStates() {
        if (!this.isInWater() && !this.onGround()) {
            if (!isGliding) {
                this.flyAnimationState.start(this.tickCount);
                isGliding = true;
            }
        } else if (this.isInWater() || this.onGround()) {
            this.flyAnimationState.stop();
            isGliding = false;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
        if (this.isInWater()) {
            this.swimAnimationState.start(this.tickCount);
        }
        else {
            this.swimAnimationState.stop();
        }
    }

    public boolean isSwimming() {
        return this.entityData.get(IS_SWIMMING);
    }

    public void setSwimming(boolean isSwimming) {
        this.entityData.set(IS_SWIMMING, isSwimming);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_SWIMMING, false);
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("IsSwimming", this.isSwimming());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setSwimming(tag.getBoolean("IsSwimming"));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(1, new GooseMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new SurfaceSwimGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1f, 100));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2f, Ingredient.of(Items.WHEAT_SEEDS), false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.targetSelector.addGoal(1, new GooseAttackPlayersGoal());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 5, true, false, this::isAngryAt));
    }

    @Override
    public boolean isAngryAt(LivingEntity entity) {
        if (entity instanceof Player player) {
            return this.getPersistentAngerTarget() != null && this.getPersistentAngerTarget().equals(player.getUUID());
        }
        return false;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        return super.finalizeSpawn(levelAccessor, difficulty, spawnType, spawnData, dataTag);
    }

    public static boolean checkDuckSpawnRules(EntityType<GooseEntity> type, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() > levelAccessor.getSeaLevel() - 4 && isBrightEnoughToSpawn(levelAccessor, pos);
    }


    protected PathNavigation createNavigation(Level arg) {
        return new DuckNavigation(this, arg);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.23000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 0.01).add(Attributes.ATTACK_KNOCKBACK, 0);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }
    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }
    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }


    private static class DuckNavigation extends AmphibiousPathNavigation {
        DuckNavigation(Mob mob, Level level) {
            super(mob, level);
        }

        @Override
        public boolean isStableDestination(BlockPos pos) {
            if (this.mob.isInWater()) {
                return this.level.getBlockState(pos).is(Blocks.WATER);
            } else {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        }
    }


    @Override
    public void travel(Vec3 travelVector) {
        if (this.isSwimming() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        } else {
            super.travel(travelVector);
        }
    }
    @Override
    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }
        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();

        if (!this.onGround() && !this.isInWater() && vec3.y < 0.0) {
            this.setDeltaMovement(vec3.x, vec3.y * 0.6, vec3.z);
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level().isClientSide && this.isAlive() && !this.isInWater() && !this.isBaby() && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(ModItem.GOOSE_EGG.get());
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggTime = this.random.nextInt(6000) + 6000;
        }
    }
    private static class DuckMoveControl extends MoveControl {
        private final GooseEntity goose;

        DuckMoveControl(GooseEntity goose) {
            super(goose);
            this.goose = goose;
        }

        @Override
        public void tick() {
            if (this.goose.isSwimming() && this.goose.isInWater()) {
                this.goose.setDeltaMovement(this.goose.getDeltaMovement().add(0.0, 0.005, 0.0));
            }
            super.tick();
        }
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int i) {
        this.remainingPersistentAngerTime = i;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID uUID) {
        this.persistentAngerTarget = uUID;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    class GooseMeleeAttackGoal extends MeleeAttackGoal {
        private final GooseEntity entity;
        private int attackDelay = 5;
        private int ticksUntilNextAttack = 10;
        private boolean shouldCountTillNextAttack = false;

        public GooseMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            entity = ((GooseEntity) pMob);
        }

        @Override
        public void start() {
            super.start();
            attackDelay = 5;
            ticksUntilNextAttack = 10;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
                shouldCountTillNextAttack = true;

                if(isTimeToStartAttackAnimation()) {
                    entity.setAttacking(true);
                }

                if(isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAttacking(false);
                entity.attackAnimationTimeout = 0;
            }
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected boolean isTimeToStartAttackAnimation() {
            return this.ticksUntilNextAttack <= attackDelay;
        }

        protected int getTicksUntilNextAttack() {
            return this.ticksUntilNextAttack;
        }


        protected void performAttack(LivingEntity pEnemy) {
            this.resetAttackCooldown();
//            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
        }

        @Override
        public void tick() {
            super.tick();
            if(shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            }
        }

        @Override
        public void stop() {
            entity.setAttacking(false);
            super.stop();
        }
    }

    private class GooseAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
        public GooseAttackPlayersGoal() {
            super(GooseEntity.this, Player.class, 10, true, true, (Predicate)null);
        }

        public boolean canUse() {
            if (GooseEntity.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    List<GooseEntity> list = GooseEntity.this.level().getEntitiesOfClass(GooseEntity.class, GooseEntity.this.getBoundingBox().inflate(8.0, 4.0, 8.0));
                    Iterator var2 = list.iterator();

                    while(var2.hasNext()) {
                        GooseEntity goose = (GooseEntity)var2.next();
                        if (goose.isBaby()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        protected double getFollowDistance() {
            return super.getFollowDistance() * 0.5;
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return GOOSE.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.GOOSE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.GOOSE_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.GOOSE_DEATH.get();
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.3F;
    }
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.WHEAT_SEEDS);
    }
    static {
        IS_SWIMMING = SynchedEntityData.defineId(GooseEntity.class, EntityDataSerializers.BOOLEAN);
        FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS);
        PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    }
}

