package net.epichunt.entity.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.YakEntity;
import net.epichunt.client.ModModels;
import net.epichunt.entity.client.model.YakModel;
import net.epichunt.item.YakCarpetItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class YakCarpetLayer extends RenderLayer<YakEntity, YakModel<YakEntity>> {
    private final YakModel<YakEntity> model;

    public YakCarpetLayer(RenderLayerParent<YakEntity, YakModel<YakEntity>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new YakModel(entityModelSet.bakeLayer(ModModels.YAK_CARPET_LAYER_LOCATION));
    }

    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, YakEntity yak, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = yak.getCarpet();
        if (itemStack.getItem() instanceof YakCarpetItem) {
            YakCarpetItem yakArmorItem = (YakCarpetItem) itemStack.getItem();
            ((YakModel) this.getParentModel()).copyPropertiesTo(this.model);
            this.model.prepareMobModel(yak, f, g, h);
            this.model.setupAnim(yak, f, g, j, k, l);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(yakArmorItem.getTexture()));
            this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}