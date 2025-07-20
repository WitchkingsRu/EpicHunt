package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.SharkModel;
import net.epichunt.entity.animals.aquatic.WhiteSharkEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class WhiteSharkRender extends MobRenderer<WhiteSharkEntity, SharkModel<WhiteSharkEntity>> {
    private static final ResourceLocation SHARK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/shark/white_shark.png");
    public WhiteSharkRender(EntityRendererProvider.Context context) {
        super(context,new SharkModel<>(context.bakeLayer(ModModels.SHARK_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteSharkEntity entity) {
        return SHARK_LOCATION;
    }

    @Override
    public void render(WhiteSharkEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(2.2f, 2.0f, 2.0f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
