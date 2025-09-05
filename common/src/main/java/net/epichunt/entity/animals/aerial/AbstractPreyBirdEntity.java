package net.epichunt.entity.animals.aerial;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Supplier;

public class AbstractPreyBirdEntity extends FlyingMob implements FlyingAnimal {
    Vec3 moveTargetPoint;
    BlockPos anchorPoint;
    AttackPhase attackPhase;
    boolean isAttacking = false;

    public AbstractPreyBirdEntity(EntityType<? extends AbstractPreyBirdEntity> entityType, Level level) {
        super(entityType, level);
        this.moveTargetPoint = Vec3.ZERO;
        this.anchorPoint = BlockPos.ZERO;
        this.attackPhase = AttackPhase.CIRCLE;
        this.xpReward = 5;
        this.moveControl = new AbstractPreyBirdMoveControl(this, this);
        this.lookControl = new AbstractPreyBirdLookControl(this, this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AbstractPreyBirdAttackStrategyGoal(this));
        this.goalSelector.addGoal(2, new AbstractPreyBirdSweepAttackGoal(this));
        this.goalSelector.addGoal(3, new AbstractPreyBirdCircleAroundAnchorGoal(this));
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    private int flyAnimTimeout = 35;

    public final AnimationState flyAnimationState = new AnimationState();
    private void setupAnimationStates() {
        if (!this.isInWater() && !this.onGround()) {
            if(this.flyAnimTimeout <= 0) {
                this.flyAnimTimeout = this.random.nextInt(400) + 1100;
                this.flyAnimationState.start(this.tickCount);
            } else {
                --this.flyAnimTimeout;
            }
        }
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.setupAnimationStates();
        }

    }

    public void aiStep() {
        super.aiStep();
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.3000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D).add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.anchorPoint = this.blockPosition().above(20);
        this.setPos(this.getX(), this.anchorPoint.getY() - 2, this.getZ());
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    public static boolean checkBirdSpawnRules(EntityType<? extends AbstractPreyBirdEntity> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {

        return isBrightEnoughToSpawn(levelAccessor, blockPos);
    }
    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos) {
        return blockAndTintGetter.getRawBrightness(blockPos, 0) > 8;
    }
    public boolean shouldRenderAtSqrDistance(double d) {
        return true;
    }

