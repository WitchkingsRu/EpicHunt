package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.DoeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.MobRenderer;

@Environment(EnvType.CLIENT)
public class DoeRender extends MobRenderer<DoeEntity, DoeModel<DoeEntity>> {
    private static final ResourceLocation DOE_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/deer/doe.png");
    public DoeRender(EntityRendererProvider.Context context) {
        super(context,new DoeModel<>(context.bakeLayer(ModModels.DOE_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(DoeEntity entity) {
        return DOE_LOCATION;
    }

    @Override
    public void render(DoeEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(0.75f, 0.75f, 0.75f);
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
