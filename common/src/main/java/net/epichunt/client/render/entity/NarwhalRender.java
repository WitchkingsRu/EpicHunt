package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.NarwhalModel;
import net.epichunt.entity.animals.aquatic.NarwhalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class NarwhalRender extends MobRenderer<NarwhalEntity, NarwhalModel<NarwhalEntity>> {
    private static final ResourceLocation NARWHAL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/narwhal/narwhal.png");
    public NarwhalRender(EntityRendererProvider.Context context) {
        super(context,new NarwhalModel<>(context.bakeLayer(ModModels.NARWHAL_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(NarwhalEntity entity) {
        return NARWHAL_LOCATION;
    }

    @Override
    public void render(NarwhalEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(2.5f, 2.5f, 2.5f);
        if(mob.isBaby()) {
            poseStack.scale(1.0f, 1.0f, 1.0f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
