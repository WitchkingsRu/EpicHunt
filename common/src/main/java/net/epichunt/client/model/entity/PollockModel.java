package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.PollockEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.PollockAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PollockModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart pollock;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart fin;
	private final ModelPart body2;
	private final ModelPart tail;

	public PollockModel(ModelPart root) {
		this.pollock = root.getChild("pollock");
		this.head = this.pollock.getChild("head");
		this.body1 = this.pollock.getChild("body1");
		this.fin = this.body1.getChild("fin");
		this.body2 = this.pollock.getChild("body2");
		this.tail = this.body2.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition pollock = partdefinition.addOrReplaceChild("pollock", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = pollock.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 5).addBox(-1.5F, -5.5F, -10.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(23, 1).addBox(-1.5F, -4.5F, -11.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition body1 = pollock.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 28).addBox(-1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 27).addBox(-1.3F, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r3 = body1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(1.3F, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition cube_r4 = body1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(6, 28).mirror().addBox(1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition fin = body1.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body2 = pollock.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(18, 21).addBox(0.0F, -2.0F, 1.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(16, 22).addBox(0.0F, 3.0F, 1.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 16).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((PollockEntity) entity).idleAnimationState, PollockAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.pollock.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		pollock.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return pollock;
	}
}