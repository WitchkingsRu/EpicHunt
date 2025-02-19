package net.epichunt.entity;

import java.util.EnumSet;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;


public class EatWoodGoal extends Goal {
    private static final int EAT_ANIMATION_TICKS = 40;
    private final Mob mob;
    private final Level level;
    private int eatAnimationTick;
    private BlockPos targetLogPos;

    public EatWoodGoal(Mob mob) {
        this.mob = mob;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 500) != 0) {
            return false;
        }

        targetLogPos = findNearbyLog(this.mob.blockPosition(), 1);
        return targetLogPos != null;
    }

    @Override
    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(EAT_ANIMATION_TICKS);
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
        this.targetLogPos = null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0 && targetLogPos != null;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);

        if (this.eatAnimationTick == this.adjustedTickDelay(4) && targetLogPos != null) {
            BlockState logState = this.level.getBlockState(targetLogPos);

            if (isLog(logState) && this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                BlockState strippedState = getStrippedLog(logState.getBlock());
                if (strippedState != null) {
                    this.level.levelEvent(2001, targetLogPos, Block.getId(logState));
                    this.level.setBlock(targetLogPos, strippedState, 2);
                    this.mob.playSound(SoundEvents.AXE_STRIP);
                }
                this.mob.ate();
            }
        }
    }
    private BlockPos findNearbyLog(BlockPos center, int radius) {
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radius, -1, -radius), center.offset(radius, 2, radius))) {
            if (isLog(this.level.getBlockState(pos))) {
                return pos.immutable();
            }
        }
        return null;
    }
    private boolean isLog(BlockState state) {
        return state.is(net.minecraft.tags.BlockTags.LOGS);
    }
    private static final Map<Block, Block> STRIPPED_LOGS = Map.of(
            Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG,
            Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG,
            Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG,
            Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG,
            Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG,
            Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG
    );
    private BlockState getStrippedLog(Block block) {
        return STRIPPED_LOGS.getOrDefault(block, null) != null ? STRIPPED_LOGS.get(block).defaultBlockState() : null;
    }

}
