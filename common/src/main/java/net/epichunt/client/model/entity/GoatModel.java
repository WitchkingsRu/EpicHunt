package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.CaribouEntity;
import net.epichunt.entity.animals.GoatEntity;
import net.epichunt.entity.animations.CaribouAnimation;
import net.epichunt.entity.animations.GoatAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GoatModel<T extends GoatEntity> extends HierarchicalModel<T> {
	private final ModelPart goat;
	private final ModelPart leg4;
	private final ModelPart leg3;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart left_horn;
	private final ModelPart right_horn;
	private final ModelPart mirror;

	public GoatModel(ModelPart root) {
		this.goat = root.getChild("goat");
		this.leg4 = this.goat.getChild("leg4");
		this.leg3 = this.goat.getChild("leg3");
		this.leg2 = this.goat.getChild("leg2");
		this.leg1 = this.goat.getChild("leg1");
		this.body = this.goat.getChild("body");
		this.head = this.goat.getChild("head");
		this.nose = this.head.getChild("nose");
		this.left_horn = this.head.getChild("left_horn");
		this.right_horn = this.head.getChild("right_horn");
		this.mirror = this.head.getChild("mirror");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition goat = partdefinition.addOrReplaceChild("goat", CubeListBuilder.create(), PartPose.offset(0.5F, 14.0F, 0.0F));

		PartDefinition leg4 = goat.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(35, 2).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition leg3 = goat.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(49, 2).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -6.0F));

		PartDefinition leg2 = goat.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(35, 3).addBox(0.0F, 1.0F, 1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition leg1 = goat.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(49, 3).addBox(0.0F, 1.0F, 1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 4.0F));

		PartDefinition body = goat.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -16.0F, -7.0F, 9.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 10.0F, 0.0F));

		PartDefinition head = goat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 52).addBox(-0.5F, 2.0F, -8.0F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(2, 61).addBox(-6.0F, -4.0F, -3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -6.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -2.0F));

		PartDefinition nose_r1 = nose.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(34, 46).addBox(-2.5F, -3.0F, -9.0F, 5.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0F, 1.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 6.0F));

		PartDefinition left_horn_r1 = left_horn.addOrReplaceChild("left_horn_r1", CubeListBuilder.create().texOffs(12, 55).addBox(-0.51F, -8.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -8.0F, -9.0F, -0.6109F, 0.48F, 0.0F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 6.0F));

		PartDefinition right_horn_r1 = right_horn.addOrReplaceChild("right_horn_r1", CubeListBuilder.create().texOffs(12, 55).addBox(-1.49F, -8.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.0F, -9.0F, -0.6109F, -0.48F, 0.0F));

		PartDefinition mirror = head.addOrReplaceChild("mirror", CubeListBuilder.create().texOffs(2, 61).mirror().addBox(2.5F, -19.0F, -9.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, 15.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(GoatEntity goat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.head.getChild("left_horn").visible = goat.hasLeftHorn();
		this.head.getChild("right_horn").visible = goat.hasRightHorn();
		float k = goat.getRammingXHeadRot();
		if (k != 0.0F) {
			this.head.xRot = k;
		}
		this.animateWalk(GoatAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		goat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return goat;
	}
}