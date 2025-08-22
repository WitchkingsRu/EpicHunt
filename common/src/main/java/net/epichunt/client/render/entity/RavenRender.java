package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.RavenModel;
import net.epichunt.entity.animals.RavenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RavenRender extends MobRenderer<RavenEntity, RavenModel<RavenEntity>> {
    public static final ResourceLocation RAVEN_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/raven/raven_new.png");
    public RavenRender(EntityRendererProvider.Context context) {
        super(context,new RavenModel<>(context.bakeLayer(ModModels.RAVEN_LAYER_LOCATION)), 0.3f);
    }


    public ResourceLocation getTextureLocation(RavenEntity entity) {
        return RAVEN_LOCATION;
    }

    public static ResourceLocation getRavenTexture() {
        return RAVEN_LOCATION;
    }

    @Override
    public void render(RavenEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }

}