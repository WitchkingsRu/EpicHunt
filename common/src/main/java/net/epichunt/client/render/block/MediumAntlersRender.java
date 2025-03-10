package net.epichunt.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.epichunt.EpicHunt;
import net.epichunt.block.MediumAntlersBlockEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.block.MediumAntlersModel;
import net.epichunt.client.model.block.SmallAntlersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@Environment(EnvType.CLIENT)
public class MediumAntlersRender implements BlockEntityRenderer<MediumAntlersBlockEntity> {
    MediumAntlersModel model;
    ModelPart root;
    public MediumAntlersRender(BlockEntityRendererProvider.Context context) {
        this.model = new MediumAntlersModel(context.bakeLayer(ModModels.MEDIUM_ANTLERS_LAYER_LOCATION));
        this.root = this.model.root();
    }
    @Override
    public int getViewDistance()
    {
        return 128;
    }
    private static final ResourceLocation MEDIUM_ANTLERS_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/block/antlers/antlers_medium.png");
    @Override
    public void render(MediumAntlersBlockEntity antlers, float f, PoseStack poseStack, MultiBufferSource buffer, int i, int j) {
        BlockState state = antlers.getBlockState();
        int light = LightTexture.FULL_BRIGHT;
        int rotation = state.getValue(BlockStateProperties.ROTATION_16);
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-rotation * 22.5F));
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0, -1.5, 0);
        RenderType renderType = RenderType.entityCutoutNoCull(MEDIUM_ANTLERS_LOCATION);
        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

        root.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

    }



}
