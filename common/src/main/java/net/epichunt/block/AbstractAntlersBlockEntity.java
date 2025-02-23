package net.epichunt.block;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.types.Type;
import net.epichunt.entity.animals.BadgerEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.google.gson.reflect.TypeToken;

import java.util.function.Supplier;

public class AbstractAntlersBlockEntity extends SkullBlockEntity {

    public AbstractAntlersBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

}
