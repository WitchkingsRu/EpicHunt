package net.epichunt.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Suppliers;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.*;
import net.epichunt.entity.client.render.*;
import net.epichunt.item.ThrownDuckEgg;
import net.epichunt.item.ThrownGooseEgg;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.function.Supplier;



public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(EpicHunt.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<DeerEntity>> DEER_ENTITY = ENTITY_TYPES.register("deer", DeerEntity.DEER);
    public static final RegistrySupplier<EntityType<DoeEntity>> DOE_ENTITY = ENTITY_TYPES.register("doe", DoeEntity.DOE);
    public static final RegistrySupplier<EntityType<CaribouEntity>> CARIBOU_ENTITY = ENTITY_TYPES.register("caribou", CaribouEntity.CARIBOU);
    public static final RegistrySupplier<EntityType<RoeDeerEntity>> ROE_DEER_ENTITY = ENTITY_TYPES.register("roe_deer", RoeDeerEntity.ROE_DEER);


    public static final RegistrySupplier<EntityType<DuckEntity>> DUCK_ENTITY = ENTITY_TYPES.register("duck", DuckEntity.DUCK);
    public static final RegistrySupplier<EntityType<DrakeEntity>> DRAKE_ENTITY = ENTITY_TYPES.register("drake", DrakeEntity.DRAKE);
    public static final RegistrySupplier<EntityType<GooseEntity>> GOOSE_ENTITY = ENTITY_TYPES.register("goose", GooseEntity.GOOSE);

    public static final RegistrySupplier<EntityType<ThrownDuckEgg>> THROWN_DUCK_EGG = ENTITY_TYPES.register(
            "thrown_duck_egg",
            () -> EntityType.Builder.<ThrownDuckEgg>of(ThrownDuckEgg::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_duck_egg")
    );
    public static final RegistrySupplier<EntityType<ThrownGooseEgg>> THROWN_GOOSE_EGG = ENTITY_TYPES.register(
            "thrown_goose_egg",
            () -> EntityType.Builder.<ThrownGooseEgg>of(ThrownGooseEgg::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("thrown_goose_egg")
    );

    public static void renderRegistry() {
        EntityRendererRegistry.register(DEER_ENTITY, DeerRender::new);
        EntityRendererRegistry.register(DOE_ENTITY, DoeRender::new);
        EntityRendererRegistry.register(CARIBOU_ENTITY, CaribouRender::new);
        EntityRendererRegistry.register(ROE_DEER_ENTITY, RoeDeerRender::new);
        EntityRendererRegistry.register(DUCK_ENTITY, DuckRender::new);
        EntityRendererRegistry.register(DRAKE_ENTITY, DrakeRender::new);
        EntityRendererRegistry.register(THROWN_DUCK_EGG, ThrownItemRenderer::new);
        EntityRendererRegistry.register(GOOSE_ENTITY, GooseRender::new);
        EntityRendererRegistry.register(THROWN_GOOSE_EGG, ThrownItemRenderer::new);
    }
}
