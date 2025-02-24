package net.epichunt.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.*;
import net.epichunt.entity.animals.fish.*;
import net.epichunt.item.ThrownDuckEgg;
import net.epichunt.item.ThrownGooseEgg;
import net.epichunt.item.ThrownPheasantEgg;
import net.epichunt.item.ThrownQuailEgg;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(EpicHunt.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<DeerEntity>> DEER_ENTITY = ENTITY_TYPES.register("deer", DeerEntity.DEER);
    public static final RegistrySupplier<EntityType<DoeEntity>> DOE_ENTITY = ENTITY_TYPES.register("doe", DoeEntity.DOE);
    public static final RegistrySupplier<EntityType<CaribouEntity>> CARIBOU_ENTITY = ENTITY_TYPES.register("caribou", CaribouEntity.CARIBOU);
    public static final RegistrySupplier<EntityType<RoeDeerEntity>> ROE_DEER_ENTITY = ENTITY_TYPES.register("roe_deer", RoeDeerEntity.ROE_DEER);

    public static final RegistrySupplier<EntityType<DuckEntity>> DUCK_ENTITY = ENTITY_TYPES.register("duck", DuckEntity.DUCK);
    public static final RegistrySupplier<EntityType<DrakeEntity>> DRAKE_ENTITY = ENTITY_TYPES.register("drake", DrakeEntity.DRAKE);
    public static final RegistrySupplier<EntityType<GooseEntity>> GOOSE_ENTITY = ENTITY_TYPES.register("goose", GooseEntity.GOOSE);
    public static final RegistrySupplier<EntityType<PheasantEntity>> PHEASANT_ENTITY = ENTITY_TYPES.register("pheasant", PheasantEntity.PHEASANT);
    public static final RegistrySupplier<EntityType<QuailEntity>> QUAIL_ENTITY = ENTITY_TYPES.register("quail", QuailEntity.QUAIL);

    public static final RegistrySupplier<EntityType<ThrownDuckEgg>> THROWN_DUCK_EGG = ENTITY_TYPES.register("thrown_duck_egg", ThrownDuckEgg.THROWN_DUCK_EGG);
    public static final RegistrySupplier<EntityType<ThrownGooseEgg>> THROWN_GOOSE_EGG = ENTITY_TYPES.register("thrown_goose_egg", ThrownGooseEgg.THROWN_GOOSE_EGG);
    public static final RegistrySupplier<EntityType<ThrownPheasantEgg>> THROWN_PHEASANT_EGG = ENTITY_TYPES.register("thrown_pheasant_egg", ThrownPheasantEgg.THROWN_PHEASANT_EGG);
    public static final RegistrySupplier<EntityType<ThrownQuailEgg>> THROWN_QUAIL_EGG = ENTITY_TYPES.register("thrown_quail_egg", ThrownQuailEgg.THROWN_QUAIL_EGG);

    public static final RegistrySupplier<EntityType<YakEntity>> YAK_ENTITY = ENTITY_TYPES.register("yak", YakEntity.YAK);
    public static final RegistrySupplier<EntityType<HighlandCowEntity>> HIGHLAND_COW_ENTITY = ENTITY_TYPES.register("highland_cow", HighlandCowEntity.HIGHLAND_COW);
    public static final RegistrySupplier<EntityType<WisentEntity>> WISENT_ENTITY = ENTITY_TYPES.register("wisent", WisentEntity.WISENT);
    public static final RegistrySupplier<EntityType<GoatEntity>> GOAT_ENTITY = ENTITY_TYPES.register("goat", GoatEntity.GOAT);

    public static final RegistrySupplier<EntityType<MooseEntity>> MOOSE_ENTITY = ENTITY_TYPES.register("moose", MooseEntity.MOOSE);
    public static final RegistrySupplier<EntityType<BadgerEntity>> BADGER_ENTITY = ENTITY_TYPES.register("badger", BadgerEntity.BADGER);
    public static final RegistrySupplier<EntityType<BoarEntity>> BOAR_ENTITY = ENTITY_TYPES.register("boar", BoarEntity.BOAR);

    public static final RegistrySupplier<EntityType<LynxEntity>> LYNX_ENTITY = ENTITY_TYPES.register("lynx", LynxEntity.LYNX);
    public static final RegistrySupplier<EntityType<BearEntity>> BEAR_ENTITY = ENTITY_TYPES.register("bear", BearEntity.BEAR);
    public static final RegistrySupplier<EntityType<WolfEntity>> WOLF_ENTITY = ENTITY_TYPES.register("wolf", WolfEntity.WOLF);
    public static final RegistrySupplier<EntityType<BeaverEntity>> BEAVER_ENTITY = ENTITY_TYPES.register("beaver", BeaverEntity.BEAVER);
    public static final RegistrySupplier<EntityType<HareEntity>> HARE_ENTITY = ENTITY_TYPES.register("hare", HareEntity.HARE);

    public static final RegistrySupplier<EntityType<BassEntity>> BASS_ENTITY = ENTITY_TYPES.register("bass", BassEntity.BASS);
    public static final RegistrySupplier<EntityType<CarpEntity>> CARP_ENTITY = ENTITY_TYPES.register("carp", CarpEntity.CARP);
    public static final RegistrySupplier<EntityType<CatfishEntity>> CATFISH_ENTITY = ENTITY_TYPES.register("catfish", CatfishEntity.CATFISH);
    public static final RegistrySupplier<EntityType<EelEntity>> EEL_ENTITY = ENTITY_TYPES.register("eel", EelEntity.EEL);
    public static final RegistrySupplier<EntityType<HalibutEntity>> HALIBUT_ENTITY = ENTITY_TYPES.register("halibut", HalibutEntity.HALIBUT);
    public static final RegistrySupplier<EntityType<HerringEntity>> HERRING_ENTITY = ENTITY_TYPES.register("herring", HerringEntity.HERRING);
    public static final RegistrySupplier<EntityType<MackerelEntity>> MACKEREL_ENTITY = ENTITY_TYPES.register("mackerel", MackerelEntity.MACKEREL);
    public static final RegistrySupplier<EntityType<PerchesEntity>> PERCHES_ENTITY = ENTITY_TYPES.register("perches", PerchesEntity.PERCHES);
    public static final RegistrySupplier<EntityType<PikefishEntity>> PIKEFISH_ENTITY = ENTITY_TYPES.register("pikefish", PikefishEntity.PIKEFISH);
    public static final RegistrySupplier<EntityType<PollockEntity>> POLLOCK_ENTITY = ENTITY_TYPES.register("pollock", PollockEntity.POLLOCK);
    public static final RegistrySupplier<EntityType<RoachEntity>> ROACH_ENTITY = ENTITY_TYPES.register("roach", RoachEntity.ROACH);
    public static final RegistrySupplier<EntityType<SardineEntity>> SARDINE_ENTITY = ENTITY_TYPES.register("sardine", SardineEntity.SARDINE);
    public static final RegistrySupplier<EntityType<SturgeonEntity>> STURGEON_ENTITY = ENTITY_TYPES.register("sturgeon", SturgeonEntity.STURGEON);
    public static final RegistrySupplier<EntityType<TroutEntity>> TROUT_ENTITY = ENTITY_TYPES.register("trout", TroutEntity.TROUT);
    public static final RegistrySupplier<EntityType<ZanderEntity>> ZANDER_ENTITY = ENTITY_TYPES.register("zander", ZanderEntity.ZANDER);


}
