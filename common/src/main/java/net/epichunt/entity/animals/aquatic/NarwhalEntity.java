package net.epichunt.entity.animals.aquatic;


import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
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

import java.util.EnumSet;
import java.util.function.Supplier;

public class NarwhalEntity extends WaterAnimal {
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL;
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    private final Level lvl;
    private int spoutCooldown = 600;

    public NarwhalEntity(EntityType<? extends NarwhalEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.lvl = level;
    }

    public static final Supplier<EntityType<NarwhalEntity>> NARWHAL = Suppliers.memoize(() -> EntityType.Builder.of(NarwhalEntity::new, MobCategory.WATER_CREATURE)
            .sized(1.3f, 0.8f).build("narwhal"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

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
        this.spoutCooldown--;

        BlockPos aboveHead = new BlockPos((int) this.getX(), (int) (this.getEyeY() + 1.0), (int) this.getZ());

        if (this.isInWater() && this.level().getBlockState(aboveHead).isAir()) {
            spawnSpoutParticles();
            this.setSpoutCooldown(600);
        }


    }

    private void setupAnimationStates() {
        if(this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(400) + 1100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
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
        this.goalSelector.addGoal(1, new SurfaceSpoutGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, (double)1.0F, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal(this, Guardian.class, 8.0F, (double)1.0F, (double)1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Guardian.class})).setAlertOthers(new Class[0]));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double)15.0F).add(Attributes.MOVEMENT_SPEED, (double)0.5F);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
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
        return 0.4F;
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

    public int getSpoutCooldown() {
        return spoutCooldown;
    }

    public void setSpoutCooldown(int spoutCooldown) {
        this.spoutCooldown = spoutCooldown;
    }

    static {
        MOISTNESS_LEVEL = SynchedEntityData.defineId(NarwhalEntity.class, EntityDataSerializers.INT);
    }
    static class SurfaceSpoutGoal extends Goal {
        private final NarwhalEntity mob;

        public SurfaceSpoutGoal(NarwhalEntity pathfinderMob) {
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
}
