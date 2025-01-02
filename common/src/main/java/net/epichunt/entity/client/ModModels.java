package net.epichunt.entity.client;

import dev.architectury.platform.Mod;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.epichunt.EpicHunt;
import net.epichunt.entity.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModels {
    public static final ModelLayerLocation DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "deer"), "main");
    public static final ModelLayerLocation DOE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "doe"), "main");
    public static final ModelLayerLocation CARIBOU_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "caribou"), "main");
    public static final ModelLayerLocation ROE_DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "roe_deer"), "main");

    public static final ModelLayerLocation DUCK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "duck"), "main");
    public static final ModelLayerLocation DRAKE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "drake"), "main");
    public static final ModelLayerLocation GOOSE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "goose"), "main");
    public static final ModelLayerLocation PHEASANT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "pheasant"), "main");
    public static final ModelLayerLocation QUAIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "quail"), "main");

    public static final ModelLayerLocation YAK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "yak"), "main");
    public static final ModelLayerLocation YAK_CARPET_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "yak"), "carpet");

    public static final ModelLayerLocation HIGHLAND_COW_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "highland_cow"), "main");
    public static final ModelLayerLocation WISENT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "wisent"), "main");
    public static final ModelLayerLocation GOAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "goat"), "main");

    public static void registerLayers() {
        EntityModelLayerRegistry.register(ModModels.DEER_LAYER_LOCATION, DeerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DOE_LAYER_LOCATION, DoeModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.CARIBOU_LAYER_LOCATION, CaribouModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.ROE_DEER_LAYER_LOCATION, RoeDeerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DUCK_LAYER_LOCATION, DuckModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DRAKE_LAYER_LOCATION, DrakeModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.GOOSE_LAYER_LOCATION, GooseModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.PHEASANT_LAYER_LOCATION, PheasantModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.QUAIL_LAYER_LOCATION, QuailModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.YAK_LAYER_LOCATION, YakModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.YAK_CARPET_LAYER_LOCATION, YakModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.HIGHLAND_COW_LAYER_LOCATION, HighlandCowModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.WISENT_LAYER_LOCATION, WisentModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.GOAT_LAYER_LOCATION, GoatModel::createBodyLayer);
    }

}
