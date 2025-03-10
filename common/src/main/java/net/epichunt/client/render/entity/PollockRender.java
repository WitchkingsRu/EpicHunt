package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.PollockEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.PollockModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PollockRender extends MobRenderer<PollockEntity, PollockModel<PollockEntity>> {
    private static final ResourceLocation POLLOCK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/pollock/pollock.png");
    public PollockRender(EntityRendererProvider.Context context) {
        super(context,new PollockModel<>(context.bakeLayer(ModModels.POLLOCK_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(PollockEntity entity) {
        return POLLOCK_LOCATION;
    }

    @Override
    public void render(PollockEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
