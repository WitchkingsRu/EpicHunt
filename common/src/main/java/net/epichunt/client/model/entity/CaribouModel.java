package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animations.CaribouAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CaribouModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart caribou;
	private final ModelPart body;
	private final ModelPart head_group;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart antlers;
	private final ModelPart big;
	private final ModelPart small;
	private final ModelPart antlers2;
	private final ModelPart small2;
	private final ModelPart big2;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;

	public CaribouModel(ModelPart root) {
		this.caribou = root.getChild("caribou");
		this.body = this.caribou.getChild("body");
		this.head_group = this.caribou.getChild("head_group");
		this.neck = this.head_group.getChild("neck");
		this.head = this.head_group.getChild("head");
		this.antlers = this.head.getChild("antlers");
		this.big = this.antlers.getChild("big");
		this.small = this.antlers.getChild("small");
		this.antlers2 = this.head.getChild("antlers2");
		this.small2 = this.antlers2.getChild("small2");
		this.big2 = this.antlers2.getChild("big2");
		this.front_right_leg = this.caribou.getChild("front_right_leg");
		this.front_left_leg = this.caribou.getChild("front_left_leg");
		this.back_left_leg = this.caribou.getChild("back_left_leg");
		this.back_right_leg = this.caribou.getChild("back_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition caribou = partdefinition.addOrReplaceChild("caribou", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, -13.0F));

		PartDefinition body = caribou.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 8.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(3, 7).addBox(-2.0F, -2.0F, 10.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 5.0F, 14.0F));

		PartDefinition head_group = caribou.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(-4.0F, 5.0F, 4.0F));

		PartDefinition neck = head_group.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, 1.0F));

		PartDefinition hair_r1 = neck.addOrReplaceChild("hair_r1", CubeListBuilder.create().texOffs(37, -3).addBox(1.0F, -8.0F, -0.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.0F, -4.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(23, 29).addBox(-4.499F, -9.2554F, -1.653F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -1.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -4.0F, -7.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(15, 45).addBox(-6.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(15, 45).mirror().addBox(0.3F, -7.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 52).addBox(-5.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, -4.0F));

		PartDefinition antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition big = antlers.addOrReplaceChild("big", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5385F, -7.614F, 4.5716F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r1 = big.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(29, 59).addBox(-1.0F, -1.386F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9615F, -1.5756F, -0.3007F, 0.0436F, 0.0F, -0.3054F));

		PartDefinition cube_r2 = big.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(25, 53).addBox(-3.0F, -0.686F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, 0.0F, 0.0436F, 0.3054F, 0.3927F));

		PartDefinition cube_r3 = big.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(29, 54).addBox(-1.0F, -0.886F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0436F, 0.3054F, 0.2618F));

		PartDefinition cube_r4 = big.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(29, 53).addBox(0.0F, -1.386F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2252F, 0.6798F, -2.7736F, -0.5672F, 0.3054F, 0.2618F));

		PartDefinition cube_r5 = big.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(29, 59).addBox(-1.0F, -1.386F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7735F, -2.1509F, 0.932F, 0.0436F, 0.0F, 0.6981F));

		PartDefinition cube_r6 = big.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(29, 53).addBox(0.0F, -1.386F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2252F, -2.3202F, -3.7736F, -0.5672F, 0.3054F, 0.2618F));

		PartDefinition cube_r7 = big.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 53).addBox(0.0F, -1.386F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -1.0F, -2.0F, 0.0436F, 0.3054F, 0.2618F));

		PartDefinition cube_r8 = big.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 53).addBox(0.0F, -1.386F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, -1.0F, 0.0436F, 0.3054F, 0.2618F));

		PartDefinition cube_r9 = big.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(25, 53).addBox(-1.0F, -1.386F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 1.0F, 0.0F, 0.0436F, 0.3054F, 0.4363F));

		PartDefinition cube_r10 = big.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 53).addBox(-1.0F, -4.386F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.0F, 0.0F, 0.0436F, 0.3054F, 0.2618F));

		PartDefinition cube_r11 = big.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(28, 54).addBox(-1.0F, -8.0F, -1.001F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5385F, 7.614F, -1.5716F, -0.3927F, 0.3491F, 0.0F));

		PartDefinition small = antlers.addOrReplaceChild("small", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5385F, -7.614F, 2.5716F, -0.0873F, 0.0436F, -0.1745F));

		PartDefinition cube_r12 = small.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 56).addBox(0.3F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5385F, 2.614F, -3.5716F, 0.4363F, -0.1309F, 0.2618F));

		PartDefinition cube_r13 = small.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(29, 56).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5385F, 2.614F, -3.5716F, 0.4363F, -0.1309F, 0.2618F));

		PartDefinition cube_r14 = small.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(29, 55).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5385F, 4.614F, -4.5716F, 0.4363F, -0.1309F, 0.2618F));

		PartDefinition cube_r15 = small.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(30, 56).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5385F, 6.614F, -1.5716F, 0.4363F, -0.1309F, 0.2618F));

		PartDefinition antlers2 = head.addOrReplaceChild("antlers2", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition small2 = antlers2.addOrReplaceChild("small2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5385F, -7.614F, 2.5716F, -0.0873F, -0.0436F, 0.1745F));

		PartDefinition cube_r16 = small2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(29, 61).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5385F, 2.614F, -3.5716F, 0.4363F, 0.1309F, -0.2618F));

		PartDefinition cube_r17 = small2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(29, 60).mirror().addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5385F, 4.614F, -4.5716F, 0.4363F, 0.1309F, -0.2618F));

		PartDefinition cube_r18 = small2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(30, 56).mirror().addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5385F, 6.614F, -1.5716F, 0.4363F, 0.1309F, -0.2618F));

		PartDefinition cube_r19 = small2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(30, 61).mirror().addBox(-1.3F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5385F, 2.614F, -3.5716F, 0.4363F, 0.1309F, -0.2618F));

		PartDefinition big2 = antlers2.addOrReplaceChild("big2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.5385F, -7.614F, 4.5716F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r20 = big2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(29, 58).mirror().addBox(-1.0F, -1.386F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.2252F, -2.3202F, -3.7736F, -0.5672F, -0.3054F, -0.2618F));

		PartDefinition cube_r21 = big2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(29, 59).mirror().addBox(-1.0F, -1.386F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.7735F, -2.1509F, 0.932F, 0.0436F, 0.0F, -0.6981F));

		PartDefinition cube_r22 = big2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(29, 59).mirror().addBox(-1.0F, -1.386F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9615F, -1.5756F, -0.3007F, 0.0436F, 0.0F, 0.3054F));

		PartDefinition cube_r23 = big2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 57).mirror().addBox(-1.0F, -1.386F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -1.0F, -2.0F, 0.0436F, -0.3054F, -0.2618F));

		PartDefinition cube_r24 = big2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 57).mirror().addBox(-1.0F, -1.386F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, -1.0F, 0.0436F, -0.3054F, -0.2618F));

		PartDefinition cube_r25 = big2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(25, 53).mirror().addBox(-5.0F, -1.386F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 1.0F, 0.0F, 0.0436F, -0.3054F, -0.4363F));

		PartDefinition cube_r26 = big2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(29, 58).mirror().addBox(-1.0F, -1.386F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.2252F, 0.6798F, -2.7736F, -0.5672F, -0.3054F, -0.2618F));

		PartDefinition cube_r27 = big2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(29, 59).mirror().addBox(-1.0F, -0.886F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0436F, -0.3054F, -0.2618F));

		PartDefinition cube_r28 = big2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(28, 58).mirror().addBox(-1.0F, -4.386F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 1.0F, 0.0F, 0.0436F, -0.3054F, -0.2618F));

		PartDefinition cube_r29 = big2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(25, 53).mirror().addBox(-3.0F, -0.686F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -1.0F, 0.0F, 0.0436F, -0.3054F, -0.3927F));

		PartDefinition cube_r30 = big2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(28, 54).mirror().addBox(-1.0F, -8.0F, -1.001F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5385F, 7.614F, -1.5716F, -0.3927F, -0.3491F, 0.0F));

		PartDefinition front_right_leg = caribou.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-1.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 8.0F, 9.0F));

		PartDefinition front_left_leg = caribou.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 8.0F, 20.0F));

		PartDefinition back_left_leg = caribou.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(48, 35).mirror().addBox(-3.0F, -1.0F, -4.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 8.0F, 9.0F));

		PartDefinition back_right_leg = caribou.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(48, 35).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, 20.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(CaribouAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((CaribouEntity) entity).idleAnimationState, CaribouAnimation.Idle, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		caribou.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return caribou;
	}
}