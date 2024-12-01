package net.epichunt.entity.animals;


import com.google.common.base.Suppliers;
import net.epichunt.entity.ModEntities;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DoeEntity extends Animal {

    public DoeEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<DoeEntity>> DOE = Suppliers.memoize(() -> EntityType.Builder.of(DoeEntity::new, MobCategory.CREATURE)
            .sized(1f, 2f).build("doe"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = this.random.nextInt(100) + 100;

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
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
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(3, new EatBlockGoal(this));;
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.9D));
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
        return DOE.get().create(serverLevel);
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
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.3F;
    }
}
