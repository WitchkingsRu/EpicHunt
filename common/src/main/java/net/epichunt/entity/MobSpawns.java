package net.epichunt.entity;

import com.google.common.base.Predicate;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

import static com.mojang.text2speech.Narrator.LOGGER;
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
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DEER_ENTITY::get, 10, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, DOE_ENTITY::get, 10, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.HAS_IGLOO)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, CARIBOU_ENTITY::get, 10, 1, 3);
        addEntitySpawns(context -> ((context.hasTag(BiomeTags.IS_FOREST)) || (context.hasTag(BiomeTags.IS_TAIGA))), MobCategory.CREATURE, ROE_DEER_ENTITY::get, 10, 1, 3);
    }

    public static void placementRegistry() {
        SpawnPlacementsRegistry.register(DEER_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(DOE_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(ROE_DEER_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacementsRegistry.register(CARIBOU_ENTITY, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }
}
