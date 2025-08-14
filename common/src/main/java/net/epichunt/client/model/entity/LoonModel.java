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


public class LoonModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart loon;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public LoonModel(ModelPart root) {
		this.loon = root.getChild("loon");
		this.head = this.loon.getChild("head");
		this.body = this.loon.getChild("body");
		this.leftwing = this.loon.getChild("leftwing");
		this.rightwing = this.loon.getChild("rightwing");
		this.leftleg = this.loon.getChild("leftleg");
		this.rightleg = this.loon.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition loon = partdefinition.addOrReplaceChild("loon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = loon.addOrReplaceChild("head", CubeListBuilder.create().texOffs(31, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 5).addBox(-1.5F, -3.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(31, 12).addBox(-0.49F, -1.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(31, 12).addBox(-0.489F, -1.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 12).addBox(-0.5F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -1.0F));

		PartDefinition body = loon.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-1.0F, -2.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(-1.5F, -2.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition leftwing = loon.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(49, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(58, 3).addBox(0.0F, 0.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -7.0F, -2.0F));

		PartDefinition rightwing = loon.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(58, 3).mirror().addBox(-1.0F, 0.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(49, 0).mirror().addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -7.0F, -2.0F));

		PartDefinition leftleg = loon.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-4, 16).addBox(-2.0F, 3.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, 2.0F));

		PartDefinition rightleg = loon.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-4, 16).mirror().addBox(-1.0F, 3.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(LoonAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DuckEntity) entity).flyAnimationState, LoonAnimation.fly, ageInTicks, 1f);
		if (entity.isInWater()) {
			this.animate(((DuckEntity) entity).swimAnimationState, LoonAnimation.swim, ageInTicks, 1f);
		}
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		loon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return loon;
	}
}