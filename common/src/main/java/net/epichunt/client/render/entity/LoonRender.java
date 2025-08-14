package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.LoonModel;
import net.epichunt.entity.animals.LoonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class LoonRender extends MobRenderer<LoonEntity, LoonModel<LoonEntity>> {
    private static final ResourceLocation LOON_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/loon/loon.png");
    public LoonRender(EntityRendererProvider.Context context) {
        super(context,new LoonModel<>(context.bakeLayer(ModModels.LOON_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(LoonEntity entity) {
        return LOON_LOCATION;
    }

    @Override
    public void render(LoonEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.0f, 1.0f, 1.0f);
        if(mob.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}