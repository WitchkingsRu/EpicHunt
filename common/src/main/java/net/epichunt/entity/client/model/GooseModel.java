package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animations.DoeAnimation;
import net.epichunt.entity.animations.DuckAnimation;
import net.epichunt.entity.animations.GooseAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GooseModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart goose;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart head_group;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightwing;
	private final ModelPart leftwing;

	public GooseModel(ModelPart root) {
		this.goose = root.getChild("goose");
		this.rightleg = this.goose.getChild("rightleg");
		this.leftleg = this.goose.getChild("leftleg");
		this.head_group = this.goose.getChild("head_group");
		this.head = this.head_group.getChild("head");
		this.body = this.goose.getChild("body");
		this.rightwing = this.body.getChild("rightwing");
		this.leftwing = this.body.getChild("leftwing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition goose = partdefinition.addOrReplaceChild("goose", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rightleg = goose.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(2, 16).addBox(1.7F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-4, 12).mirror().addBox(0.7F, 3.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -3.0F, 1.0F));

		PartDefinition leftleg = goose.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(2, 16).mirror().addBox(-2.7F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-4, 12).addBox(-3.75F, 3.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 1.0F));

		PartDefinition head_group = goose.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -1.0F));

		PartDefinition neck_r1 = head_group.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(0, 26).addBox(0.5F, -4.2F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(10, 29).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(5, 29).addBox(-0.489F, -1.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 28).addBox(-0.49F, -1.5F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 28).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition body = goose.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 0).addBox(-1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.0F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-1.2F, -0.9258F, -0.6777F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(24, 2).mirror().addBox(-1.2F, -0.9258F, 2.3223F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(22, 0).addBox(0.2F, -0.9922F, -0.7014F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 2).addBox(0.2F, -0.9922F, 2.2986F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		if (GooseEntity.isAngry) {
			this.animateWalk(GooseAnimation.WalkAngry, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animateWalk(GooseAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		}
		this.animate(((GooseEntity) entity).flyAnimationState, GooseAnimation.Fly, ageInTicks, 1f);
		this.animate(((GooseEntity) entity).attackAnimationState, GooseAnimation.Attack, ageInTicks, 1f);
		if (entity.isInWater()) {
			this.animate(((GooseEntity) entity).swimAnimationState, GooseAnimation.Swim, ageInTicks, 1f);
		}
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		goose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return goose;
	}
}