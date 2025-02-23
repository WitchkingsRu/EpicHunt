package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.PerchesEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.PerchesModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PerchesRender extends MobRenderer<PerchesEntity, PerchesModel<PerchesEntity>> {
    private static final ResourceLocation PERCHES_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/perches/perches.png");
    public PerchesRender(EntityRendererProvider.Context context) {
        super(context,new PerchesModel<>(context.bakeLayer(ModModels.PERCHES_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(PerchesEntity entity) {
        return PERCHES_LOCATION;
    }

    @Override
    public void render(PerchesEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
