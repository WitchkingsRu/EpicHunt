package net.epichunt.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.epichunt.EpicHunt;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.block.SmallAntlersModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SmallAntlersItemRender extends BlockEntityWithoutLevelRenderer {
    private final SmallAntlersModel model;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final EntityModelSet entityModelSet;

    public SmallAntlersItemRender(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
        this.entityModelSet = entityModelSet;
        this.model = new SmallAntlersModel(this.entityModelSet.bakeLayer(ModModels.SMALL_ANTLERS_LAYER_LOCATION));}

    private static final ResourceLocation SMALL_ANTLERS_LOCATION = new ResourceLocation(EpicHunt.MOD_ID,"textures/block/antlers/antlers_small.png");

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5); // Центрируем в руке/инвентаре
        poseStack.scale(1.0F, -1.0F, 1.0F); // Исправляем переворот
        poseStack.mulPose(Axis.YP.rotationDegrees(180F)); // Поворот для инвентаря

        RenderType renderType = RenderType.entityCutoutNoCull(SMALL_ANTLERS_LOCATION);
        this.model.renderToBuffer(poseStack, buffer.getBuffer(renderType), light, overlay, 1F, 1F, 1F, 1F);

        poseStack.popPose();
    }
}

