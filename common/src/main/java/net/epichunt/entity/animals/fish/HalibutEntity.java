package net.epichunt.entity.animals.fish;

import com.google.common.base.Suppliers;
import net.epichunt.item.ModItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class HalibutEntity extends AbstractSchoolingFish {
    public HalibutEntity(EntityType<? extends HalibutEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static final Supplier<EntityType<HalibutEntity>> HALIBUT = Suppliers.memoize(() -> EntityType.Builder.of(HalibutEntity::new, MobCategory.WATER_CREATURE)
            .sized(1f, 1f).build("halibut"));

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimTimeout = 15;

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(400) + 1100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItem.HALIBUT_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }
}
