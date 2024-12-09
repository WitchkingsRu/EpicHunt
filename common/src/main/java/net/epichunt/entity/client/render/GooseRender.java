package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.GooseModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class GooseRender extends MobRenderer<GooseEntity, GooseModel<GooseEntity>> {
    private static final ResourceLocation GOOSE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/goose/goose.png");
    public GooseRender(EntityRendererProvider.Context context) {
        super(context,new GooseModel<>(context.bakeLayer(ModModels.GOOSE_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(GooseEntity entity) {
        return GOOSE_LOCATION;
    }

    @Override
    public void render(GooseEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}