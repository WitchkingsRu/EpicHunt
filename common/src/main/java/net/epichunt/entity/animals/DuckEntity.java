package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.entity.SurfaceSwimGoal;
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
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DuckEntity extends Animal {
    private static final EntityDataAccessor<Boolean> IS_SWIMMING;
    public static final Ingredient FOOD_ITEMS;
    public DuckEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_OPEN, -1.0F);
        this.moveControl = new DuckMoveControl(this);
        this.setMaxUpStep(1.0F);
    }

    public static final Supplier<EntityType<DuckEntity>> DUCK = Suppliers.memoize(() -> EntityType.Builder.of(DuckEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("duck"));

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
    public boolean isSwimming() {
        return this.entityData.get(IS_SWIMMING);
    }

    public void setSwimming(boolean isSwimming) {
        this.entityData.set(IS_SWIMMING, isSwimming);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_SWIMMING, false);
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

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        return super.finalizeSpawn(levelAccessor, difficulty, spawnType, spawnData, dataTag);
    }

    public static boolean checkDuckSpawnRules(EntityType<DuckEntity> type, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return pos.getY() > levelAccessor.getSeaLevel() - 4 && isBrightEnoughToSpawn(levelAccessor, pos);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new SurfaceSwimGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1f, 10));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    protected PathNavigation createNavigation(Level arg) {
        return new DuckNavigation(this, arg);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 4D).add(Attributes.MOVEMENT_SPEED, 0.23000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false; // Утки могут плавать, но дышат воздухом
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
                return this.level.getBlockState(pos).is(Blocks.WATER); // Вода
            } else {
                return !this.level.getBlockState(pos.below()).isAir(); // Суша
            }
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return DUCK.get().create(serverLevel);
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

    static {
        IS_SWIMMING = SynchedEntityData.defineId(DuckEntity.class, EntityDataSerializers.BOOLEAN);
        FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS);
    }

    private static class DuckMoveControl extends MoveControl {
        private final DuckEntity duck;

        DuckMoveControl(DuckEntity duck) {
            super(duck);
            this.duck = duck;
        }

        @Override
        public void tick() {
            if (this.duck.isSwimming() && this.duck.isInWater()) {
                this.duck.setDeltaMovement(this.duck.getDeltaMovement().add(0.0, 0.005, 0.0));
            }
            super.tick();
        }
    }
}
