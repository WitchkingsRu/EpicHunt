package net.epichunt.item;

import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties VENISON = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.4f)
            .build();

    public static final FoodProperties COOKED_VENISON = new FoodProperties.Builder().meat().nutrition(8).saturationMod(0.6f)
            .build();
}
