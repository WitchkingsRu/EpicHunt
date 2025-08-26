package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.item.ModItem;
import net.epichunt.sound.Sounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Supplier;

public class NightingaleEntity extends Animal {
    private static final EntityDataAccessor<Integer> DATA_SONG_PITCH;
    public static final Ingredient FOOD_ITEMS;
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    //public int eggTime = 9999;
    private boolean isGliding = false;
    private int jumpCounter = 0;

    public NightingaleEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static final Supplier<EntityType<NightingaleEntity>> NIGHTINGALE = Suppliers.memoize(() -> EntityType.Builder.of(NightingaleEntity::new, MobCategory.AMBIENT)
            .sized(0.7f, 0.7f).build("nightingale"));

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

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {

        int randomPitchValue = serverLevelAccessor.getRandom().nextInt(6);
        this.setSongPitch(randomPitchValue);

        if (spawnGroupData == null) {
            spawnGroupData = new AgeableMob.AgeableMobGroupData(false);
        }

        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
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
//            this.spawnAtLocation(ModItem.QUAIL_EGG.get());
//            this.gameEvent(GameEvent.ENTITY_PLACE);
//            this.eggTime = this.random.nextInt(6000) + 6000;
//        }
        if (this.onGround() && !this.isInWater()) {
            jumpCounter++;
            if (this.getDeltaMovement().horizontalDistanceSqr() > 0.001 && jumpCounter >= 3) {
                Vec3 currentMotion = this.getDeltaMovement();
                float jumpHeight = 0.14f + random.nextFloat() * 0.02f;
                this.setDeltaMovement(currentMotion.x, jumpHeight, currentMotion.z);
                jumpCounter = 0;

                if (this.tickCount % 10 == 0) {
                    this.playSound(SoundEvents.CHICKEN_STEP, 0.06F, 1.7F);
                }
            }
        } else {
            jumpCounter = 0;
        }
    }

    public int getSongPitch() {
        return this.entityData.get(DATA_SONG_PITCH);
    }

    public void setSongPitch(int pitch) {
        this.entityData.set(DATA_SONG_PITCH, pitch);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("SongPitch", this.getSongPitch());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSongPitch(nbt.getInt("SongPitch"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SONG_PITCH, random.nextInt(6));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return NIGHTINGALE.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        int a = this.getSongPitch();
        return switch (a) {
            case 0 -> Sounds.NIGHTINGALE_AMBIENT1.get();
            case 1 -> Sounds.NIGHTINGALE_AMBIENT2.get();
            case 2 -> Sounds.NIGHTINGALE_AMBIENT3.get();
            case 3 -> Sounds.NIGHTINGALE_AMBIENT4.get();
            case 4 -> Sounds.NIGHTINGALE_AMBIENT5.get();
            case 5 -> Sounds.NIGHTINGALE_AMBIENT6.get();
            default -> Sounds.NIGHTINGALE_AMBIENT1.get();
        };
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.QUAIL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.QUAIL_HURT.get();
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
        DATA_SONG_PITCH = SynchedEntityData.defineId(NightingaleEntity.class, EntityDataSerializers.INT);
    }
}
