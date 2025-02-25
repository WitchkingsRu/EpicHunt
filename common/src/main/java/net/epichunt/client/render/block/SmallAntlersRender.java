package net.epichunt.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.epichunt.EpicHunt;
import net.epichunt.block.AbstractAntlersBlock;
import net.epichunt.block.AbstractAntlersBlockEntity;
import net.epichunt.block.AbstractWallAntlersBlock;
import net.epichunt.client.model.block.SmallAntlersModel;
import net.epichunt.entity.animals.BadgerEntity;
import net.epichunt.client.ModModels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RotationSegment;

@Environment(EnvType.CLIENT)
public class SmallAntlersRender implements BlockEntityRenderer<AbstractAntlersBlockEntity> {
    SmallAntlersModel model;
    ModelPart root;
    public SmallAntlersRender(BlockEntityRendererProvider.Context context) {
        this.model = new SmallAntlersModel(context.bakeLayer(ModModels.SMALL_ANTLERS_LAYER_LOCATION));
        this.root = this.model.root();
    }
    @Override
    public int getViewDistance()
    {
        return 128;
    }
    private static final ResourceLocation SMALL_ANTLERS_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/block/antlers/antlers_small.png");
    @Override
    public void render(AbstractAntlersBlockEntity antlers, float f, PoseStack poseStack, MultiBufferSource buffer, int i, int j) {
        BlockState state = antlers.getBlockState();
        int light = LightTexture.FULL_BRIGHT; // Постоянное максимальное освещение


        int rotation = state.getValue(BlockStateProperties.ROTATION_16);
        poseStack.pushPose();
//        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-rotation * 22.5F));
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0, -1.5, 0);
        RenderType renderType = RenderType.entityCutoutNoCull(SMALL_ANTLERS_LOCATION);
        VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

        root.render(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

    }


}
