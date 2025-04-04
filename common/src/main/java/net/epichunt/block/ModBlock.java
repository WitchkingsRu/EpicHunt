package net.epichunt.block;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BadgerEntity;
import net.epichunt.entity.animals.fish.ZanderEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.datafix.fixes.References;

import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(EpicHunt.MOD_ID, Registries.BLOCK);
    public static final RegistrySupplier<Block> SMALL_ANTLERS = BLOCKS.register("small_antlers", () ->
            new SmallAntlersBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> MEDIUM_ANTLERS = BLOCKS.register("medium_antlers", () ->
            new MediumAntlersBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> LARGE_ANTLERS = BLOCKS.register("large_antlers", () ->
            new LargeAntlersBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> MUSSEL_BLOCK = BLOCKS.register("mussel_block", () ->
            new MusselBlock(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> OYSTER_BLOCK = BLOCKS.register("oyster_block", () ->
            new OysterBlock(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> CLAM_BLOCK = BLOCKS.register("clam_block", () ->
            new ClamBlock(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).pushReaction(PushReaction.DESTROY)));


}
