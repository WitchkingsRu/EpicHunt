package net.epichunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MediumAntlersBlockEntity extends BlockEntity {
    public MediumAntlersBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.MEDIUM_ANTLERS_ENTITY.get(), blockPos, blockState);

    }
}
