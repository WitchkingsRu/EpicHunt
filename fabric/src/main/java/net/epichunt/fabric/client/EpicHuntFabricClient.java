package net.epichunt.fabric.client;

import net.epichunt.EpicHuntClient;
import net.epichunt.client.ModModels;
import net.epichunt.item.ModItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.world.item.alchemy.PotionUtils;

public final class EpicHuntFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EpicHuntClient.clientInit();
        EpicHuntClient.clientSetup();
        ModModels.renderRegistry();
        ModModels.registerLayers((modelLayerLocation, layerDefinitionSupplier) -> EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get));
        ModModels.blockEntityRender();
        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex == 0 ? PotionUtils.getColor(stack) : -1,
                ModItem.FILLED_HORN_FLASK.get()
        );
    }
}
