package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.PartridgeModel;
import net.epichunt.entity.animals.PartridgeEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PartridgeRender extends MobRenderer<PartridgeEntity, PartridgeModel<PartridgeEntity>> {
    private static final ResourceLocation PARTRIDGE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/partridge/partridge.png");
    public PartridgeRender(EntityRendererProvider.Context context) {
        super(context,new PartridgeModel<>(context.bakeLayer(ModModels.PARTRIDGE_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(PartridgeEntity entity) {
        return PARTRIDGE_LOCATION;
    }

    @Override
    public void render(PartridgeEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.0f, 1.0f, 1.0f);
        if(mob.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}