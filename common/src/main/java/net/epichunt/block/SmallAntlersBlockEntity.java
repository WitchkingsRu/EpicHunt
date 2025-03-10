package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SmallAntlersBlockEntity extends BlockEntity {
    public SmallAntlersBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SMALL_ANTLERS_ENTITY.get(), blockPos, blockState);
    }
}
