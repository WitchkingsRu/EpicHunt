package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
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
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public class PigeonEntity extends Animal {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;
    public static final Ingredient FOOD_ITEMS;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    //public int eggTime = 9999;
    private boolean isGliding = false;

    public PigeonEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);

    }

    public static final Supplier<EntityType<PigeonEntity>> PIGEON = Suppliers.memoize(() -> EntityType.Builder.of(PigeonEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("pigeon"));

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

    public Variant getVariant() {
        return Variant.byId((Integer)this.entityData.get(DATA_VARIANT_ID));
    }

    public void setVariant(Variant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant.id);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant().id);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setVariant(Variant.byId(compoundTag.getInt("Variant")));
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

//        if (!this.level().isClientSide && this.isAlive() && !this.isInWater() && !this.isBaby() && --this.eggTime <= 0) {
//            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//            this.spawnAtLocation(ModItem.PHEASANT_EGG.get());
//            this.gameEvent(GameEvent.ENTITY_PLACE);
//            this.eggTime = this.random.nextInt(6000) + 6000;
//        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return PIGEON.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.PIGEON_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.PIGEON_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.PIGEON_DEATH.get();
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
        FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.BREAD);
        DATA_VARIANT_ID = SynchedEntityData.defineId(PigeonEntity.class, EntityDataSerializers.INT);

    }
    public static enum Variant implements StringRepresentable {
        WHITE(0, "white"),
        ORANGE(1, "orange"),
        GREY(2, "grey");

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        private Variant(int j, String string2) {
            this.id = j;
            this.name = string2;
        }

        public int getId() {
            return this.id;
        }

        public static Variant byId(int i) {
            return (Variant)BY_ID.apply(i);
        }

        public String getSerializedName() {
            return this.name;
        }
    }
}
