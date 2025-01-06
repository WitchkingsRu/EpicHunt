package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class BearEntity extends PolarBear {
    public static final Supplier<EntityType<BearEntity>> BEAR = Suppliers.memoize(() -> EntityType.Builder.of(BearEntity::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("lynx"));
    public BearEntity(EntityType<? extends PolarBear> entityType, Level level) {
        super(entityType, level);
    }
}