package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DuckEntity extends Animal {
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
    private double lastYd;
    private double waterLevel;
    public int eggTime;
    public DuckEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<DuckEntity>> DUCK = Suppliers.memoize(() -> EntityType.Builder.of(DuckEntity::new, MobCategory.CREATURE)
            .sized(1f, 2f).build("duck"));

    public final AnimationState flyAnimationState = new AnimationState();
    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.isFlapping()) {
            this.flyAnimationState.start(this.tickCount);
        }
    }


    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 6F, 1f);
        }
        else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }


    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(3, new EatBlockGoal(this));;
        this.goalSelector.addGoal(5, new FloatGoal(this));
        //this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH,16D).add(Attributes.MOVEMENT_SPEED, 0.23000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return DUCK.get().create(serverLevel);
    }

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
        if (!this.onGround() && vec3.y < 0.0) {
            this.setDeltaMovement(vec3.multiply(1.0, 0.6, 1.0));
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.EGG);
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggTime = this.random.nextInt(6000) + 6000;
        }

    }

    public float getWaterLevelAbove() {
        AABB aABB = this.getBoundingBox();
        int i = Mth.floor(aABB.minX);
        int j = Mth.ceil(aABB.maxX);
        int k = Mth.floor(aABB.maxY);
        int l = Mth.ceil(aABB.maxY - this.lastYd);
        int m = Mth.floor(aABB.minZ);
        int n = Mth.ceil(aABB.maxZ);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        label39:
        for(int o = k; o < l; ++o) {
            float f = 0.0F;

            for(int p = i; p < j; ++p) {
                for(int q = m; q < n; ++q) {
                    mutableBlockPos.set(p, o, q);
                    FluidState fluidState = this.level().getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.WATER)) {
                        f = Math.max(f, fluidState.getHeight(this.level(), mutableBlockPos));
                    }

                    if (f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if (f < 1.0F) {
                return (float)mutableBlockPos.getY() + f;
            }
        }

        return (float)(l+1);
    }

    private Status getStatus() {
        Status status = Status.ON_LAND;
        if (this.checkInWater()) {
            this.waterLevel = this.getBoundingBox().maxY;
            return Status.IN_WATER;
        } else if (this.isFlapping()) {
            return Status.IN_AIR;
        }
        return status;
    }

    private boolean checkInWater() {
        AABB aABB = this.getBoundingBox();
        int i = Mth.floor(aABB.minX);
        int j = Mth.ceil(aABB.maxX);
        int k = Mth.floor(aABB.minY);
        int l = Mth.ceil(aABB.minY + 0.001);
        int m = Mth.floor(aABB.minZ);
        int n = Mth.ceil(aABB.maxZ);
        boolean bl = false;
        this.waterLevel = -1.7976931348623157E308;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(int o = i; o < j; ++o) {
            for(int p = k; p < l; ++p) {
                for(int q = m; q < n; ++q) {
                    mutableBlockPos.set(o, p, q);
                    FluidState fluidState = this.level().getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.WATER)) {
                        float f = (float)p + fluidState.getHeight(this.level(), mutableBlockPos);
                        this.waterLevel = Math.max((double)f, this.waterLevel);
                        bl |= aABB.minY < (double)f;
                    }
                }
            }
        }

        return bl;
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    @Override
    public SoundEvent getEatingSound(ItemStack itemStack) {
        return Sounds.DEER_EAT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.DEER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.DEER_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.DEER_DEATH.get();
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.3F;
    }
    public static enum Status {
        IN_WATER,
        ON_LAND,
        IN_AIR;

        private Status() {
        }
    }
}
