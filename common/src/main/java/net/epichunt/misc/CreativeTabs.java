package net.epichunt.misc;

import dev.architectury.platform.Mod;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.item.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(EpicHunt.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final ResourceKey<CreativeModeTab> SPAWN_EGGS_RESOURCE_KEY = CreativeModeTabs.SPAWN_EGGS;
    public static final ResourceKey<CreativeModeTab> FOOD_RESOURCE_KEY = CreativeModeTabs.FOOD_AND_DRINKS;
    public static final ResourceKey<CreativeModeTab> COMBAT_RESOURCE_KEY = CreativeModeTabs.COMBAT;
    public static final ResourceKey<CreativeModeTab> TOOLS_RESOURCE_KEY = CreativeModeTabs.TOOLS_AND_UTILITIES;
    public static final ResourceKey<CreativeModeTab> NATURAL_RESOURCE_KEY = CreativeModeTabs.NATURAL_BLOCKS;
    public static final ResourceKey<CreativeModeTab> INGREDIENTS_RESOURCE_KEY = CreativeModeTabs.INGREDIENTS;
    public static void init() {
        CreativeTabRegistry.append(SPAWN_EGGS_RESOURCE_KEY, ModItem.DEER_SPAWN_EGG, ModItem.DOE_SPAWN_EGG,
                ModItem.CARIBOU_SPAWN_EGG, ModItem.MOOSE_SPAWN_EGG, ModItem.ROE_DEER_SPAWN_EGG, ModItem.DUCK_SPAWN_EGG, ModItem.DRAKE_SPAWN_EGG,
                ModItem.GOOSE_SPAWN_EGG, ModItem.PHEASANT_SPAWN_EGG, ModItem.QUAIL_SPAWN_EGG, ModItem.YAK_SPAWN_EGG,
                ModItem.HIGHLAND_COW_SPAWN_EGG, ModItem.WISENT_SPAWN_EGG, ModItem.WOLF_SPAWN_EGG, ModItem.BOAR_SPAWN_EGG,
                ModItem.BEAR_SPAWN_EGG, ModItem.BADGER_SPAWN_EGG, ModItem.GOAT_SPAWN_EGG, ModItem.LYNX_SPAWN_EGG, ModItem.BEAVER_SPAWN_EGG,
                ModItem.HARE_SPAWN_EGG, ModItem.STURGEON_SPAWN_EGG, ModItem.CATFISH_SPAWN_EGG, ModItem.EEL_SPAWN_EGG, ModItem.NARWHAL_SPAWN_EGG,
                ModItem.WHALE_SPAWN_EGG, ModItem.ORCA_SPAWN_EGG, ModItem.WHITE_SHARK_SPAWN_EGG, ModItem.SWORDFISH_SPAWN_EGG,
                ModItem.PIGEON_SPAWN_EGG, ModItem.GREAT_AUK_SPAWN_EGG, ModItem.RAZORBILL_SPAWN_EGG, ModItem.WHITE_STORK_SPAWN_EGG,
                ModItem.CRANE_SPAWN_EGG, ModItem.KESTREL_SPAWN_EGG, ModItem.LOON_SPAWN_EGG, ModItem.PARTRIDGE_SPAWN_EGG,
                ModItem.NIGHTINGALE_SPAWN_EGG, ModItem.RAVEN_SPAWN_EGG, ModItem.HAWK_SPAWN_EGG, ModItem.EAGLE_SPAWN_EGG,
                ModItem.OWL_SPAWN_EGG, ModItem.BULLFINCH_SPAWN_EGG);
        CreativeTabRegistry.append(FOOD_RESOURCE_KEY, ModItem.VENISON, ModItem.COOKED_VENISON, ModItem.EQUINE,
                ModItem.COOKED_EQUINE, ModItem.LLAMA, ModItem.COOKED_LLAMA, ModItem.CAMEL, ModItem.COOKED_CAMEL, ModItem.WOLF,
                ModItem.COOKED_WOLF, ModItem.BEAR, ModItem.COOKED_BEAR, ModItem.BADGER, ModItem.COOKED_BADGER, ModItem.BEAVER,
                ModItem.COOKED_BEAVER, ModItem.WHALE, ModItem.COOKED_WHALE, ModItem.BIRD, ModItem.COOKED_BIRD, ModItem.DUCK_EGG, ModItem.GOOSE_EGG, ModItem.PHEASANT_EGG,
                ModItem.QUAIL_EGG, ModItem.BASS, ModItem.COOKED_BASS, ModItem.CARP, ModItem.COOKED_CARP,
                ModItem.HALIBUT, ModItem.COOKED_HALIBUT, ModItem.HERRING, ModItem.COOKED_HERRING, ModItem.MACKEREL,
                ModItem.COOKED_MACKEREL, ModItem.PERCHES, ModItem.COOKED_PERCHES, ModItem.PIKEFISH, ModItem.COOKED_PIKEFISH,
                ModItem.POLLOCK, ModItem.COOKED_POLLOCK, ModItem.ROACH, ModItem.COOKED_ROACH, ModItem.SARDINE, ModItem.COOKED_SARDINE,
                ModItem.TROUT, ModItem.COOKED_TROUT, ModItem.ZANDER, ModItem.COOKED_ZANDER, ModItem.EEL, ModItem.COOKED_EEL,
                ModItem.STURGEON, ModItem.COOKED_STURGEON, ModItem.CATFISH, ModItem.COOKED_CATFISH, ModItem.SWORDFISH, ModItem.COOKED_SWORDFISH,
                ModItem.SHARK, ModItem.COOKED_SHARK, ModItem.CALAMARI, ModItem.COOKED_CALAMARI, ModItem.MUSSEL, ModItem.COOKED_MUSSEL,
                ModItem.OYSTER, ModItem.COOKED_OYSTER, ModItem.CLAM, ModItem.COOKED_CLAM);
        CreativeTabRegistry.append(COMBAT_RESOURCE_KEY, ModItem.YAK_CARPET_WHITE, ModItem.YAK_CARPET_BLACK, ModItem.YAK_CARPET_BLUE,
                ModItem.YAK_CARPET_BROWN, ModItem.YAK_CARPET_CYAN, ModItem.YAK_CARPET_GRAY, ModItem.YAK_CARPET_GREEN,
                ModItem.YAK_CARPET_LIGHT_BLUE, ModItem.YAK_CARPET_LIGHT_GRAY, ModItem.YAK_CARPET_LIME, ModItem.YAK_CARPET_MAGENTA,
                ModItem.YAK_CARPET_ORANGE, ModItem.YAK_CARPET_PINK, ModItem.YAK_CARPET_PURPLE, ModItem.YAK_CARPET_RED,
                ModItem.YAK_CARPET_YELLOW, ModItem.YAK_CARPET_VILLAGER, ModItem.FANG_NECKLACE, ModItem.CLAW_NECKLACE, ModItem.TUSK_NECKLACE);
        CreativeTabRegistry.append(TOOLS_RESOURCE_KEY, ModItem.BASS_BUCKET, ModItem.CARP_BUCKET, ModItem.HALIBUT_BUCKET,
                ModItem.HERRING_BUCKET, ModItem.MACKEREL_BUCKET, ModItem.PERCHES_BUCKET, ModItem.PIKEFISH_BUCKET,
                ModItem.POLLOCK_BUCKET, ModItem.ROACH_BUCKET, ModItem.SARDINE_BUCKET, ModItem.TROUT_BUCKET,
                ModItem.ZANDER_BUCKET, ModItem.BONE_COMB, ModItem.BONE_ROSARY, ModItem.HORN_FLASK, ModItem.ANTLER_SEWING_KIT);
        CreativeTabRegistry.append(NATURAL_RESOURCE_KEY, ModItem.SMALL_ANTLERS_BLOCK, ModItem.MEDIUM_ANTLERS_BLOCK,
                ModItem.LARGE_ANTLERS_BLOCK);
        CreativeTabRegistry.append(INGREDIENTS_RESOURCE_KEY, ModItem.FANG, ModItem.CLAW,
                ModItem.TUSK, ModItem.HORN, ModItem.BLUBBER);
    }
}
