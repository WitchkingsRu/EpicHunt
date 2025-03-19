package net.epichunt.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;


public class OysterBlockModel extends Model {
	private final ModelPart head;
	private final ModelPart oyster5;
	private final ModelPart oyster6;
	private final ModelPart oyster8;
	private final ModelPart oyster4;
	private final ModelPart oyster3;
	private final ModelPart oyster2;
	private final ModelPart oyster7;
	private final ModelPart oyster1;
	private final ModelPart oyster9;

	public OysterBlockModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.head = root.getChild("head");
		this.oyster5 = this.head.getChild("oyster5");
		this.oyster6 = this.head.getChild("oyster6");
		this.oyster8 = this.head.getChild("oyster8");
		this.oyster4 = this.head.getChild("oyster4");
		this.oyster3 = this.head.getChild("oyster3");
		this.oyster2 = this.head.getChild("oyster2");
		this.oyster7 = this.head.getChild("oyster7");
		this.oyster1 = this.head.getChild("oyster1");
		this.oyster9 = this.head.getChild("oyster9");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition oyster5 = head.addOrReplaceChild("oyster5", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -1.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition oyster6 = head.addOrReplaceChild("oyster6", CubeListBuilder.create().texOffs(0, 17).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 4.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition oyster8 = head.addOrReplaceChild("oyster8", CubeListBuilder.create().texOffs(20, 9).addBox(0.0F, 3.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -1.0F, -0.4363F, -0.3927F, 0.0F));

		PartDefinition oyster4 = head.addOrReplaceChild("oyster4", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, 3.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition oyster3 = head.addOrReplaceChild("oyster3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = oyster3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-3.0F, -1.9F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 2.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition oyster2 = head.addOrReplaceChild("oyster2", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = oyster2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 7).addBox(-3.0F, -2.1F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition oyster7 = head.addOrReplaceChild("oyster7", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 4.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition oyster1 = head.addOrReplaceChild("oyster1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition oyster9 = head.addOrReplaceChild("oyster9", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 5.0F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public ModelPart root() {
		return this.head;
	}
	public void setVisibleByAge(int age) {
		oyster1.visible = false;
		oyster2.visible = false;
		oyster3.visible = false;
		oyster4.visible = false;
		oyster5.visible = false;
		oyster6.visible = false;
		oyster7.visible = false;
		oyster8.visible = false;
		oyster9.visible = false;
		if (age >= 1) oyster1.visible = true;
		if (age >= 2) oyster2.visible = true;
		if (age >= 3) oyster3.visible = true;
		if (age >= 4) oyster4.visible = true;
		if (age >= 5) oyster5.visible = true;
		if (age >= 6) {oyster6.visible = true;
			oyster7.visible = true;}
		if (age >= 7) {oyster8.visible = true;
			oyster9.visible = true;}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		this.head.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}