package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.MooseEntity;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.CaribouAnimation;
import net.epichunt.entity.animations.HighlandCowAnimation;
import net.epichunt.entity.animations.MooseAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BoarModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart boar;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leg4;
	private final ModelPart leg3;
	private final ModelPart leg1;
	private final ModelPart leg2;

	public BoarModel(ModelPart root) {
		this.boar = root.getChild("boar");
		this.head = this.boar.getChild("head");
		this.body = this.boar.getChild("body");
		this.leg4 = this.boar.getChild("leg4");
		this.leg3 = this.boar.getChild("leg3");
		this.leg1 = this.boar.getChild("leg1");
		this.leg2 = this.boar.getChild("leg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition boar = partdefinition.addOrReplaceChild("boar", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = boar.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, 2.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -2.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -6.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 20).mirror().addBox(-1.0F, -4.7F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 6.0F, -7.0F, 0.9707F, 0.4439F, 0.8127F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 20).addBox(0.0F, -4.7F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, -7.0F, 0.9707F, -0.4439F, -0.8127F));

		PartDefinition body = boar.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(28, 8).addBox(-5.0F, -23.0F, -5.0F, 10.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -14.0F, -1.5708F, 0.0F, -3.1416F));

		PartDefinition leg4 = boar.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -4.0F));

		PartDefinition leg3 = boar.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, -4.0F));

		PartDefinition leg1 = boar.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 7.0F));

		PartDefinition leg2 = boar.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(BoarAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((BoarEntity) entity).attackAnimationState, BoarAnimation.attack, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		boar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return boar;
	}
}