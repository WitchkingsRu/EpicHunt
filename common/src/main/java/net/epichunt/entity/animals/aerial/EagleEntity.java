package net.epichunt.entity.animals.aerial;

import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

public class EagleEntity extends AbstractPreyBirdEntity {
    public EagleEntity(EntityType<? extends AbstractPreyBirdEntity> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<EagleEntity>> EAGLE = Suppliers.memoize(() -> EntityType.Builder.of(EagleEntity::new, MobCategory.AMBIENT)
            .sized(0.7f, 0.7f).build("eagle"));

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AbstractPreyBirdAttackStrategyGoal(this));
        this.goalSelector.addGoal(2, new AbstractPreyBirdSweepAttackGoal(this));
        this.goalSelector.addGoal(3, new AbstractPreyBirdCircleAroundAnchorGoal(this));
        this.targetSelector.addGoal(1, new AbstractPreyBirdAttackTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.3000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D).add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    public SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }

    protected SoundEvent getAmbientSound() {
        return Sounds.EAGLE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.EAGLE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return Sounds.EAGLE_DEATH.get();
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    class AbstractPreyBirdAttackTargetGoal extends Goal {
        private final TargetingConditions ATTACK_TARGETING = TargetingConditions.forCombat().range(64.0);
        private final AbstractPreyBirdEntity eagle;
        private int nextScanTick = reducedTickDelay(20);

        public AbstractPreyBirdAttackTargetGoal(AbstractPreyBirdEntity eagle) {
            this.eagle = eagle;
        }

        @Override
        public boolean canUse() {
            if (nextScanTick > 0) {
                --nextScanTick;
                return false;
            } else {
                nextScanTick = reducedTickDelay(60);

                List<Rabbit> rabbits = eagle.level().getEntitiesOfClass(
                        Rabbit.class,
                        eagle.getBoundingBox().inflate(16.0, 64.0, 16.0),
                        rabbit -> rabbit.isAlive() && eagle.canAttack(rabbit, ATTACK_TARGETING)
                );

                if (!rabbits.isEmpty()) {
                    for (Rabbit rabbit : rabbits) {
                        if (eagle.canAttack(rabbit, ATTACK_TARGETING)) {
                            eagle.setTarget(rabbit);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = eagle.getTarget();
            return target instanceof Rabbit && eagle.canAttack(target, ATTACK_TARGETING) && target.isAlive();
        }
    }
}
