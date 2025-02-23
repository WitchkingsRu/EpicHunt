package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.CarpEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.CarpModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CarpRender extends MobRenderer<CarpEntity, CarpModel<CarpEntity>> {
    private static final ResourceLocation CARP_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/carp/carp.png");
    public CarpRender(EntityRendererProvider.Context context) {
        super(context,new CarpModel<>(context.bakeLayer(ModModels.CARP_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(CarpEntity entity) {
        return CARP_LOCATION;
    }

    @Override
    public void render(CarpEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
