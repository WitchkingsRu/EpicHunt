package net.epichunt.block;

import com.llamalad7.mixinextras.utils.MixinExtrasLogger;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractAntlersBlock extends SkullBlock {
    private static final Logger LOGGER = LogManager.getLogger("EpicHunt");
    private final SkullBlock.Type type;
    public AbstractAntlersBlock(Type type, Properties properties) {
        super(type, properties);
        this.type = type;
    }
    public BlockEntity newBlockEntity(BlockPos arg, BlockState arg2) {
        return new AbstractAntlersBlockEntity(arg, arg2);
    }
    public SkullBlock.Type getType() {
        return this.type;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity entity = world.getBlockEntity(pos);
            LOGGER.info("BlockEntity at {}: {}", pos, entity);
        }
        return InteractionResult.SUCCESS;
    }

    public static enum Types implements Type {
        ROE_DEER;

        private Types() {
        }
    }
}
