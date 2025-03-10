package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.QuailEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.QuailModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class QuailRender extends MobRenderer<QuailEntity, QuailModel<QuailEntity>> {
    private static final ResourceLocation QUAIL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/quail/quail.png");
    public QuailRender(EntityRendererProvider.Context context) {
    super(context,new QuailModel<>(context.bakeLayer(ModModels.QUAIL_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(QuailEntity entity) {
        return QUAIL_LOCATION;
    }

    @Override
    public void render(QuailEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}