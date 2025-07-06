package net.epichunt.entity.animals.aquatic;

import com.google.common.base.Suppliers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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
        this.setBoundingBox(new AABB(0, 0, 0, f, g, f));
    }

    @Override
    public boolean isPickable() {
        return true;
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

    @Override
    public void tick() {
        // Позиция обновляется родителем
    }

    @Nullable
    public ItemStack getPickResult() {
        return this.parentMob.getPickResult();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        System.out.println("Part hurt: " + this + ", damage: " + amount);
        return parentMob.hurt(source, amount);
    }

    public boolean is(Entity entity) {
        return this == entity || this.parentMob == entity;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
    public EntityDimensions getDimensions(Pose pose) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public void updatePosition(Vec3 pos) {
        this.setPos(pos.x, pos.y, pos.z);
    }

    public String getPartName() {
        return this.name;
    }
}


