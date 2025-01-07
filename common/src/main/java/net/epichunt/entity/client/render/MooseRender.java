package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.MooseEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.CaribouModel;
import net.epichunt.entity.client.model.MooseModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class MooseRender extends MobRenderer<MooseEntity, MooseModel<MooseEntity>> {
    private static final ResourceLocation MOOSE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/moose/moose.png");
    public MooseRender(EntityRendererProvider.Context context) {
        super(context,new MooseModel<>(context.bakeLayer(ModModels.MOOSE_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(MooseEntity entity) {
        return MOOSE_LOCATION;
    }

    @Override
    public void render(MooseEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(1f, 1f, 1f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
