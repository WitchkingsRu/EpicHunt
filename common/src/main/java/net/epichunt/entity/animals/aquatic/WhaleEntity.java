package net.epichunt.entity.animals.aquatic;


import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BinaryHeap;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class WhaleEntity extends WaterAnimal {
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL;
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    public static boolean shouldSpawnParticles = false;
    private final Level lvl;
    public final WhalePart head;
    public final WhalePart body;
    public final WhalePart tail;

    public final WhalePart[] whaleParts;

    public WhaleEntity(EntityType<? extends WhaleEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.lvl = level;

        this.head = new WhalePart(this, "head", 4.0F, 4.0F);
        this.body = new WhalePart(this, "body", 8.0F, 6.0F);
        this.tail = new WhalePart(this, "tail", 6.0F, 4.0F);
        this.whaleParts = new WhalePart[]{this.head, this.body, this.tail};
    }

    public static final Supplier<EntityType<WhaleEntity>> WHALE = Suppliers.memoize(() -> EntityType.Builder.of(WhaleEntity::new, MobCategory.WATER_AMBIENT)
            .sized(2.5f, 1.0f).build("whale"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
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
//        Vec3[] avector3d = new Vec3[this.whaleParts.length];
//        for (int j = 0; j < this.whaleParts.length; ++j) {
//            this.whaleParts[j].collideWithNearbyEntities();
//            avector3d[j] = new Vec3(this.whaleParts[j].getX(), this.whaleParts[j].getY(), this.whaleParts[j].getZ());
//        }
//        final float f17 = this.getYRot() * Mth.DEG_TO_RAD;
//        final float pitch = this.getXRot() * Mth.DEG_TO_RAD;
//        final float xRotDiv90 = Math.abs(this.getXRot() / 90F);
//        final float f3 = Mth.sin(f17) * (1 - xRotDiv90);
//        final float f18 = Mth.cos(f17) * (1 - xRotDiv90);
//
//        this.setPartPosition(this.body, f3 * 0.5F, -pitch * 0.5F, -f18 * 0.5F);
//        this.setPartPosition(this.tail, (f3) * -3.5F, -pitch * 3F, (f18) * 3.5F);
//        this.setPartPosition(this.head, f3 * -7F, -pitch * 5F, -f18 * -7F);
//        for (int l = 0; l < this.whaleParts.length; ++l) {
//            this.whaleParts[l].xo = avector3d[l].x;
//            this.whaleParts[l].yo = avector3d[l].y;
//            this.whaleParts[l].zo = avector3d[l].z;
//            this.whaleParts[l].xOld = avector3d[l].x;
//            this.whaleParts[l].yOld = avector3d[l].y;
//            this.whaleParts[l].zOld = avector3d[l].z;
//        }
        this.updateParts();
    }

    private void updateParts() {
        // Более логичное позиционирование частей тела
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;
        float pitchRad = this.getXRot() * Mth.DEG_TO_RAD;

        // Тело - сразу за головой
        float bodyX = -Mth.sin(yawRad) * 4.0F;
        float bodyZ = Mth.cos(yawRad) * 4.0F;
        this.setPartPosition(this.body, bodyX, 0.0F, bodyZ);

        // Хвост - дальше от тела
        float tailX = -Mth.sin(yawRad) * 8.0F;
        float tailZ = Mth.cos(yawRad) * 8.0F;
        this.setPartPosition(this.tail, tailX, 0.0F, tailZ);

        // Голова - впереди основного ентити
        float headX = Mth.sin(yawRad) * 4.0F;
        float headZ = -Mth.cos(yawRad) * 4.0F;
        this.setPartPosition(this.head, headX, 0.0F, headZ);

        // Обновляем поворот частей
        for (WhalePart part : whaleParts) {
            part.setYRot(this.getYRot());
            part.setXRot(this.getXRot());
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(400) + 1100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
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

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new NarwhalEntity.SurfaceSpoutGoal(this, 400));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, (double) 1.0F, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal(this, Guardian.class, 8.0F, (double) 1.0F, (double) 1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Guardian.class})).setAlertOthers(new Class[0]));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double) 15.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.5F);
    }

    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    public boolean isPickable() {
        return true;
    }
    public void pushEntities() {
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
        return blockPos != null ? blockPos.closerToCenterThan(this.position(), (double) 12.0F) : false;
    }
    public boolean hurt(WhalePart enderDragonPart, DamageSource damageSource, float f) {
            if (enderDragonPart != this.head) {
                f = f / 4.0F + Math.min(f, 1.0F);
            }

            if (f < 0.01F) {
                return false;
            } else {
                if (damageSource.getEntity() instanceof Player || damageSource.is(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)) {
                    float g = this.getHealth();
                    this.reallyHurt(damageSource, f);
                    if (this.isDeadOrDying()) {
                        this.setHealth(1.0F);
                    }

                }

                return true;
            }

    }

    public boolean hurt(DamageSource damageSource, float f) {
        return !this.level().isClientSide ? this.hurt(this.body, damageSource, f) : false;
    }

    protected boolean reallyHurt(DamageSource damageSource, float f) {
        return super.hurt(damageSource, f);
    }

    private void setPartPosition(WhalePart part, double offsetX, double offsetY, double offsetZ) {
        part.setPos(this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ);
    }
    public WhalePart[] getSubEntities() {
        return this.whaleParts;
    }

    public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
        super.recreateFromPacket(clientboundAddEntityPacket);
        WhalePart[] enderDragonParts = this.getSubEntities();

        for(int i = 0; i < enderDragonParts.length; ++i) {
            enderDragonParts[i].setId(i + clientboundAddEntityPacket.getId());
        }

    }

    public void travel(Vec3 vec3) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add((double) 0.0F, -0.005, (double) 0.0F));
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
        MOISTNESS_LEVEL = SynchedEntityData.defineId(Dolphin.class, EntityDataSerializers.INT);
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
            return true; // постоянная цель, но сама управляет частотой
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
                    return pos.below(); // Вода под воздухом — значит, поверхность
                }
            }
            return null;
        }

    }
}
