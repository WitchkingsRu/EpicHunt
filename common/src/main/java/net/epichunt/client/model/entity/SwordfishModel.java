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

public class SwordfishModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart swordfish;
	private final ModelPart fin2;
	private final ModelPart fin1;
	private final ModelPart body1;
	private final ModelPart fin3;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart fin4;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart head;

	public SwordfishModel(ModelPart root) {
		this.swordfish = root.getChild("swordfish");
		this.fin2 = this.swordfish.getChild("fin2");
		this.fin1 = this.swordfish.getChild("fin1");
		this.body1 = this.swordfish.getChild("body1");
		this.fin3 = this.body1.getChild("fin3");
		this.body2 = this.body1.getChild("body2");
		this.body3 = this.body2.getChild("body3");
		this.fin4 = this.body3.getChild("fin4");
		this.tail = this.body3.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.head = this.body1.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition swordfish = partdefinition.addOrReplaceChild("swordfish", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition fin2 = swordfish.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offset(3.0F, -3.0F, -6.0F));

		PartDefinition cube_r1 = fin2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, -2).mirror().addBox(-1.5F, -3.0F, -1.7F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, -0.577F, 0.4229F, -0.3216F));

		PartDefinition fin1 = swordfish.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offset(-3.0F, -3.0F, -5.0F));

		PartDefinition cube_r2 = fin1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -2).addBox(1.5F, -3.0F, -1.7F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, -1.0F, -0.577F, -0.4229F, 0.3216F));

		PartDefinition body1 = swordfish.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(2, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -6.0F));

		PartDefinition fin3 = body1.addOrReplaceChild("fin3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition cube_r3 = fin3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-6, 19).addBox(-3.5F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.0F, 1.2217F, -1.5708F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(28, 18).addBox(-1.49F, 0.1F, -2.0F, 3.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.2F, -1.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition fin4 = body3.addOrReplaceChild("fin4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r4 = fin4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 23).addBox(-2.5F, 0.0F, -1.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5898F, -1.6596F, 0.0F, 1.2217F, -1.5708F));

		PartDefinition tail = body3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(49, 25).addBox(-1.0F, 0.3F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 1.0F, 4.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r5 = tail2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 21).addBox(-2.5F, 0.0F, -1.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -1.0F, 0.0F, 1.1345F, 0.0F));

		PartDefinition cube_r6 = tail2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(13, 23).addBox(-2.5F, -0.01F, -1.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.0F, -0.9163F, 0.0F));

		PartDefinition head = body1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(39, 8).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(57, 13).addBox(-0.5F, 0.5F, -6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(0.0F, 0.5F, -18.0F, 0.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((SturgeonEntity) entity).idleAnimationState, SwordfishAnimation.swim, ageInTicks, 1f);
//		if (!entity.isInWater()) {
//			this.sturgeon.zRot = (float) Math.toRadians(90.0);
//		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		swordfish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return swordfish;
	}
}