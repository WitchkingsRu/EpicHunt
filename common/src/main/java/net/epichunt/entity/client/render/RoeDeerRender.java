package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.RoeDeerEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.DeerModel;
import net.epichunt.entity.client.model.RoeDeerModel;
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
        poseStack.scale(0.75f, 0.75f, 0.75f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
