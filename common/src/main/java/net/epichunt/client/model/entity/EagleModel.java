package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.aerial.AbstractPreyBirdEntity;
import net.epichunt.entity.animals.aerial.EagleEntity;
import net.epichunt.entity.animations.EagleAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class EagleModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart eagle;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart wing1;
	private final ModelPart wing1_2;
	private final ModelPart wing2;
	private final ModelPart wing2_2;

	public EagleModel(ModelPart root) {
		this.eagle = root.getChild("eagle");
		this.head = this.eagle.getChild("head");
		this.body = this.eagle.getChild("body");
		this.tail = this.body.getChild("tail");
		this.leg1 = this.eagle.getChild("leg1");
		this.leg2 = this.eagle.getChild("leg2");
		this.wing1 = this.eagle.getChild("wing1");
		this.wing1_2 = this.wing1.getChild("wing1_2");
		this.wing2 = this.eagle.getChild("wing2");
		this.wing2_2 = this.wing2.getChild("wing2_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eagle = partdefinition.addOrReplaceChild("eagle", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -3.0F));

		PartDefinition head = eagle.addOrReplaceChild("head", CubeListBuilder.create().texOffs(37, 7).addBox(-1.0F, -0.89F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 2).addBox(-1.5F, -0.9F, -1.3F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -2.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 7).mirror().addBox(-0.3F, 0.1F, -0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition body = eagle.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -1.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(37, 2).addBox(-1.0F, -2.0F, -1.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -1.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(1, 10).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 10).addBox(-1.0F, -0.5F, -1.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(1, 10).addBox(-2.0F, -0.5F, -1.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition leg1 = eagle.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(3, 31).addBox(-0.51F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-1, 26).addBox(-1.0F, 2.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 4.0F));

		PartDefinition leg2 = eagle.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(-1, 26).mirror().addBox(-2.0F, 2.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 31).mirror().addBox(-0.49F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -2.0F, 4.0F));

		PartDefinition wing1 = eagle.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(21, 25).mirror().addBox(0.0F, -0.51F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 20).mirror().addBox(0.0F, -0.3F, 1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -5.0F, -2.0F));

		PartDefinition wing1_2 = wing1.addOrReplaceChild("wing1_2", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, 1.0F));

		PartDefinition cube_r6 = wing1_2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 28).mirror().addBox(-1.0F, -0.51F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.366F, 0.0F, 0.366F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r7 = wing1_2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(18, 14).mirror().addBox(-3.0F, 0.71F, -1.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -1.0F, 2.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition wing2 = eagle.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(21, 25).addBox(-6.0F, -0.51F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(21, 20).addBox(-6.0F, -0.3F, 1.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, -2.0F));

		PartDefinition wing2_2 = wing2.addOrReplaceChild("wing2_2", CubeListBuilder.create(), PartPose.offset(-6.0F, 0.0F, 1.0F));

		PartDefinition cube_r8 = wing2_2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 28).addBox(-6.0F, -0.51F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.366F, 0.0F, 0.366F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r9 = wing2_2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(18, 14).addBox(-4.0F, 0.71F, -1.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 2.0F, 0.0F, 0.6109F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(((EagleEntity) entity).flyAnimationState, EagleAnimation.fly, ageInTicks, 1f);
		this.eagle.xRot = -headPitch * ((float)Math.PI / 180F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		eagle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return eagle;
	}
}