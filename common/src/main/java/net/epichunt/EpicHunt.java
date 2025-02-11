package net.epichunt;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Mod;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.epichunt.entity.MobSpawns;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.client.render.DeerRender;
import net.epichunt.event.AttributeRegisterEvents;
import net.epichunt.misc.CreativeTabs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.epichunt.entity.ModEntities.*;
import static net.epichunt.entity.client.ModModels.registerLayers;
import static net.epichunt.item.ModItem.ITEMS;
import static net.epichunt.misc.CreativeTabs.TABS;
import static net.epichunt.sound.Sounds.SOUNDS;

public class EpicHunt {
    public static final String MOD_ID = "epichunt";
    public static void init() {
        ENTITY_TYPES.register();
        SOUNDS.register();
        ITEMS.register();
        MobSpawns.placementRegistry();
        TABS.register();
        CreativeTabs.init();
        AttributeRegisterEvents.init();
        //System.out.println(ExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
    public static void spawnInit() {
        MobSpawns.spawnsInit();

    }

}

