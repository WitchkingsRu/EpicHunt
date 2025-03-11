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

    public static final FoodProperties WOLF = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_WOLF = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.3f)
            .build();

    public static final FoodProperties BEAR = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.POISON, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_BEAR = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.3f)
            .build();

    public static final FoodProperties BADGER = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_BADGER = new FoodProperties.Builder().meat().nutrition(4).saturationMod(0.4f)
            .build();

    public static final FoodProperties BEAVER = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_BEAVER = new FoodProperties.Builder().meat().nutrition(4).saturationMod(0.4f)
            .build();

    public static final FoodProperties EQUINE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_EQUINE = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.8f)
            .build();

    public static final FoodProperties LLAMA = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_LLAMA = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.8f)
            .build();

    public static final FoodProperties CAMEL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_CAMEL = new FoodProperties.Builder().meat().nutrition(6).saturationMod(0.8f)
            .build();


    public static final FoodProperties BASS = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_BASS = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties CARP = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_CARP = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties HALIBUT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_HALIBUT = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties HERRING = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).meat().build();

    public static final FoodProperties COOKED_HERRING = new FoodProperties.Builder().meat().nutrition(2).saturationMod(0.3f)
            .build();

    public static final FoodProperties MACKEREL = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).meat().build();

    public static final FoodProperties COOKED_MACKEREL = new FoodProperties.Builder().meat().nutrition(2).saturationMod(0.3f)
            .build();

    public static final FoodProperties PERCHES = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_PERCHES = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties PIKEFISH = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_PIKEFISH = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties POLLOCK = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_POLLOCK = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties ROACH = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_ROACH = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties SARDINE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).meat().build();

    public static final FoodProperties COOKED_SARDINE = new FoodProperties.Builder().meat().nutrition(2).saturationMod(0.3f)
            .build();

    public static final FoodProperties TROUT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_TROUT = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties ZANDER = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_ZANDER = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties STURGEON = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.4F).fast().meat().build();

    public static final FoodProperties COOKED_STURGEON = new FoodProperties.Builder().fast().meat().nutrition(2).saturationMod(0.7f)
            .build();

    public static final FoodProperties CATFISH = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.4F).fast().meat().build();

    public static final FoodProperties COOKED_CATFISH = new FoodProperties.Builder().fast().meat().nutrition(2).saturationMod(0.7f)
            .build();

    public static final FoodProperties EEL = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_EEL = new FoodProperties.Builder().meat().nutrition(5).saturationMod(0.5f)
            .build();

    public static final FoodProperties CALAMARI = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_CALAMARI = new FoodProperties.Builder().meat().nutrition(4).saturationMod(0.6f)
            .build();

}

