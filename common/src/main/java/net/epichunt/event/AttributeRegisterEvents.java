package net.epichunt.event;


import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.epichunt.entity.animals.*;
import net.epichunt.entity.animals.fish.BassEntity;

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
        EntityAttributeRegistry.register(WISENT_ENTITY, WisentEntity::createAttributes);
        EntityAttributeRegistry.register(GOAT_ENTITY, GoatEntity::createAttributes);
        EntityAttributeRegistry.register(MOOSE_ENTITY, MooseEntity::createAttributes);
        EntityAttributeRegistry.register(BADGER_ENTITY, BadgerEntity::createAttributes);
        EntityAttributeRegistry.register(BOAR_ENTITY, BoarEntity::createAttributes);
        EntityAttributeRegistry.register(LYNX_ENTITY, LynxEntity::createAttributes);
        EntityAttributeRegistry.register(BEAR_ENTITY, BearEntity::createAttributes);
        EntityAttributeRegistry.register(WOLF_ENTITY, WolfEntity::createAttributes);
        EntityAttributeRegistry.register(BASS_ENTITY, BassEntity::createAttributes);
    }

}
