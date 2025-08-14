package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animations.*;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class RavenModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart raven;
	private final ModelPart wing2;
	private final ModelPart wing1;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart leg1;
	private final ModelPart leg2;

	public RavenModel(ModelPart root) {
		this.raven = root.getChild("raven");
		this.wing2 = this.raven.getChild("wing2");
		this.wing1 = this.raven.getChild("wing1");
		this.body = this.raven.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = this.raven.getChild("head");
		this.leg1 = this.raven.getChild("leg1");
		this.leg2 = this.raven.getChild("leg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition raven = partdefinition.addOrReplaceChild("raven", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, -3.0F));

		PartDefinition wing2 = raven.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(0, 25).addBox(-0.3F, -0.8203F, -0.4701F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(11, 28).addBox(-0.3F, -0.8203F, 3.5299F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 24).addBox(-0.3F, -0.8203F, 5.5299F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition wing1 = raven.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-0.7F, -0.8203F, -0.4701F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(11, 28).mirror().addBox(-0.7F, -0.8203F, 3.5299F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(11, 24).mirror().addBox(-0.7F, -0.8203F, 5.5299F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition body = raven.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 0).addBox(-1.0F, -0.6F, -1.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 0).addBox(-0.99F, -0.8F, -1.51F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.01F, -2.8F, -1.5F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 4.0F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(2, 17).addBox(-1.0F, -1.5F, -1.4F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -1.5F, -1.4F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition head = raven.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.01F, -1.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(-0.51F, -0.5F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(-0.51F, -0.7F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(-0.49F, -0.5F, -2.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition leg1 = raven.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(28, 28).addBox(-0.51F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(25, 23).addBox(-1.0F, 2.1F, -2.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 2.0F));

		PartDefinition leg2 = raven.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(28, 28).mirror().addBox(-0.49F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(25, 23).mirror().addBox(-1.0F, 2.1F, -2.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 2.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(RavenAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DuckEntity) entity).flyAnimationState, RavenAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		raven.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return raven;
	}
}