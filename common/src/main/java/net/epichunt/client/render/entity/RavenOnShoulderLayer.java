package net.epichunt.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.client.ModModels;
import net.epichunt.client.model.entity.RavenModel;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.animals.RavenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class RavenOnShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private final RavenModel model;

    public RavenOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new RavenModel(entityModelSet.bakeLayer(ModModels.RAVEN_LAYER_LOCATION));
    }
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T player, float f, float g, float h, float j, float k, float l) {
        this.render(poseStack, multiBufferSource, i, player, f, g, k, l, true);
        this.render(poseStack, multiBufferSource, i, player, f, g, k, l, false);
    }

    private void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T player, float f, float g, float h, float j, boolean bl) {
        CompoundTag compoundTag = bl ? player.getShoulderEntityLeft() : player.getShoulderEntityRight();
        EntityType.byString(compoundTag.getString("id")).filter((entityType) -> entityType == ModEntities.RAVEN_ENTITY.get()).ifPresent((entityType) -> {
            poseStack.pushPose();
            poseStack.translate(bl ? 0.4F : -0.4F, player.isCrouching() ? -1.3F : -1.5F, 0.0F);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(RavenRender.getRavenTexture()));
            this.model.renderOnShoulder(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, f, g, h, j, player.tickCount);
            poseStack.popPose();
        });
    }

}
