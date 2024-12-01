package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animations.DeerAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DeerModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart deer;
	private final ModelPart body;
	private final ModelPart head_group;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart antlers;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;

	public DeerModel(ModelPart root) {
		this.deer = root.getChild("deer");
		this.body = this.deer.getChild("body");
		this.head_group = this.deer.getChild("head_group");
		this.neck = this.head_group.getChild("neck");
		this.head = this.head_group.getChild("head");
		this.antlers = this.head.getChild("antlers");
		this.front_right_leg = this.deer.getChild("front_right_leg");
		this.front_left_leg = this.deer.getChild("front_left_leg");
		this.back_left_leg = this.deer.getChild("back_left_leg");
		this.back_right_leg = this.deer.getChild("back_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition deer = partdefinition.addOrReplaceChild("deer", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -7.0F));

		PartDefinition body = deer.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 8.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(3, 7).addBox(-2.0F, -2.0F, 10.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 8.0F));

		PartDefinition head_group = deer.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition neck = head_group.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(23, 29).addBox(-4.499F, -9.2554F, -1.653F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -4.0F, -7.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(15, 45).addBox(-6.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(15, 45).mirror().addBox(0.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 52).addBox(-5.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -7.0F, -4.0F));

		PartDefinition antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create(), PartPose.offset(1.0F, -5.0F, 0.0F));

		PartDefinition cube_r1 = antlers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(25, 53).addBox(-5.0F, -1.0F, -1.3F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9134F, -6.2954F, -0.2059F, -0.8727F, 0.7854F, -0.4363F));

		PartDefinition cube_r2 = antlers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(29, 53).addBox(-2.0F, -1.0F, -1.4F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0866F, -6.2954F, 1.7941F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r3 = antlers.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 53).addBox(-2.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0499F, -10.305F, -0.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r4 = antlers.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 53).addBox(-2.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0499F, -6.305F, -2.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r5 = antlers.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(23, 53).mirror().addBox(-3.0F, -1.0F, -1.3F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.9134F, -9.2954F, 1.7941F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r6 = antlers.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(25, 53).mirror().addBox(-1.0F, -1.0F, -1.3F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.9134F, -6.2954F, -0.2059F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r7 = antlers.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(-1.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -1.0F, 1.0F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r8 = antlers.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -1.0F, 1.0F, 1.0377F, -0.8805F, -1.5037F));

		PartDefinition cube_r9 = antlers.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(1.0F, -3.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.0499F, -11.305F, -0.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r10 = antlers.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(1.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0499F, -10.305F, -0.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r11 = antlers.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(1.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0499F, -6.305F, -2.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r12 = antlers.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(29, 53).mirror().addBox(0.0F, -1.0F, -1.4F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.9134F, -6.2954F, 1.7941F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r13 = antlers.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(30, 53).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 1.0377F, 0.8805F, 1.5037F));

		PartDefinition cube_r14 = antlers.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(30, 53).addBox(-2.0F, -3.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0499F, -11.305F, -0.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r15 = antlers.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(23, 53).addBox(-5.0F, -1.0F, -1.3F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9134F, -9.2954F, 1.7941F, -0.8727F, 0.7854F, -0.4363F));

		PartDefinition cube_r16 = antlers.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(30, 53).addBox(0.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, -0.8727F, 0.7854F, -0.4363F));

		PartDefinition front_right_leg = deer.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-1.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, 3.0F));

		PartDefinition front_left_leg = deer.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 6.0F, 14.0F));

		PartDefinition back_left_leg = deer.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-3.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 6.0F, 3.0F));

		PartDefinition back_right_leg = deer.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 6.0F, 14.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(DeerAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DeerEntity) entity).idleAnimationState, DeerAnimation.Idle, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return deer;
	}
}