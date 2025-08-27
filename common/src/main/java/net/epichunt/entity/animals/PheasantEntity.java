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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
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

import java.util.function.Supplier;

public class PheasantEntity extends Animal {
    public static final Ingredient FOOD_ITEMS;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public int eggTime = 9999;
    private boolean isGliding = false;

    public PheasantEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);

    }

    public static final Supplier<EntityType<PheasantEntity>> PHEASANT = Suppliers.memoize(() -> EntityType.Builder.of(PheasantEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("pheasant"));

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

    @Override
    public void tick() {
        super.tick();
        if(!this.isInWater()&&!this.onGround()) {
            this.resetFallDistance();
        }
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public final AnimationState flyAnimationState = new AnimationState();
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
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1f, Ingredient.of(Items.WHEAT_SEEDS), false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1f));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1f, 100));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.23000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D);
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
            this.spawnAtLocation(ModItem.PHEASANT_EGG.get());
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggTime = this.random.nextInt(6000) + 6000;
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return PHEASANT.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.PHEASANT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.PHEASANT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.PHEASANT_DEATH.get();
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.WHEAT_SEEDS);
    }

    protected float getSoundVolume() {
        return 0.3F;
    }

    static {
        FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS);
    }
}
