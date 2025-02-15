package net.epichunt.entity;

import com.google.common.base.Predicate;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.epichunt.entity.animals.DrakeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

import static net.epichunt.entity.ModEntities.*;


public class MobSpawns {
    public static void addEntitySpawns(com.google.common.base.Predicate<BiomeModifications.BiomeContext> context, MobCategory mobCategory, Supplier<EntityType<?>> entityTypeSupplier, int weight, int min, int max) {
        BiomeModifications.addProperties(
                context,
                (context2, mutable) -> mutable.getSpawnProperties().addSpawn(
                        mobCategory, // Spawn group
                        new MobSpawnSettings.SpawnerData(entityTypeSupplier.get(), weight, min, max) // Weight, min, max
                )
        );
    }


    public static void spawnsInit() {
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DEER_ENTITY::get, 12, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DOE_ENTITY::get, 12, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, CARIBOU_ENTITY::get, 10, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, ROE_DEER_ENTITY::get, 12, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG))), MobCategory.CREATURE, DUCK_ENTITY::get, 10, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG))), MobCategory.CREATURE, DRAKE_ENTITY::get, 10, 1, 2);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER)) || (context.hasTag(BiomeTags.HAS_CLOSER_WATER_FOG))), MobCategory.CREATURE, GOOSE_ENTITY::get, 10, 2, 4);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, PHEASANT_ENTITY::get, 15, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, QUAIL_ENTITY::get, 15, 2, 4);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, YAK_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_HILL)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, HIGHLAND_COW_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, LYNX_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS)) || (context.hasTag(BiomeTags.HAS_VILLAGE_SAVANNA))
                ||(context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, WISENT_ENTITY::get, 4, 1, 4);
        addEntitySpawns(context -> ((!(context.hasTag(BiomeTags.HAS_IGLOO)) && !(context.hasTag(BiomeTags.IS_TAIGA)) && !(context.hasTag(BiomeTags.SPAWNS_SNOW_FOXES))) && (context.hasTag(BiomeTags.IS_OVERWORLD))), MobCategory.CREATURE, GOAT_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, BOAR_ENTITY::get, 12, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, BEAR_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.MONSTER, WOLF_ENTITY::get, 8, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.HAS_VILLAGE_PLAINS))), MobCategory.CREATURE, BADGER_ENTITY::get, 8, 1, 1);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, MOOSE_ENTITY::get, 4, 1, 3);

        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, BASS_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, HALIBUT_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, MACKEREL_ENTITY::get, 30, 3, 7);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, HERRING_ENTITY::get, 30, 3, 7);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, SARDINE_ENTITY::get, 30, 3, 7);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, POLLOCK_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_OCEAN)) || (context.hasTag(BiomeTags.IS_DEEP_OCEAN)) || (context.hasTag(BiomeTags.HAS_OCEAN_MONUMENT)) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_COLD) || (context.hasTag(BiomeTags.HAS_OCEAN_RUIN_WARM)))),
                MobCategory.WATER_AMBIENT, STURGEON_ENTITY::get, 10, 1, 1);

        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, CARP_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, EEL_ENTITY::get, 20, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, PERCHES_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, PIKEFISH_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, ROACH_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, ZANDER_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, TROUT_ENTITY::get, 20, 3, 5);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_RIVER))), MobCategory.WATER_AMBIENT, CATFISH_ENTITY::get, 4, 1, 1);
    }

    public static void placementRegistry() {
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
        SpawnPlacementsRegistry.register(WOLF_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);

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

    }
}
