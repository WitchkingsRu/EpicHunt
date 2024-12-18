package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.YakEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.YakModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class YakRender extends MobRenderer<YakEntity, YakModel<YakEntity>> {
    private static final ResourceLocation YAK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/yak/yak.png");
    public YakRender(EntityRendererProvider.Context context) {
        super(context,new YakModel<>(context.bakeLayer(ModModels.YAK_LAYER_LOCATION)), 0.6f);
        this.addLayer(new YakCarpetLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(YakEntity entity) {
        return YAK_LOCATION;
    }

    @Override
    public void render(YakEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.1f, 1.1f, 1.1f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
