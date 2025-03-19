package net.epichunt.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.block.ClamBlock;
import net.epichunt.block.ClamBlockEntity;
import net.epichunt.block.MusselBlock;
import net.epichunt.block.MusselBlockEntity;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.block.ClamBlockModel;
import net.epichunt.client.model.block.MusselBlockModel;
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
public class ClamBlockRender implements BlockEntityRenderer<ClamBlockEntity> {
    ClamBlockModel model;
    ModelPart root;
    public ClamBlockRender(BlockEntityRendererProvider.Context context) {
        this.model = new ClamBlockModel(context.bakeLayer(ModModels.CLAM_LAYER_LOCATION));
        this.root = this.model.root();
    }
    @Override
    public int getViewDistance()
    {
        return 128;
    }
    private static final ResourceLocation CLAM_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/block/clusters/clam.png");
    @Override
    public void render(ClamBlockEntity clam, float f, PoseStack poseStack, MultiBufferSource buffer, int i, int j) {
        BlockState state = clam.getBlockState();
        int age = state.getValue(ClamBlock.AGE);
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0, -1.5, 0);
        model.setVisibleByAge(age);
        RenderType renderType = RenderType.entityCutoutNoCull(CLAM_LOCATION);
        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

        root.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

    }
}
