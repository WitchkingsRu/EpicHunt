package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animations.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class OwlModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart owl;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart wing1;
	private final ModelPart wing2;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail;

	public OwlModel(ModelPart root) {
		this.owl = root.getChild("owl");
		this.leg2 = this.owl.getChild("leg2");
		this.leg1 = this.owl.getChild("leg1");
		this.wing1 = this.owl.getChild("wing1");
		this.wing2 = this.owl.getChild("wing2");
		this.head = this.owl.getChild("head");
		this.body = this.owl.getChild("body");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition owl = partdefinition.addOrReplaceChild("owl", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, -2.0F));

		PartDefinition leg2 = owl.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(26, 28).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(23, 25).mirror().addBox(-1.5F, 3.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 3.0F, 0.0F));

		PartDefinition leg1 = owl.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(26, 28).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 25).addBox(-1.5F, 3.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 0.0F));

		PartDefinition wing1 = owl.addOrReplaceChild("wing1", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, -2.0F));

		PartDefinition cube_r1 = wing1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 1).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.39F, 2.7515F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r2 = wing1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition wing2 = owl.addOrReplaceChild("wing2", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -2.0F));

		PartDefinition cube_r3 = wing2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 1).mirror().addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.39F, 2.7515F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r4 = wing2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition head = owl.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.5F, -2.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -2.0F, -2.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-1.3F, -1.0F, -1.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 31).mirror().addBox(-1.0F, -0.5F, -1.2F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -2.0F, -1.0F, 0.0F, 0.3491F, 0.6981F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -0.5F, -1.2F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, -1.0F, 0.0F, -0.3491F, -0.6981F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 23).addBox(0.3F, -1.0F, -1.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition body = owl.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.5F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.0F, 1.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 2.0F));

		PartDefinition cube_r10 = tail.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(19, 18).mirror().addBox(-0.5F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, -0.4461F, 0.4802F, -0.2174F));

		PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(19, 18).addBox(-1.5F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, -0.4461F, -0.4802F, 0.2174F));

		PartDefinition cube_r12 = tail.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(18, 11).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(OwlAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DuckEntity) entity).flyAnimationState, OwlAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		owl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return owl;
	}
}