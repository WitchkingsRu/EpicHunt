package net.epichunt.entity.animals.aquatic;


import com.google.common.base.Suppliers;
import net.epichunt.entity.animals.WisentEntity;
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
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
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
    public static boolean shouldSpawnParticles = false;
    private final Level lvl;

    public OrcaEntity(EntityType<? extends OrcaEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.lvl = level;
        this.noCulling = true;
    }

    public static final Supplier<EntityType<OrcaEntity>> ORCA = Suppliers.memoize(() -> EntityType.Builder.of(OrcaEntity::new, MobCategory.WATER_AMBIENT)
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
            if (shouldSpawnParticles) {
                spawnSpoutParticles();
            }
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
        return false;
    }

    protected void handleAirSupply(int i) {
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new OrcaMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new SurfaceSpoutGoal(this, 400));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, (double)1.0F, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal(this, Guardian.class, 8.0F, (double)1.0F, (double)1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Guardian.class})).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(1, new OrcaAttackPlayersGoal());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 5, true, false, this::isAngryAt));
    }
    @Override
    public boolean isAngryAt(LivingEntity entity) {
        if (entity instanceof Player player) {
            return this.getPersistentAngerTarget() != null && this.getPersistentAngerTarget().equals(player.getUUID());
        }
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double)15.0F).add(Attributes.MOVEMENT_SPEED, (double)0.5F)
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
        return Sounds.NARWHAL_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return Sounds.NARWHAL_DEATH.get();
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return Sounds.NARWHAL_AMBIENT.get();
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
        for (int i = 0; i < 30; i++) {
            double x = base.x + this.getRandom().nextGaussian() * 0.15;
            double y = base.y + i * 0.25;
            double z = base.z + this.getRandom().nextGaussian() * 0.15;

            lvl.addAlwaysVisibleParticle(ParticleTypes.SPLASH,
                    x, y, z,
                    0, 0.1 + this.getRandom().nextDouble() * 0.1, 0);
        }
    }
    public boolean canBeLeashed(Player player) {
        return true;
    }

    static {
        MOISTNESS_LEVEL = SynchedEntityData.defineId(OrcaEntity.class, EntityDataSerializers.INT);
        PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    }
    static class SurfaceSpoutGoal extends Goal {
        private final WaterAnimal mob;
        private final Level level;
        private final int interval;
        private int cooldown;
        private int flashTimer = 0;


        public SurfaceSpoutGoal(WaterAnimal mob, int interval) {
            this.mob = mob;
            this.level = mob.level();
            this.interval = interval;
            this.cooldown = mob.getRandom().nextInt(interval);
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            if (flashTimer > 0) {
                flashTimer--;
                shouldSpawnParticles = true;
                return;
            } else {
                shouldSpawnParticles = false;
            }

            cooldown--;
            if (cooldown > 0) return;
            cooldown = interval;

            BlockPos headPos = BlockPos.containing(mob.getX(), mob.getEyeY(), mob.getZ());

            if (isAtWaterSurface(headPos)) {
                mob.setDeltaMovement(mob.getDeltaMovement().add(0, 0.1, 0));
                flashTimer = 15;
            } else {
                BlockPos target = findSurfaceAbove(mob.blockPosition(), 16);
                if (target != null) {
                    mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
                }
            }
        }

        public boolean isAtWaterSurface(BlockPos headPos) {
            return level.getFluidState(headPos).is(FluidTags.WATER)
                    && level.getBlockState(headPos.above()).isAir();
        }

        private BlockPos findSurfaceAbove(BlockPos start, int maxDistance) {
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(start.getX(), start.getY(), start.getZ());
            for (int dy = 0; dy < maxDistance; dy++) {
                pos.move(0, 1, 0);
                if (level.getBlockState(pos).isAir() && level.getFluidState(pos.below()).is(FluidTags.WATER)) {
                    return pos.below();
                }
            }
            return null;
        }

    }
    class OrcaMeleeAttackGoal extends MeleeAttackGoal {
        private final OrcaEntity entity;
        private int attackDelay = 10;
        private int ticksUntilNextAttack = 10;
        private boolean shouldCountTillNextAttack = false;

        public OrcaMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            entity = ((OrcaEntity) pMob);
        }

        @Override
        public void start() {
            super.start();
            attackDelay = 10;
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
    private class OrcaAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
        public OrcaAttackPlayersGoal() {
            super(OrcaEntity.this, Player.class, 10, true, true, (Predicate)null);
        }

        public boolean canUse() {
            if (OrcaEntity.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    List<OrcaEntity> list = OrcaEntity.this.level().getEntitiesOfClass(OrcaEntity.class, OrcaEntity.this.getBoundingBox().inflate(8.0, 4.0, 8.0));
                    Iterator var2 = list.iterator();

                    while(var2.hasNext()) {
                        OrcaEntity goose = (OrcaEntity)var2.next();
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


}
