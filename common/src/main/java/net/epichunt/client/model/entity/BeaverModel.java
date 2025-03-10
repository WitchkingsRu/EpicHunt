package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.MooseEntity;
import net.epichunt.entity.animations.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BeaverModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart beaver;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leg3;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart leg0;
	private final ModelPart tail;

	public BeaverModel(ModelPart root) {
		this.beaver = root.getChild("beaver");
		this.head = this.beaver.getChild("head");
		this.body = this.beaver.getChild("body");
		this.leg3 = this.beaver.getChild("leg3");
		this.leg2 = this.beaver.getChild("leg2");
		this.leg1 = this.beaver.getChild("leg1");
		this.leg0 = this.beaver.getChild("leg0");
		this.tail = this.beaver.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition beaver = partdefinition.addOrReplaceChild("beaver", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition head = beaver.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 1).addBox(-1.49F, -0.2F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 1).mirror().addBox(-1.51F, -0.2F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 10).mirror().addBox(-0.7F, -0.7F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 10).addBox(-0.3F, -0.7F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.5F, -1.1F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, -3.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 5).addBox(-1.49F, -3.116F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.01F, 2.224F, -3.1986F, -1.0472F, 0.0F, 0.0F));

		PartDefinition body = beaver.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 1).addBox(-2.0F, -0.5F, -2.01F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).mirror().addBox(-2.01F, -1.0F, -0.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition leg3 = beaver.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.3F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 2.5F, -2.0F));

		PartDefinition leg2 = beaver.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.3F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 2.5F, 2.0F));

		PartDefinition leg1 = beaver.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 18).addBox(-0.7F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.5F, -2.0F));

		PartDefinition leg0 = beaver.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 18).addBox(-0.7F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.5F, 2.0F));

		PartDefinition tail = beaver.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(17, 26).addBox(0.01F, -1.5F, -1.2F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.7817F, 0.5877F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(16, 25).addBox(0.0F, -1.0F, -1.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.7817F, 0.5877F, -0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(BeaverAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		beaver.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return beaver;
	}
}