package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BeaverEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.BeaverModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BeaverRender extends MobRenderer<BeaverEntity, BeaverModel<BeaverEntity>> {
    private static final ResourceLocation BEAVER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/beaver/beaver.png");
    public BeaverRender(EntityRendererProvider.Context context) {
        super(context,new BeaverModel<>(context.bakeLayer(ModModels.BEAVER_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(BeaverEntity entity) {
        return BEAVER_LOCATION;
    }

    @Override
    public void render(BeaverEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
