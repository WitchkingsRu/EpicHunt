package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.epichunt.sound.Sounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GreatAukEntity extends Animal {
    public GreatAukEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<GreatAukEntity>> GREAT_AUK = Suppliers.memoize(() -> EntityType.Builder.of(GreatAukEntity::new, MobCategory.AMBIENT)
            .sized(0.7f, 0.7f).build("great_auk"));

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
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return GREAT_AUK.get().create(serverLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224).add(Attributes.ATTACK_DAMAGE, 3.0);
    }

    protected SoundEvent getAmbientSound() {
        return Sounds.RAZORBILL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Sounds.RAZORBILL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return Sounds.RAZORBILL_DEATH.get();
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    static {
    }
}
