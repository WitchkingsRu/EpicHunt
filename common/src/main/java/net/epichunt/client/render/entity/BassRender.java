package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.BassModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BassRender extends MobRenderer<BassEntity, BassModel<BassEntity>> {
    private static final ResourceLocation BASS_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/bass/bass.png");
    public BassRender(EntityRendererProvider.Context context) {
        super(context,new BassModel<>(context.bakeLayer(ModModels.BASS_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(BassEntity entity) {
        return BASS_LOCATION;
    }

    @Override
    public void render(BassEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
