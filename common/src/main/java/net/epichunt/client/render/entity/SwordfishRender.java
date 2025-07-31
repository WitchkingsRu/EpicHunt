package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.SturgeonModel;
import net.epichunt.client.model.entity.SwordfishModel;
import net.epichunt.entity.animals.aquatic.SwordfishEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SwordfishRender extends MobRenderer<SwordfishEntity, SwordfishModel<SwordfishEntity>> {
    private static final ResourceLocation SWORDFISH_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/swordfish/swordfish.png");
    public SwordfishRender(EntityRendererProvider.Context context) {
        super(context,new SwordfishModel<>(context.bakeLayer(ModModels.SWORDFISH_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SwordfishEntity entity) {
        return SWORDFISH_LOCATION;
    }

    @Override
    public void render(SwordfishEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
