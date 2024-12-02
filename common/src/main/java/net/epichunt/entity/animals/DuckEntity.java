package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.entity.SurfaceSwimGoal;
import net.epichunt.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Supplier;

public class DuckEntity extends Animal {
    private boolean stuck;
    private BlockPos targetPos; // Новая переменная для хранения цели

    public DuckEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
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

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.7));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new SurfaceSwimGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1f, 10));
        this.goalSelector.addGoal(6, new DuckRandomStrollGoal(this, 6f, 10));
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

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        // Восстановление состояния утки
        this.stuck = compoundTag.getBoolean("Stuck");

        // Восстановление цели передвижения, если она была сохранена
        if (compoundTag.contains("TargetPos")) {
            this.targetPos = BlockPos.of(compoundTag.getLong("TargetPos"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        // Сохранение состояния
        compoundTag.putBoolean("Stuck", this.stuck);

        // Сохранение текущей цели передвижения, если она установлена
        if (this.targetPos != null) {
            compoundTag.putLong("TargetPos", this.targetPos.asLong());
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

    public class DuckRandomStrollGoal extends Goal {
        public static final int DEFAULT_INTERVAL = 120;
        private final DuckEntity duck;
        private final double speedModifier;
        protected int interval;

        public DuckRandomStrollGoal(DuckEntity duck, double speedModifier) {
            this(duck, speedModifier, 120, true);
        }

        public DuckRandomStrollGoal(DuckEntity duck, double d, int i) {
            this(duck, d, i, true);
        }

        public DuckRandomStrollGoal(DuckEntity duck, double d, int i, boolean bl) {
            this.duck = duck;
            this.speedModifier = d;
            this.interval = i;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            // Моб должен двигаться, если не застрял и не находится в любовном состоянии
            return !this.duck.isInLove() && !this.duck.stuck;
        }

        @Override
        public void start() {
            // Инициализация для начала передвижения
            this.duck.stuck = false;
        }

        @Override
        public void tick() {
            if (this.duck.getNavigation().isDone()) {
                // Проверка на наличие воды или суши для поиска цели
                BlockPos targetPos;
                if (this.duck.isInWater()) {
                    // Если в воде, ищем случайную позицию в воде
                    targetPos = getRandomWaterPosition();
                } else {
                    // Если на суше, ищем случайную позицию на суше
                    targetPos = getRandomLandPosition();
                }
                if (targetPos != null) {
                    this.duck.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), this.speedModifier);
                    this.duck.targetPos = targetPos; // Устанавливаем текущую цель
                    this.duck.stuck = false; // Убираем застревание
                } else {
                    this.duck.stuck = true; // Моб застрял
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.duck.getNavigation().isDone() && !this.duck.stuck;
        }

        @Override
        public void stop() {
            // Остановить движение
        }

        private BlockPos getRandomWaterPosition() {
            // Попробуем найти позицию рядом с мобом в пределах 10 блоков в радиусе
            Vec3 randomPos = DefaultRandomPos.getPosTowards(this.duck, 3, 7, Vec3.atCenterOf(this.duck.blockPosition()), 1.5707963705062866);
            if (randomPos != null) {
                BlockPos pos = new BlockPos((int) randomPos.x, (int) randomPos.y, (int) randomPos.z); // Преобразуем в BlockPos
                // Проверка, что найденная позиция в воде
                if (this.duck.level().getBlockState(pos).is(Blocks.WATER)) {
                    return pos;
                }
            }
            return null; // Если не нашли, вернем null
        }

        // Метод для поиска случайной позиции на суше
        private BlockPos getRandomLandPosition() {
            // Попробуем найти позицию на суше
            Vec3 randomPos = DefaultRandomPos.getPosTowards(this.duck, 3, 7, Vec3.atCenterOf(this.duck.blockPosition()), 0.3141592741012573);
            if (randomPos != null) {
                BlockPos pos = new BlockPos((int) randomPos.x, (int) randomPos.y, (int) randomPos.z); // Преобразуем в BlockPos
                // Проверка, что позиция не в воде
                if (!this.duck.level().getBlockState(pos).is(Blocks.WATER)) {
                    return pos;
                }
            }
            return null; // Если не нашли, вернем null
        }
    }
}
