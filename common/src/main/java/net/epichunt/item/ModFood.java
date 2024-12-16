package net.epichunt.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties VENISON = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.4f)
            .build();

    public static final FoodProperties COOKED_VENISON = new FoodProperties.Builder().meat().nutrition(8).saturationMod(0.6f)
            .build();

    public static final FoodProperties DUCK = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_DUCK = new FoodProperties.Builder().meat().nutrition(7).saturationMod(0.6f)
            .build();

    public static final FoodProperties GOOSE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_GOOSE = new FoodProperties.Builder().meat().nutrition(7).saturationMod(0.6f)
            .build();

    public static final FoodProperties PHEASANT = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_PHEASANT = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.5f)
            .build();

    public static final FoodProperties QUAIL = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_QUAIL = new FoodProperties.Builder().meat().nutrition(3).saturationMod(0.3f)
            .build();
}

