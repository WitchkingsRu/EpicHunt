package net.epichunt.fabric.client;

import net.epichunt.EpicHuntClient;
import net.fabricmc.api.ClientModInitializer;

public final class EpicHuntFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EpicHuntClient.clientInit();
    }
}
