package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.CommonCraneModel;
import net.epichunt.entity.animals.CommonCraneEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CommonCraneRender extends MobRenderer<CommonCraneEntity, CommonCraneModel<CommonCraneEntity>> {
    private static final ResourceLocation COMMON_CRANE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/crane/crane.png");
    public CommonCraneRender(EntityRendererProvider.Context context) {
        super(context,new CommonCraneModel<>(context.bakeLayer(ModModels.COMMON_CRANE_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(CommonCraneEntity entity) {
        return COMMON_CRANE_LOCATION;
    }

    @Override
    public void render(CommonCraneEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        if(mob.isBaby()) {
            poseStack.scale(1.0f, 1.0f, 1.0f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}