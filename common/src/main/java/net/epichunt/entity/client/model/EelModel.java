package net.epichunt.entity.client.model;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.EelEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.EelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class EelModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart eel;
	private final ModelPart body5;
	private final ModelPart tail2;
	private final ModelPart tail;
	private final ModelPart body4;
	private final ModelPart body3;
	private final ModelPart body2;
	private final ModelPart body1;
	private final ModelPart head;

	public EelModel(ModelPart root) {
		this.eel = root.getChild("eel");
		this.body5 = this.eel.getChild("body5");
		this.tail2 = this.eel.getChild("tail2");
		this.tail = this.eel.getChild("tail");
		this.body4 = this.eel.getChild("body4");
		this.body3 = this.eel.getChild("body3");
		this.body2 = this.eel.getChild("body2");
		this.body1 = this.eel.getChild("body1");
		this.head = this.eel.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eel = partdefinition.addOrReplaceChild("eel", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 4.0F));

		PartDefinition body5 = eel.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, -1.5F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, 3.5F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 6.0F));

		PartDefinition tail2 = eel.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(11, 12).addBox(-0.99F, -0.5F, -0.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 14).addBox(0.0F, -2.0F, 1.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		PartDefinition tail = eel.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 12).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 14).addBox(0.0F, 2.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 8).addBox(0.0F, -2.0F, 3.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(11, 13).addBox(-0.99F, 0.0F, 0.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 10.0F));

		PartDefinition body4 = eel.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.99F, 0.0F, -1.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, -1.5F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, 3.5F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition body3 = eel.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, -1.5F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 4).addBox(0.0F, 3.5F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

		PartDefinition body2 = eel.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(20, 0).addBox(-0.99F, 0.0F, -0.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 4).addBox(0.0F, -1.5F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 8).addBox(0.0F, 3.5F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -6.0F));

		PartDefinition body1 = eel.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(20, 24).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -10.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(23, 21).mirror().addBox(-1.3F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(23, 21).addBox(-1.7F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, 1.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition head = eel.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -10.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((EelEntity) entity).idleAnimationState, EelAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.eel.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		eel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return eel;
	}
}