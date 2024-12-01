package net.epichunt.misc;

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
    public static void init() {
        CreativeTabRegistry.append(SPAWN_EGGS_RESOURCE_KEY, ModItem.DEER_SPAWN_EGG, ModItem.DOE_SPAWN_EGG, ModItem.CARIBOU_SPAWN_EGG,
                ModItem.ROE_DEER_SPAWN_EGG);
        CreativeTabRegistry.append(FOOD_RESOURCE_KEY, ModItem.VENISON, ModItem.COOKED_VENISON);
    }
}
