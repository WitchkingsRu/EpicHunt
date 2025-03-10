package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.RoeDeerEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.RoeDeerModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RoeDeerRender extends MobRenderer<RoeDeerEntity, RoeDeerModel<RoeDeerEntity>> {
    private static final ResourceLocation ROE_DEER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/deer/roe_deer.png");
    public RoeDeerRender(EntityRendererProvider.Context context) {
        super(context,new RoeDeerModel<>(context.bakeLayer(ModModels.ROE_DEER_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(RoeDeerEntity entity) {
        return ROE_DEER_LOCATION;
    }

    @Override
    public void render(RoeDeerEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(0.65f, 0.65f, 0.65f);
        if(mob.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
