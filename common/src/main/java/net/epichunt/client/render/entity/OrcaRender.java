package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.OrcaModel;
import net.epichunt.client.model.entity.WhaleModel;
import net.epichunt.entity.animals.aquatic.OrcaEntity;
import net.epichunt.entity.animals.aquatic.WhaleEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class OrcaRender extends MobRenderer<OrcaEntity, OrcaModel<OrcaEntity>> {
    private static final ResourceLocation ORCA_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/orca/orca.png");
    public OrcaRender(EntityRendererProvider.Context context) {
        super(context,new OrcaModel<>(context.bakeLayer(ModModels.ORCA_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(OrcaEntity entity) {
        return ORCA_LOCATION;
    }

    @Override
    public void render(OrcaEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.8f, 1.8f, 1.8f);
        if(mob.isBaby()) {
            poseStack.scale(1.0f, 1.0f, 1.0f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
