package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LargeAntlersBlockEntity extends BlockEntity {
    public LargeAntlersBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.LARGE_ANTLERS_ENTITY.get(), blockPos, blockState);
    }
}
