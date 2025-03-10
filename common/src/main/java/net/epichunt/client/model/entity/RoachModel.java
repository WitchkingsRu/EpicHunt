package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.RoachEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.RoachAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RoachModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart roach;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart fin;
	private final ModelPart body2;
	private final ModelPart tail;

	public RoachModel(ModelPart root) {
		this.roach = root.getChild("roach");
		this.head = this.roach.getChild("head");
		this.body1 = this.roach.getChild("body1");
		this.fin = this.body1.getChild("fin");
		this.body2 = this.roach.getChild("body2");
		this.tail = this.body2.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roach = partdefinition.addOrReplaceChild("roach", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = roach.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 17).addBox(-1.5F, -6.2F, -10.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(-1.0F, -5.2F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 3.0F));

		PartDefinition body1 = roach.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(4, 3).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 27).mirror().addBox(0.5F, -1.2F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 27).addBox(-0.5F, -1.2F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 4.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = body1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 27).addBox(-2.5F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r4 = body1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(2.5F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition fin = body1.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(20, 23).addBox(0.0F, -3.0F, -0.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.0F));

		PartDefinition body2 = roach.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(5, 19).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 26).addBox(0.0F, 3.0F, -1.5F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 18).addBox(0.0F, -1.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((RoachEntity) entity).idleAnimationState, RoachAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.roach.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		roach.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return roach;
	}
}