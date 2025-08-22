package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.BullfinchModel;
import net.epichunt.entity.animals.BullfinchEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BullfinchRender extends MobRenderer<BullfinchEntity, BullfinchModel<BullfinchEntity>> {
    public static final ResourceLocation BULLFINCH_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/songbirds/bullfinch.png");
    public BullfinchRender(EntityRendererProvider.Context context) {
        super(context,new BullfinchModel<>(context.bakeLayer(ModModels.BULLFINCH_LAYER_LOCATION)), 0.3f);
    }


    public ResourceLocation getTextureLocation(BullfinchEntity entity) {
        return BULLFINCH_LOCATION;
    }

    public static ResourceLocation getBullfinchTexture() {
        return BULLFINCH_LOCATION;
    }

    @Override
    public void render(BullfinchEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }

}