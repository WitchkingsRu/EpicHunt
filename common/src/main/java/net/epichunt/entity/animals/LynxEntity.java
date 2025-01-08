package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class LynxEntity extends Ocelot {
    public static final Supplier<EntityType<LynxEntity>> LYNX = Suppliers.memoize(() -> EntityType.Builder.of(LynxEntity::new, MobCategory.CREATURE).sized(0.98F, 0.7F).clientTrackingRange(8).build("lynx"));
    public LynxEntity(EntityType<? extends Ocelot> entityType, Level level) {
        super(entityType, level);
    }
}