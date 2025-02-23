package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BadgerEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.BadgerModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BadgerRender extends MobRenderer<BadgerEntity, BadgerModel<BadgerEntity>> {
    private static final ResourceLocation BADGER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/badger/badger.png");
    public BadgerRender(EntityRendererProvider.Context context) {
        super(context,new BadgerModel<>(context.bakeLayer(ModModels.BADGER_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(BadgerEntity entity) {
        return BADGER_LOCATION;
    }

    @Override
    public void render(BadgerEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
