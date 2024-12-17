package net.epichunt.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.EggItem;
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


    public static final RegistrySupplier<Item> VENISON = ITEMS.register("raw_venison", ()-> new Item(new Item.Properties().food(ModFood.VENISON)));

    public static final RegistrySupplier<Item> COOKED_VENISON = ITEMS.register("cooked_venison", ()-> new Item(new Item.Properties().food(ModFood.COOKED_VENISON)));

    public static final RegistrySupplier<Item> DUCK = ITEMS.register("raw_duck", ()-> new Item(new Item.Properties().food(ModFood.DUCK)));

    public static final RegistrySupplier<Item> COOKED_DUCK = ITEMS.register("cooked_duck", ()-> new Item(new Item.Properties().food(ModFood.COOKED_DUCK)));

    public static final RegistrySupplier<Item> GOOSE = ITEMS.register("raw_goose", ()-> new Item(new Item.Properties().food(ModFood.GOOSE)));

    public static final RegistrySupplier<Item> COOKED_GOOSE = ITEMS.register("cooked_goose", ()-> new Item(new Item.Properties().food(ModFood.COOKED_GOOSE)));

    public static final RegistrySupplier<Item> PHEASANT = ITEMS.register("raw_pheasant", ()-> new Item(new Item.Properties().food(ModFood.PHEASANT)));

    public static final RegistrySupplier<Item> COOKED_PHEASANT = ITEMS.register("cooked_pheasant", ()-> new Item(new Item.Properties().food(ModFood.COOKED_PHEASANT)));

    public static final RegistrySupplier<Item> QUAIL = ITEMS.register("raw_quail", ()-> new Item(new Item.Properties().food(ModFood.QUAIL)));

    public static final RegistrySupplier<Item> COOKED_QUAIL = ITEMS.register("cooked_quail", ()-> new Item(new Item.Properties().food(ModFood.COOKED_QUAIL)));


    public static final RegistrySupplier<Item> DUCK_EGG = ITEMS.register("duck_egg", () -> new DuckEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> GOOSE_EGG = ITEMS.register("goose_egg", () -> new GooseEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> PHEASANT_EGG = ITEMS.register("pheasant_egg", () -> new PheasantEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistrySupplier<Item> QUAIL_EGG = ITEMS.register("quail_egg", () -> new QuailEggItem(new Item.Properties().stacksTo(16)));

}
