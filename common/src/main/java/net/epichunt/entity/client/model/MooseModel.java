package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.MooseEntity;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.animations.CaribouAnimation;
import net.epichunt.entity.animations.MooseAnimation;
import net.epichunt.entity.animations.WisentAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MooseModel<T extends MooseEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart moose;
	private final ModelPart body;
	private final ModelPart head_group;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart horns;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;

	public MooseModel(ModelPart root) {
		this.moose = root.getChild("moose");
		this.body = this.moose.getChild("body");
		this.head_group = this.moose.getChild("head_group");
		this.neck = this.head_group.getChild("neck");
		this.head = this.head_group.getChild("head");
		this.horns = this.head.getChild("horns");
		this.front_right_leg = this.moose.getChild("front_right_leg");
		this.front_left_leg = this.moose.getChild("front_left_leg");
		this.back_left_leg = this.moose.getChild("back_left_leg");
		this.back_right_leg = this.moose.getChild("back_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition moose = partdefinition.addOrReplaceChild("moose", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, -13.0F));

		PartDefinition body = moose.addOrReplaceChild("body", CubeListBuilder.create().texOffs(3, 7).addBox(-2.0F, -3.0F, 10.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-5.0F, -4.0F, -10.0F, 10.0F, 9.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 5.0F, 14.0F));

		PartDefinition head_group = moose.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(-4.0F, 5.0F, 5.0F));

		PartDefinition neck = head_group.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, 1.0F));

		PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(22, 29).addBox(-4.499F, -7.2554F, -1.653F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 45).addBox(-6.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(21, 45).mirror().addBox(0.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 52).addBox(-5.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, -5.0F));

		PartDefinition nose_r1 = head.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -3.4F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -8.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(-4.0F, 6.0F, 5.0F));

		PartDefinition cube_r1 = horns.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.3327F, -14.9812F, -9.0922F, 0.5236F, -1.1781F, -0.0873F));

		PartDefinition cube_r2 = horns.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(25, 52).mirror().addBox(-1.0F, -0.5F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.9281F, -14.3936F, -8.8208F, 0.0F, 0.3491F, -0.0873F));

		PartDefinition cube_r3 = horns.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.9281F, -14.3936F, -6.8208F, -0.4363F, 0.3491F, -0.0873F));

		PartDefinition cube_r4 = horns.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.9281F, -15.3936F, -6.8208F, -0.4363F, 0.3491F, -0.0873F));

		PartDefinition cube_r5 = horns.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.5387F, -14.6862F, -7.8759F, -0.3054F, 0.9163F, -0.0873F));

		PartDefinition cube_r6 = horns.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.3327F, -14.9812F, -10.0922F, 0.4363F, -0.0436F, -0.0873F));

		PartDefinition cube_r7 = horns.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -3.5F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.9281F, -15.3936F, -9.8208F, 0.4363F, 0.3491F, -0.0873F));

		PartDefinition cube_r8 = horns.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(30, 54).mirror().addBox(-1.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.9281F, -14.3936F, -7.8208F, 0.4363F, 0.3491F, -0.0873F));

		PartDefinition cube_r9 = horns.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3327F, -14.9812F, -9.0922F, 0.5236F, 1.1781F, 0.0873F));

		PartDefinition cube_r10 = horns.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5387F, -14.6862F, -7.8759F, -0.3054F, -0.9163F, 0.0873F));

		PartDefinition cube_r11 = horns.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3327F, -14.9812F, -10.0922F, 0.4363F, 0.0436F, 0.0873F));

		PartDefinition cube_r12 = horns.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.9281F, -15.3936F, -6.8208F, -0.4363F, -0.3491F, 0.0873F));

		PartDefinition cube_r13 = horns.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -3.5F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9281F, -15.3936F, -9.8208F, 0.4363F, -0.3491F, 0.0873F));

		PartDefinition cube_r14 = horns.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9281F, -14.3936F, -6.8208F, -0.4363F, -0.3491F, 0.0873F));

		PartDefinition cube_r15 = horns.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(30, 54).addBox(0.0F, -2.5F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9281F, -14.3936F, -7.8208F, 0.4363F, -0.3491F, 0.0873F));

		PartDefinition cube_r16 = horns.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(25, 52).addBox(-3.0F, -0.5F, -1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9281F, -14.3936F, -8.8208F, 0.0F, -0.3491F, 0.0873F));

		PartDefinition cube_r17 = horns.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(30, 58).addBox(0.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -12.0F, -6.0F, 0.3491F, 0.0F, -1.0908F));

		PartDefinition cube_r18 = horns.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(30, 58).mirror().addBox(-1.0F, -4.5F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -12.0F, -6.0F, 0.3491F, 0.0F, 1.0908F));

		PartDefinition front_right_leg = moose.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-1.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 8.0F, 9.0F));

		PartDefinition front_left_leg = moose.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-2.0F, -1.0F, -1.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 8.0F, 20.0F));

		PartDefinition back_left_leg = moose.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-3.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 8.0F, 9.0F));

		PartDefinition back_right_leg = moose.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, 20.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		if (entity.isAngry) {
			this.animateWalk(MooseAnimation.Walk_Angry, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animateWalk(MooseAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		}
		this.animate(((MooseEntity) entity).attackAnimationState, MooseAnimation.Attack, ageInTicks, 1f);
		this.animate(((MooseEntity) entity).idleAnimationState, MooseAnimation.Idle, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		moose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return moose;
	}
}