package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.SturgeonModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SturgeonRender extends MobRenderer<SturgeonEntity, SturgeonModel<SturgeonEntity>> {
    private static final ResourceLocation STURGEON_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/sturgeon/sturgeon.png");
    public SturgeonRender(EntityRendererProvider.Context context) {
        super(context,new SturgeonModel<>(context.bakeLayer(ModModels.STURGEON_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SturgeonEntity entity) {
        return STURGEON_LOCATION;
    }

    @Override
    public void render(SturgeonEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
