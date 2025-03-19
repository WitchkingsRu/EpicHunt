package net.epichunt.worldgen;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.epichunt.EpicHunt;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGen {
    public static void init() {
        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if ((ctx.hasTag(BiomeTags.IS_OCEAN)) || (ctx.hasTag(BiomeTags.IS_DEEP_OCEAN))) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                            ResourceKey.create(Registries.PLACED_FEATURE,
                                    new ResourceLocation(EpicHunt.MOD_ID + ":mussel_cluster")));
                }
            });
        });
        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if ((ctx.hasTag(BiomeTags.IS_OCEAN)) || (ctx.hasTag(BiomeTags.IS_DEEP_OCEAN))) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                            ResourceKey.create(Registries.PLACED_FEATURE,
                                    new ResourceLocation(EpicHunt.MOD_ID + ":oyster_cluster")));
                }
            });
        });
        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if ((ctx.hasTag(BiomeTags.IS_OCEAN)) || (ctx.hasTag(BiomeTags.IS_DEEP_OCEAN))) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                            ResourceKey.create(Registries.PLACED_FEATURE,
                                    new ResourceLocation(EpicHunt.MOD_ID + ":clam_cluster")));
                }
            });
        });
    }
}
