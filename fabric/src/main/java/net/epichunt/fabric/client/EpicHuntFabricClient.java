package net.epichunt.fabric.client;

import net.epichunt.EpicHuntClient;
import net.epichunt.client.ModModels;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public final class EpicHuntFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EpicHuntClient.clientInit();
        EpicHuntClient.clientSetup();
        ModModels.renderRegistry();
        ModModels.registerLayers((modelLayerLocation, layerDefinitionSupplier) -> EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get));
        ModModels.blockEntityRender();
    }
}
