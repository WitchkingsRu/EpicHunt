package net.epichunt.entity.client.model;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.CatfishEntity;
import net.epichunt.entity.animations.BassAnimation;
import net.epichunt.entity.animations.BoarAnimation;
import net.epichunt.entity.animations.CatfishAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CatfishModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart catfish;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart us2;
	private final ModelPart us1;
	private final ModelPart body1;
	private final ModelPart fins;
	private final ModelPart body2;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;

	public CatfishModel(ModelPart root) {
		this.catfish = root.getChild("catfish");
		this.head = this.catfish.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.us2 = this.head.getChild("us2");
		this.us1 = this.head.getChild("us1");
		this.body1 = this.catfish.getChild("body1");
		this.fins = this.body1.getChild("fins");
		this.body2 = this.catfish.getChild("body2");
		this.tail1 = this.catfish.getChild("tail1");
		this.tail2 = this.catfish.getChild("tail2");
		this.tail3 = this.catfish.getChild("tail3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition catfish = partdefinition.addOrReplaceChild("catfish", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, -2.0F));

		PartDefinition head = catfish.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, 0.8F, -6.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 13).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(49, 19).mirror().addBox(-2.51F, 2.7F, -2.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -2.0F, -12.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(50, 5).addBox(-2.0F, -0.2F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -3.0F));

		PartDefinition us2 = head.addOrReplaceChild("us2", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, -4.0F));

		PartDefinition cube_r1 = us2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 21).mirror().addBox(-1.0F, -1.5F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.0F, 0.9599F, 0.5236F));

		PartDefinition cube_r2 = us2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 20).mirror().addBox(-1.0F, -1.5F, -1.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.9599F, 0.1309F));

		PartDefinition cube_r3 = us2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 20).mirror().addBox(-1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9815F, 1.1991F, 3.709F, -0.3491F, 0.3054F, 0.0F));

		PartDefinition us1 = head.addOrReplaceChild("us1", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, -4.0F));

		PartDefinition cube_r4 = us1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 21).addBox(1.0F, -1.5F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.0F, -0.9599F, -0.5236F));

		PartDefinition cube_r5 = us1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 20).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9811F, 1.1992F, 3.7084F, -0.3491F, -0.3054F, 0.0F));

		PartDefinition cube_r6 = us1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(54, 20).addBox(1.0F, -1.5F, -1.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, -0.9599F, -0.1309F));

		PartDefinition body1 = catfish.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -11.0F));

		PartDefinition cube_r7 = body1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, -3).mirror().addBox(-1.0F, -4.0F, -1.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 5.0F, 2.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r8 = body1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, -3).addBox(1.0F, -4.0F, -1.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 5.0F, 2.0F, 0.0F, -0.48F, 0.0F));

		PartDefinition fins = body1.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -3.5F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition body2 = catfish.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, -0.5F, 0.0F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(0.0F, 0.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition tail1 = catfish.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 41).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 9.0F));

		PartDefinition tail2 = catfish.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 53).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(3, 56).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 13.0F));

		PartDefinition tail3 = catfish.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(45, 48).addBox(0.0F, -0.75F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 17.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((CatfishEntity) entity).idleAnimationState, CatfishAnimation.swim, ageInTicks, 1f);
		if (!entity.isInWater()) {
			this.catfish.zRot = (float) Math.toRadians(90.0);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		catfish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return catfish;
	}
}