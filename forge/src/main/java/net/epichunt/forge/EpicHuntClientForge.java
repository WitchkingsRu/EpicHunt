package net.epichunt.forge;

import net.epichunt.EpicHuntClient;
import net.epichunt.client.ModModels;
import net.epichunt.item.ModItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class EpicHuntClientForge {

    static void init(IEventBus bus) {
        EpicHuntClient.clientInit();
        bus.addListener(EpicHuntClientForge::setup);
        bus.addListener(EpicHuntClientForge::renderers);
        bus.addListener(EpicHuntClientForge::modelLayers);
        bus.addListener(EpicHuntClientForge::registerItemColors);
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
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) ->
                        tintIndex == 0 ? PotionUtils.getColor(stack) : -1,
                ModItem.FILLED_HORN_FLASK.get()
        );
    }
}
