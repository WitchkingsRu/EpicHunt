package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DrakeEntity;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.DeerModel;
import net.epichunt.entity.client.model.DrakeModel;
import net.epichunt.entity.client.model.DuckModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class DrakeRender extends MobRenderer<DrakeEntity, DrakeModel<DrakeEntity>> {
    private static final ResourceLocation DRAKE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/duck/drake.png");
    public DrakeRender(EntityRendererProvider.Context context) {
        super(context,new DrakeModel<>(context.bakeLayer(ModModels.DRAKE_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(DrakeEntity entity) {
        return DRAKE_LOCATION;
    }

    @Override
    public void render(DrakeEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}