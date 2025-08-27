package net.epichunt.entity;

import com.google.common.base.Predicate;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.epichunt.EpicHunt;
import net.epichunt.config.ConfigMain;
import net.epichunt.entity.animals.DrakeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animals.WolfEntity;
import net.epichunt.entity.animals.aerial.AbstractPreyBirdEntity;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

import static net.epichunt.entity.ModEntities.*;


public class MobSpawns {
    public static final ConfigMain CONFIG = EpicHunt.CONFIG;
    public static void addEntitySpawns(com.google.common.base.Predicate<BiomeModifications.BiomeContext> context, MobCategory mobCategory, Supplier<EntityType<?>> entityTypeSupplier, int weight, int min, int max) {
        BiomeModifications.addProperties(
                context,
                (context2, mutable) -> mutable.getSpawnProperties().addSpawn(
                        mobCategory,
                        new MobSpawnSettings.SpawnerData(entityTypeSupplier.get(), weight, min, max)
                )
        );
    }


    public static void spawnsInit(ConfigMain SPAWN_CONFIG) {
        //ANIMALS
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DEER_ENTITY::get, CONFIG.deer.weight, CONFIG.deer.minAmount, CONFIG.deer.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DOE_ENTITY::get, CONFIG.doe.weight, CONFIG.doe.minAmount, CONFIG.doe.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, CARIBOU_ENTITY::get, CONFIG.caribou.weight, CONFIG.caribou.minAmount, CONFIG.caribou.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, ROE_DEER_ENTITY::get, CONFIG.roe_deer.weight, CONFIG.roe_deer.minAmount, CONFIG.roe_deer.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, PHEASANT_ENTITY::get, CONFIG.pheasant.weight, CONFIG.pheasant.minAmount, CONFIG.pheasant.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, QUAIL_ENTITY::get, CONFIG.quail.weight, CONFIG.quail.minAmount, CONFIG.quail.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, YAK_ENTITY::get, CONFIG.yak.weight, CONFIG.yak.minAmount, CONFIG.yak.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_HILL)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, HIGHLAND_COW_ENTITY::get, CONFIG.highland_cow.weight, CONFIG.highland_cow.minAmount, CONFIG.highland_cow.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, LYNX_ENTITY::get, CONFIG.lynx.weight, CONFIG.lynx.minAmount, CONFIG.lynx.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS)) || (context.hasTag(BiomeTags.HAS_VILLAGE_SAVANNA))
                ||(context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, WISENT_ENTITY::get, CONFIG.wisent.weight, CONFIG.wisent.minAmount, CONFIG.wisent.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.IS_TAIGA)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, GOAT_ENTITY::get,
                CONFIG.short_haired_goat.weight, CONFIG.short_haired_goat.minAmount, CONFIG.short_haired_goat.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, BOAR_ENTITY::get, CONFIG.boar.weight, CONFIG.boar.minAmount, CONFIG.boar.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, BEAR_ENTITY::get, CONFIG.brown_bear.weight, CONFIG.brown_bear.minAmount, CONFIG.brown_bear.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.MONSTER, WOLF_ENTITY::get, CONFIG.wild_wolf.weight, CONFIG.wild_wolf.minAmount, CONFIG.wild_wolf.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, BADGER_ENTITY::get, CONFIG.badger.weight, CONFIG.badger.minAmount, CONFIG.badger.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, MOOSE_ENTITY::get, CONFIG.moose.weight, CONFIG.moose.minAmount, CONFIG.moose.maxAmount);

        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.IS_RIVER))), MobCategory.CREATURE, BEAVER_ENTITY::get, CONFIG.beaver.weight, CONFIG.beaver.minAmount, CONFIG.beaver.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, HARE_ENTITY::get, CONFIG.hare.weight, CONFIG.hare.minAmount, CONFIG.hare.maxAmount);



        //BIRDS
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD))), MobCategory.CREATURE, GREAT_AUK_ENTITY::get, CONFIG.great_auk.weight, CONFIG.great_auk.minAmount, CONFIG.great_auk.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD))), MobCategory.CREATURE, RAZORBILL_ENTITY::get, CONFIG.razorbill.weight, CONFIG.razorbill.minAmount, CONFIG.razorbill.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))|| (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, DUCK_ENTITY::get, CONFIG.duck.weight, CONFIG.duck.minAmount, CONFIG.duck.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))|| (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, DRAKE_ENTITY::get, CONFIG.drake.weight, CONFIG.drake.minAmount, CONFIG.drake.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))|| (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, GOOSE_ENTITY::get, CONFIG.goose.weight, CONFIG.goose.minAmount, CONFIG.goose.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.IS_TAIGA)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, PIGEON_ENTITY::get, CONFIG.pigeon.weight, CONFIG.pigeon.minAmount, CONFIG.pigeon.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))), MobCategory.CREATURE, WHITE_STORK_ENTITY::get, CONFIG.stork.weight, CONFIG.stork.minAmount, CONFIG.stork.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))), MobCategory.CREATURE, COMMON_CRANE_ENTITY::get, CONFIG.crane.weight, CONFIG.crane.minAmount, CONFIG.crane.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, KESTREL_ENTITY::get, CONFIG.kestrel.weight, CONFIG.kestrel.minAmount, CONFIG.kestrel.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, EAGLE_ENTITY::get, CONFIG.eagle.weight, CONFIG.eagle.minAmount, CONFIG.eagle.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, HAWK_ENTITY::get, CONFIG.hawk.weight, CONFIG.hawk.minAmount, CONFIG.hawk.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG)) || (context.hasTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))|| (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, LOON_ENTITY::get, CONFIG.loon.weight, CONFIG.loon.minAmount, CONFIG.loon.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_TAIGA)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, PARTRIDGE_ENTITY::get, CONFIG.partridge.weight, CONFIG.partridge.minAmount, CONFIG.partridge.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, BULLFINCH_ENTITY::get, CONFIG.bullfinch.weight, CONFIG.bullfinch.minAmount, CONFIG.bullfinch.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST))), MobCategory.CREATURE, NIGHTINGALE_ENTITY::get, CONFIG.nightingale.weight, CONFIG.nightingale.minAmount, CONFIG.nightingale.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, RAVEN_ENTITY::get, CONFIG.raven.weight, CONFIG.raven.minAmount, CONFIG.raven.maxAmount);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, OWL_ENTITY::get, CONFIG.owl.weight, CONFIG.owl.minAmount, CONFIG.owl.maxAmount);



        //FISH
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, BASS_ENTITY::get, CONFIG.bass.weight, CONFIG.bass.minAmount, CONFIG.bass.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, HALIBUT_ENTITY::get, CONFIG.halibut.weight, CONFIG.halibut.minAmount, CONFIG.halibut.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, MACKEREL_ENTITY::get, CONFIG.mackerel.weight, CONFIG.mackerel.minAmount, CONFIG.mackerel.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, HERRING_ENTITY::get, CONFIG.herring.weight, CONFIG.herring.minAmount, CONFIG.herring.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, SARDINE_ENTITY::get, CONFIG.sardine.weight, CONFIG.sardine.minAmount, CONFIG.sardine.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, POLLOCK_ENTITY::get, CONFIG.pollock.weight, CONFIG.pollock.minAmount, CONFIG.pollock.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_BEACH) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))),
                MobCategory.WATER_AMBIENT, STURGEON_ENTITY::get, CONFIG.sturgeon.weight, CONFIG.sturgeon.minAmount, CONFIG.sturgeon.maxAmount);

        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, CARP_ENTITY::get, CONFIG.carp.weight, CONFIG.carp.minAmount, CONFIG.carp.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, EEL_ENTITY::get, CONFIG.eel.weight, CONFIG.eel.minAmount, CONFIG.eel.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, PERCHES_ENTITY::get, CONFIG.perches.weight, CONFIG.perches.minAmount, CONFIG.perches.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, PIKEFISH_ENTITY::get, CONFIG.pike_fish.weight, CONFIG.pike_fish.minAmount, CONFIG.pike_fish.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, ROACH_ENTITY::get, CONFIG.roach.weight, CONFIG.roach.minAmount, CONFIG.roach.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, ZANDER_ENTITY::get, CONFIG.zander.weight, CONFIG.zander.minAmount, CONFIG.zander.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, TROUT_ENTITY::get, CONFIG.trout.weight, CONFIG.trout.minAmount, CONFIG.trout.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OVERWORLD) && !context.hasTag(BiomeTags.IS_OCEAN) && !context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_AMBIENT, CATFISH_ENTITY::get, CONFIG.catfish.weight, CONFIG.catfish.minAmount, CONFIG.catfish.maxAmount);

        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD))), MobCategory.WATER_CREATURE, NARWHAL_ENTITY::get, CONFIG.narwhal.weight, CONFIG.narwhal.minAmount, CONFIG.narwhal.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_CREATURE, WHALE_ENTITY::get, CONFIG.whale.weight, CONFIG.whale.minAmount, CONFIG.whale.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_DEEP_OCEAN))), MobCategory.WATER_CREATURE, ORCA_ENTITY::get, CONFIG.orca.weight, CONFIG.orca.minAmount, CONFIG.orca.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN) || context.hasTag(BiomeTags.IS_DEEP_OCEAN) && !context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD))), MobCategory.MONSTER, WHITE_SHARK_ENTITY::get, CONFIG.white_shark.weight, CONFIG.white_shark.minAmount, CONFIG.white_shark.maxAmount);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM))), MobCategory.WATER_AMBIENT, SWORDFISH_ENTITY::get, CONFIG.swordfish.weight, CONFIG.swordfish.minAmount, CONFIG.swordfish.maxAmount);


    }

    public static void placementRegistry(ConfigMain SPAWN_CONFIG) {
        SpawnPlacementsRegistry.register(DEER_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(DOE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(ROE_DEER_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(CARIBOU_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(DUCK_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, DuckEntity::checkDuckSpawnRules);
        SpawnPlacementsRegistry.register(DRAKE_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, DrakeEntity::checkDuckSpawnRules);
        SpawnPlacementsRegistry.register(GOOSE_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, GooseEntity::checkDuckSpawnRules);
        SpawnPlacementsRegistry.register(PHEASANT_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(QUAIL_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(YAK_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(HIGHLAND_COW_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(WISENT_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(LYNX_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(GOAT_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(BOAR_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(BEAR_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(MOOSE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(BADGER_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(WOLF_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WolfEntity::checkWolfSpawnRules);

        SpawnPlacementsRegistry.register(BASS_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(CARP_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(CATFISH_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(EEL_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(HALIBUT_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(HERRING_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(MACKEREL_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(PERCHES_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(PIKEFISH_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(POLLOCK_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(ROACH_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(SARDINE_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(STURGEON_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(TROUT_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(ZANDER_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, AbstractFish::checkMobSpawnRules);

        SpawnPlacementsRegistry.register(NARWHAL_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(WHALE_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(ORCA_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(WHITE_SHARK_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);
        SpawnPlacementsRegistry.register(SWORDFISH_ENTITY, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING, WaterAnimal::checkMobSpawnRules);

        SpawnPlacementsRegistry.register(GREAT_AUK_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(RAZORBILL_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(PIGEON_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(WHITE_STORK_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(COMMON_CRANE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(KESTREL_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, AbstractPreyBirdEntity::checkBirdSpawnRules);
        SpawnPlacementsRegistry.register(LOON_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(PARTRIDGE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(BULLFINCH_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(NIGHTINGALE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(RAVEN_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(HAWK_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, AbstractPreyBirdEntity::checkBirdSpawnRules);
        SpawnPlacementsRegistry.register(EAGLE_ENTITY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE_WG, AbstractPreyBirdEntity::checkBirdSpawnRules);
        SpawnPlacementsRegistry.register(OWL_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }
}