    public boolean canAttackType(EntityType<?> entityType) {
        return true;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    static enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    class AbstractPreyBirdMoveControl extends MoveControl {
        private float speed = 0.1F;

        public AbstractPreyBirdMoveControl(AbstractPreyBirdEntity phantom, Mob mob) {
            super(mob);
        }

        public void tick() {
            if (AbstractPreyBirdEntity.this.horizontalCollision) {
                AbstractPreyBirdEntity.this.setYRot(AbstractPreyBirdEntity.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d = AbstractPreyBirdEntity.this.moveTargetPoint.x - AbstractPreyBirdEntity.this.getX();
            double e = AbstractPreyBirdEntity.this.moveTargetPoint.y - AbstractPreyBirdEntity.this.getY();
            double f = AbstractPreyBirdEntity.this.moveTargetPoint.z - AbstractPreyBirdEntity.this.getZ();
            double g = Math.sqrt(d * d + f * f);
            if (Math.abs(g) > (double)1.0E-5F) {
                double h = (double)1.0F - Math.abs(e * (double)0.7F) / g;
                d *= h;
                f *= h;
                g = Math.sqrt(d * d + f * f);
                double i = Math.sqrt(d * d + f * f + e * e);
                float j = AbstractPreyBirdEntity.this.getYRot();
                float k = (float)Mth.atan2(f, d);
                float l = Mth.wrapDegrees(AbstractPreyBirdEntity.this.getYRot() + 90.0F);
                float m = Mth.wrapDegrees(k * (180F / (float)Math.PI));
                AbstractPreyBirdEntity.this.setYRot(Mth.approachDegrees(l, m, 4.0F) - 90.0F);
                AbstractPreyBirdEntity.this.yBodyRot = AbstractPreyBirdEntity.this.getYRot();
                if (Mth.degreesDifferenceAbs(j, AbstractPreyBirdEntity.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float n = (float)(-(Mth.atan2(-e, g) * (double)(180F / (float)Math.PI)));
                AbstractPreyBirdEntity.this.setXRot(n);
                float o = AbstractPreyBirdEntity.this.getYRot() + 90.0F;
                double p = (double)(this.speed * Mth.cos(o * ((float)Math.PI / 180F))) * Math.abs(d / i);
                double q = (double)(this.speed * Mth.sin(o * ((float)Math.PI / 180F))) * Math.abs(f / i);
                double r = (double)(this.speed * Mth.sin(n * ((float)Math.PI / 180F))) * Math.abs(e / i);
                Vec3 vec3 = AbstractPreyBirdEntity.this.getDeltaMovement();
                AbstractPreyBirdEntity.this.setDeltaMovement(vec3.add((new Vec3(p, r, q)).subtract(vec3).scale(0.2)));
            }

        }
    }

    class AbstractPreyBirdBodyRotationControl extends BodyRotationControl {
        public AbstractPreyBirdBodyRotationControl(AbstractPreyBirdEntity phantom, Mob mob) {
            super(mob);
        }

        public void clientTick() {
            AbstractPreyBirdEntity.this.yHeadRot = AbstractPreyBirdEntity.this.yBodyRot;
            AbstractPreyBirdEntity.this.yBodyRot = AbstractPreyBirdEntity.this.getYRot();
        }
    }

    class AbstractPreyBirdLookControl extends LookControl {
        public AbstractPreyBirdLookControl(AbstractPreyBirdEntity phantom, Mob mob) {
            super(mob);
        }

        public void tick() {
        }
    }

    abstract class AbstractPreyBirdMoveTargetGoal extends Goal {
        public AbstractPreyBirdMoveTargetGoal(AbstractPreyBirdEntity phantom) {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return AbstractPreyBirdEntity.this.moveTargetPoint.distanceToSqr(AbstractPreyBirdEntity.this.getX(), AbstractPreyBirdEntity.this.getY(), AbstractPreyBirdEntity.this.getZ()) < (double)4.0F;
        }
    }

    class AbstractPreyBirdCircleAroundAnchorGoal extends AbstractPreyBirdMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        AbstractPreyBirdCircleAroundAnchorGoal(AbstractPreyBirdEntity phantom) {
            super(phantom);
        }

        public boolean canUse() {
            return AbstractPreyBirdEntity.this.getTarget() == null || AbstractPreyBirdEntity.this.attackPhase == AbstractPreyBirdEntity.AttackPhase.CIRCLE;
        }

        public void start() {
            this.distance = 5.0F + AbstractPreyBirdEntity.this.random.nextFloat() * 10.0F;
            this.height = 4.0F + AbstractPreyBirdEntity.this.random.nextFloat() * 6.0F;
            this.clockwise = AbstractPreyBirdEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        public void tick() {
            if (AbstractPreyBirdEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = 4.0F + AbstractPreyBirdEntity.this.random.nextFloat() * 6.0F;
            }

            if (AbstractPreyBirdEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (AbstractPreyBirdEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = AbstractPreyBirdEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (AbstractPreyBirdEntity.this.moveTargetPoint.y < AbstractPreyBirdEntity.this.getY() && !AbstractPreyBirdEntity.this.level().isEmptyBlock(AbstractPreyBirdEntity.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (AbstractPreyBirdEntity.this.moveTargetPoint.y > AbstractPreyBirdEntity.this.getY() && !AbstractPreyBirdEntity.this.level().isEmptyBlock(AbstractPreyBirdEntity.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(AbstractPreyBirdEntity.this.anchorPoint)) {
                AbstractPreyBirdEntity.this.anchorPoint = AbstractPreyBirdEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            AbstractPreyBirdEntity.this.moveTargetPoint = Vec3.atLowerCornerOf(AbstractPreyBirdEntity.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    class AbstractPreyBirdSweepAttackGoal extends AbstractPreyBirdMoveTargetGoal {
        private static final int CAT_SEARCH_TICK_DELAY = 20;
        private boolean isScaredOfCat;
        private int catSearchTick;

        AbstractPreyBirdSweepAttackGoal(AbstractPreyBirdEntity phantom) {
            super(phantom);
        }

        public boolean canUse() {
            return AbstractPreyBirdEntity.this.getTarget() != null && AbstractPreyBirdEntity.this.attackPhase == AttackPhase.SWOOP;
        }

        public boolean canContinueToUse() {
            LivingEntity livingEntity = AbstractPreyBirdEntity.this.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else {
                if (livingEntity instanceof Player) {
                    Player player = (Player)livingEntity;
                    if (livingEntity.isSpectator() || player.isCreative()) {
                        return false;
                    }
                }

                if (!this.canUse()) {
                    return false;
                } else {
                    if (AbstractPreyBirdEntity.this.tickCount > this.catSearchTick) {
                        this.catSearchTick = AbstractPreyBirdEntity.this.tickCount + 20;
                        List<Cat> list = AbstractPreyBirdEntity.this.level().getEntitiesOfClass(Cat.class, AbstractPreyBirdEntity.this.getBoundingBox().inflate((double)16.0F), EntitySelector.ENTITY_STILL_ALIVE);

                        for(Cat cat : list) {
                            cat.hiss();
                        }

                        this.isScaredOfCat = !list.isEmpty();
                    }

                    return !this.isScaredOfCat;
                }
            }
        }

        public void start() {
        }

        public void stop() {
            AbstractPreyBirdEntity.this.setTarget((LivingEntity)null);
            AbstractPreyBirdEntity.this.attackPhase = AttackPhase.CIRCLE;
        }

        public void tick() {
            LivingEntity target = AbstractPreyBirdEntity.this.getTarget();
            if (target != null) {
                Vec3 targetPos = new Vec3(
                        target.getX(),
                        target.getY(0.5),
                        target.getZ()
                );

                AbstractPreyBirdEntity.this.moveTargetPoint = targetPos;

                Vec3 motion = AbstractPreyBirdEntity.this.getDeltaMovement();
                double dx = motion.x;
                double dy = motion.y;
                double dz = motion.z;

                float horizontalSpeed = Mth.sqrt((float) (dx * dx + dz * dz));
                float pitch = (float)(Mth.atan2(dy, horizontalSpeed) * (180F / Math.PI));
                AbstractPreyBirdEntity.this.setXRot(pitch);

                if (AbstractPreyBirdEntity.this.getBoundingBox().inflate(0.2).intersects(target.getBoundingBox())) {
                    AbstractPreyBirdEntity.this.doHurtTarget(target);
                    AbstractPreyBirdEntity.this.attackPhase = AttackPhase.CIRCLE;
                    if (!AbstractPreyBirdEntity.this.isSilent()) {
                        AbstractPreyBirdEntity.this.level().levelEvent(1039, AbstractPreyBirdEntity.this.blockPosition(), 0);
                    }
                } else if (AbstractPreyBirdEntity.this.horizontalCollision || AbstractPreyBirdEntity.this.hurtTime > 0) {
                    AbstractPreyBirdEntity.this.attackPhase = AttackPhase.CIRCLE;
                }
            }
            }
    }

    class AbstractPreyBirdAttackStrategyGoal extends Goal {
        private int nextSweepTick;

        AbstractPreyBirdAttackStrategyGoal(AbstractPreyBirdEntity phantom) {
        }

        public boolean canUse() {
            LivingEntity livingEntity = AbstractPreyBirdEntity.this.getTarget();
            return livingEntity != null ? AbstractPreyBirdEntity.this.canAttack(livingEntity, TargetingConditions.DEFAULT) : false;
        }

        public void start() {
            this.nextSweepTick = this.adjustedTickDelay(10);
            AbstractPreyBirdEntity.this.attackPhase = AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        public void stop() {
            AbstractPreyBirdEntity.this.anchorPoint = AbstractPreyBirdEntity.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, AbstractPreyBirdEntity.this.anchorPoint).above(10 + AbstractPreyBirdEntity.this.random.nextInt(20));
        }

        public void tick() {
            if (AbstractPreyBirdEntity.this.attackPhase == AbstractPreyBirdEntity.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    AbstractPreyBirdEntity.this.attackPhase = AbstractPreyBirdEntity.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = this.adjustedTickDelay((8 + AbstractPreyBirdEntity.this.random.nextInt(4)) * 20);
                    AbstractPreyBirdEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + AbstractPreyBirdEntity.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            BlockPos targetPos = AbstractPreyBirdEntity.this.getTarget().blockPosition();
            int heightAbove = 15 + AbstractPreyBirdEntity.this.random.nextInt(10);
            AbstractPreyBirdEntity.this.anchorPoint = targetPos.above(heightAbove);

            if (AbstractPreyBirdEntity.this.anchorPoint.getY() < AbstractPreyBirdEntity.this.level().getSeaLevel()) {
                AbstractPreyBirdEntity.this.anchorPoint = new BlockPos(
                        AbstractPreyBirdEntity.this.anchorPoint.getX(),
                        AbstractPreyBirdEntity.this.level().getSeaLevel() + 10,
                        AbstractPreyBirdEntity.this.anchorPoint.getZ()
                );
            }
        }
    }


}
