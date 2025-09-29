package net.epichunt.mixin;

import net.epichunt.item.ModItem;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(Cat.class)
public class CatMixin {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/Ingredient;of([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;"
            )
    )
    private static Ingredient redirectTemptIngredient(ItemLike[] items) {
        List<ItemLike> newItems = new ArrayList<>(Arrays.asList(items));
        newItems.add(Items.COD);
        newItems.add(Items.SALMON);
        newItems.add(ModItem.BASS.get());
        newItems.add(ModItem.EEL.get());
        newItems.add(ModItem.STURGEON.get());
        newItems.add(ModItem.PIKEFISH.get());
        newItems.add(ModItem.POLLOCK.get());
        newItems.add(ModItem.HERRING.get());
        newItems.add(ModItem.TROUT.get());
        newItems.add(ModItem.CARP.get());
        newItems.add(ModItem.MACKEREL.get());
        newItems.add(ModItem.SARDINE.get());
        newItems.add(ModItem.PERCHES.get());
        newItems.add(ModItem.ZANDER.get());
        newItems.add(ModItem.ROACH.get());
        newItems.add(ModItem.HALIBUT.get());
        newItems.add(ModItem.CATFISH.get());
        newItems.add(ModItem.SWORDFISH.get());
        return Ingredient.of(newItems.toArray(new ItemLike[0]));
    }
}
