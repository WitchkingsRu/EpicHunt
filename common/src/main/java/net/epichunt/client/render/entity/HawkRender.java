package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.HawkModel;
import net.epichunt.entity.animals.aerial.HawkEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HawkRender extends MobRenderer<HawkEntity, HawkModel<HawkEntity>> {
    private static final ResourceLocation HAWK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/hawk/hawk.png");
    public HawkRender(EntityRendererProvider.Context context) {
        super(context,new HawkModel<>(context.bakeLayer(ModModels.HAWK_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(HawkEntity entity) {
        return HAWK_LOCATION;
    }

    @Override
    public void render(HawkEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.0f, 1.0f, 1.0f);
        if(mob.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}