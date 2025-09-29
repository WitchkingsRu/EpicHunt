package net.epichunt.fabric.entity;

import net.epichunt.item.ModItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CatFoodHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("EpicHunt");
    private static boolean modified = false;

    public static void initialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            if (!modified) {
                modifyCatTemptIngredient();
                modified = true;
            }
        });
    }

    private static void modifyCatTemptIngredient() {
        try {
            LOGGER.info("Modifying Cat TEMPT_INGREDIENT on Fabric...");

            Field temptField = Cat.class.getDeclaredField("TEMPT_INGREDIENT");

            Unsafe unsafe = getUnsafe();
            long offset = unsafe.staticFieldOffset(temptField);
            Object base = unsafe.staticFieldBase(temptField);

            List<ItemLike> items = createItemList();
            Ingredient newIngredient = Ingredient.of(items.toArray(new ItemLike[0]));

            unsafe.putObject(base, offset, newIngredient);

            LOGGER.info("Successfully modified Cat TEMPT_INGREDIENT with {} items on Fabric", items.size());

            Field checkField = Cat.class.getDeclaredField("TEMPT_INGREDIENT");
            checkField.setAccessible(true);
            Ingredient current = (Ingredient) checkField.get(null);
            LOGGER.info("Verification - TEMPT_INGREDIENT now has {} items", current.getItems().length);

        } catch (Exception e) {
            LOGGER.error("Failed to modify Cat TEMPT_INGREDIENT on Fabric", e);
        }
    }

    private static Unsafe getUnsafe() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        return (Unsafe) unsafeField.get(null);
    }

    private static List<ItemLike> createItemList() {
        List<ItemLike> items = new ArrayList<>();
        items.add(Items.COD);
        items.add(Items.SALMON);

        // Ваши предметы
        items.add(ModItem.BASS.get());
        items.add(ModItem.EEL.get());
        items.add(ModItem.STURGEON.get());
        items.add(ModItem.PIKEFISH.get());
        items.add(ModItem.POLLOCK.get());
        items.add(ModItem.HERRING.get());
        items.add(ModItem.TROUT.get());
        items.add(ModItem.CARP.get());
        items.add(ModItem.MACKEREL.get());
        items.add(ModItem.SARDINE.get());
        items.add(ModItem.PERCHES.get());
        items.add(ModItem.ZANDER.get());
        items.add(ModItem.ROACH.get());
        items.add(ModItem.HALIBUT.get());
        items.add(ModItem.CATFISH.get());
        items.add(ModItem.SWORDFISH.get());

        return items;
    }
}