package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.TroutEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.TroutModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class TroutRender extends MobRenderer<TroutEntity, TroutModel<TroutEntity>> {
    private static final ResourceLocation TROUT_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/trout/trout.png");
    public TroutRender(EntityRendererProvider.Context context) {
        super(context,new TroutModel<>(context.bakeLayer(ModModels.TROUT_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(TroutEntity entity) {
        return TROUT_LOCATION;
    }

    @Override
    public void render(TroutEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
