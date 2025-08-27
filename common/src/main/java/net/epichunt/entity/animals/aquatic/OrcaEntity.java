package net.epichunt.entity.animals.aquatic;


import com.google.common.base.Suppliers;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OrcaEntity extends WaterAnimal implements NeutralMob {
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(
            OrcaEntity.class, EntityDataSerializers.BOOLEAN);
    private int warningSoundTicks;
    private static final UniformInt PERSISTENT_ANGER_TIME;
    private int remainingPersistentAngerTime;
    @Nullable
    private UUID persistentAngerTarget;
    private static final int AGGRESSION_RADIUS = 10;
    private static final int AGGRESSION_THRESHOLD = 50;
    public static boolean isAngry = false;
    private int proximityTimer = 0;
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL;
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    private final Level lvl;
    private int spoutCooldown = 800;

    public OrcaEntity(EntityType<? extends OrcaEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.lvl = level;
        this.noCulling = true;
    }

    public static final Supplier<EntityType<OrcaEntity>> ORCA = Suppliers.memoize(() -> EntityType.Builder.of(OrcaEntity::new, MobCategory.WATER_CREATURE)
            .sized(1.8f, 1.0f).build("orca"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

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
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }

                if (this.onGround()) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), (double)0.5F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float g = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float h = 1.2F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)h + (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)h + (double)g, (double)0.0F, (double)0.0F, (double)0.0F);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)h - (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)h - (double)g, (double)0.0F, (double)0.0F, (double)0.0F);
                }
            }

        }
        if (this.warningSoundTicks > 0) {
            --this.warningSoundTicks;
        }
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
        updateProximityTimer();
        this.spoutCooldown--;

        BlockPos aboveHead = new BlockPos((int) this.getX(), (int) (this.getEyeY() + 1.0), (int) this.getZ());

        if (this.isInWater() && this.level().getBlockState(aboveHead).isAir()) {
            spawnSpoutParticles();
            this.setSpoutCooldown(800);
        }

    }

    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;

    private void setupAnimationStates() {
        if(this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(400) + 1100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
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

    public int getMoistnessLevel() {
        return (Integer)this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int i) {
        this.entityData.set(MOISTNESS_LEVEL, i);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);
        this.entityData.define(ATTACKING, false);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Moistness", this.getMoistnessLevel());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setMoisntessLevel(compoundTag.getInt("Moistness"));
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void handleAirSupply(int i) {
    }

    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new OrcaMeleeAttackGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new SurfaceSpoutGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, (double)1.0F, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal(this, Guardian.class, 8.0F, (double)1.0F, (double)1.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(1, new OrcaAttackEntitiesGoal(this));
    }
    @Override
    public boolean isAngryAt(LivingEntity entity) {
        if (entity instanceof Player player) {
            return this.getPersistentAngerTarget() != null && this.getPersistentAngerTarget().equals(player.getUUID());
        }
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double)35.0F).add(Attributes.MOVEMENT_SPEED, (double)0.5F)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D).add(Attributes.ATTACK_KNOCKBACK, 2);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
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

    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }


    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int i) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.7F;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.WHALE_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return Sounds.WHALE_DEATH.get();
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return Sounds.ORCA_AMBIENT.get();
    }

    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }

    protected boolean closeToNextPos() {
        BlockPos blockPos = this.getNavigation().getTargetPos();
        return blockPos != null ? blockPos.closerToCenterThan(this.position(), (double)12.0F) : false;
    }

    public void travel(Vec3 vec3) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add((double)0.0F, -0.005, (double)0.0F));
            }
        } else {
            super.travel(vec3);
        }

    }
    protected void spawnSpoutParticles() {
        Vec3 look = this.getLookAngle();
        Vec3 base = new Vec3(this.getX(), this.getEyeY(), this.getZ()).add(look.scale(1)); // вершина хитбокса
        for (int i = 0; i < 40; i++) {
            double x = base.x + this.getRandom().nextGaussian() * 0.15;
            double y = base.y + i * 0.25;
            double z = base.z + this.getRandom().nextGaussian() * 0.15;

            lvl.addAlwaysVisibleParticle(ParticleTypes.SPLASH,
                    x, y, z,
                    0, 0.1 + this.getRandom().nextDouble() * 0.1, 0);
        }
    }
    public int getSpoutCooldown() {
        return spoutCooldown;
    }

    public void setSpoutCooldown(int spoutCooldown) {
        this.spoutCooldown = spoutCooldown;
    }

    public boolean canBeLeashed(Player player) {
        return true;
    }

    static {
        MOISTNESS_LEVEL = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.INT);
        PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    }
    static class SurfaceSpoutGoal extends Goal {
        private final OrcaEntity mob;

        public SurfaceSpoutGoal(OrcaEntity pathfinderMob) {
            this.mob = pathfinderMob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            return this.mob.getSpoutCooldown() <= 100;
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public boolean isInterruptable() {
            return false;
        }

        public void start() {
            this.findAirPosition();
        }

        private void findAirPosition() {
            Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.mob.getX() - (double)1.0F), this.mob.getBlockY(), Mth.floor(this.mob.getZ() - (double)1.0F), Mth.floor(this.mob.getX() + (double)1.0F), Mth.floor(this.mob.getY() + (double)8.0F), Mth.floor(this.mob.getZ() + (double)1.0F));
            BlockPos blockPos = null;

            for(BlockPos blockPos2 : iterable) {
                if (this.givesAir(this.mob.level(), blockPos2)) {
                    blockPos = blockPos2;
                    break;
                }
            }

            if (blockPos == null) {
                blockPos = BlockPos.containing(this.mob.getX(), this.mob.getY() + (double)8.0F, this.mob.getZ());
            }

            this.mob.getNavigation().moveTo((double)blockPos.getX(), (double)(blockPos.getY() + 1), (double)blockPos.getZ(), (double)1.0F);
        }

        public void tick() {
            this.findAirPosition();
            this.mob.moveRelative(0.02F, new Vec3((double)this.mob.xxa, (double)this.mob.yya, (double)this.mob.zza));
            this.mob.move(MoverType.SELF, this.mob.getDeltaMovement());
        }

        private boolean givesAir(LevelReader levelReader, BlockPos blockPos) {
            BlockState blockState = levelReader.getBlockState(blockPos);
            return (levelReader.getFluidState(blockPos).isEmpty() || blockState.is(Blocks.BUBBLE_COLUMN)) && blockState.isPathfindable(levelReader, blockPos, PathComputationType.LAND);
        }
    }
    class OrcaMeleeAttackGoal extends Goal {
        private final OrcaEntity orca;
        private LivingEntity target;
        private final double speed;
        private int ticksUntilNextAttack = 20;
        private final int attackDelay = 20;
        private boolean countingAttack = false;

        public OrcaMeleeAttackGoal(OrcaEntity orca, double speed) {
            this.orca = orca;
            this.speed = speed;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.orca.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean canContinueToUse() {
            return canUse();
        }

        @Override
        public void start() {
            this.target = this.orca.getTarget();
            this.resetAttackCooldown();
            this.countingAttack = false;
        }

        @Override
        public void stop() {
            this.target = null;
            this.orca.getNavigation().stop();
            this.orca.setAttacking(false);
            this.orca.attackAnimationTimeout = 0;
            this.countingAttack = false;
        }

        @Override
        public void tick() {
            if (this.target == null) return;

            double distanceSqr = this.orca.distanceToSqr(this.target);
            double attackReach = (this.orca.getBbWidth() + this.target.getBbWidth()) * 2.0F;
            double attackReachSqr = attackReach * attackReach;

            Vec3 direction = new Vec3(
                    this.target.getX() - this.orca.getX(),
                    this.target.getY() - this.orca.getY(),
                    this.target.getZ() - this.orca.getZ()
            ).normalize();

            double moveSpeed = 0.1D;

            this.orca.setDeltaMovement(
                    this.orca.getDeltaMovement().scale(0.5).add(direction.scale(moveSpeed))
            );
            this.orca.setYRot((float)(Mth.atan2(direction.z, direction.x) * (180F / Math.PI)) - 90.0F);
            this.orca.yBodyRot = this.orca.getYRot();
            this.orca.yHeadRot = this.orca.getYRot();
            this.orca.getNavigation().stop();

            if (distanceSqr <= attackReachSqr) {
                if (!countingAttack) {
                    countingAttack = true;
                    ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
                    this.orca.setAttacking(true);
                } else {
                    ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);

                    if (ticksUntilNextAttack == attackDelay) {
                        this.orca.setAttacking(true);
                    }

                    if (ticksUntilNextAttack == 0) {
                        this.orca.doHurtTarget(this.target);
                        this.orca.setAttacking(false);
                        countingAttack = false;
                    }
                }
            } else {
                countingAttack = false;
                ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
                this.orca.setAttacking(false);
                this.orca.attackAnimationTimeout = 0;
            }
        }

        private void resetAttackCooldown() {
            this.ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
        }

        protected int adjustedTickDelay(int ticks) {
            return ticks;
        }
    }
    public class OrcaAttackEntitiesGoal extends NearestAttackableTargetGoal<LivingEntity> {
        private static final Set<Class<? extends LivingEntity>> TARGET_CLASSES = Set.of(
                Dolphin.class,
                SturgeonEntity.class
        );

        public OrcaAttackEntitiesGoal(OrcaEntity orca) {
            super(orca, LivingEntity.class, 10, true, true, entity -> {
                return TARGET_CLASSES.stream().anyMatch(clazz -> clazz.isInstance(entity));
            });
        }

        @Override
        public boolean canUse() {
            if (this.mob.isBaby()) return false;
            return super.canUse();
        }
    }

}
