package net.epichunt.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class HornFlaskItem extends Item {
    public HornFlaskItem(Item.Properties arg) {
        super(arg);
    }

    public InteractionResultHolder<ItemStack> use(Level arg, Player arg2, InteractionHand arg3) {
        List<AreaEffectCloud> list = arg.getEntitiesOfClass(AreaEffectCloud.class, arg2.getBoundingBox().inflate(2.0), (argx) -> {
            return argx != null && argx.isAlive() && argx.getOwner() instanceof EnderDragon;
        });
        ItemStack itemStack = arg2.getItemInHand(arg3);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(arg, arg2, ClipContext.Fluid.SOURCE_ONLY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        } else {
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (!arg.mayInteract(arg2, blockPos)) {
                    return InteractionResultHolder.pass(itemStack);
                }

                if (arg.getFluidState(blockPos).is(FluidTags.WATER)) {
                    arg.playSound(arg2, arg2.getX(), arg2.getY(), arg2.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    arg.gameEvent(arg2, GameEvent.FLUID_PICKUP, blockPos);
                    return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemStack, arg2, PotionUtils.setPotion(new ItemStack(ModItem.FILLED_HORN_FLASK.get()), Potions.WATER)), arg.isClientSide());
                }
            }

            return InteractionResultHolder.pass(itemStack);
        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack arg, Player arg2, ItemStack arg3) {
        arg2.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(arg, arg2, arg3);
    }
}
