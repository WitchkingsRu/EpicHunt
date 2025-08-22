package net.epichunt.config;

import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.epichunt.EpicHunt;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GiftManager {
    public static final ConfigMain CONFIG = EpicHunt.CONFIG;
    public record GiftItem(Item item, int minAmount, int maxAmount, double weight) {}

    private static final List<GiftItem> GIFTS = new ArrayList<>();
    private static double totalWeight = 0.0;

    public static void loadGifts() {
        GIFTS.clear();
        totalWeight = 0.0;

        for (String giftString : CONFIG.gift_list.gifts) {
            try {
                GiftItem gift = parseGiftString(giftString);
                if (gift != null) {
                    GIFTS.add(gift);
                    totalWeight += gift.weight();
                }
            } catch (Exception e) {
                EpicHunt.LOGGER.error("Gift parsing error: {}", giftString, e);
            }
        }
    }

    private static GiftItem parseGiftString(String giftString) {
        String[] parts = giftString.split(";");
        if (parts.length != 3) {
            EpicHunt.LOGGER.warn("Invalid gift: {}", giftString);
            return null;
        }

        try {
            String itemId = parts[0].trim();
            String amountRange = parts[1].trim();
            double weight = Double.parseDouble(parts[2].trim());

            // Парсим предмет
            Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(itemId));
            if (item == Items.AIR) {
                EpicHunt.LOGGER.warn("Предмет не найден: {}", itemId);
                return null;
            }

            // Парсим количество
            int minAmount, maxAmount;
            if (amountRange.contains("-")) {
                String[] amountParts = amountRange.split("-");
                minAmount = Integer.parseInt(amountParts[0].trim());
                maxAmount = Integer.parseInt(amountParts[1].trim());
            } else {
                minAmount = maxAmount = Integer.parseInt(amountRange.trim());
            }

            return new GiftItem(item, minAmount, maxAmount, weight);
        } catch (Exception e) {
            EpicHunt.LOGGER.error("Ошибка обработки подарка: {}", giftString, e);
            return null;
        }
    }

    @Nullable
    public static ItemStack getRandomGift(RandomSource random) {
        if (GIFTS.isEmpty() || totalWeight <= 0) {
            return null;
        }

        double randomValue = random.nextDouble() * totalWeight;
        double currentWeight = 0.0;

        for (GiftItem gift : GIFTS) {
            currentWeight += gift.weight();
            if (randomValue <= currentWeight) {
                int amount = gift.minAmount() == gift.maxAmount()
                        ? gift.minAmount()
                        : gift.minAmount() + random.nextInt(gift.maxAmount() - gift.minAmount() + 1);

                return new ItemStack(gift.item(), amount);
            }
        }

        return null;
    }

    public static List<GiftItem> getAvailableGifts() {
        return Collections.unmodifiableList(GIFTS);
    }
}
