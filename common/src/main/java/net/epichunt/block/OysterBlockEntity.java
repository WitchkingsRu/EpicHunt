package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OysterBlockEntity extends BlockEntity {
    public OysterBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.OYSTER_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
