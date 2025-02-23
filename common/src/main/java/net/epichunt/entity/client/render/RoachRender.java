package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.RoachEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.RoachModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RoachRender extends MobRenderer<RoachEntity, RoachModel<RoachEntity>> {
    private static final ResourceLocation ROACH_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/roach/roach.png");
    public RoachRender(EntityRendererProvider.Context context) {
        super(context,new RoachModel<>(context.bakeLayer(ModModels.ROACH_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(RoachEntity entity) {
        return ROACH_LOCATION;
    }

    @Override
    public void render(RoachEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
