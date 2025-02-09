package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animations.HighlandCowAnimation;
import net.epichunt.entity.animations.YakAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class HighlandCowModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart highland_cow;
	private final ModelPart leg3;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart leg0;
	private final ModelPart body;
	private final ModelPart hair;
	private final ModelPart head;
	private final ModelPart horns;
	private final ModelPart hair2;

	public HighlandCowModel(ModelPart root) {
		this.highland_cow = root.getChild("highland_cow");
		this.leg3 = this.highland_cow.getChild("leg3");
		this.leg2 = this.highland_cow.getChild("leg2");
		this.leg1 = this.highland_cow.getChild("leg1");
		this.leg0 = this.highland_cow.getChild("leg0");
		this.body = this.highland_cow.getChild("body");
		this.hair = this.body.getChild("hair");
		this.head = this.highland_cow.getChild("head");
		this.horns = this.head.getChild("horns");
		this.hair2 = this.head.getChild("hair2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition highland = partdefinition.addOrReplaceChild("highland_cow", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg3 = highland.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(48, 22).addBox(-1.99F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, -6.0F));

		PartDefinition leg2 = highland.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(48, 22).addBox(-2.01F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, -6.0F));

		PartDefinition leg1 = highland.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(48, 22).addBox(-1.99F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, 7.0F));

		PartDefinition leg0 = highland.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(48, 22).addBox(-2.01F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 7.0F));

		PartDefinition body = highland.addOrReplaceChild("body", CubeListBuilder.create().texOffs(6, 37).addBox(-5.5F, -2.0F, -10.0F, 11.0F, 9.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(3, 28).mirror().addBox(-2.5F, 6.0F, -1.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -18.0F, 2.0F));

		PartDefinition hair = body.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(88, 22).addBox(-5.5F, -20.3F, -5.0F, 11.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -5.0F));

		PartDefinition cube_r1 = hair.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(92, -11).mirror().addBox(-1.0F, -10.3F, -1.0F, 0.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, -10.0F, -4.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r2 = hair.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(86, 4).mirror().addBox(-0.99F, -10.3F, -1.0F, 0.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -10.0F, -6.0F, 0.0F, 1.5708F, -0.1745F));

		PartDefinition cube_r3 = hair.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(86, 4).addBox(0.99F, -10.3F, -1.0F, 0.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -10.0F, -6.0F, 0.0F, -1.5708F, 0.1745F));

		PartDefinition cube_r4 = hair.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(74, 21).addBox(-9.5F, 1.7F, -1.0F, 11.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -19.0F, 15.0F, -1.4835F, 0.0F, 0.0F));

		PartDefinition cube_r5 = hair.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(92, -11).addBox(1.0F, -10.3F, -1.0F, 0.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -10.0F, -4.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition head = highland.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(-2.5F, 1.0F, -7.5F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -3.0F, -7.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -8.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 7.0F));

		PartDefinition cube_r6 = horns.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-2.1489F, -3.9201F, -1.5096F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.7489F, -24.0799F, -8.9904F, 0.829F, -0.2618F, 0.1309F));

		PartDefinition cube_r7 = horns.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(39, 6).addBox(1.1489F, -3.9201F, -1.5096F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7489F, -24.0799F, -8.9904F, 0.829F, 0.2618F, -0.1309F));

		PartDefinition cube_r8 = horns.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -22.0F, -9.0F, 0.6109F, -0.8727F, 0.3054F));

		PartDefinition cube_r9 = horns.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(39, 6).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -22.0F, -9.0F, 0.6109F, 0.8727F, -0.3054F));

		PartDefinition hair2 = head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(67, 0).addBox(-3.5F, -3.05F, -7.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = hair2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(60, -7).mirror().addBox(-1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 4.0F, -6.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r11 = hair2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(60, 7).addBox(-5.5F, -3.0F, -0.5F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r12 = hair2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(60, 0).addBox(-5.5F, -7.0F, -1.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, 2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r13 = hair2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(60, -7).addBox(1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, -6.0F, 0.0F, 0.0F, 0.0873F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(HighlandCowAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		highland_cow.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return highland_cow;
	}
}