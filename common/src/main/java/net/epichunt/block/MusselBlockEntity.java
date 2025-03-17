package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MusselBlockEntity extends BlockEntity {
    public MusselBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.MUSSEL_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
