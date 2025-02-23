package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.HerringEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.HerringModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HerringRender extends MobRenderer<HerringEntity, HerringModel<HerringEntity>> {
    private static final ResourceLocation HERRING_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/herring/herring.png");
    public HerringRender(EntityRendererProvider.Context context) {
        super(context,new HerringModel<>(context.bakeLayer(ModModels.HERRING_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(HerringEntity entity) {
        return HERRING_LOCATION;
    }

    @Override
    public void render(HerringEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
