package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.SardineEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.SardineModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SardineRender extends MobRenderer<SardineEntity, SardineModel<SardineEntity>> {
    private static final ResourceLocation SARDINE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/sardine/sardine.png");
    public SardineRender(EntityRendererProvider.Context context) {
        super(context,new SardineModel<>(context.bakeLayer(ModModels.SARDINE_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SardineEntity entity) {
        return SARDINE_LOCATION;
    }

    @Override
    public void render(SardineEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
