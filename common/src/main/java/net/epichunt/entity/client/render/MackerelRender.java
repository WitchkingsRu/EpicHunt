package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.fish.MackerelEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.MackerelModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class MackerelRender extends MobRenderer<MackerelEntity, MackerelModel<MackerelEntity>> {
    private static final ResourceLocation MACKEREL_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/fish/mackerel/mackerel.png");
    public MackerelRender(EntityRendererProvider.Context context) {
        super(context,new MackerelModel<>(context.bakeLayer(ModModels.MACKEREL_LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(MackerelEntity entity) {
        return MACKEREL_LOCATION;
    }

    @Override
    public void render(MackerelEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(mob.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
