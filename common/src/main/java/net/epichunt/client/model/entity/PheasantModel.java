package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animals.PheasantEntity;
import net.epichunt.entity.animations.DoeAnimation;
import net.epichunt.entity.animations.DuckAnimation;
import net.epichunt.entity.animations.GooseAnimation;
import net.epichunt.entity.animations.PheasantAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PheasantModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart pheasant;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head_group;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public PheasantModel(ModelPart root) {
		this.pheasant = root.getChild("pheasant");
		this.body = this.pheasant.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head_group = this.pheasant.getChild("head_group");
		this.head = this.head_group.getChild("head");
		this.leftwing = this.pheasant.getChild("leftwing");
		this.rightwing = this.pheasant.getChild("rightwing");
		this.leftleg = this.pheasant.getChild("leftleg");
		this.rightleg = this.pheasant.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition pheasant = partdefinition.addOrReplaceChild("pheasant", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = pheasant.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -1.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 10).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 10).addBox(-1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition head_group = pheasant.addOrReplaceChild("head_group", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -3.6F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -1.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 26).addBox(-1.5F, -2.6F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 29).addBox(-0.5F, -1.49F, -2.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 29).addBox(-0.51F, -0.5F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 30).addBox(-0.49F, -0.9F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -1.0F));

		PartDefinition leftwing = pheasant.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(22, 0).addBox(0.0F, -0.1404F, -0.2035F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 6).addBox(0.0F, -0.1404F, 3.7965F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0F, -1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightwing = pheasant.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-1.0F, -0.1848F, 0.0179F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 6).mirror().addBox(-1.0F, -0.1848F, 4.0179F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -8.0F, -1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition leftleg = pheasant.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(27, 20).addBox(0.01F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 15).addBox(-1.0F, 4.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 0.0F));

		PartDefinition rightleg = pheasant.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(22, 15).addBox(-2.0F, 4.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 20).mirror().addBox(-0.99F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(PheasantAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((PheasantEntity) entity).flyAnimationState, PheasantAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		pheasant.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return pheasant;
	}
}