package net.epichunt.event;


import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.epichunt.entity.animals.*;

import static net.epichunt.entity.ModEntities.*;

public class AttributeRegisterEvents {
    public static void init() {
        EntityAttributeRegistry.register(DEER_ENTITY, DeerEntity::createAttributes);
        EntityAttributeRegistry.register(DOE_ENTITY, DoeEntity::createAttributes);
        EntityAttributeRegistry.register(CARIBOU_ENTITY, CaribouEntity::createAttributes);
        EntityAttributeRegistry.register(ROE_DEER_ENTITY, RoeDeerEntity::createAttributes);
        EntityAttributeRegistry.register(DUCK_ENTITY, DuckEntity::createAttributes);
        EntityAttributeRegistry.register(DRAKE_ENTITY, DuckEntity::createAttributes);
        EntityAttributeRegistry.register(GOOSE_ENTITY, GooseEntity::createAttributes);
        EntityAttributeRegistry.register(PHEASANT_ENTITY, PheasantEntity::createAttributes);
        EntityAttributeRegistry.register(QUAIL_ENTITY, QuailEntity::createAttributes);
        EntityAttributeRegistry.register(YAK_ENTITY, YakEntity::createAttributes);
        EntityAttributeRegistry.register(HIGHLAND_COW_ENTITY, HighlandCowEntity::createAttributes);
    }

}
