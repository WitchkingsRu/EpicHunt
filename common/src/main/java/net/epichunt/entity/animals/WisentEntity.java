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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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

public class WisentEntity extends Animal implements NeutralMob{
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(
            WisentEntity.class, EntityDataSerializers.BOOLEAN);
    private int warningSoundTicks;
    private static final UniformInt PERSISTENT_ANGER_TIME;
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    private static final int AGGRESSION_RADIUS = 10;
    private static final int AGGRESSION_THRESHOLD = 50;
    public static boolean isAngry = false;

    private int proximityTimer = 0;

    public WisentEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0F);

    }

    public static final Supplier<EntityType<WisentEntity>> WISENT = Suppliers.memoize(() -> EntityType.Builder.of(WisentEntity::new, MobCategory.CREATURE)
            .sized(1.5f, 1.5f).build("wisent"));

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
    
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;


    private void setupAnimationStates() {
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
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
        this.entityData.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(1, new WisentMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1f, 100));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(1, new WisentAttackPlayersGoal());
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

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 45D).add(Attributes.MOVEMENT_SPEED, 0.3000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D).add(Attributes.ATTACK_KNOCKBACK, 2);
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

    class WisentMeleeAttackGoal extends MeleeAttackGoal {
        private final WisentEntity entity;
        private int attackDelay = 5;
        private int ticksUntilNextAttack = 10;
        private boolean shouldCountTillNextAttack = false;

        public WisentMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            entity = ((WisentEntity) pMob);
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

    private class WisentAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
        public WisentAttackPlayersGoal() {
            super(WisentEntity.this, Player.class, 10, true, true, (Predicate)null);
        }

        public boolean canUse() {
            if (WisentEntity.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    List<WisentEntity> list = WisentEntity.this.level().getEntitiesOfClass(WisentEntity.class, WisentEntity.this.getBoundingBox().inflate(8.0, 4.0, 8.0));
                    Iterator var2 = list.iterator();

                    while(var2.hasNext()) {
                        WisentEntity goose = (WisentEntity)var2.next();
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
        return WISENT.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.WISENT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.WISENT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.WISENT_DEATH.get();
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }
    protected float getSoundVolume() {
        return 0.3F;
    }
    static {
        PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    }
}

