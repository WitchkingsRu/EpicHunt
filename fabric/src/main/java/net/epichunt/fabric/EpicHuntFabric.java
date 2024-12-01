package net.epichunt.fabric;

import net.epichunt.EpicHunt;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import static com.mojang.text2speech.Narrator.LOGGER;

public class EpicHuntFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        EpicHunt.init();
        EpicHunt.Client.clientInit();
        EpicHunt.spawnInit();

    }

}
