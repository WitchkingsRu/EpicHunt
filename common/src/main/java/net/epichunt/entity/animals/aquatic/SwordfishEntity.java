package net.epichunt.entity.animals.aquatic;

import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SwordfishEntity extends Dolphin {
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL;
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    public SwordfishEntity(EntityType<? extends SwordfishEntity> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<SwordfishEntity>> SWORDFISH = Suppliers.memoize(() -> EntityType.Builder.of(SwordfishEntity::new, MobCategory.WATER_AMBIENT)
            .sized(2.0f, 1.0f).build("swordfish"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

    private void setupAnimationStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(400) + 1100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
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
                    this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), (double) 0.5F, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * 0.3F;
                float g = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * 0.3F;
                float h = 1.2F - this.random.nextFloat() * 0.7F;

                for (int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double) h + (double) f, this.getY() - vec3.y, this.getZ() - vec3.z * (double) h + (double) g, (double) 0.0F, (double) 0.0F, (double) 0.0F);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double) h - (double) f, this.getY() - vec3.y, this.getZ() - vec3.z * (double) h - (double) g, (double) 0.0F, (double) 0.0F, (double) 0.0F);
                }
            }

        }
    }

    public int getMoistnessLevel() {
        return (Integer) this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int i) {
        this.entityData.set(MOISTNESS_LEVEL, i);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);
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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double) 15.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.8F);
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

    static {
        MOISTNESS_LEVEL = SynchedEntityData.defineId(SwordfishEntity.class, EntityDataSerializers.INT);
    }
}

