package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.HalibutEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.HalibutModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HalibutRender extends MobRenderer<HalibutEntity, HalibutModel<HalibutEntity>> {
    private static final ResourceLocation HALIBUT_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/halibut/halibut.png");
    public HalibutRender(EntityRendererProvider.Context context) {
        super(context,new HalibutModel<>(context.bakeLayer(ModModels.HALIBUT_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(HalibutEntity entity) {
        return HALIBUT_LOCATION;
    }

    @Override
    public void render(HalibutEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
