package net.epichunt.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Iterator;
import java.util.List;


public class HornFlaskPotionItem extends PotionItem {
    private static final int DRINK_DURATION = 32;

    public HornFlaskPotionItem(Item.Properties arg) {
        super(arg);
    }

    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.WATER);
    }

    public ItemStack finishUsingItem(ItemStack arg, Level arg2, LivingEntity arg3) {
        Player player = arg3 instanceof Player ? (Player)arg3 : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, arg);
        }

        if (!arg2.isClientSide) {
            List<MobEffectInstance> list = PotionUtils.getMobEffects(arg);
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                MobEffectInstance mobEffectInstance = (MobEffectInstance)var6.next();
                if (mobEffectInstance.getEffect().isInstantenous()) {
                    mobEffectInstance.getEffect().applyInstantenousEffect(player, player, arg3, mobEffectInstance.getAmplifier(), 1.0);
                } else {
                    arg3.addEffect(new MobEffectInstance(mobEffectInstance));
                }
            }
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                arg.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (arg.isEmpty()) {
                return new ItemStack(ModItem.HORN_FLASK.get());
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(ModItem.HORN_FLASK.get()));
            }
        }

        arg3.gameEvent(GameEvent.DRINK);
        return arg;
    }


    public InteractionResult useOn(UseOnContext arg) {
        Level level = arg.getLevel();
        BlockPos blockPos = arg.getClickedPos();
        Player player = arg.getPlayer();
        ItemStack itemStack = arg.getItemInHand();
        BlockState blockState = level.getBlockState(blockPos);
        if (arg.getClickedFace() != Direction.DOWN && blockState.is(BlockTags.CONVERTABLE_TO_MUD) && PotionUtils.getPotion(itemStack) == Potions.WATER) {
            level.playSound((Player)null, blockPos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
            player.setItemInHand(arg.getHand(), ItemUtils.createFilledResult(itemStack, player, new ItemStack(ModItem.HORN_FLASK.get())));
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!level.isClientSide) {
                ServerLevel serverLevel = (ServerLevel)level;

                for(int i = 0; i < 5; ++i) {
                    serverLevel.sendParticles(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), (double)(blockPos.getY() + 1), (double)blockPos.getZ() + level.random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
                }
            }

            level.playSound((Player)null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockPos);
            level.setBlockAndUpdate(blockPos, Blocks.MUD.defaultBlockState());
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public int getUseDuration(ItemStack arg) {
        return 32;
    }

    public UseAnim getUseAnimation(ItemStack arg) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level arg, Player arg2, InteractionHand arg3) {
        return ItemUtils.startUsingInstantly(arg, arg2, arg3);
    }

    public String getDescriptionId(ItemStack arg) {
        return PotionUtils.getPotion(arg).getName(this.getDescriptionId() + ".effect.");
    }

    public void appendHoverText(ItemStack arg, Level arg2, List<Component> list, TooltipFlag arg3) {
        PotionUtils.addPotionTooltip(arg, list, 1.0F);
        PotionUtils.getColor(arg);
    }

}
