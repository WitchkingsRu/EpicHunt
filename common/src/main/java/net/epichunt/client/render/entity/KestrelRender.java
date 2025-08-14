package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.KestrelModel;
import net.epichunt.entity.animals.aerial.KestrelEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class KestrelRender extends MobRenderer<KestrelEntity, KestrelModel<KestrelEntity>> {
    private static final ResourceLocation KESTREL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/kestrel/kestrel.png");
    public KestrelRender(EntityRendererProvider.Context context) {
        super(context,new KestrelModel<>(context.bakeLayer(ModModels.KESTREL_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(KestrelEntity entity) {
        return KESTREL_LOCATION;
    }

    @Override
    public void render(KestrelEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}