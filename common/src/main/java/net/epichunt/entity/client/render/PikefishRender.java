package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.PikefishEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.PikefishModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PikefishRender extends MobRenderer<PikefishEntity, PikefishModel<PikefishEntity>> {
    private static final ResourceLocation PIKEFISH_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/pikefish/pikefish.png");
    public PikefishRender(EntityRendererProvider.Context context) {
        super(context,new PikefishModel<>(context.bakeLayer(ModModels.PIKEFISH_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(PikefishEntity entity) {
        return PIKEFISH_LOCATION;
    }

    @Override
    public void render(PikefishEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
