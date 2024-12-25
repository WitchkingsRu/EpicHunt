package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.WisentModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class WisentRender extends MobRenderer<WisentEntity, WisentModel<WisentEntity>> {
    private static final ResourceLocation WISENT_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/wisent/wisent.png");
    public WisentRender(EntityRendererProvider.Context context) {
        super(context,new WisentModel<>(context.bakeLayer(ModModels.WISENT_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(WisentEntity entity) {
        return WISENT_LOCATION;
    }

    @Override
    public void render(WisentEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
