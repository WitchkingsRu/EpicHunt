package net.epichunt.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class AbstractBreedableFish extends AbstractFish {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;
    public static final int BABY_START_AGE = -24000;
    private static final int FORCED_AGE_PARTICLE_TICKS = 40;
    protected int age;
    protected int forcedAge;
    protected int forcedAgeTimer;
    protected static final int PARENT_AGE_AFTER_BREEDING = 6000;
    private int inLove;
    @Nullable
    private UUID loveCause;

    public AbstractBreedableFish(EntityType<? extends AbstractBreedableFish> entityType, Level level) {
        super(entityType, level);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FishBreedGoal(this, 1.0));
        //this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    public void tick() {
        super.tick();


    }
    protected void customServerAiStep() {
        if (this.getAge() != 0) {
            this.inLove = 0;
        }

        super.customServerAiStep();
    }
    public boolean hurt(DamageSource damageSource, float f) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            this.inLove = 0;
            return super.hurt(damageSource, f);
        }
    }

    public void aiStep() {
        super.aiStep();
        if (this.getAge() != 0) {
            this.inLove = 0;
        }

        if (this.inLove > 0) {
            --this.inLove;
            if (this.inLove % 10 == 0) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
            }
        }

    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Age", this.getAge());
        compoundTag.putInt("ForcedAge", this.forcedAge);
        compoundTag.putInt("InLove", this.inLove);
        if (this.loveCause != null) {
            compoundTag.putUUID("LoveCause", this.loveCause);
        }

    }
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setAge(compoundTag.getInt("Age"));
        this.forcedAge = compoundTag.getInt("ForcedAge");
        this.inLove = compoundTag.getInt("InLove");
        this.loveCause = compoundTag.hasUUID("LoveCause") ? compoundTag.getUUID("LoveCause") : null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false); // Значение по умолчанию (не младенец)
    }


    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (DATA_BABY_ID.equals(entityDataAccessor)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(entityDataAccessor);
    }

    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.WHEAT_SEEDS);
    }

    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (this.isFood(itemStack)) {
            int i = this.getAge();
            if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
                this.usePlayerItem(player, interactionHand, itemStack);
                this.setInLove(player);
                return InteractionResult.SUCCESS;
            }

            if (this.isBaby()) {
                this.usePlayerItem(player, interactionHand, itemStack);
                this.ageUp(getSpeedUpSecondsWhenFeeding(-i), true);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }
        }

        return super.mobInteract(player, interactionHand);
    }

    protected void usePlayerItem(Player player, InteractionHand interactionHand, ItemStack itemStack) {
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

    }

    public boolean canFallInLove() {
        return this.inLove <= 0;
    }

    public void setInLove(@Nullable Player player) {
        this.inLove = 600;
        if (player != null) {
            this.loveCause = player.getUUID();
        }

        this.level().broadcastEntityEvent(this, (byte)18);
    }

    public void setInLoveTime(int i) {
        this.inLove = i;
    }

    public int getInLoveTime() {
        return this.inLove;
    }

    @Nullable
    public ServerPlayer getLoveCause() {
        if (this.loveCause == null) {
            return null;
        } else {
            Player player = this.level().getPlayerByUUID(this.loveCause);
            return player instanceof ServerPlayer ? (ServerPlayer)player : null;
        }
    }

    public boolean isInLove() {
        return this.inLove > 0;
    }

    public void resetLove() {
        this.inLove = 0;
    }

    public boolean canMate(AbstractBreedableFish fish) {
        if (fish == this) {
            return false;
        } else if (fish.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && fish.isInLove();
        }
    }

    public void spawnChildFromBreeding(ServerLevel serverLevel, AbstractBreedableFish fish) {
        AbstractBreedableFish ageableMob = this.getBreedOffspring(serverLevel, fish);
        if (ageableMob != null) {
            ageableMob.setBaby(true);
            ageableMob.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.finalizeSpawnChildFromBreeding(serverLevel, fish, ageableMob);
            serverLevel.addFreshEntityWithPassengers(ageableMob);
        }
    }

    public void finalizeSpawnChildFromBreeding(ServerLevel serverLevel, AbstractBreedableFish fish, @Nullable AbstractBreedableFish ageableMob) {
        Optional.ofNullable(this.getLoveCause()).or(() -> {
            return Optional.ofNullable(fish.getLoveCause());
        }).ifPresent((serverPlayer) -> {
            serverPlayer.awardStat(Stats.ANIMALS_BRED);
        });
        this.setAge(6000);
        fish.setAge(6000);
        this.resetLove();
        fish.resetLove();
        serverLevel.broadcastEntityEvent(this, (byte)18);
        if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }

    }
    
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, (SpawnGroupData)spawnGroupData, compoundTag);
        return (SpawnGroupData)spawnGroupData;
    }

    public void handleEntityEvent(byte b) {
        if (b == 18) {
            for(int i = 0; i < 7; ++i) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
            }
        } else {
            super.handleEntityEvent(b);
        }

    }
    @Nullable
    public abstract AbstractBreedableFish getBreedOffspring(ServerLevel serverLevel, AbstractBreedableFish ageableMob);

    public boolean canBreed() {
        return false;
    }

    public int getAge() {
        if (this.level().isClientSide) {
            return (Boolean)this.entityData.get(DATA_BABY_ID) ? -1 : 1;
        } else {
            return this.age;
        }
    }

    public void ageUp(int i, boolean bl) {
        int j = this.getAge();
        int k = j;
        j += i * 20;
        if (j > 0) {
            j = 0;
        }

        int l = j - k;
        this.setAge(j);
        if (bl) {
            this.forcedAge += l;
            if (this.forcedAgeTimer == 0) {
                this.forcedAgeTimer = 40;
            }
        }

        if (this.getAge() == 0) {
            this.setAge(this.forcedAge);
        }

    }

    public void ageUp(int i) {
        this.ageUp(i, false);
    }

    public void setAge(int i) {
        int j = this.getAge();
        this.age = i;
        if (j < 0 && i >= 0 || j >= 0 && i < 0) {
            this.entityData.set(DATA_BABY_ID, i < 0);
            this.ageBoundaryReached();
        }

    }
    protected void ageBoundaryReached() {
        if (!this.isBaby() && this.isPassenger()) {
            Entity var2 = this.getVehicle();
            if (var2 instanceof Boat) {
                Boat boat = (Boat)var2;
                if (!boat.hasEnoughSpaceFor(this)) {
                    this.stopRiding();
                }
            }
        }

    }

    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public void setBaby(boolean bl) {
        this.setAge(bl ? -24000 : 0);
    }

    public static int getSpeedUpSecondsWhenFeeding(int i) {
        return (int)((float)(i / 20) * 0.1F);
    }

    static {
        DATA_BABY_ID = SynchedEntityData.defineId(AbstractBreedableFish.class, EntityDataSerializers.BOOLEAN);
    }
}
