package net.epichunt.entity.animals.aerial;

import com.google.common.base.Suppliers;
import net.epichunt.entity.animals.DuckEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Supplier;

public class EagleEntity extends FlyingMob {
    Vec3 moveTargetPoint;
    BlockPos anchorPoint;
    AttackPhase attackPhase;

    public EagleEntity(EntityType<? extends EagleEntity> entityType, Level level) {
        super(entityType, level);
        this.moveTargetPoint = Vec3.ZERO;
        this.anchorPoint = BlockPos.ZERO;
        this.attackPhase = AttackPhase.CIRCLE;
        this.xpReward = 5;
        this.moveControl = new EagleMoveControl(this, this);
        this.lookControl = new EagleLookControl(this, this);
    }


    public static final Supplier<EntityType<EagleEntity>> EAGLE = Suppliers.memoize(() -> EntityType.Builder.of(EagleEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("eagle"));

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new EagleAttackStrategyGoal(this));
        this.goalSelector.addGoal(2, new EagleSweepAttackGoal(this));
        this.goalSelector.addGoal(3, new EagleCircleAroundAnchorGoal(this));
        //this.targetSelector.addGoal(1, new EagleAttackTargetGoal(this));
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    public final AnimationState flyAnimationState = new AnimationState();
    private void setupAnimationStates() {
        if (!this.isInWater() && !this.onGround()) {
            this.flyAnimationState.start(this.tickCount);
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
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.anchorPoint = this.blockPosition().above(5);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    public boolean shouldRenderAtSqrDistance(double d) {
        return true;
    }

    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PHANTOM_DEATH;
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public boolean canAttackType(EntityType<?> entityType) {
        return true;
    }

    static enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    class EagleMoveControl extends MoveControl {
        private float speed = 0.1F;

        public EagleMoveControl(EagleEntity phantom, Mob mob) {
            super(mob);
        }

        public void tick() {
            if (EagleEntity.this.horizontalCollision) {
                EagleEntity.this.setYRot(EagleEntity.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d = EagleEntity.this.moveTargetPoint.x - EagleEntity.this.getX();
            double e = EagleEntity.this.moveTargetPoint.y - EagleEntity.this.getY();
            double f = EagleEntity.this.moveTargetPoint.z - EagleEntity.this.getZ();
            double g = Math.sqrt(d * d + f * f);
            if (Math.abs(g) > (double)1.0E-5F) {
                double h = (double)1.0F - Math.abs(e * (double)0.7F) / g;
                d *= h;
                f *= h;
                g = Math.sqrt(d * d + f * f);
                double i = Math.sqrt(d * d + f * f + e * e);
                float j = EagleEntity.this.getYRot();
                float k = (float)Mth.atan2(f, d);
                float l = Mth.wrapDegrees(EagleEntity.this.getYRot() + 90.0F);
                float m = Mth.wrapDegrees(k * (180F / (float)Math.PI));
                EagleEntity.this.setYRot(Mth.approachDegrees(l, m, 4.0F) - 90.0F);
                EagleEntity.this.yBodyRot = EagleEntity.this.getYRot();
                if (Mth.degreesDifferenceAbs(j, EagleEntity.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float n = (float)(-(Mth.atan2(-e, g) * (double)(180F / (float)Math.PI)));
                EagleEntity.this.setXRot(n);
                float o = EagleEntity.this.getYRot() + 90.0F;
                double p = (double)(this.speed * Mth.cos(o * ((float)Math.PI / 180F))) * Math.abs(d / i);
                double q = (double)(this.speed * Mth.sin(o * ((float)Math.PI / 180F))) * Math.abs(f / i);
                double r = (double)(this.speed * Mth.sin(n * ((float)Math.PI / 180F))) * Math.abs(e / i);
                Vec3 vec3 = EagleEntity.this.getDeltaMovement();
                EagleEntity.this.setDeltaMovement(vec3.add((new Vec3(p, r, q)).subtract(vec3).scale(0.2)));
            }

        }
    }

    class EagleBodyRotationControl extends BodyRotationControl {
        public EagleBodyRotationControl(EagleEntity phantom, Mob mob) {
            super(mob);
        }

        public void clientTick() {
            EagleEntity.this.yHeadRot = EagleEntity.this.yBodyRot;
            EagleEntity.this.yBodyRot = EagleEntity.this.getYRot();
        }
    }

    class EagleLookControl extends LookControl {
        public EagleLookControl(EagleEntity phantom, Mob mob) {
            super(mob);
        }

        public void tick() {
        }
    }

    abstract class EagleMoveTargetGoal extends Goal {
        public EagleMoveTargetGoal(EagleEntity phantom) {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return EagleEntity.this.moveTargetPoint.distanceToSqr(EagleEntity.this.getX(), EagleEntity.this.getY(), EagleEntity.this.getZ()) < (double)4.0F;
        }
    }

    class EagleCircleAroundAnchorGoal extends EagleMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        EagleCircleAroundAnchorGoal(EagleEntity phantom) {
            super(phantom);
        }

        public boolean canUse() {
            return EagleEntity.this.getTarget() == null || EagleEntity.this.attackPhase == EagleEntity.AttackPhase.CIRCLE;
        }

        public void start() {
            this.distance = 5.0F + EagleEntity.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + EagleEntity.this.random.nextFloat() * 9.0F;
            this.clockwise = EagleEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        public void tick() {
            if (EagleEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + EagleEntity.this.random.nextFloat() * 9.0F;
            }

            if (EagleEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (EagleEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = EagleEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (EagleEntity.this.moveTargetPoint.y < EagleEntity.this.getY() && !EagleEntity.this.level().isEmptyBlock(EagleEntity.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (EagleEntity.this.moveTargetPoint.y > EagleEntity.this.getY() && !EagleEntity.this.level().isEmptyBlock(EagleEntity.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(EagleEntity.this.anchorPoint)) {
                EagleEntity.this.anchorPoint = EagleEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            EagleEntity.this.moveTargetPoint = Vec3.atLowerCornerOf(EagleEntity.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    class EagleSweepAttackGoal extends EagleMoveTargetGoal {
        private static final int CAT_SEARCH_TICK_DELAY = 20;
        private boolean isScaredOfCat;
        private int catSearchTick;

        EagleSweepAttackGoal(EagleEntity phantom) {
            super(phantom);
        }

        public boolean canUse() {
            return EagleEntity.this.getTarget() != null && EagleEntity.this.attackPhase == AttackPhase.SWOOP;
        }

        public boolean canContinueToUse() {
            LivingEntity livingEntity = EagleEntity.this.getTarget();
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
                    if (EagleEntity.this.tickCount > this.catSearchTick) {
                        this.catSearchTick = EagleEntity.this.tickCount + 20;
                        List<Cat> list = EagleEntity.this.level().getEntitiesOfClass(Cat.class, EagleEntity.this.getBoundingBox().inflate((double)16.0F), EntitySelector.ENTITY_STILL_ALIVE);

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
            EagleEntity.this.setTarget((LivingEntity)null);
            EagleEntity.this.attackPhase = AttackPhase.CIRCLE;
        }

        public void tick() {
            LivingEntity livingEntity = EagleEntity.this.getTarget();
            if (livingEntity != null) {
                EagleEntity.this.moveTargetPoint = new Vec3(livingEntity.getX(), livingEntity.getY((double)0.5F), livingEntity.getZ());
                if (EagleEntity.this.getBoundingBox().inflate((double)0.2F).intersects(livingEntity.getBoundingBox())) {
                    EagleEntity.this.doHurtTarget(livingEntity);
                    EagleEntity.this.attackPhase = AttackPhase.CIRCLE;
                    if (!EagleEntity.this.isSilent()) {
                        EagleEntity.this.level().levelEvent(1039, EagleEntity.this.blockPosition(), 0);
                    }
                } else if (EagleEntity.this.horizontalCollision || EagleEntity.this.hurtTime > 0) {
                    EagleEntity.this.attackPhase = AttackPhase.CIRCLE;
                }

            }
        }
    }

    class EagleAttackStrategyGoal extends Goal {
        private int nextSweepTick;

        EagleAttackStrategyGoal(EagleEntity phantom) {
        }

        public boolean canUse() {
            LivingEntity livingEntity = EagleEntity.this.getTarget();
            return livingEntity != null ? EagleEntity.this.canAttack(livingEntity, TargetingConditions.DEFAULT) : false;
        }

        public void start() {
            this.nextSweepTick = this.adjustedTickDelay(10);
            EagleEntity.this.attackPhase = AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        public void stop() {
            EagleEntity.this.anchorPoint = EagleEntity.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EagleEntity.this.anchorPoint).above(10 + EagleEntity.this.random.nextInt(20));
        }

        public void tick() {
            if (EagleEntity.this.attackPhase == EagleEntity.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    EagleEntity.this.attackPhase = EagleEntity.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = this.adjustedTickDelay((8 + EagleEntity.this.random.nextInt(4)) * 20);
                    EagleEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + EagleEntity.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            EagleEntity.this.anchorPoint = EagleEntity.this.getTarget().blockPosition().above(20 + EagleEntity.this.random.nextInt(20));
            if (EagleEntity.this.anchorPoint.getY() < EagleEntity.this.level().getSeaLevel()) {
                EagleEntity.this.anchorPoint = new BlockPos(EagleEntity.this.anchorPoint.getX(), EagleEntity.this.level().getSeaLevel() + 1, EagleEntity.this.anchorPoint.getZ());
            }

        }
    }
//    class EagleAttackTargetGoal extends Goal {
//        private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range((double)64.0F);
//        private int nextScanTick = reducedTickDelay(20);
//
//        EagleAttackTargetGoal(EagleEntity phantom) {
//        }
//
//        public boolean canUse() {
//            if (this.nextScanTick > 0) {
//                --this.nextScanTick;
//                return false;
//            } else {
//                this.nextScanTick = reducedTickDelay(60);
//                List<Rabbit> list = EagleEntity.this.level().getNearbyPlayers(this.attackTargeting, EagleEntity.this, EagleEntity.this.getBoundingBox().inflate((double)16.0F, (double)64.0F, (double)16.0F));
//                if (!list.isEmpty()) {
//                    list.sort(Comparator.comparing(Entity::getY).reversed());
//
//                    for(Rabbit player : list) {
//                        if (EagleEntity.this.canAttack(player, TargetingConditions.DEFAULT)) {
//                            EagleEntity.this.setTarget(player);
//                            return true;
//                        }
//                    }
//                }
//
//                return false;
//            }
//        }
//
//        public boolean canContinueToUse() {
//            LivingEntity livingEntity = EagleEntity.this.getTarget();
//            return livingEntity != null ? EagleEntity.this.canAttack(livingEntity, TargetingConditions.DEFAULT) : false;
//        }
//    }

}
