package net.epichunt.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.block.MusselBlock;
import net.epichunt.block.MusselBlockEntity;
import net.epichunt.block.OysterBlock;
import net.epichunt.block.OysterBlockEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.block.MusselBlockModel;
import net.epichunt.client.model.block.OysterBlockModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

@Environment(EnvType.CLIENT)
public class OysterBlockRender implements BlockEntityRenderer<OysterBlockEntity> {
    OysterBlockModel model;
    ModelPart root;
    public OysterBlockRender(BlockEntityRendererProvider.Context context) {
        this.model = new OysterBlockModel(context.bakeLayer(ModModels.OYSTER_LAYER_LOCATION));
        this.root = this.model.root();
    }
    @Override
    public int getViewDistance()
    {
        return 128;
    }
    private static final ResourceLocation OYSTER_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/block/clusters/oyster.png");
    @Override
    public void render(OysterBlockEntity oyster, float f, PoseStack poseStack, MultiBufferSource buffer, int i, int j) {
        BlockState state = oyster.getBlockState();
        int age = state.getValue(OysterBlock.AGE);
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0, -1.5, 0);
        model.setVisibleByAge(age);
        RenderType renderType = RenderType.entityCutoutNoCull(OYSTER_LOCATION);
        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

        root.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

    }
}
