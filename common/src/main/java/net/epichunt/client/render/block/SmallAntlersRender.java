package net.epichunt.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.epichunt.EpicHunt;
import net.epichunt.block.AbstractAntlersBlock;
import net.epichunt.block.AbstractAntlersBlockEntity;
import net.epichunt.block.AbstractWallAntlersBlock;
import net.epichunt.client.model.block.AntlersModelBase;
import net.epichunt.client.model.block.SmallAntlersModel;
import net.epichunt.entity.animals.BadgerEntity;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;

@Environment(EnvType.CLIENT)
public class SmallAntlersRender implements BlockEntityRenderer<AbstractAntlersBlockEntity> {
    SmallAntlersModel model;
    public static final AntlersModelBase base = new AntlersModelBase() {
        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {

        }

        @Override
        public void setupAnim(float f, float g, float h) {

        }
    };
    private static final ResourceLocation SMALL_ANTLERS_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/blocks/antlers/antlers_small.png");
    public SmallAntlersRender(BlockEntityRendererProvider.Context context) {
        this.model = new SmallAntlersModel(context.bakeLayer(ModModels.SMALL_ANTLERS_LAYER_LOCATION));
    }

    @Override
    public void render(AbstractAntlersBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        BlockState blockstate = blockEntity.getBlockState();
        boolean flag = blockstate.getBlock() instanceof AbstractWallAntlersBlock;
        Direction direction = flag ? (Direction)blockstate.getValue(AbstractWallAntlersBlock.FACING) : null;
        int g = flag ? RotationSegment.convertToSegment(direction.getOpposite()) : (Integer)blockstate.getValue(AbstractAntlersBlock.ROTATION);
        float f1 = RotationSegment.convertToDegrees(g);
        RenderType rendertype = RenderType.entityCutoutNoCullZOffset(SMALL_ANTLERS_LOCATION);
        renderAntlers(direction, f1, f, poseStack, multiBufferSource, j, base, rendertype);

    }

    public static void renderAntlers(@Nullable Direction arg, float g, float h, PoseStack arg2, MultiBufferSource arg3, int i, AntlersModelBase arg4, RenderType arg5) {
        arg2.pushPose();
        if (arg == null) {
            arg2.translate(0.5F, 0.0F, 0.5F);
        } else {
            float f = 0.25F;
            arg2.translate(0.5F - (float)arg.getStepX() * 0.25F, 0.25F, 0.5F - (float)arg.getStepZ() * 0.25F);
        }

        arg2.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = arg3.getBuffer(arg5);
        arg4.setupAnim(h, g, 0.0F);
        arg4.renderToBuffer(arg2, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        arg2.popPose();
    }
}
