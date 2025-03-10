package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.PheasantEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.PheasantModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PheasantRender extends MobRenderer<PheasantEntity, PheasantModel<PheasantEntity>> {
    private static final ResourceLocation PHEASANT_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/pheasant/pheasant.png");
    public PheasantRender(EntityRendererProvider.Context context) {
        super(context,new PheasantModel<>(context.bakeLayer(ModModels.PHEASANT_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(PheasantEntity entity) {
        return PHEASANT_LOCATION;
    }

    @Override
    public void render(PheasantEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}