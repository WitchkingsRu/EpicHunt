package net.epichunt.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class MusselBlockModel extends Model {
	private final ModelPart head;
	private final ModelPart mussel4;
	private final ModelPart mussel3;
	private final ModelPart mussel5;
	private final ModelPart mussel6;
	private final ModelPart mussel7;
	private final ModelPart mussel2;
	private final ModelPart mussel1;

	public MusselBlockModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.head = root.getChild("head");
		this.mussel4 = this.head.getChild("mussel4");
		this.mussel3 = this.head.getChild("mussel3");
		this.mussel5 = this.mussel3.getChild("mussel5");
		this.mussel6 = this.head.getChild("mussel6");
		this.mussel7 = this.head.getChild("mussel7");
		this.mussel2 = this.head.getChild("mussel2");
		this.mussel1 = this.head.getChild("mussel1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition mussel4 = head.addOrReplaceChild("mussel4", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 13).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 13).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, -0.6069F, -0.1991F, 0.6229F));

		PartDefinition mussel3 = head.addOrReplaceChild("mussel3", CubeListBuilder.create().texOffs(21, 7).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(27, 8).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(27, 8).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 2.0F, 0.0991F, 0.6054F, -0.2405F));

		PartDefinition mussel5 = mussel3.addOrReplaceChild("mussel5", CubeListBuilder.create().texOffs(11, 7).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 8).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(17, 8).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5791F, 0.6054F, -0.2405F));

		PartDefinition mussel6 = head.addOrReplaceChild("mussel6", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 7).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7722F, -0.3333F, 0.3991F));

		PartDefinition mussel7 = head.addOrReplaceChild("mussel7", CubeListBuilder.create().texOffs(21, 0).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(27, 1).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(27, 1).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 1.0F, -0.9029F, 1.2039F, 0.2358F));

		PartDefinition mussel2 = head.addOrReplaceChild("mussel2", CubeListBuilder.create().texOffs(11, 0).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 1).addBox(0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(17, 1).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.4918F, 0.6054F, -0.2405F));

		PartDefinition mussel1 = head.addOrReplaceChild("mussel1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 1).addBox(0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 1).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4333F, -0.05F, 0.121F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public ModelPart root() {
		return this.head;
	}
	public void setVisibleByAge(int age) {
		mussel1.visible = false;
		mussel2.visible = false;
		mussel3.visible = false;
		mussel4.visible = false;
		mussel5.visible = false;
		mussel6.visible = false;
		mussel7.visible = false;
		if (age >= 1) mussel1.visible = true;
		if (age >= 2) mussel2.visible = true;
		if (age >= 3) mussel3.visible = true;
		if (age >= 4) mussel4.visible = true;
		if (age >= 5) mussel5.visible = true;
		if (age >= 6) mussel6.visible = true;
		if (age >= 7) mussel7.visible = true;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		this.head.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}