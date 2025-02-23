package net.epichunt;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.epichunt.config.ConfigMain;
import net.epichunt.entity.MobSpawns;
import net.epichunt.event.AttributeRegisterEvents;
import net.epichunt.misc.CreativeTabs;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

import static net.epichunt.block.ModBlock.BLOCKS;
import static net.epichunt.entity.ModEntities.*;
import static net.epichunt.item.ModItem.ITEMS;
import static net.epichunt.misc.CreativeTabs.TABS;
import static net.epichunt.sound.Sounds.SOUNDS;

public class EpicHunt {
    public static final String MOD_ID = "epichunt";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigMain CONFIG;
    public static void init() {
        ENTITY_TYPES.register();
        BLOCKS.register();
        BLOCK_ENTITY_TYPES.register();
        SOUNDS.register();
        ITEMS.register();
        AutoConfig.register(ConfigMain.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ConfigMain.class).getConfig();
        LOGGER.info("Config loaded");
        MobSpawns.placementRegistry(CONFIG);
        TABS.register();
        CreativeTabs.init();
        AttributeRegisterEvents.init();
    }
    public static void spawnInit() {
        MobSpawns.spawnsInit(CONFIG);

    }

}

