package net.epichunt.event;


import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.RoeDeerEntity;

import static net.epichunt.entity.ModEntities.*;

public class AttributeRegisterEvents {
    public static void init() {
        EntityAttributeRegistry.register(DEER_ENTITY, DeerEntity::createAttributes);
        EntityAttributeRegistry.register(DOE_ENTITY, DoeEntity::createAttributes);
        EntityAttributeRegistry.register(CARIBOU_ENTITY, CaribouEntity::createAttributes);
        EntityAttributeRegistry.register(ROE_DEER_ENTITY, RoeDeerEntity::createAttributes);
    }

}
