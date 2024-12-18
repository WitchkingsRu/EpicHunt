package net.epichunt.item;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class YakCarpetItem extends Item {
    private static final String TEX_FOLDER = "epichunt:textures/entity/yak/";
    private final int protection;
    private final String texture;

    public YakCarpetItem(int i, String string, Item.Properties properties) {
        super(properties);
        this.protection = i;
        this.texture = "epichunt:textures/entity/yak/carpet/" + string + ".png";
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation(this.texture);
    }

    public int getProtection() {
        return this.protection;
    }
}
