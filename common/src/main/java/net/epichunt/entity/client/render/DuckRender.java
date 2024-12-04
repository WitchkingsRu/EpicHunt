package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.DuckModel;
import net.epichunt.entity.client.model.DeerModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class DuckRender extends MobRenderer<DuckEntity, DuckModel<DuckEntity>> {
    private static final ResourceLocation DUCK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/duck/duck.png");
    public DuckRender(EntityRendererProvider.Context context) {
        super(context,new DuckModel<>(context.bakeLayer(ModModels.DUCK_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(DuckEntity entity) {
        return DUCK_LOCATION;
    }

    @Override
    public void render(DuckEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}