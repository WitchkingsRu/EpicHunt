package net.epichunt.block;

import com.llamalad7.mixinextras.utils.MixinExtrasLogger;
import com.mojang.math.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class AbstractAntlersBlock extends Block implements EntityBlock {
    public static final IntegerProperty ROTATION;
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    static final AABB COLLISION_AABB = new AABB(0.0 / 16.0, 0.0, 7.5 / 16.0, 1.0, 1.0, 8.5 / 16.0);
    static final Vector3d CENTER = new Vector3d(0.5, 0.5, 0.5);
    static final Vector3d BOXMIN = new Vector3d(0.0, 0.0, 0.0);
    static final Vector3d BOXMAX = new Vector3d(1.0, 1.0, 1.0);
    private static final Logger LOGGER = LogManager.getLogger("EpicHunt");
    public AbstractAntlersBlock(Properties properties) {
        super(properties);

    }

    public boolean hasBlockEntity(BlockState state) {
        return true;
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos arg, BlockState arg2) {
        return new AbstractAntlersBlockEntity(arg, arg2);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        return this.defaultBlockState().setValue(ROTATION, RotationSegment.convertToSegment(blockPlaceContext.getRotation()));
    }
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), 16));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), 16));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Override
    public VoxelShape getShape(BlockState blockstate, BlockGetter blockgetter, BlockPos blockpos, CollisionContext context)
    {
        AABB aabb = COLLISION_AABB;

        float yrot = -RotationSegment.convertToDegrees(blockstate.getValue(BannerBlock.ROTATION));
        aabb = rotateAABB(aabb, Axis.YP.rotationDegrees(yrot));

        return Shapes.create(aabb);
    }

    public static AABB rotateAABB(AABB axisAlignedBB, Quaternionf quaternion)
    {
        // Extract the minimum and maximum coordinates of the AABB into vectors
        final Vector3d mincoords = new Vector3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        final Vector3d maxcoords = new Vector3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);

        mincoords.sub(CENTER);
        maxcoords.sub(CENTER);

        // Rotate the vectors in-place
        quaternion.transform(mincoords);
        quaternion.transform(maxcoords);

        mincoords.add(CENTER).max(BOXMIN);
        maxcoords.add(CENTER).min(BOXMAX);

        // Return an AABB with the new coordinates
        return new AABB(mincoords.x(), mincoords.y(), mincoords.z(), maxcoords.x(), maxcoords.y(), maxcoords.z());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity entity = world.getBlockEntity(pos);
            LOGGER.info("BlockEntity at {}: {}", pos, entity);
        }
        return InteractionResult.SUCCESS;
    }
    static {
        ROTATION = BlockStateProperties.ROTATION_16;
    }
}
