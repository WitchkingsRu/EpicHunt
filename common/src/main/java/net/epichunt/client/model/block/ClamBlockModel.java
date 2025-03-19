package net.epichunt.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;


import net.minecraft.client.model.Model;

public class ClamBlockModel extends Model {
	private final ModelPart head;
	private final ModelPart clam3;
	private final ModelPart clam2;
	private final ModelPart clam4;
	private final ModelPart clam5;
	private final ModelPart clam7;
	private final ModelPart clam8;
	private final ModelPart clam6;
	private final ModelPart clam1;

	public ClamBlockModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.head = root.getChild("head");
		this.clam3 = this.head.getChild("clam3");
		this.clam2 = this.head.getChild("clam2");
		this.clam4 = this.head.getChild("clam4");
		this.clam5 = this.head.getChild("clam5");
		this.clam7 = this.head.getChild("clam7");
		this.clam8 = this.head.getChild("clam8");
		this.clam6 = this.head.getChild("clam6");
		this.clam1 = this.head.getChild("clam1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition clam3 = head.addOrReplaceChild("clam3", CubeListBuilder.create().texOffs(12, 15).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(15, 19).addBox(-1.5F, -1.02F, 1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -1.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition clam2 = head.addOrReplaceChild("clam2", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -1.5F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 19).addBox(-1.5F, -1.51F, 1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition clam4 = head.addOrReplaceChild("clam4", CubeListBuilder.create().texOffs(20, 7).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 11).addBox(-1.5F, -1.01F, 1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, 0.0F, 2.9234F, 0.0F));

		PartDefinition clam5 = head.addOrReplaceChild("clam5", CubeListBuilder.create().texOffs(8, 7).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(11, 11).addBox(-1.5F, -1.01F, 1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 1.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition clam7 = head.addOrReplaceChild("clam7", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 11).addBox(-0.5F, -0.51F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -3.0F, 0.0F, 0.7418F, 0.0F));

		PartDefinition clam8 = head.addOrReplaceChild("clam8", CubeListBuilder.create().texOffs(20, 1).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(-0.5F, -0.51F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 3.0F, 0.0F, -2.4435F, 0.0F));

		PartDefinition clam6 = head.addOrReplaceChild("clam6", CubeListBuilder.create().texOffs(12, 1).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 4).addBox(-0.5F, -0.51F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -3.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition clam1 = head.addOrReplaceChild("clam1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 4).addBox(-1.5F, -1.01F, 1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public ModelPart root() {
		return this.head;
	}
	public void setVisibleByAge(int age) {
		clam1.visible = false;
		clam2.visible = false;
		clam3.visible = false;
		clam4.visible = false;
		clam5.visible = false;
		clam6.visible = false;
		clam7.visible = false;
		clam8.visible = false;

		if (age >= 1) clam1.visible = true;
		if (age >= 2) clam2.visible = true;
		if (age >= 3) clam3.visible = true;
		if (age >= 4) clam4.visible = true;
		if (age >= 5) clam5.visible = true;
		if (age >= 6) clam6.visible = true;
		if (age >= 7) {clam7.visible = true;
			clam8.visible = true;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		this.head.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}