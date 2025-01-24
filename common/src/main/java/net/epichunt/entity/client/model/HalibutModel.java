package net.epichunt.entity.client.model;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.HalibutEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.HalibutAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class HalibutModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart halibut;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart fin;
	private final ModelPart body2;
	private final ModelPart tail;
	private final ModelPart halibut2;
	private final ModelPart body3;

	public HalibutModel(ModelPart root) {
		this.halibut = root.getChild("halibut");
		this.head = this.halibut.getChild("head");
		this.body1 = this.halibut.getChild("body1");
		this.fin = this.body1.getChild("fin");
		this.body2 = this.halibut.getChild("body2");
		this.tail = this.body2.getChild("tail");
		this.halibut2 = root.getChild("halibut2");
		this.body3 = this.halibut2.getChild("body3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition halibut = partdefinition.addOrReplaceChild("halibut", CubeListBuilder.create(), PartPose.offset(1.0F, 22.0F, 0.0F));

		PartDefinition head = halibut.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 14).addBox(-2.0F, -6.2F, -10.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(-2.0F, -5.2F, -11.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 3.0F));

		PartDefinition body1 = halibut.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.5F, 0.0F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(11, 19).addBox(-1.0F, -4.5F, -1.5F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(11, 22).addBox(-1.0F, 2.0F, -1.5F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(4.0F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 3.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition fin = body1.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 4.0F));

		PartDefinition body2 = halibut.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(7, 19).addBox(-1.0F, -4.5F, -0.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(6, 26).addBox(-1.0F, 3.0F, -0.5F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 15).addBox(-1.0F, -1.0F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition halibut2 = partdefinition.addOrReplaceChild("halibut2", CubeListBuilder.create(), PartPose.offset(-1.0F, 25.0F, 0.0F));

		PartDefinition body3 = halibut2.addOrReplaceChild("body3", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -5.0F));

		PartDefinition cube_r2 = body3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 27).addBox(-4.0F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((HalibutEntity) entity).idleAnimationState, HalibutAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.halibut.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		halibut.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		halibut2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return halibut;
	}
}