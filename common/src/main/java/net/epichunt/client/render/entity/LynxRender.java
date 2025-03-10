package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.LynxEntity;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class LynxRender extends MobRenderer<LynxEntity, OcelotModel<LynxEntity>> {
    private static final ResourceLocation LYNX_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/lynx/lynx.png");
    public LynxRender(EntityRendererProvider.Context context) {
        super(context,new OcelotModel<>(context.bakeLayer(ModModels.LYNX_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(LynxEntity entity) {
        return LYNX_LOCATION;
    }

    @Override
    public void render(LynxEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
