package net.epichunt.item;

import com.google.common.base.Suppliers;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.QuailEntity;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class ThrownQuailEgg extends ThrowableItemProjectile {
    public ThrownQuailEgg(EntityType<? extends ThrownQuailEgg> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<ThrownQuailEgg>> THROWN_QUAIL_EGG = Suppliers.memoize(() -> EntityType.Builder.<ThrownQuailEgg>of(ThrownQuailEgg::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_quail_egg")
    );
    public ThrownQuailEgg(Level level, LivingEntity livingEntity) {
        super(THROWN_QUAIL_EGG.get(), livingEntity, level);
    }

    public ThrownQuailEgg(Level level, double x, double y, double z) {
        super(THROWN_QUAIL_EGG.get(), x, y, z, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            if (this.random.nextInt(8) == 0) {
                int amount = this.random.nextInt(32) == 0 ? 4 : 1;
                for (int i = 0; i < amount; ++i) {
                    QuailEntity quail = QuailEntity.QUAIL.get().create(this.level()); // Замените Chicken на вашу сущность утки.
                    if (quail != null) {
                        quail.setAge(-24000); // Детёныш утки.
                        quail.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        this.level().addFreshEntity(quail);
                    }
                }
            }
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte event) {
        if (event == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                        this.getX(), this.getY(), this.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.08,
                        (this.random.nextDouble() - 0.5) * 0.08,
                        (this.random.nextDouble() - 0.5) * 0.08
                );
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.QUAIL_EGG.get(); // Укажите ваш предмет утиного яйца.
    }
}
