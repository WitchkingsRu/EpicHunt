package net.epichunt.item;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.block.ModBlock;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.*;
import net.epichunt.entity.animals.aquatic.*;
import net.epichunt.entity.animals.aerial.*;
import net.epichunt.entity.animals.fish.*;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.material.Fluids;

import java.util.Set;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(EpicHunt.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> DEER_SPAWN_EGG = ITEMS.register("deer_spawn_egg", () -> new SpawnEggItem(DeerEntity.DEER.get(),0x97724C, 0x6B4625,
            new Item.Properties()));

    public static final RegistrySupplier<Item> DOE_SPAWN_EGG = ITEMS.register("doe_spawn_egg", () -> new SpawnEggItem(DoeEntity.DOE.get(),0xBD9065, 0x846341,
            new Item.Properties()));

    public static final RegistrySupplier<Item> CARIBOU_SPAWN_EGG = ITEMS.register("caribou_spawn_egg", () -> new SpawnEggItem(CaribouEntity.CARIBOU.get(),0x584532, 0xF2F0EF,
            new Item.Properties()));

    public static final RegistrySupplier<Item> ROE_DEER_SPAWN_EGG = ITEMS.register("roe_deer_spawn_egg", () -> new SpawnEggItem(RoeDeerEntity.ROE_DEER.get(),0x8F6B47, 0xBF9266,
            new Item.Properties()));

    public static final RegistrySupplier<Item> DUCK_SPAWN_EGG = ITEMS.register("duck_spawn_egg", () -> new SpawnEggItem(DuckEntity.DUCK.get(),0x483822, 0x6F5B3F,
            new Item.Properties()));

    public static final RegistrySupplier<Item> DRAKE_SPAWN_EGG = ITEMS.register("drake_spawn_egg", () -> new SpawnEggItem(DrakeEntity.DRAKE.get(),0xD8D8D8, 0x274C32,
            new Item.Properties()));

    public static final RegistrySupplier<Item> GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg", () -> new SpawnEggItem(GooseEntity.GOOSE.get(),0xE6E6E6, 0xE05700,
            new Item.Properties()));

    public static final RegistrySupplier<Item> PHEASANT_SPAWN_EGG = ITEMS.register("pheasant_spawn_egg", () -> new SpawnEggItem(PheasantEntity.PHEASANT.get(),0x6F3521, 0x002E4A,
            new Item.Properties()));

    public static final RegistrySupplier<Item> QUAIL_SPAWN_EGG = ITEMS.register("quail_spawn_egg", () -> new SpawnEggItem(QuailEntity.QUAIL.get(),0x584434, 0x180C09,
            new Item.Properties()));

    public static final RegistrySupplier<Item> YAK_SPAWN_EGG = ITEMS.register("yak_spawn_egg", () -> new SpawnEggItem(YakEntity.YAK.get(),0x171007, 0x150E06,
            new Item.Properties()));

    public static final RegistrySupplier<Item> HIGHLAND_COW_SPAWN_EGG = ITEMS.register("highland_cow_spawn_egg", () -> new SpawnEggItem(HighlandCowEntity.HIGHLAND_COW.get(),0x543218, 0x472A14,
            new Item.Properties()));

    public static final RegistrySupplier<Item> WISENT_SPAWN_EGG = ITEMS.register("wisent_spawn_egg", () -> new SpawnEggItem(WisentEntity.WISENT.get(),0x2C1C11, 0x1E140C,
            new Item.Properties()));

    public static final RegistrySupplier<Item> MOOSE_SPAWN_EGG = ITEMS.register("moose_spawn_egg", () -> new SpawnEggItem(MooseEntity.MOOSE.get(),0x39280D, 0x302008,
            new Item.Properties()));

    public static final RegistrySupplier<Item> WOLF_SPAWN_EGG = ITEMS.register("wolf_spawn_egg", () -> new SpawnEggItem(WolfEntity.WOLF.get(),0x6E6F6F, 0x494A4A,
            new Item.Properties()));

    public static final RegistrySupplier<Item> LYNX_SPAWN_EGG = ITEMS.register("lynx_spawn_egg", () -> new SpawnEggItem(LynxEntity.LYNX.get(),0xB88E67, 0x523C1E,
            new Item.Properties()));

    public static final RegistrySupplier<Item> BOAR_SPAWN_EGG = ITEMS.register("boar_spawn_egg", () -> new SpawnEggItem(BoarEntity.BOAR.get(),0x211708, 0x040000,
            new Item.Properties()));

    public static final RegistrySupplier<Item> GOAT_SPAWN_EGG = ITEMS.register("goat_spawn_egg", () -> new SpawnEggItem(GoatEntity.GOAT.get(),0xE9E8E7, 0xF5F5F5,
            new Item.Properties()));

    public static final RegistrySupplier<Item> BADGER_SPAWN_EGG = ITEMS.register("badger_spawn_egg", () -> new SpawnEggItem(BadgerEntity.BADGER.get(),0x3D3D3D, 0xC8C8C8,
            new Item.Properties()));

    public static final RegistrySupplier<Item> BEAR_SPAWN_EGG = ITEMS.register("bear_spawn_egg", () -> new SpawnEggItem(BearEntity.BEAR.get(),0x120700, 0x312616,
            new Item.Properties()));

    public static final RegistrySupplier<Item> BEAVER_SPAWN_EGG = ITEMS.register("beaver_spawn_egg", () -> new SpawnEggItem(BeaverEntity.BEAVER.get(),0x3D3D3D, 0x312616,
            new Item.Properties()));

    public static final RegistrySupplier<Item> HARE_SPAWN_EGG = ITEMS.register("hare_spawn_egg", () -> new SpawnEggItem(HareEntity.HARE.get(),0xAE9D86, 0x6C5F4B,
            new Item.Properties()));


    public static final RegistrySupplier<Item> STURGEON_SPAWN_EGG = ITEMS.register("sturgeon_spawn_egg", () -> new SpawnEggItem(SturgeonEntity.STURGEON.get(),0x3F3737, 0xDDD9DB,
            new Item.Properties()));

    public static final RegistrySupplier<Item> CATFISH_SPAWN_EGG = ITEMS.register("catfish_spawn_egg", () -> new SpawnEggItem(CatfishEntity.CATFISH.get(),0x1D1D1D, 0xDBDCB4,
            new Item.Properties()));

    public static final RegistrySupplier<Item> EEL_SPAWN_EGG = ITEMS.register("eel_spawn_egg", () -> new SpawnEggItem(EelEntity.EEL.get(),0x2C2C2C, 0x555555,
            new Item.Properties()));

    public static final RegistrySupplier<Item> NARWHAL_SPAWN_EGG = ITEMS.register("narwhal_spawn_egg", () -> new SpawnEggItem(NarwhalEntity.NARWHAL.get(),0x504E5B, 0xD5D0D8,
            new Item.Properties()));

    public static final RegistrySupplier<Item> WHALE_SPAWN_EGG = ITEMS.register("whale_spawn_egg", () -> new SpawnEggItem(WhaleEntity.WHALE.get(),0x3B4856, 0x1E252B,
            new Item.Properties()));

    public static final RegistrySupplier<Item> ORCA_SPAWN_EGG = ITEMS.register("orca_spawn_egg", () -> new SpawnEggItem(OrcaEntity.ORCA.get(),0x1D1D1D, 0xDADADA,
            new Item.Properties()));

    public static final RegistrySupplier<Item> WHITE_SHARK_SPAWN_EGG = ITEMS.register("white_shark_spawn_egg", () -> new SpawnEggItem(WhiteSharkEntity.WHITE_SHARK.get(),0x4B4B4B, 0xE2E2E2,
            new Item.Properties()));

    public static final RegistrySupplier<Item> SWORDFISH_SPAWN_EGG = ITEMS.register("swordfish_spawn_egg", () -> new SpawnEggItem(SwordfishEntity.SWORDFISH.get(),0x29293C, 0xEFECDF,
            new Item.Properties()));

    public static final RegistrySupplier<Item> PIGEON_SPAWN_EGG = ITEMS.register("pigeon_spawn_egg", () -> new SpawnEggItem(PigeonEntity.PIGEON.get(),0x494D62, 0x63566E,
            new Item.Properties()));

    public static final RegistrySupplier<Item> GREAT_AUK_SPAWN_EGG = ITEMS.register("great_auk_spawn_egg", () -> new SpawnEggItem(GreatAukEntity.GREAT_AUK.get(),0x242424, 0xD1D1D1,
            new Item.Properties()));

    public static final RegistrySupplier<Item> RAZORBILL_SPAWN_EGG = ITEMS.register("razorbill_spawn_egg", () -> new SpawnEggItem(RazorbillEntity.RAZORBILL.get(),0x000000, 0xE8E8E8,
            new Item.Properties()));

    public static final RegistrySupplier<Item> WHITE_STORK_SPAWN_EGG = ITEMS.register("white_stork_spawn_egg", () -> new SpawnEggItem(WhiteStorkEntity.WHITE_STORK.get(),0xC6C6C6, 0xC34650,
            new Item.Properties()));

    public static final RegistrySupplier<Item> CRANE_SPAWN_EGG = ITEMS.register("crane_spawn_egg", () -> new SpawnEggItem(CommonCraneEntity.COMMON_CRANE.get(),0xBEBEBE, 0x343434,
            new Item.Properties()));

    public static final RegistrySupplier<Item> KESTREL_SPAWN_EGG = ITEMS.register("kestrel_spawn_egg", () -> new SpawnEggItem(KestrelEntity.KESTREL.get(),0x8D400D, 0x995C33,
            new Item.Properties()));

    public static final RegistrySupplier<Item> LOON_SPAWN_EGG = ITEMS.register("loon_spawn_egg", () -> new SpawnEggItem(LoonEntity.LOON.get(),0x121418, 0x88898B,
            new Item.Properties()));

    public static final RegistrySupplier<Item> PARTRIDGE_SPAWN_EGG = ITEMS.register("partridge_spawn_egg", () -> new SpawnEggItem(PartridgeEntity.PARTRIDGE.get(),0x4B4741, 0x4E3521,
            new Item.Properties()));

    public static final RegistrySupplier<Item> NIGHTINGALE_SPAWN_EGG = ITEMS.register("nightingale_spawn_egg", () -> new SpawnEggItem(NightingaleEntity.NIGHTINGALE.get(),0x5A4738, 0x94876F,
            new Item.Properties()));

    public static final RegistrySupplier<Item> RAVEN_SPAWN_EGG = ITEMS.register("raven_spawn_egg", () -> new SpawnEggItem(RavenEntity.RAVEN.get(),0x090910, 0x15151C,
            new Item.Properties()));

    public static final RegistrySupplier<Item> HAWK_SPAWN_EGG = ITEMS.register("hawk_spawn_egg", () -> new SpawnEggItem(HawkEntity.HAWK.get(),0x86522F, 0xDCC7B4,
            new Item.Properties()));

    public static final RegistrySupplier<Item> EAGLE_SPAWN_EGG = ITEMS.register("eagle_spawn_egg", () -> new SpawnEggItem(EagleEntity.EAGLE.get(),0x432F28, 0x634941,
            new Item.Properties()));

    public static final RegistrySupplier<Item> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> new SpawnEggItem(OwlEntity.OWL.get(),0x2C2624, 0xB0927B,
            new Item.Properties()));

    public static final RegistrySupplier<Item> BULLFINCH_SPAWN_EGG = ITEMS.register("bullfinch_spawn_egg", () -> new SpawnEggItem(BullfinchEntity.BULLFINCH.get(),0x1E1E1E, 0xF15133,
            new Item.Properties()));



    public static final RegistrySupplier<Item> VENISON = ITEMS.register("raw_venison", ()-> new Item(new Item.Properties().food(ModFood.VENISON)));

    public static final RegistrySupplier<Item> COOKED_VENISON = ITEMS.register("cooked_venison", ()-> new Item(new Item.Properties().food(ModFood.COOKED_VENISON)));

    public static final RegistrySupplier<Item> WHALE = ITEMS.register("raw_whale", ()-> new Item(new Item.Properties().food(ModFood.WHALE)));

    public static final RegistrySupplier<Item> COOKED_WHALE = ITEMS.register("cooked_whale", ()-> new Item(new Item.Properties().food(ModFood.COOKED_WHALE)));

    public static final RegistrySupplier<Item> SHARK = ITEMS.register("raw_shark", ()-> new Item(new Item.Properties().food(ModFood.SHARK)));

    public static final RegistrySupplier<Item> COOKED_SHARK = ITEMS.register("cooked_shark", ()-> new Item(new Item.Properties().food(ModFood.COOKED_SHARK)));

    public static final RegistrySupplier<Item> SWORDFISH = ITEMS.register("raw_swordfish", ()-> new Item(new Item.Properties().food(ModFood.SWORDFISH)));

    public static final RegistrySupplier<Item> COOKED_SWORDFISH = ITEMS.register("cooked_swordfish", ()-> new Item(new Item.Properties().food(ModFood.COOKED_SWORDFISH)));

    public static final RegistrySupplier<Item> BIRD = ITEMS.register("raw_bird", ()-> new Item(new Item.Properties().food(ModFood.BIRD)));

    public static final RegistrySupplier<Item> COOKED_BIRD = ITEMS.register("cooked_bird", ()-> new Item(new Item.Properties().food(ModFood.COOKED_BIRD)));

    public static final RegistrySupplier<Item> WOLF = ITEMS.register("raw_wolf", ()-> new Item(new Item.Properties().food(ModFood.WOLF)));

    public static final RegistrySupplier<Item> COOKED_WOLF = ITEMS.register("cooked_wolf", ()-> new Item(new Item.Properties().food(ModFood.COOKED_WOLF)));

    public static final RegistrySupplier<Item> BEAR = ITEMS.register("raw_bear", ()-> new Item(new Item.Properties().food(ModFood.BEAR)));

    public static final RegistrySupplier<Item> COOKED_BEAR = ITEMS.register("cooked_bear", ()-> new Item(new Item.Properties().food(ModFood.COOKED_BEAR)));

    public static final RegistrySupplier<Item> BADGER = ITEMS.register("raw_badger", ()-> new Item(new Item.Properties().food(ModFood.BADGER)));

    public static final RegistrySupplier<Item> COOKED_BADGER = ITEMS.register("cooked_badger", ()-> new Item(new Item.Properties().food(ModFood.COOKED_BADGER)));

    public static final RegistrySupplier<Item> BEAVER = ITEMS.register("raw_beaver", ()-> new Item(new Item.Properties().food(ModFood.BEAVER)));

    public static final RegistrySupplier<Item> COOKED_BEAVER = ITEMS.register("cooked_beaver", ()-> new Item(new Item.Properties().food(ModFood.COOKED_BEAVER)));

    public static final RegistrySupplier<Item> EQUINE = ITEMS.register("raw_equine", ()-> new Item(new Item.Properties().food(ModFood.EQUINE)));

    public static final RegistrySupplier<Item> COOKED_EQUINE = ITEMS.register("cooked_equine", ()-> new Item(new Item.Properties().food(ModFood.COOKED_EQUINE)));

    public static final RegistrySupplier<Item> LLAMA = ITEMS.register("raw_llama", ()-> new Item(new Item.Properties().food(ModFood.LLAMA)));

    public static final RegistrySupplier<Item> COOKED_LLAMA = ITEMS.register("cooked_llama", ()-> new Item(new Item.Properties().food(ModFood.COOKED_LLAMA)));

    public static final RegistrySupplier<Item> CAMEL = ITEMS.register("raw_camel", ()-> new Item(new Item.Properties().food(ModFood.CAMEL)));

    public static final RegistrySupplier<Item> COOKED_CAMEL = ITEMS.register("cooked_camel", ()-> new Item(new Item.Properties().food(ModFood.COOKED_CAMEL)));



    public static final RegistrySupplier<Item> BASS = ITEMS.register("raw_bass", ()-> new Item(new Item.Properties().food(ModFood.BASS)));

    public static final RegistrySupplier<Item> COOKED_BASS = ITEMS.register("cooked_bass", ()-> new Item(new Item.Properties().food(ModFood.COOKED_BASS)));

    public static final RegistrySupplier<Item> CARP = ITEMS.register("raw_carp", ()-> new Item(new Item.Properties().food(ModFood.CARP)));

    public static final RegistrySupplier<Item> COOKED_CARP = ITEMS.register("cooked_carp", ()-> new Item(new Item.Properties().food(ModFood.COOKED_CARP)));

    public static final RegistrySupplier<Item> HALIBUT = ITEMS.register("raw_halibut", ()-> new Item(new Item.Properties().food(ModFood.HALIBUT)));

    public static final RegistrySupplier<Item> COOKED_HALIBUT = ITEMS.register("cooked_halibut", ()-> new Item(new Item.Properties().food(ModFood.COOKED_HALIBUT)));

    public static final RegistrySupplier<Item> HERRING = ITEMS.register("raw_herring", ()-> new Item(new Item.Properties().food(ModFood.HERRING)));

    public static final RegistrySupplier<Item> COOKED_HERRING = ITEMS.register("cooked_herring", ()-> new Item(new Item.Properties().food(ModFood.COOKED_HERRING)));

    public static final RegistrySupplier<Item> MACKEREL = ITEMS.register("raw_mackerel", ()-> new Item(new Item.Properties().food(ModFood.MACKEREL)));

    public static final RegistrySupplier<Item> COOKED_MACKEREL = ITEMS.register("cooked_mackerel", ()-> new Item(new Item.Properties().food(ModFood.COOKED_MACKEREL)));

    public static final RegistrySupplier<Item> PERCHES = ITEMS.register("raw_perches", ()-> new Item(new Item.Properties().food(ModFood.PERCHES)));

    public static final RegistrySupplier<Item> COOKED_PERCHES = ITEMS.register("cooked_perches", ()-> new Item(new Item.Properties().food(ModFood.COOKED_PERCHES)));

    public static final RegistrySupplier<Item> PIKEFISH = ITEMS.register("raw_pikefish", ()-> new Item(new Item.Properties().food(ModFood.PIKEFISH)));

    public static final RegistrySupplier<Item> COOKED_PIKEFISH = ITEMS.register("cooked_pikefish", ()-> new Item(new Item.Properties().food(ModFood.COOKED_PIKEFISH)));

    public static final RegistrySupplier<Item> POLLOCK = ITEMS.register("raw_pollock", ()-> new Item(new Item.Properties().food(ModFood.POLLOCK)));

    public static final RegistrySupplier<Item> COOKED_POLLOCK = ITEMS.register("cooked_pollock", ()-> new Item(new Item.Properties().food(ModFood.COOKED_POLLOCK)));

    public static final RegistrySupplier<Item> ROACH = ITEMS.register("raw_roach", ()-> new Item(new Item.Properties().food(ModFood.ROACH)));

    public static final RegistrySupplier<Item> COOKED_ROACH = ITEMS.register("cooked_roach", ()-> new Item(new Item.Properties().food(ModFood.COOKED_ROACH)));

    public static final RegistrySupplier<Item> SARDINE = ITEMS.register("raw_sardine", ()-> new Item(new Item.Properties().food(ModFood.SARDINE)));

    public static final RegistrySupplier<Item> COOKED_SARDINE = ITEMS.register("cooked_sardine", ()-> new Item(new Item.Properties().food(ModFood.COOKED_SARDINE)));

    public static final RegistrySupplier<Item> TROUT = ITEMS.register("raw_trout", ()-> new Item(new Item.Properties().food(ModFood.TROUT)));

    public static final RegistrySupplier<Item> COOKED_TROUT = ITEMS.register("cooked_trout", ()-> new Item(new Item.Properties().food(ModFood.COOKED_TROUT)));

    public static final RegistrySupplier<Item> ZANDER = ITEMS.register("raw_zander", ()-> new Item(new Item.Properties().food(ModFood.ZANDER)));

    public static final RegistrySupplier<Item> COOKED_ZANDER = ITEMS.register("cooked_zander", ()-> new Item(new Item.Properties().food(ModFood.COOKED_ZANDER)));

    public static final RegistrySupplier<Item> STURGEON = ITEMS.register("raw_sturgeon", ()-> new Item(new Item.Properties().food(ModFood.STURGEON)));

    public static final RegistrySupplier<Item> COOKED_STURGEON = ITEMS.register("cooked_sturgeon", ()-> new Item(new Item.Properties().food(ModFood.COOKED_STURGEON)));

    public static final RegistrySupplier<Item> CATFISH = ITEMS.register("raw_catfish", ()-> new Item(new Item.Properties().food(ModFood.CATFISH)));

    public static final RegistrySupplier<Item> COOKED_CATFISH = ITEMS.register("cooked_catfish", ()-> new Item(new Item.Properties().food(ModFood.COOKED_CATFISH)));

    public static final RegistrySupplier<Item> EEL = ITEMS.register("raw_eel", ()-> new Item(new Item.Properties().food(ModFood.EEL)));

    public static final RegistrySupplier<Item> COOKED_EEL = ITEMS.register("cooked_eel", ()-> new Item(new Item.Properties().food(ModFood.COOKED_EEL)));

    public static final RegistrySupplier<Item> CALAMARI = ITEMS.register("raw_calamari", ()-> new Item(new Item.Properties().food(ModFood.CALAMARI)));

    public static final RegistrySupplier<Item> COOKED_CALAMARI = ITEMS.register("cooked_calamari", ()-> new Item(new Item.Properties().food(ModFood.COOKED_CALAMARI)));

    public static final RegistrySupplier<Item> MUSSEL = ITEMS.register("raw_mussel", ()-> new ItemNameBlockItem(ModBlock.MUSSEL_BLOCK.get(), new Item.Properties().food(ModFood.MUSSEL)));

    public static final RegistrySupplier<Item> COOKED_MUSSEL = ITEMS.register("cooked_mussel", ()-> new Item(new Item.Properties().food(ModFood.COOKED_MUSSEL)));

    public static final RegistrySupplier<Item> OYSTER = ITEMS.register("raw_oyster", ()-> new ItemNameBlockItem(ModBlock.OYSTER_BLOCK.get(), new Item.Properties().food(ModFood.OYSTER)));

    public static final RegistrySupplier<Item> COOKED_OYSTER = ITEMS.register("cooked_oyster", ()-> new Item(new Item.Properties().food(ModFood.COOKED_OYSTER)));

    public static final RegistrySupplier<Item> CLAM = ITEMS.register("raw_clam", ()-> new ItemNameBlockItem(ModBlock.CLAM_BLOCK.get(), new Item.Properties().food(ModFood.CLAM)));

    public static final RegistrySupplier<Item> COOKED_CLAM = ITEMS.register("cooked_clam", ()-> new Item(new Item.Properties().food(ModFood.COOKED_CLAM)));


    public static final RegistrySupplier<Item> BASS_BUCKET = ITEMS.register("bass_bucket", () -> new MobBucketItem(BassEntity.BASS.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> CARP_BUCKET = ITEMS.register("carp_bucket", () -> new MobBucketItem(CarpEntity.CARP.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> HALIBUT_BUCKET = ITEMS.register("halibut_bucket", () -> new MobBucketItem(HalibutEntity.HALIBUT.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> HERRING_BUCKET = ITEMS.register("herring_bucket", () -> new MobBucketItem(HerringEntity.HERRING.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> MACKEREL_BUCKET = ITEMS.register("mackerel_bucket", () -> new MobBucketItem(MackerelEntity.MACKEREL.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> PERCHES_BUCKET = ITEMS.register("perches_bucket", () -> new MobBucketItem(PerchesEntity.PERCHES.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> PIKEFISH_BUCKET = ITEMS.register("pikefish_bucket", () -> new MobBucketItem(PikefishEntity.PIKEFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> POLLOCK_BUCKET = ITEMS.register("pollock_bucket", () -> new MobBucketItem(PollockEntity.POLLOCK.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> ROACH_BUCKET = ITEMS.register("roach_bucket", () -> new MobBucketItem(RoachEntity.ROACH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> SARDINE_BUCKET = ITEMS.register("sardine_bucket", () -> new MobBucketItem(SardineEntity.SARDINE.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> TROUT_BUCKET = ITEMS.register("trout_bucket", () -> new MobBucketItem(TroutEntity.TROUT.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));

    public static final RegistrySupplier<Item> ZANDER_BUCKET = ITEMS.register("zander_bucket", () -> new MobBucketItem(ZanderEntity.ZANDER.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));




    public static final RegistrySupplier<Item> YAK_CARPET_WHITE = ITEMS.register("white_yak_carpet", () -> new YakCarpetItem(0, "white_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_BLACK = ITEMS.register("black_yak_carpet", () -> new YakCarpetItem(0, "black_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_BLUE = ITEMS.register("blue_yak_carpet", () -> new YakCarpetItem(0, "blue_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_BROWN = ITEMS.register("brown_yak_carpet", () -> new YakCarpetItem(0, "brown_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_CYAN = ITEMS.register("cyan_yak_carpet", () -> new YakCarpetItem(0, "cyan_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_GRAY = ITEMS.register("gray_yak_carpet", () -> new YakCarpetItem(0, "gray_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_GREEN = ITEMS.register("green_yak_carpet", () -> new YakCarpetItem(0, "green_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_LIGHT_BLUE = ITEMS.register("light_blue_yak_carpet", () -> new YakCarpetItem(0, "light_blue_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_LIGHT_GRAY = ITEMS.register("light_gray_yak_carpet", () -> new YakCarpetItem(0, "light_gray_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_LIME = ITEMS.register("lime_yak_carpet", () -> new YakCarpetItem(0, "lime_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_MAGENTA = ITEMS.register("magenta_yak_carpet", () -> new YakCarpetItem(0, "magenta_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_ORANGE = ITEMS.register("orange_yak_carpet", () -> new YakCarpetItem(0, "orange_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_PINK = ITEMS.register("pink_yak_carpet", () -> new YakCarpetItem(0, "pink_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_PURPLE = ITEMS.register("purple_yak_carpet", () -> new YakCarpetItem(0, "purple_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_RED = ITEMS.register("red_yak_carpet", () -> new YakCarpetItem(0, "red_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_YELLOW = ITEMS.register("yellow_yak_carpet", () -> new YakCarpetItem(0, "yellow_carpet", new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> YAK_CARPET_VILLAGER = ITEMS.register("villager_yak_carpet", () -> new YakCarpetItem(0, "villager_carpet", new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> FANG = ITEMS.register("fang", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> CLAW = ITEMS.register("claw", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> TUSK = ITEMS.register("tusk", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> HORN = ITEMS.register("horn", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> BLUBBER = ITEMS.register("blubber", () -> new Item(new Item.Properties()));

    public static final RegistrySupplier<Item> FANG_NECKLACE = ITEMS.register("fang_necklace", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> CLAW_NECKLACE = ITEMS.register("claw_necklace", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> TUSK_NECKLACE = ITEMS.register("tusk_necklace", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> BONE_COMB = ITEMS.register("bone_comb", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> BONE_ROSARY = ITEMS.register("bone_rosary", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> ANTLER_SEWING_KIT = ITEMS.register("antler_sewing_kit", () -> new Item(new Item.Properties()));

    public static final RegistrySupplier<Item> HORN_FLASK = ITEMS.register("horn_flask", () -> new HornFlaskItem(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> FILLED_HORN_FLASK = ITEMS.register("filled_horn_flask", () -> new HornFlaskPotionItem(new Item.Properties().stacksTo(1)));

    public static final RegistrySupplier<Item> DUCK_EGG = ITEMS.register("duck_egg", () -> new DuckEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> GOOSE_EGG = ITEMS.register("goose_egg", () -> new GooseEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> PHEASANT_EGG = ITEMS.register("pheasant_egg", () -> new PheasantEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> QUAIL_EGG = ITEMS.register("quail_egg", () -> new QuailEggItem(new Item.Properties().stacksTo(16)));

    public static final RegistrySupplier<Item> SMALL_ANTLERS_BLOCK = ITEMS.register("small_antlers", () ->
            new BlockItem(ModBlock.SMALL_ANTLERS.get(), new Item.Properties()));

    public static final RegistrySupplier<Item> MEDIUM_ANTLERS_BLOCK = ITEMS.register("medium_antlers", () ->
            new BlockItem(ModBlock.MEDIUM_ANTLERS.get(), new Item.Properties()));

    public static final RegistrySupplier<Item> LARGE_ANTLERS_BLOCK = ITEMS.register("large_antlers", () ->
            new BlockItem(ModBlock.LARGE_ANTLERS.get(), new Item.Properties()));



}
