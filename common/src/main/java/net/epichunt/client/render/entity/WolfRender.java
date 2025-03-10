package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.WolfEntity;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.epichunt.client.model.entity.WolfModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class WolfRender extends MobRenderer<WolfEntity, WolfModel<WolfEntity>> {
    private static final ResourceLocation WOLF_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/wolf/wolf.png");
    public WolfRender(EntityRendererProvider.Context context) {
        super(context,new WolfModel<>(context.bakeLayer(ModModels.WOLF_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(WolfEntity entity) {
        return WOLF_LOCATION;
    }

    @Override
    public void render(WolfEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.25f, 1.25f, 1.25f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
