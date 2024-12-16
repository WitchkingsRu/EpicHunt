package net.epichunt.item;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.DuckEntity;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class ThrownDuckEgg extends ThrowableItemProjectile {
    public ThrownDuckEgg(EntityType<? extends ThrownDuckEgg> entityType, Level level) {
        super(entityType, level);
    }
    public static final Supplier<EntityType<ThrownDuckEgg>> THROWN_DUCK_EGG = Suppliers.memoize(() -> EntityType.Builder.<ThrownDuckEgg>of(ThrownDuckEgg::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_duck_egg")
    );
    public ThrownDuckEgg(Level level, LivingEntity livingEntity) {
        super(THROWN_DUCK_EGG.get(), livingEntity, level); // Замените EntityType.SNOWBALL на ваше зарегистрированное EntityType для утиного яйца.
    }

    public ThrownDuckEgg(Level level, double x, double y, double z) {
        super(THROWN_DUCK_EGG.get(), x, y, z, level); // Замените EntityType.SNOWBALL на ваше EntityType.
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
                    DuckEntity duck = DuckEntity.DUCK.get().create(this.level()); // Замените Chicken на вашу сущность утки.
                    if (duck != null) {
                        duck.setAge(-24000); // Детёныш утки.
                        duck.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        this.level().addFreshEntity(duck);
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
        return ModItem.DUCK_EGG.get(); // Укажите ваш предмет утиного яйца.
    }
}
