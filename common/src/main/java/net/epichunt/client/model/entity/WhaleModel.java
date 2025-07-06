package net.epichunt.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.BoarEntity;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.animals.aquatic.WhaleEntity;
import net.epichunt.entity.animals.fish.BassEntity;
import net.epichunt.entity.animals.fish.SturgeonEntity;
import net.epichunt.entity.animations.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class WhaleModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart whale;
	private final ModelPart fin2;
	private final ModelPart fin1;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart head;

	public WhaleModel(ModelPart root) {
		this.whale = root.getChild("whale");
		this.fin2 = this.whale.getChild("fin2");
		this.fin1 = this.whale.getChild("fin1");
		this.body1 = this.whale.getChild("body1");
		this.body2 = this.body1.getChild("body2");
		this.body3 = this.body2.getChild("body3");
		this.tail = this.body3.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.head = this.body1.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition whale = partdefinition.addOrReplaceChild("whale", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition fin2 = whale.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offset(5.0F, -3.0F, -19.0F));

		PartDefinition cube_r1 = fin2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 74).mirror().addBox(1.0F, -6.0F, -1.0F, 0.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.4363F, 0.4363F, 0.0F));

		PartDefinition fin1 = whale.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offset(-5.0F, -3.0F, -18.0F));

		PartDefinition cube_r2 = fin1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 74).addBox(1.0F, -6.0F, -1.0F, 0.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.0F, -1.0F, -0.4363F, -0.4363F, 0.0F));

		PartDefinition body1 = whale.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -16.0F, 12.0F, 12.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -6.0F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(44, 56).addBox(-5.5F, -4.9F, -1.0F, 11.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 9.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(44, 37).addBox(-5.0F, -3.8F, -1.0F, 10.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));

		PartDefinition tail = body3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(74, 15).addBox(-4.5F, -2.7F, -2.0F, 9.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition cube_r3 = tail2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(74, 0).addBox(-5.5F, -0.51F, -1.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = tail2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 71).addBox(-6.5F, -0.5F, -1.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 4.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head = body1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 55).addBox(-5.5F, -4.5F, -11.0F, 11.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 37).addBox(-5.5F, 0.5F, -11.0F, 11.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -16.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((WhaleEntity) entity).idleAnimationState, WhaleAnimation.swim, ageInTicks, 1f);
//		if (!entity.isInWater()) {
//			this.sturgeon.zRot = (float) Math.toRadians(90.0);
//		}
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		whale.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return whale;
	}
}