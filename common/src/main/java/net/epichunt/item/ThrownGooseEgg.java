package net.epichunt.item;

import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownGooseEgg extends ThrowableItemProjectile {
    public ThrownGooseEgg(EntityType<? extends ThrownGooseEgg> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownGooseEgg(Level level, LivingEntity livingEntity) {
        super(ModEntities.THROWN_GOOSE_EGG.get(), livingEntity, level); 
    }

    public ThrownGooseEgg(Level level, double x, double y, double z) {
        super(ModEntities.THROWN_GOOSE_EGG.get(), x, y, z, level); 
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
                    GooseEntity goose = GooseEntity.GOOSE.get().create(this.level()); 
                    if (goose != null) {
                        goose.setAge(-24000); 
                        goose.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        this.level().addFreshEntity(goose);
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
        return ModItem.GOOSE_EGG.get(); // Укажите ваш предмет утиного яйца.
    }
}
