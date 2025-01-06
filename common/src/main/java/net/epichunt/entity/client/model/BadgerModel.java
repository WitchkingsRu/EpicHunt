package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animations.BadgerAnimation;
import net.epichunt.entity.animations.CaribouAnimation;
import net.epichunt.entity.animations.HighlandCowAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BadgerModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart badger;
	private final ModelPart head_group;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;

	public BadgerModel(ModelPart root) {
		this.badger = root.getChild("badger");
		this.head_group = this.badger.getChild("head_group");
		this.head = this.head_group.getChild("head");
		this.body = this.badger.getChild("body");
		this.tail = this.badger.getChild("tail");
		this.leg1 = this.badger.getChild("leg1");
		this.leg2 = this.badger.getChild("leg2");
		this.leg3 = this.badger.getChild("leg3");
		this.leg4 = this.badger.getChild("leg4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition badger = partdefinition.addOrReplaceChild("badger", CubeListBuilder.create(), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition head_group = badger.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, -5.0F));

		PartDefinition cube_r1 = head_group.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(41, 4).mirror().addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 4).addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 10).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -2.5F, -0.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition body = badger.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -3.0F));

		PartDefinition tail = badger.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 2.0F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.8F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition leg1 = badger.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 27).addBox(-1.8F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, 1.0F));

		PartDefinition leg2 = badger.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-0.2F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -5.0F, 1.0F));

		PartDefinition leg3 = badger.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 27).addBox(-1.8F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, -5.0F));

		PartDefinition leg4 = badger.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-0.2F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -5.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(BadgerAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		badger.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return badger;
	}
}