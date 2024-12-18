package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import com.google.common.collect.UnmodifiableIterator;
import net.epichunt.item.ModItem;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class YakEntity extends Animal implements Saddleable {
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(YakEntity.class, EntityDataSerializers.BOOLEAN);

    public YakEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0F);
    }

    public static final Supplier<EntityType<YakEntity>> YAK = Suppliers.memoize(() -> EntityType.Builder.of(YakEntity::new, MobCategory.CREATURE)
            .sized(1f, 2f).build("yak"));

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
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false); // Состояние седла
    }

    protected void doPlayerRide(Player player) {
        if (!this.level().isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }

    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.is(ModItem.YAK_CARPET_WHITE.get()) && !this.isSaddled()) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            this.setCarpet(itemstack);
            this.equipSaddle(SoundSource.NEUTRAL);
            return InteractionResult.SUCCESS;
        } else if (this.isSaddled() && !this.isVehicle()) {
            this.doPlayerRide(player);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(SADDLED);
    }


    @Override
    public void equipSaddle(@Nullable SoundSource soundSource) {
        this.entityData.set(SADDLED, true);
        if (soundSource != null) {
            this.level().playSound(null, this.blockPosition(), SoundEvents.HORSE_SADDLE, soundSource, 1.0F, 1.0F);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Saddled", this.isSaddled());

    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.entityData.set(SADDLED, compoundTag.getBoolean("Saddled"));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return YAK.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.YAK_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.YAK_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return Sounds.YAK_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.3F;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity var3 = this.getFirstPassenger();
        if (var3 instanceof Mob mob) {
            return mob;
        } else {
            if (this.isSaddled()) {
                var3 = this.getFirstPassenger();
                if (var3 instanceof Player) {
                    Player player = (Player)var3;
                    return player;
                }
            }

            return null;
        }
    }

    public ItemStack getCarpet() {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }

    private void setCarpet(ItemStack itemStack) {
        this.setItemSlot(EquipmentSlot.CHEST, itemStack);
        this.setDropChance(EquipmentSlot.CHEST, 100.0F);
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 vec3, LivingEntity livingEntity) {
        double d = this.getX() + vec3.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + vec3.z;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        UnmodifiableIterator var10 = livingEntity.getDismountPoses().iterator();

        while(var10.hasNext()) {
            Pose pose = (Pose)var10.next();
            mutableBlockPos.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;

            while(true) {
                double h = this.level().getBlockFloorHeight(mutableBlockPos);
                if ((double)mutableBlockPos.getY() + h > g) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(h)) {
                    AABB aABB = livingEntity.getLocalBoundsForPose(pose);
                    Vec3 vec32 = new Vec3(d, (double)mutableBlockPos.getY() + h, f);
                    if (DismountHelper.canDismountTo(this.level(), livingEntity, aABB.move(vec32))) {
                        livingEntity.setPose(pose);
                        return vec32;
                    }
                }

                mutableBlockPos.move(Direction.UP);
                if (!((double)mutableBlockPos.getY() < g)) {
                    break;
                }
            }
        }

        return null;
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
        Vec3 vec32 = this.getDismountLocationInDirection(vec3, livingEntity);
        if (vec32 != null) {
            return vec32;
        } else {
            Vec3 vec33 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
            Vec3 vec34 = this.getDismountLocationInDirection(vec33, livingEntity);
            return vec34 != null ? vec34 : this.position();
        }

    }

    protected void tickRidden(Player player, Vec3 vec3) {
        super.tickRidden(player, vec3);
        Vec2 vec2 = this.getRiddenRotation(player);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();

    }

    protected Vec2 getRiddenRotation(LivingEntity livingEntity) {
        return new Vec2(livingEntity.getXRot() * 0.5F, livingEntity.getYRot());
    }

    protected Vec3 getRiddenInput(Player player, Vec3 vec3) {
        float f = player.xxa * 0.5F;
        float g = player.zza;
        if (g <= 0.0F) {
            g *= 0.25F;
        }
        return new Vec3((double)f, 0.0, (double)g);
    }
    protected float getRiddenSpeed(Player player) {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        super.positionRider(entity, moveFunction);
        float i = 0.3F;
        moveFunction.accept(entity,
                this.getX(),
                this.getY() + this.getPassengersRidingOffset() + entity.getMyRidingOffset() - (double)i,
                this.getZ()
        );
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).yBodyRot = this.yBodyRot;
        }

    }


}
