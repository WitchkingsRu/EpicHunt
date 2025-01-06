package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.Level;


import java.util.function.Supplier;

public class GoatEntity extends Goat {
    public static final Supplier<EntityType<GoatEntity>> GOAT = Suppliers.memoize(() -> EntityType.Builder.of(GoatEntity::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("goat"));
    public GoatEntity(EntityType<? extends Goat> entityType, Level level) {
        super(entityType, level);
    }
}