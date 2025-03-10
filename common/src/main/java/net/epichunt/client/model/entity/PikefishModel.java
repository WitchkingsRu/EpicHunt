package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.PikefishEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.PikefishAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PikefishModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart pikefish;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart tail;
	private final ModelPart fins;

	public PikefishModel(ModelPart root) {
		this.pikefish = root.getChild("pikefish");
		this.head = this.pikefish.getChild("head");
		this.body1 = this.pikefish.getChild("body1");
		this.body2 = this.pikefish.getChild("body2");
		this.tail = this.body2.getChild("tail");
		this.fins = this.body2.getChild("fins");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition pikefish = partdefinition.addOrReplaceChild("pikefish", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = pikefish.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 17).addBox(-1.5F, -6.5F, -10.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-1.5F, -5.5F, -13.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition body1 = pikefish.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -8.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 26).addBox(-1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 6.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 26).addBox(1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition body2 = pikefish.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition cube_r3 = body2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 26).mirror().addBox(-1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 6.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r4 = body2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 26).mirror().addBox(1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 20).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.0F));

		PartDefinition fins = body2.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 5.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((PikefishEntity) entity).idleAnimationState, PikefishAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.pikefish.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		pikefish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return pikefish;
	}
}