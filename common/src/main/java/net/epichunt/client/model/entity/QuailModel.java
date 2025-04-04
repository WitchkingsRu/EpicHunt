package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.PheasantEntity;
import net.epichunt.entity.animals.QuailEntity;
import net.epichunt.entity.animations.PheasantAnimation;
import net.epichunt.entity.animations.QuailAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class QuailModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart quail;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public QuailModel(ModelPart root) {
		this.quail = root.getChild("duck");
		this.head = this.quail.getChild("head");
		this.body = this.quail.getChild("body");
		this.leftwing = this.quail.getChild("leftwing");
		this.rightwing = this.quail.getChild("rightwing");
		this.leftleg = this.quail.getChild("leftleg");
		this.rightleg = this.quail.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition duck = partdefinition.addOrReplaceChild("duck", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = duck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 1).addBox(-1.0F, 1.3F, -1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 6).addBox(-1.0F, -0.7F, -2.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(27, 13).addBox(-0.5F, 0.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -1.0F));

		PartDefinition body = duck.addOrReplaceChild("body", CubeListBuilder.create().texOffs(3, 2).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-1.0F, -1.5F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition leftwing = duck.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(28, 1).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(37, 4).addBox(-0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition rightwing = duck.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(37, 4).mirror().addBox(-0.5F, 0.5F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 1).mirror().addBox(-0.5F, 0.5F, 0.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -6.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition leftleg = duck.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 12).addBox(-1.51F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-2, 18).addBox(-2.0F, 3.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, 1.0F));

		PartDefinition rightleg = duck.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.51F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-2, 18).mirror().addBox(-1.0F, 3.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(QuailAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((QuailEntity) entity).flyAnimationState, QuailAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return quail;
	}
}