package net.epichunt.event;


import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.epichunt.entity.animals.*;
import net.epichunt.entity.animals.aquatic.NarwhalEntity;
import net.epichunt.entity.animals.aquatic.WhaleEntity;
import net.epichunt.entity.animals.fish.*;
import net.epichunt.client.render.entity.*;

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
        EntityAttributeRegistry.register(BEAVER_ENTITY, BeaverEntity::createAttributes);
        EntityAttributeRegistry.register(HARE_ENTITY, HareEntity::createAttributes);
        
        EntityAttributeRegistry.register(BASS_ENTITY, BassEntity::createAttributes);
        EntityAttributeRegistry.register(CARP_ENTITY, CarpEntity::createAttributes);
        EntityAttributeRegistry.register(CATFISH_ENTITY, CatfishEntity::createAttributes);
        EntityAttributeRegistry.register(EEL_ENTITY, EelEntity::createAttributes);
        EntityAttributeRegistry.register(HALIBUT_ENTITY, HalibutEntity::createAttributes);
        EntityAttributeRegistry.register(HERRING_ENTITY, HerringEntity::createAttributes);
        EntityAttributeRegistry.register(MACKEREL_ENTITY, MackerelEntity::createAttributes);
        EntityAttributeRegistry.register(PERCHES_ENTITY, PerchesEntity::createAttributes);
        EntityAttributeRegistry.register(PIKEFISH_ENTITY, PikefishEntity::createAttributes);
        EntityAttributeRegistry.register(POLLOCK_ENTITY, PollockEntity::createAttributes);
        EntityAttributeRegistry.register(ROACH_ENTITY, RoachEntity::createAttributes);
        EntityAttributeRegistry.register(SARDINE_ENTITY, SardineEntity::createAttributes);
        EntityAttributeRegistry.register(STURGEON_ENTITY, SturgeonEntity::createAttributes);
        EntityAttributeRegistry.register(TROUT_ENTITY, TroutEntity::createAttributes);
        EntityAttributeRegistry.register(ZANDER_ENTITY, ZanderEntity::createAttributes);

        EntityAttributeRegistry.register(NARWHAL_ENTITY, NarwhalEntity::createAttributes);
        EntityAttributeRegistry.register(WHALE_ENTITY, WhaleEntity::createAttributes);
    }

}
