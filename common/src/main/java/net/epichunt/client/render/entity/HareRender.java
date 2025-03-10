package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.HareEntity;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HareRender extends MobRenderer<HareEntity, RabbitModel<HareEntity>> {
    private static final ResourceLocation HARE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/hare/hare.png");
    public HareRender(EntityRendererProvider.Context context) {
        super(context,new RabbitModel<>(context.bakeLayer(ModModels.HARE_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(HareEntity entity) {
        return HARE_LOCATION;
    }

    @Override
    public void render(HareEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {

        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
