package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.CatfishEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.CatfishModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CatfishRender extends MobRenderer<CatfishEntity, CatfishModel<CatfishEntity>> {
    private static final ResourceLocation CATFISH_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/catfish/catfish.png");
    public CatfishRender(EntityRendererProvider.Context context) {
        super(context,new CatfishModel<>(context.bakeLayer(ModModels.CATFISH_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(CatfishEntity entity) {
        return CATFISH_LOCATION;
    }

    @Override
    public void render(CatfishEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
