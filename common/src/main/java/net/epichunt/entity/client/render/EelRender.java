package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.EelEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.EelModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class EelRender extends MobRenderer<EelEntity, EelModel<EelEntity>> {
    private static final ResourceLocation EEL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/eel/eel.png");
    public EelRender(EntityRendererProvider.Context context) {
        super(context,new EelModel<>(context.bakeLayer(ModModels.EEL_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(EelEntity entity) {
        return EEL_LOCATION;
    }

    @Override
    public void render(EelEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
