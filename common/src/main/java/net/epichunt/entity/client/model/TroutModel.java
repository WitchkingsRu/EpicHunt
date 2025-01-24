package net.epichunt.entity.client.model;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.TroutEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.TroutAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class TroutModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart trout;
	private final ModelPart body2;
	private final ModelPart fins3;
	private final ModelPart tail;
	private final ModelPart fins2;
	private final ModelPart body1;
	private final ModelPart fins;
	private final ModelPart head;

	public TroutModel(ModelPart root) {
		this.trout = root.getChild("trout");
		this.body2 = this.trout.getChild("body2");
		this.fins3 = this.body2.getChild("fins3");
		this.tail = this.body2.getChild("tail");
		this.fins2 = this.body2.getChild("fins2");
		this.body1 = this.trout.getChild("body1");
		this.fins = this.body1.getChild("fins");
		this.head = this.trout.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition trout = partdefinition.addOrReplaceChild("trout", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition body2 = trout.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition fin_left_r1 = body2.addOrReplaceChild("fin_left_r1", CubeListBuilder.create().texOffs(-3, 0).mirror().addBox(1.0F, -1.0F, -8.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 8.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition fin_right_r1 = body2.addOrReplaceChild("fin_right_r1", CubeListBuilder.create().texOffs(-3, 0).addBox(-4.0F, -1.0F, -8.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 8.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition fins3 = body2.addOrReplaceChild("fins3", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, 5.0F, 4.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 8.0F));

		PartDefinition fins2 = body2.addOrReplaceChild("fins2", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition body1 = trout.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -5.0F, -8.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition fin_left_r2 = body1.addOrReplaceChild("fin_left_r2", CubeListBuilder.create().texOffs(-3, 0).mirror().addBox(1.0F, -1.0F, -8.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition fin_right_r2 = body1.addOrReplaceChild("fin_right_r2", CubeListBuilder.create().texOffs(-3, 0).addBox(-4.0F, -1.0F, -8.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition fins = body1.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(0, -1).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -1.0F));

		PartDefinition head = trout.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -4.5F, -11.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((TroutEntity) entity).idleAnimationState, TroutAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.trout.zRot = (float) Math.toRadians(90.0);
			this.body2.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
			this.tail.yRot = (float) Math.sin(ageInTicks * 1.5F) * 0.1F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		trout.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return trout;
	}
}