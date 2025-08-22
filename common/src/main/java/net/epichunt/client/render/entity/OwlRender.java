package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.OwlModel;
import net.epichunt.entity.animals.OwlEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class OwlRender extends MobRenderer<OwlEntity, OwlModel<OwlEntity>> {
    public static final ResourceLocation OWL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/owl/owl.png");
    public OwlRender(EntityRendererProvider.Context context) {
        super(context,new OwlModel<>(context.bakeLayer(ModModels.OWL_LAYER_LOCATION)), 0.3f);
    }


    public ResourceLocation getTextureLocation(OwlEntity entity) {
        return OWL_LOCATION;
    }

    public static ResourceLocation getOwlTexture() {
        return OWL_LOCATION;
    }

    @Override
    public void render(OwlEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }

}