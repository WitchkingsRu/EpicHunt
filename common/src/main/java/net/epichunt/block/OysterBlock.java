package net.epichunt.block;

import net.epichunt.item.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class OysterBlock extends CropBlock implements SimpleWaterloggedBlock, EntityBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE;
    private static final VoxelShape[] SHAPE_BY_AGE;
    public static final BooleanProperty WATERLOGGED;

    public OysterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 1)).setValue(WATERLOGGED, true));

    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        boolean isWater = level.getBlockState(pos).getFluidState().is(Fluids.WATER);
        if (!isWater) {
            return null;
        }

        return this.defaultBlockState().setValue(WATERLOGGED, true);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OysterBlockEntity(pos, state);
    }

    public static boolean isDead(BlockState blockState) {
        return !(Boolean)blockState.getValue(WATERLOGGED);
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.below();
        return this.mayPlaceOn(levelReader.getBlockState(blockPos2), levelReader, blockPos2);
    }

    protected ItemLike getBaseSeedId() {
        return ModItem.OYSTER.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(Blocks.DIRT) || blockState.is(Blocks.SAND) || blockState.is(Blocks.GRAVEL);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.getRawBrightness(blockPos, 0) >= 1) {
            int age = this.getAge(blockState);
            if (age < this.getMaxAge()) {
                float growthSpeed = getGrowthSpeed(this, serverLevel, blockPos);
                if (randomSource.nextInt((int) (25.0F / growthSpeed) + 1) == 0) {
                    serverLevel.setBlock(blockPos, this.getStateForAge(age + 1).setValue(WATERLOGGED, true), 2);
                }
            }
        }
    }

    @Override
    public void growCrops(Level level, BlockPos blockPos, BlockState blockState) {
        if (!blockState.getValue(WATERLOGGED)) {
            return;
        }
        int newAge = this.getAge(blockState) + this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (newAge > maxAge) {
            newAge = maxAge;
        }
        level.setBlock(blockPos, this.getStateForAge(newAge).setValue(WATERLOGGED, true), 2);
    }


    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 2, 5);
    }

    protected static float getGrowthSpeed(Block block, BlockGetter blockGetter, BlockPos blockPos) {
        float growthFactor = 1.0F;

        for (Direction direction : Direction.values()) {
            if (blockGetter.getBlockState(blockPos.relative(direction)).getFluidState().is(Fluids.WATER)) {
                growthFactor += 0.5F;
            }
        }

        return growthFactor;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE, WATERLOGGED});
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        AGE = BlockStateProperties.AGE_7;
        SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    }
}
