package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ClamBlockEntity extends BlockEntity {
    public ClamBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CLAM_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
