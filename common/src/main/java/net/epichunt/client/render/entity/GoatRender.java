package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.GoatEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.GoatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class GoatRender extends MobRenderer<GoatEntity, GoatModel<GoatEntity>> {
    private static final ResourceLocation GOAT_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/goat/goat.png");
    public GoatRender(EntityRendererProvider.Context context) {
        super(context,new GoatModel<>(context.bakeLayer(ModModels.GOAT_LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(GoatEntity entity) {
        return GOAT_LOCATION;
    }

    @Override
    public void render(GoatEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {

        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
