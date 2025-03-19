package net.epichunt.block;

import com.mojang.logging.LogUtils;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;

public class ModBlockEntities {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(EpicHunt.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    public static final RegistrySupplier<BlockEntityType<SmallAntlersBlockEntity>> SMALL_ANTLERS_ENTITY = BLOCK_ENTITY_TYPES.register("small_antlers", () -> BlockEntityType.Builder.of(SmallAntlersBlockEntity::new, ModBlock.SMALL_ANTLERS.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<MediumAntlersBlockEntity>> MEDIUM_ANTLERS_ENTITY = BLOCK_ENTITY_TYPES.register("medium_antlers", () -> BlockEntityType.Builder.of(MediumAntlersBlockEntity::new, ModBlock.MEDIUM_ANTLERS.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<LargeAntlersBlockEntity>> LARGE_ANTLERS_ENTITY = BLOCK_ENTITY_TYPES.register("large_antlers", () -> BlockEntityType.Builder.of(LargeAntlersBlockEntity::new, ModBlock.LARGE_ANTLERS.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<MusselBlockEntity>> MUSSEL_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("mussel_block", () -> BlockEntityType.Builder.of(MusselBlockEntity::new, ModBlock.MUSSEL_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<OysterBlockEntity>> OYSTER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("oyster_block", () -> BlockEntityType.Builder.of(OysterBlockEntity::new, ModBlock.OYSTER_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<ClamBlockEntity>> CLAM_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("clam_block", () -> BlockEntityType.Builder.of(ClamBlockEntity::new, ModBlock.CLAM_BLOCK.get()).build(null));


    public static void init() {
        BLOCK_ENTITY_TYPES.register();
    }


}
