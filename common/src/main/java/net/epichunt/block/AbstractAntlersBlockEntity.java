package net.epichunt.block;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.types.Type;
import com.mojang.logging.LogUtils;
import net.epichunt.entity.animals.BadgerEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.epichunt.EpicHunt;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class AbstractAntlersBlockEntity extends BlockEntity {
    private static final Logger LOGGER = LogUtils.getLogger();
    public AbstractAntlersBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SMALL_ANTLERS_ENTITY.get(), blockPos, blockState);
        LOGGER.info("AbstractAntlersBlockEntity created at {}", blockPos);
    }

}
