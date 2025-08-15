package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.RazorbillModel;
import net.epichunt.entity.animals.RazorbillEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RazorbillRender extends MobRenderer<RazorbillEntity, RazorbillModel<RazorbillEntity>> {
    private static final ResourceLocation RAZORBILL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/razorbill/razorbill.png");
    public RazorbillRender(EntityRendererProvider.Context context) {
        super(context,new RazorbillModel<>(context.bakeLayer(ModModels.RAZORBILL_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(RazorbillEntity entity) {
        return RAZORBILL_LOCATION;
    }

    @Override
    public void render(RazorbillEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}