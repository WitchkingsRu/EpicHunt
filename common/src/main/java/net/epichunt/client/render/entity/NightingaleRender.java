package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.NightingaleModel;
import net.epichunt.entity.animals.NightingaleEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class NightingaleRender extends MobRenderer<NightingaleEntity, NightingaleModel<NightingaleEntity>> {
    public static final ResourceLocation NIGHTINGALE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/songbirds/nightingale.png");
    public NightingaleRender(EntityRendererProvider.Context context) {
        super(context,new NightingaleModel<>(context.bakeLayer(ModModels.NIGHTINGALE_LAYER_LOCATION)), 0.3f);
    }


    public ResourceLocation getTextureLocation(NightingaleEntity entity) {
        return NIGHTINGALE_LOCATION;
    }

    public static ResourceLocation getNightingaleTexture() {
        return NIGHTINGALE_LOCATION;
    }

    @Override
    public void render(NightingaleEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }

}