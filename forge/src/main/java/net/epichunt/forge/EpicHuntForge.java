package net.epichunt.forge;

import dev.architectury.platform.forge.EventBuses;
import net.epichunt.EpicHunt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EpicHunt.MOD_ID)
public class EpicHuntForge {
    public EpicHuntForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(EpicHunt.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        EpicHunt.init();
        EpicHunt.Client.clientInit();
        EpicHunt.spawnInit();
    }
}
