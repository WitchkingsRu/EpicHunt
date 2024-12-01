package net.epichunt.entity.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.epichunt.EpicHunt;
import net.epichunt.entity.client.model.CaribouModel;
import net.epichunt.entity.client.model.DeerModel;
import net.epichunt.entity.client.model.DoeModel;
import net.epichunt.entity.client.model.RoeDeerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModels {
    public static final ModelLayerLocation DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "deer"), "main");
    public static final ModelLayerLocation DOE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "doe"), "main");
    public static final ModelLayerLocation CARIBOU_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "caribou"), "main");
    public static final ModelLayerLocation ROE_DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "roe_deer"), "main");

    public static void registerLayers() {
        EntityModelLayerRegistry.register(ModModels.DEER_LAYER_LOCATION, DeerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DOE_LAYER_LOCATION, DoeModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.CARIBOU_LAYER_LOCATION, CaribouModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.ROE_DEER_LAYER_LOCATION, RoeDeerModel::createBodyLayer);
    }

}
