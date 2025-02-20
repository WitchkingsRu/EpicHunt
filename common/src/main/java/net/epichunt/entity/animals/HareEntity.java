package net.epichunt.entity.animals;

import com.google.common.base.Suppliers;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.Level;


import java.util.function.Supplier;

public class HareEntity extends Rabbit {
    public static final Supplier<EntityType<HareEntity>> HARE = Suppliers.memoize(() -> EntityType.Builder.of(HareEntity::new, MobCategory.CREATURE).sized(0.98F, 0.7F).clientTrackingRange(8).build("hare"));
    public HareEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);
    }
}