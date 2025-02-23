package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.HighlandCowEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.HighlandCowModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HighlandCowRender extends MobRenderer<HighlandCowEntity, HighlandCowModel<HighlandCowEntity>> {
    private static final ResourceLocation HIGHLAND_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/highland_cow/highland_cow_1.png");
    public HighlandCowRender(EntityRendererProvider.Context context) {
        super(context,new HighlandCowModel<>(context.bakeLayer(ModModels.HIGHLAND_COW_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(HighlandCowEntity entity) {
        return HIGHLAND_LOCATION;
    }

    @Override
    public void render(HighlandCowEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
