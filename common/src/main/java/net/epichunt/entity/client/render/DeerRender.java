package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.client.model.DeerModel;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class DeerRender extends MobRenderer<DeerEntity, DeerModel<DeerEntity>> {
    private static final ResourceLocation DEER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/deer/deer.png");
    public DeerRender(EntityRendererProvider.Context context) {
        super(context,new DeerModel<>(context.bakeLayer(ModModels.DEER_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(DeerEntity entity) {
        return DEER_LOCATION;
    }

    @Override
    public void render(DeerEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(0.75f, 0.75f, 0.75f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
