package net.epichunt.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.epichunt.EpicHunt;
import net.epichunt.EpicHuntClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EpicHunt.MOD_ID)
public class EpicHuntForge {
    public EpicHuntForge() {
        EventBuses.registerModEventBus(EpicHunt.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        EpicHunt.init();
        if (Platform.getEnv() == Dist.CLIENT)
            EpicHuntClient.clientInit();
        EpicHunt.spawnInit();
    }

}
