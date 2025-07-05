package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.aquatic.NarwhalEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.NarwhalAnimation;
import net.epichunt.entity.animations.SturgeonAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class NarwhalModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart narwhal;
	private final ModelPart fin2;
	private final ModelPart fin1;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart head;
	private final ModelPart horn;

	public NarwhalModel(ModelPart root) {
		this.narwhal = root.getChild("narwhal");
		this.fin2 = this.narwhal.getChild("fin2");
		this.fin1 = this.narwhal.getChild("fin1");
		this.body1 = this.narwhal.getChild("body1");
		this.body2 = this.body1.getChild("body2");
		this.body3 = this.body2.getChild("body3");
		this.tail = this.body3.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.head = this.body1.getChild("head");
		this.horn = this.narwhal.getChild("horn");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition narwhal = partdefinition.addOrReplaceChild("narwhal", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition fin2 = narwhal.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offset(3.0F, -3.0F, -6.0F));

		PartDefinition cube_r1 = fin2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, -2).addBox(1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, -0.4363F, 0.4363F, 0.0F));

		PartDefinition fin1 = narwhal.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offset(-3.0F, -3.0F, -5.0F));

		PartDefinition cube_r2 = fin1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -2).addBox(1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, -1.0F, -0.4363F, -0.4363F, 0.0F));

		PartDefinition body1 = narwhal.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -6.0F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(26, 18).addBox(-2.5F, 0.1F, -2.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(23, 0).addBox(-2.0F, 0.2F, -1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail = body3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 25).addBox(-1.5F, 0.3F, -1.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition cube_r3 = tail2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 22).addBox(-2.5F, -1.51F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = tail2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(13, 23).addBox(-2.5F, -1.5F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head = body1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 7).addBox(-2.5F, -0.5F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(57, 14).addBox(-1.0F, 0.5F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-0.5F, 1.0F, -16.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition horn = narwhal.addOrReplaceChild("horn", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((NarwhalEntity) entity).idleAnimationState, NarwhalAnimation.swim, ageInTicks, 1f);
//		if (!entity.isInWater()) {
//			this.sturgeon.zRot = (float) Math.toRadians(90.0);
//		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		narwhal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return narwhal;
	}
}