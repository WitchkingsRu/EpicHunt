package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.CaribouModel;
import net.epichunt.entity.client.model.DeerModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CaribouRender extends MobRenderer<CaribouEntity, CaribouModel<CaribouEntity>> {
    private static final ResourceLocation CARIBOU_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/deer/caribou.png");
    public CaribouRender(EntityRendererProvider.Context context) {
        super(context,new CaribouModel<>(context.bakeLayer(ModModels.CARIBOU_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(CaribouEntity entity) {
        return CARIBOU_LOCATION;
    }

    @Override
    public void render(CaribouEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
