package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.BoarModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BoarRender extends MobRenderer<BoarEntity, BoarModel<BoarEntity>> {
    private static final ResourceLocation BOAR_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/boar/boar.png");
    public BoarRender(EntityRendererProvider.Context context) {
        super(context,new BoarModel<>(context.bakeLayer(ModModels.BOAR_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(BoarEntity entity) {
        return BOAR_LOCATION;
    }

    @Override
    public void render(BoarEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
