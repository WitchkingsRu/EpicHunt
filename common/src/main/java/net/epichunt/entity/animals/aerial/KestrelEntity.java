package net.epichunt.entity.animals.aerial;

import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class KestrelEntity extends AbstractPreyBirdEntity {
    public KestrelEntity(EntityType<? extends AbstractPreyBirdEntity> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<KestrelEntity>> KESTREL = Suppliers.memoize(() -> EntityType.Builder.of(KestrelEntity::new, MobCategory.CREATURE)
            .sized(0.7f, 0.7f).build("kestrel"));

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AbstractPreyBirdAttackStrategyGoal(this));
        this.goalSelector.addGoal(2, new AbstractPreyBirdSweepAttackGoal(this));
        this.goalSelector.addGoal(3, new AbstractPreyBirdCircleAroundAnchorGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 10D).add(Attributes.MOVEMENT_SPEED, 0.3000000298023224)
                .add(Attributes.FOLLOW_RANGE, 25D).add(Attributes.ATTACK_DAMAGE, 10D).add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    public SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }

    protected SoundEvent getAmbientSound() {
        return Sounds.KESTREL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.PHEASANT_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return Sounds.PHEASANT_DEATH.get();
    }

    protected float getSoundVolume() {
        return 1.0F;
    }
}
