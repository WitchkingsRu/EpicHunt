package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.WhiteStorkModel;
import net.epichunt.entity.animals.WhiteStorkEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class WhiteStorkRender extends MobRenderer<WhiteStorkEntity, WhiteStorkModel<WhiteStorkEntity>> {
    private static final ResourceLocation WHITE_STORK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/stork/white_stork.png");
    public WhiteStorkRender(EntityRendererProvider.Context context) {
        super(context,new WhiteStorkModel<>(context.bakeLayer(ModModels.WHITE_STORK_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(WhiteStorkEntity entity) {
        return WHITE_STORK_LOCATION;
    }

    @Override
    public void render(WhiteStorkEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        if(mob.isBaby()) {
            poseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}