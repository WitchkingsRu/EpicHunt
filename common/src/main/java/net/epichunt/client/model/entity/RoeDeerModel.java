package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.RoeDeerEntity;
import net.epichunt.entity.animations.DeerAnimation;
import net.epichunt.entity.animations.RoeDeerAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RoeDeerModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart roe_deer;
	private final ModelPart body;
	private final ModelPart head_group;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart antlers;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;

	public RoeDeerModel(ModelPart root) {
		this.roe_deer = root.getChild("roe_deer");
		this.body = this.roe_deer.getChild("body");
		this.head_group = this.roe_deer.getChild("head_group");
		this.neck = this.head_group.getChild("neck");
		this.head = this.head_group.getChild("head");
		this.antlers = this.head.getChild("antlers");
		this.front_right_leg = this.roe_deer.getChild("front_right_leg");
		this.front_left_leg = this.roe_deer.getChild("front_left_leg");
		this.back_left_leg = this.roe_deer.getChild("back_left_leg");
		this.back_right_leg = this.roe_deer.getChild("back_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roe_deer = partdefinition.addOrReplaceChild("roe_deer", CubeListBuilder.create(), PartPose.offset(-1.0F, 3.0F, -7.0F));

		PartDefinition body = roe_deer.addOrReplaceChild("body", CubeListBuilder.create().texOffs(6, 4).addBox(-2.0F, 1.0F, -10.0F, 6.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(5, 8).addBox(-0.5F, 1.0F, 6.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(7, 11).addBox(0.0F, 1.3F, 7.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 8.0F));

		PartDefinition head_group = roe_deer.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition neck = head_group.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(27, 31).addBox(-1.5F, -4.2554F, -1.653F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 43).addBox(-2.5F, 3.1F, -7.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(16, 46).addBox(0.5F, -0.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 46).mirror().addBox(-4.5F, -0.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(1, 54).addBox(-3.0F, 1.0F, -5.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -10.0F, -1.0F));

		PartDefinition antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create().texOffs(28, 42).addBox(0.0F, -5.7588F, 0.3681F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 42).mirror().addBox(-3.0F, -5.7588F, 0.3681F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = antlers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 42).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(28, 42).addBox(2.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r2 = antlers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 42).mirror().addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -2.0F, 1.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r3 = antlers.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 42).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition front_right_leg = roe_deer.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(52, 37).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 10.0F, 0.0F));

		PartDefinition front_left_leg = roe_deer.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(52, 37).mirror().addBox(-1.5F, 0.0F, 0.99F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 10.0F, 10.0F));

		PartDefinition back_left_leg = roe_deer.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(52, 37).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 10.0F, 0.0F));

		PartDefinition back_right_leg = roe_deer.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(52, 37).addBox(-0.5F, 0.0F, 0.99F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 10.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(RoeDeerAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((RoeDeerEntity) entity).idleAnimationState, RoeDeerAnimation.Idle, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		roe_deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return roe_deer;
	}
}