package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.GreatAukModel;
import net.epichunt.entity.animals.GreatAukEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class GreatAukRender extends MobRenderer<GreatAukEntity, GreatAukModel<GreatAukEntity>> {
    private static final ResourceLocation GREAT_AUK_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/entity/great_auk/great_auk.png");
    public GreatAukRender(EntityRendererProvider.Context context) {
        super(context,new GreatAukModel<>(context.bakeLayer(ModModels.GREAT_AUK_LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(GreatAukEntity entity) {
        return GREAT_AUK_LOCATION;
    }

    @Override
    public void render(GreatAukEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        //poseStack.scale(0.6f, 0.6f, 0.6f);

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}