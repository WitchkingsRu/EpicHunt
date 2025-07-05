package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.entity.animations.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SharkModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart shark;
	private final ModelPart fin2;
	private final ModelPart fin1;
	private final ModelPart body1;
	private final ModelPart fin3;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart fin4;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart fin5;
	private final ModelPart head;
	private final ModelPart jaw;

	public SharkModel(ModelPart root) {
		this.shark = root.getChild("shark");
		this.fin2 = this.shark.getChild("fin2");
		this.fin1 = this.shark.getChild("fin1");
		this.body1 = this.shark.getChild("body1");
		this.fin3 = this.body1.getChild("fin3");
		this.body2 = this.body1.getChild("body2");
		this.body3 = this.body2.getChild("body3");
		this.fin4 = this.body3.getChild("fin4");
		this.tail = this.body3.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.fin5 = this.body3.getChild("fin5");
		this.head = this.body1.getChild("head");
		this.jaw = this.head.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shark = partdefinition.addOrReplaceChild("shark", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition fin2 = shark.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offset(3.0F, -3.0F, -6.0F));

		PartDefinition cube_r1 = fin2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, -4).mirror().addBox(-1.5F, -3.0F, -1.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, -0.7079F, 0.5538F, -0.4525F));

		PartDefinition fin1 = shark.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offset(-3.0F, -3.0F, -5.0F));

		PartDefinition cube_r2 = fin1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -4).addBox(1.5F, -3.0F, -0.5F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, -1.0F, -0.7079F, -0.5538F, 0.4525F));

		PartDefinition body1 = shark.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(1, 0).addBox(-2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -6.0F));

		PartDefinition fin3 = body1.addOrReplaceChild("fin3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition cube_r3 = fin3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-5, 20).addBox(-4.5F, 0.0F, -1.0F, 4.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(27, 18).addBox(-2.0F, 0.5F, -2.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(22, 0).addBox(-1.5F, 1.1F, -1.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition fin4 = body3.addOrReplaceChild("fin4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r4 = fin4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(9, 24).addBox(-2.5F, 0.0F, -1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.5898F, -1.6596F, -0.5236F, 1.2217F, -1.5708F));

		PartDefinition tail = body3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 24).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 1.0F, 4.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r5 = tail2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-6, 26).addBox(-3.5F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, 0.0F, 1.3526F, 0.0F));

		PartDefinition cube_r6 = tail2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(10, 21).addBox(-1.5F, -0.01F, -2.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0F, 1.0F, 0.0F, -1.3526F, 0.0F));

		PartDefinition fin5 = body3.addOrReplaceChild("fin5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r7 = fin5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(9, 24).addBox(-2.5F, 0.0F, -1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.5898F, -1.6596F, 0.5236F, 1.2217F, -1.5708F));

		PartDefinition head = body1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 8).addBox(-2.0F, -0.5F, -4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 0).addBox(-1.5F, 0.0F, -8.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(52, 7).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((SturgeonEntity) entity).idleAnimationState, SharkAnimation.swim, ageInTicks, 1f);
		this.animate(((WisentEntity) entity).attackAnimationState, SharkAnimation.attack, ageInTicks, 1f);
//		if (!entity.isInWater()) {
//			this.sturgeon.zRot = (float) Math.toRadians(90.0);
//		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		shark.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return shark;
	}
}