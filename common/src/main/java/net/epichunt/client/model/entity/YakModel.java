package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.entity.animals.YakEntity;
import net.epichunt.entity.animations.YakAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class YakModel<T extends YakEntity> extends HierarchicalModel<T> {
	private final ModelPart yak;
	private final ModelPart carpet;
	private final ModelPart leg3;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart leg0;
	private final ModelPart body;
	private final ModelPart hair;
	private final ModelPart head;
	private final ModelPart horns;
	private final ModelPart hair2;
	private final ModelPart carpet_stuff;

	public YakModel(ModelPart root) {
		this.yak = root.getChild("yak");
		this.carpet = this.yak.getChild("carpet");
		this.leg3 = this.yak.getChild("leg3");
		this.leg2 = this.yak.getChild("leg2");
		this.leg1 = this.yak.getChild("leg1");
		this.leg0 = this.yak.getChild("leg0");
		this.body = this.yak.getChild("body");
		this.hair = this.body.getChild("hair");
		this.head = this.yak.getChild("head");
		this.horns = this.head.getChild("horns");
		this.hair2 = this.head.getChild("hair2");
		this.carpet_stuff = this.head.getChild("carpet_stuff");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition yak = partdefinition.addOrReplaceChild("yak", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition carpet = yak.addOrReplaceChild("carpet", CubeListBuilder.create().texOffs(88, 48).addBox(-4.0F, -23.0F, -8.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(90, 42).addBox(-4.0F, -21.5F, 0.0F, 8.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = carpet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 22).mirror().addBox(-0.977F, -10.3F, -1.021F, 0.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1602F, -12.8153F, -8.976F, 0.0F, 1.5708F, -0.1745F));

		PartDefinition cube_r2 = carpet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 22).mirror().addBox(-0.977F, -10.3F, -1.0F, 0.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1244F, -12.9853F, -8.99F, 0.0F, 1.5708F, -0.1745F));

		PartDefinition cube_r3 = carpet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(98, 43).addBox(0.84F, -7.0F, -1.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3883F, -12.4974F, -7.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r4 = carpet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(112, 43).addBox(-7.0F, -2.0F, -0.99F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -21.4633F, 1.6228F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r5 = carpet.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(104, 48).mirror().addBox(-1.0F, -1.0F, -0.991F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.634F, -19.9029F, -7.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r6 = carpet.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(98, 43).mirror().addBox(-0.84F, -7.0F, -1.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.3873F, -12.4972F, -7.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r7 = carpet.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 22).addBox(0.977F, -10.3F, -1.021F, 0.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1602F, -12.8153F, -8.976F, 0.0F, -1.5708F, 0.1745F));

		PartDefinition cube_r8 = carpet.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(116, 50).mirror().addBox(-1.0F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2922F, -20.2433F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r9 = carpet.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(116, 50).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2922F, -20.2433F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r10 = carpet.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(104, 48).addBox(-3.0F, -1.0F, -0.991F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.634F, -19.9029F, -7.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition leg3 = yak.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(48, 22).addBox(-1.99F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, -6.0F));

		PartDefinition leg2 = yak.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(48, 22).addBox(-2.01F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, -6.0F));

		PartDefinition leg1 = yak.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(48, 22).addBox(-1.99F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, 7.0F));

		PartDefinition leg0 = yak.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(48, 22).addBox(-2.01F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 7.0F));

		PartDefinition body = yak.addOrReplaceChild("body", CubeListBuilder.create().texOffs(6, 37).addBox(-5.5F, -2.0F, -9.99F, 11.0F, 9.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(0, 27).mirror().addBox(-3.5F, -4.0F, -10.0F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -18.0F, 2.0F));

		PartDefinition hair = body.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(107, 0).addBox(-3.5F, -22.3F, -5.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(88, 22).addBox(-5.5F, -20.05F, -5.0F, 11.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -5.0F));

		PartDefinition cube_r11 = hair.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(92, -11).mirror().addBox(-1.1F, -10.08F, -1.0F, 0.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, -10.0F, -4.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r12 = hair.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(99, -4).mirror().addBox(-1.7F, -3.0F, -1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -20.0F, -4.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r13 = hair.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(86, 4).mirror().addBox(-1.1F, -10.3F, -1.0F, 0.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -10.0F, -6.0F, 0.0F, 1.5708F, -0.1745F));

		PartDefinition cube_r14 = hair.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(86, 4).addBox(1.1F, -10.3F, -1.0F, 0.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -10.0F, -6.0F, 0.0F, -1.5708F, 0.1745F));

		PartDefinition cube_r15 = hair.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(74, 21).addBox(-9.5F, 1.7F, -1.0F, 11.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -19.0F, 15.0F, -1.4835F, 0.0F, 0.0F));

		PartDefinition cube_r16 = hair.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(99, 3).addBox(-5.5F, -3.0F, -1.0F, 7.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -20.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r17 = hair.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(99, -4).addBox(1.7F, -3.0F, -1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -20.0F, -4.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r18 = hair.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(92, -11).addBox(1.1F, -10.08F, -1.0F, 0.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -10.0F, -4.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition head = yak.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -7.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 15).addBox(-2.5F, 0.0F, -7.8F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -8.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 7.0F));

		PartDefinition cube_r19 = horns.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-1.9489F, -5.2201F, -2.1096F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.7489F, -24.0799F, -7.9904F, 0.829F, -0.3927F, 0.1309F));

		PartDefinition cube_r20 = horns.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(39, 6).addBox(0.9489F, -5.2201F, -2.1096F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7489F, -24.0799F, -7.9904F, 0.829F, 0.3927F, -0.1309F));

		PartDefinition cube_r21 = horns.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -22.0F, -9.0F, 0.6109F, -0.8727F, 0.3054F));

		PartDefinition cube_r22 = horns.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(39, 6).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -22.0F, -9.0F, 0.6109F, 0.8727F, -0.3054F));

		PartDefinition hair2 = head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(53, 0).addBox(-3.5F, -3.05F, -7.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r23 = hair2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(60, -7).mirror().addBox(-1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 4.0F, -6.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r24 = hair2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(60, -7).addBox(1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, -6.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r25 = hair2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(53, 0).addBox(-5.5F, 1.4F, -1.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -5.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition carpet_stuff = head.addOrReplaceChild("carpet_stuff", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r26 = carpet_stuff.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(80, 50).mirror().addBox(-1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 4.0F, -6.0F, 0.0F, 0.3054F, -0.0873F));

		PartDefinition cube_r27 = carpet_stuff.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(80, 42).addBox(1.9F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, -6.0F, 0.0F, -0.3054F, 0.0873F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(YakAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		yak.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return yak;
	}
}