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
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.client.render.*;
import net.epichunt.item.ThrownDuckEgg;
import net.epichunt.item.ThrownGooseEgg;
import net.epichunt.item.ThrownPheasantEgg;
import net.epichunt.item.ThrownQuailEgg;
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
    public static final RegistrySupplier<EntityType<PheasantEntity>> PHEASANT_ENTITY = ENTITY_TYPES.register("pheasant", PheasantEntity.PHEASANT);
    public static final RegistrySupplier<EntityType<QuailEntity>> QUAIL_ENTITY = ENTITY_TYPES.register("quail", QuailEntity.QUAIL);

    public static final RegistrySupplier<EntityType<ThrownDuckEgg>> THROWN_DUCK_EGG = ENTITY_TYPES.register("thrown_duck_egg", ThrownDuckEgg.THROWN_DUCK_EGG);
    public static final RegistrySupplier<EntityType<ThrownGooseEgg>> THROWN_GOOSE_EGG = ENTITY_TYPES.register("thrown_goose_egg", ThrownGooseEgg.THROWN_GOOSE_EGG);
    public static final RegistrySupplier<EntityType<ThrownPheasantEgg>> THROWN_PHEASANT_EGG = ENTITY_TYPES.register("thrown_pheasant_egg", ThrownPheasantEgg.THROWN_PHEASANT_EGG);
    public static final RegistrySupplier<EntityType<ThrownQuailEgg>> THROWN_QUAIL_EGG = ENTITY_TYPES.register("thrown_quail_egg", ThrownQuailEgg.THROWN_QUAIL_EGG);

    public static final RegistrySupplier<EntityType<YakEntity>> YAK_ENTITY = ENTITY_TYPES.register("yak", YakEntity.YAK);
    public static final RegistrySupplier<EntityType<HighlandCowEntity>> HIGHLAND_COW_ENTITY = ENTITY_TYPES.register("highland_cow", HighlandCowEntity.HIGHLAND_COW);
    public static final RegistrySupplier<EntityType<WisentEntity>> WISENT_ENTITY = ENTITY_TYPES.register("wisent", WisentEntity.WISENT);
    public static final RegistrySupplier<EntityType<GoatEntity>> GOAT_ENTITY = ENTITY_TYPES.register("goat", GoatEntity.GOAT);

    public static final RegistrySupplier<EntityType<MooseEntity>> MOOSE_ENTITY = ENTITY_TYPES.register("moose", MooseEntity.MOOSE);
    public static final RegistrySupplier<EntityType<BadgerEntity>> BADGER_ENTITY = ENTITY_TYPES.register("badger", BadgerEntity.BADGER);
    public static final RegistrySupplier<EntityType<BoarEntity>> BOAR_ENTITY = ENTITY_TYPES.register("boar", BoarEntity.BOAR);

    public static final RegistrySupplier<EntityType<LynxEntity>> LYNX_ENTITY = ENTITY_TYPES.register("lynx", LynxEntity.LYNX);
    public static final RegistrySupplier<EntityType<BearEntity>> BEAR_ENTITY = ENTITY_TYPES.register("bear", BearEntity.BEAR);
    public static final RegistrySupplier<EntityType<WolfEntity>> WOLF_ENTITY = ENTITY_TYPES.register("wolf", WolfEntity.WOLF);

    public static final RegistrySupplier<EntityType<BassEntity>> BASS_ENTITY = ENTITY_TYPES.register("bass", BassEntity.BASS);

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
        EntityRendererRegistry.register(PHEASANT_ENTITY, PheasantRender::new);
        EntityRendererRegistry.register(THROWN_PHEASANT_EGG, ThrownItemRenderer::new);
        EntityRendererRegistry.register(QUAIL_ENTITY, QuailRender::new);
        EntityRendererRegistry.register(THROWN_QUAIL_EGG, ThrownItemRenderer::new);
        EntityRendererRegistry.register(YAK_ENTITY, YakRender::new);
        EntityRendererRegistry.register(HIGHLAND_COW_ENTITY, HighlandCowRender::new);
        EntityRendererRegistry.register(WISENT_ENTITY, WisentRender::new);
        EntityRendererRegistry.register(GOAT_ENTITY, GoatRender::new);
        EntityRendererRegistry.register(MOOSE_ENTITY, MooseRender::new);
        EntityRendererRegistry.register(BADGER_ENTITY, BadgerRender::new);
        EntityRendererRegistry.register(BOAR_ENTITY, BoarRender::new);
        EntityRendererRegistry.register(LYNX_ENTITY, LynxRender::new);
        EntityRendererRegistry.register(BEAR_ENTITY, BearRender::new);
        EntityRendererRegistry.register(WOLF_ENTITY, WolfRender::new);

        EntityRendererRegistry.register(BASS_ENTITY, BassRender::new);
    }
}
