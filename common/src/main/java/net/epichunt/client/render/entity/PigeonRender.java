package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.PigeonModel;
import net.epichunt.entity.animals.PigeonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;

@Environment(EnvType.CLIENT)
public class PigeonRender extends MobRenderer<PigeonEntity, PigeonModel<PigeonEntity>> {
    private static final ResourceLocation WHITE = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/pigeon/pigeon_white.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/pigeon/pigeon_orange.png");
    private static final ResourceLocation GREY = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/pigeon/pigeon_gray.png");

    public PigeonRender(EntityRendererProvider.Context context) {
        super(context,new PigeonModel<>(context.bakeLayer(ModModels.PIGEON_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(PigeonEntity pigeon) {
        return getVariantTexture(pigeon.getVariant());
    }

    public static ResourceLocation getVariantTexture(PigeonEntity.Variant variant) {
        ResourceLocation var10000;
        switch (variant) {
            case WHITE -> var10000 = WHITE;
            case ORANGE -> var10000 = ORANGE;
            case GREY -> var10000 = GREY;
            default -> throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    @Override
    public void render(PigeonEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.scale(1.0f, 1.0f, 1.0f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}