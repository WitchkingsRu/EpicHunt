package net.epichunt.entity.animals.aquatic;

import com.google.common.base.Suppliers;
import net.epichunt.entity.animals.WolfEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.sound.Sounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public class WhiteSharkEntity extends Monster implements Enemy {
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(
            WhiteSharkEntity.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    private UUID persistentAngerTarget;

    public WhiteSharkEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.xpReward = 5;
    }
    public static final Supplier<EntityType<WhiteSharkEntity>> WHITE_SHARK = Suppliers.memoize(() -> EntityType.Builder.of(WhiteSharkEntity::new, MobCategory.MONSTER)
            .sized(1.1f, 0.8f).build("white_shark"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

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
            attackAnimationTimeout = 12;
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
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }
    
    public void aiStep() {
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
    }
    
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new SharkMeleeAttackGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, (double)1.0F, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal(this, Guardian.class, 8.0F, (double)1.0F, (double)1.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(1, new SharkTargetGoal(this, Player.class));
        this.targetSelector.addGoal(1, new SharkAttackEntitiesGoal(this));
    }

    public boolean isAngryAt(LivingEntity entity) {
        if (entity instanceof Player player) {
            return this.getPersistentAngerTarget() != null && this.getPersistentAngerTarget().equals(player.getUUID());
        }
        return false;
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double)20.0F).add(Attributes.MOVEMENT_SPEED, (double)0.5F)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 7D).add(Attributes.ATTACK_KNOCKBACK, 1);
    }
    
    public boolean canBreatheUnderwater() {
        return true;
    }
    
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.7F;
    }

    public void setPersistentAngerTarget(@Nullable UUID uUID) {
        this.persistentAngerTarget = uUID;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COD_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }
    
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
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

    class SharkMeleeAttackGoal extends Goal {
        private final WhiteSharkEntity whiteShark;
        private LivingEntity target;
        private final double speed;
        private int ticksUntilNextAttack = 6;
        private final int attackDelay = 6;
        private boolean countingAttack = false;

        public SharkMeleeAttackGoal(WhiteSharkEntity whiteShark, double speed) {
            this.whiteShark = whiteShark;
            this.speed = speed;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.whiteShark.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean canContinueToUse() {
            return canUse();
        }

        @Override
        public void start() {
            this.target = this.whiteShark.getTarget();
            this.resetAttackCooldown();
            this.countingAttack = false;
        }

        @Override
        public void stop() {
            this.target = null;
            this.whiteShark.getNavigation().stop();
            this.whiteShark.setAttacking(false);
            this.whiteShark.attackAnimationTimeout = 0;
            this.countingAttack = false;
        }

        @Override
        public void tick() {
            if (this.target == null) return;

            double distanceSqr = this.whiteShark.distanceToSqr(this.target);
            double attackReach = (this.whiteShark.getBbWidth() + this.target.getBbWidth()) * 2.0F;
            double attackReachSqr = attackReach * attackReach;

            Vec3 direction = new Vec3(
                    this.target.getX() - this.whiteShark.getX(),
                    this.target.getY() - this.whiteShark.getY(),
                    this.target.getZ() - this.whiteShark.getZ()
            ).normalize();

            double moveSpeed = 0.1D;

            this.whiteShark.setDeltaMovement(
                    this.whiteShark.getDeltaMovement().scale(0.5).add(direction.scale(moveSpeed))
            );
            this.whiteShark.setYRot((float)(Mth.atan2(direction.z, direction.x) * (180F / Math.PI)) - 90.0F);
            this.whiteShark.yBodyRot = this.whiteShark.getYRot();
            this.whiteShark.yHeadRot = this.whiteShark.getYRot();
            this.whiteShark.getNavigation().stop();

            if (distanceSqr <= attackReachSqr) {
                if (!countingAttack) {
                    countingAttack = true;
                    ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
                    this.whiteShark.setAttacking(true);
                } else {
                    ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);

                    if (ticksUntilNextAttack == attackDelay) {
                        this.whiteShark.setAttacking(true);
                    }

                    if (ticksUntilNextAttack == 0) {
                        this.whiteShark.doHurtTarget(this.target);
                        this.whiteShark.setAttacking(false);
                        countingAttack = false;
                    }
                }
            } else {
                countingAttack = false;
                ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
                this.whiteShark.setAttacking(false);
                this.whiteShark.attackAnimationTimeout = 0;
            }
        }

        private void resetAttackCooldown() {
            this.ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
        }

        protected int adjustedTickDelay(int ticks) {
            return ticks;
        }
    }
    public class SharkAttackEntitiesGoal extends NearestAttackableTargetGoal<LivingEntity> {
        private static final Set<Class<? extends LivingEntity>> TARGET_CLASSES = Set.of(
                Dolphin.class,
                SturgeonEntity.class
        );

        public SharkAttackEntitiesGoal(WhiteSharkEntity whiteShark) {
            super(whiteShark, LivingEntity.class, 10, true, true, entity -> {
                return TARGET_CLASSES.stream().anyMatch(clazz -> clazz.isInstance(entity));
            });
        }

        @Override
        public boolean canUse() {
            if (this.mob.isBaby()) return false;
            return super.canUse();
        }
    }
    public static class SharkTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public SharkTargetGoal(WhiteSharkEntity shark, Class<T> class_) {
            super(shark, class_, true);
        }

        public boolean canUse() {
            return super.canUse();
        }
    }
}
