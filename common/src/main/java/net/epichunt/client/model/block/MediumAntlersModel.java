package net.epichunt.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class MediumAntlersModel extends Model{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart root;

	public MediumAntlersModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition antlers = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 24.0F, -6.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r1 = antlers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 6).addBox(-5.0F, -1.0F, -1.3F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9134F, -6.2954F, -0.2059F, -0.8727F, 0.7854F, -0.4363F));

		PartDefinition cube_r2 = antlers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 15).addBox(-2.0F, -1.0F, -1.4F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0866F, -6.2954F, 1.7941F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r3 = antlers.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 17).addBox(-2.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0499F, -10.305F, -0.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r4 = antlers.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 17).addBox(-2.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0499F, -6.305F, -2.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r5 = antlers.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 2).addBox(-3.0F, -1.0F, -1.3F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.9134F, -9.2954F, 1.7941F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r6 = antlers.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -1.0F, -1.3F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.9134F, -6.2954F, -0.2059F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r7 = antlers.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(4, 8).addBox(-1.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, 1.0F, -0.8727F, -0.7854F, 0.4363F));

		PartDefinition cube_r8 = antlers.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 8).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, 1.0F, 1.0377F, -0.8805F, -1.5037F));

		PartDefinition cube_r9 = antlers.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(14, 4).addBox(1.0F, -3.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0499F, -11.305F, -0.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r10 = antlers.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 17).addBox(1.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0499F, -10.305F, -0.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r11 = antlers.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 8).addBox(1.0F, -2.0F, -1.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0499F, -6.305F, -2.5631F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r12 = antlers.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 13).addBox(0.0F, -1.0F, -1.4F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9134F, -6.2954F, 1.7941F, -2.4005F, -0.6395F, 2.3779F));

		PartDefinition cube_r13 = antlers.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(8, 8).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 1.0377F, 0.8805F, 1.5037F));

		PartDefinition cube_r14 = antlers.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(8, 13).addBox(-2.0F, -3.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0499F, -11.305F, -0.5631F, -2.4005F, 0.6395F, -2.3779F));

		PartDefinition cube_r15 = antlers.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -1.3F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9134F, -9.2954F, 1.7941F, -0.8727F, 0.7854F, -0.4363F));

		PartDefinition cube_r16 = antlers.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -10.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, -0.8727F, 0.7854F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public ModelPart root() {
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		this.root.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}