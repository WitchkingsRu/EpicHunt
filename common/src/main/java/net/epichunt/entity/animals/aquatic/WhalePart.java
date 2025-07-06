package net.epichunt.entity.animals.aquatic;

import com.google.common.base.Suppliers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class WhalePart extends Entity {
    public final WhaleEntity parentMob;
    public final String name;
    private final EntityDimensions size;

    public WhalePart(WhaleEntity whale, String string, float f, float g) {
        super(whale.getType(), whale.level());
        this.size = EntityDimensions.scalable(f, g);
        this.refreshDimensions();
        this.parentMob = whale;
        this.name = string;
        this.setBoundingBox(makeBoundingBox());
    }
    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.parentMob == null) {
            this.discard();
        }
    }

    @Override
    public boolean isPickable() {
        return this.parentMob != null && this.parentMob.isAlive();
    }

    protected void defineSynchedData() {
    }

    protected void readAdditionalSaveData(CompoundTag compoundTag) {
    }

    protected void addAdditionalSaveData(CompoundTag compoundTag) {
    }

    protected void collideWithNearbyEntities() {
        final List<Entity> entities = this.level().getEntities(this, this.getBoundingBox().expandTowards(0.2D, 0.0D, 0.2D));
        Entity parent = this.getParent();
        if (parent != null) {
            entities.stream().filter(entity -> entity != parent && !(entity instanceof WhalePart && ((WhalePart) entity).getParent() == parent) && entity.isPushable()).forEach(entity -> entity.push(parent));
        }
    }

    public Entity getParent() {
        return this.parentMob;
    }


    @Nullable
    public ItemStack getPickResult() {
        return this.parentMob.getPickResult();
    }

    public boolean hurt(DamageSource damageSource, float f) {
        return this.parentMob.hurt(this, damageSource, f);
    }

    public boolean is(Entity entity) {
        return this == entity || this.parentMob == entity;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    public EntityDimensions getDimensions(Pose pose) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }
}


