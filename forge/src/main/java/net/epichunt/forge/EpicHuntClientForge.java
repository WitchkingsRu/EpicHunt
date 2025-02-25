package net.epichunt.forge;

import net.epichunt.EpicHuntClient;
import net.epichunt.client.ModModels;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class EpicHuntClientForge {

    static void init(IEventBus bus) {
        EpicHuntClient.clientInit();
        bus.addListener(EpicHuntClientForge::setup);
        bus.addListener(EpicHuntClientForge::renderers);
        bus.addListener(EpicHuntClientForge::modelLayers);
    }

    static void setup(FMLClientSetupEvent event) {
        EpicHuntClient.clientSetup();
    }

    static void modelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModels.registerLayers(event::registerLayerDefinition);
    }

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        ModModels.renderRegistry();
        ModModels.blockEntityRender();
    }
}
