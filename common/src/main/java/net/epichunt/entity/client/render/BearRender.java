package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BearEntity;
import net.epichunt.entity.animals.LynxEntity;
import net.epichunt.entity.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BearRender extends MobRenderer<BearEntity, PolarBearModel<BearEntity>> {
    private static final ResourceLocation BEAR_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/bear/bear.png");
    public BearRender(EntityRendererProvider.Context context) {
        super(context,new PolarBearModel<>(context.bakeLayer(ModModels.BEAR_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(BearEntity entity) {
        return BEAR_LOCATION;
    }

    @Override
    public void render(BearEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.4f, 1.4f, 1.4f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
