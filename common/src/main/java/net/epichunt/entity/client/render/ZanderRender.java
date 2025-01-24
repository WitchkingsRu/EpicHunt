package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.ZanderEntity;
import net.epichunt.entity.client.ModModels;
import net.epichunt.entity.client.model.ZanderModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ZanderRender extends MobRenderer<ZanderEntity, ZanderModel<ZanderEntity>> {
    private static final ResourceLocation ZANDER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/zander/zander.png");
    public ZanderRender(EntityRendererProvider.Context context) {
        super(context,new ZanderModel<>(context.bakeLayer(ModModels.ZANDER_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZanderEntity entity) {
        return ZANDER_LOCATION;
    }

    @Override
    public void render(ZanderEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
