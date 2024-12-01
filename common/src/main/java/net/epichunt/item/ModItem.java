package net.epichunt.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.RoeDeerEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

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

    public static final RegistrySupplier<Item> VENISON = ITEMS.register("raw_venison", ()-> new Item(new Item.Properties().food(ModFood.VENISON)));

    public static final RegistrySupplier<Item> COOKED_VENISON = ITEMS.register("cooked_venison", ()-> new Item(new Item.Properties().food(ModFood.COOKED_VENISON)));
}
