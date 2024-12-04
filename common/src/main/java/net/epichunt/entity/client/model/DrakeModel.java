package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.DrakeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animations.DrakeAnimation;
import net.epichunt.entity.animations.DuckAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DrakeModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart duck;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public DrakeModel(ModelPart root) {
		this.duck = root.getChild("duck");
		this.head = this.duck.getChild("head");
		this.body = this.duck.getChild("body");
		this.leftwing = this.duck.getChild("leftwing");
		this.rightwing = this.duck.getChild("rightwing");
		this.leftleg = this.duck.getChild("leftleg");
		this.rightleg = this.duck.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition duck = partdefinition.addOrReplaceChild("duck", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = duck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(31, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(28, 5).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(31, 12).addBox(-0.49F, -1.5F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(31, 12).addBox(-0.489F, -1.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 12).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -1.0F));

		PartDefinition body = duck.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addBox(-1.0F, -2.0F, 3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition leftwing = duck.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(60, 4).addBox(0.0F, 0.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -7.0F, -2.0F));

		PartDefinition rightwing = duck.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(60, 4).mirror().addBox(-1.0F, 0.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(50, 0).mirror().addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -7.0F, -2.0F));

		PartDefinition leftleg = duck.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(-4, 16).addBox(-2.0F, 3.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, 2.0F));

		PartDefinition rightleg = duck.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(-4, 16).mirror().addBox(-1.0F, 3.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(DrakeAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DrakeEntity) entity).flyAnimationState, DuckAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		duck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return duck;
	}
}