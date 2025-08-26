package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import com.google.common.collect.Sets;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

public class OwlEntity extends Animal implements FlyingAnimal {
    //private static final Set<Item> TAME_FOOD;
    private boolean isInAir = false;

    public OwlEntity(EntityType<? extends OwlEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public static final Supplier<EntityType<OwlEntity>> OWL = Suppliers.memoize(() -> EntityType.Builder.of(OwlEntity::new, MobCategory.AMBIENT)
            .sized(0.7f, 0.7f).build("owl"));


    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if(this.getPose() == Pose.STANDING && this.onGround()) {
            f = Math.min(partialTick * 6F, 1f);
        }
        else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    public boolean isBaby() {
        return false;
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
            if (!isInAir) {
                this.flyAnimationState.start(this.tickCount);
                isInAir = true;
            }
        } else if (this.isInWater() || this.onGround()) {
            this.flyAnimationState.stop();
            isInAir = false;

        }
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, (double)1.25F));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new OwlWanderGoal(this, (double)1.0F));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, (double)1.0F, 3.0F, 7.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, (double)6.0F).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        flyingPathNavigation.setCanPassDoors(true);
        return flyingPathNavigation;
    }
    

    public boolean isFood(ItemStack itemStack) {
        return false;
    }


    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
    }

    public boolean canMate(Animal animal) {
        return false;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    protected SoundEvent getAmbientSound() {
        return Sounds.OWL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.OWL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return Sounds.OWL_DEATH.get();
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    static {
    }
    static class OwlWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public OwlWanderGoal(PathfinderMob pathfinderMob, double d) {
            super(pathfinderMob, d);
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vec3 = null;
            if (this.mob.isInWater()) {
                vec3 = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3 = this.getTreePos();
            }

            return vec3 == null ? super.getPosition() : vec3;
        }

        @Nullable
        private Vec3 getTreePos() {
            BlockPos blockPos = this.mob.blockPosition();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos mutableBlockPos2 = new BlockPos.MutableBlockPos();

            for(BlockPos blockPos2 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - (double)3.0F), Mth.floor(this.mob.getY() - (double)6.0F), Mth.floor(this.mob.getZ() - (double)3.0F), Mth.floor(this.mob.getX() + (double)3.0F), Mth.floor(this.mob.getY() + (double)6.0F), Mth.floor(this.mob.getZ() + (double)3.0F))) {
                if (!blockPos.equals(blockPos2)) {
                    BlockState blockState = this.mob.level().getBlockState(mutableBlockPos2.setWithOffset(blockPos2, Direction.DOWN));
                    boolean bl = blockState.getBlock() instanceof LeavesBlock || blockState.is(BlockTags.LOGS);
                    if (bl && this.mob.level().isEmptyBlock(blockPos2) && this.mob.level().isEmptyBlock(mutableBlockPos.setWithOffset(blockPos2, Direction.UP))) {
                        return Vec3.atBottomCenterOf(blockPos2);
                    }
                }
            }

            return null;
        }
    }
}
