package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animals.RoeDeerEntity;
import net.epichunt.entity.animals.WisentEntity;
import net.epichunt.entity.animations.DeerAnimation;
import net.epichunt.entity.animations.GooseAnimation;
import net.epichunt.entity.animations.RoeDeerAnimation;
import net.epichunt.entity.animations.WisentAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class WisentModel<T extends WisentEntity> extends HierarchicalModel<T> {
	private final ModelPart wisent;
	private final ModelPart leg3;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart leg0;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart horns;

	public WisentModel(ModelPart root) {
		this.wisent = root.getChild("wisent");
		this.leg3 = this.wisent.getChild("leg3");
		this.leg2 = this.wisent.getChild("leg2");
		this.leg1 = this.wisent.getChild("leg1");
		this.leg0 = this.wisent.getChild("leg0");
		this.body = this.wisent.getChild("body");
		this.head = this.wisent.getChild("head");
		this.hair = this.head.getChild("hair");
		this.horns = this.head.getChild("horns");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wisent = partdefinition.addOrReplaceChild("wisent", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg3 = wisent.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(48, 24).addBox(-1.99F, 2.0F, -1.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, -6.0F));

		PartDefinition leg2 = wisent.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(48, 24).addBox(-2.01F, 2.0F, -1.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, -6.0F));

		PartDefinition leg1 = wisent.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(48, 24).addBox(-1.99F, 2.0F, 0.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, 7.0F));

		PartDefinition leg0 = wisent.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(48, 24).addBox(-2.01F, 2.0F, 0.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 7.0F));

		PartDefinition body = wisent.addOrReplaceChild("body", CubeListBuilder.create().texOffs(4, 35).addBox(-5.5F, -2.0F, -9.99F, 11.0F, 9.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 22).mirror().addBox(-4.0F, -4.0F, -10.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(69, 25).mirror().addBox(-3.5F, -3.0F, -2.0F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -16.0F, 2.0F));

		PartDefinition head = wisent.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.3F, -7.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -8.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 4).addBox(-3.5F, -5.5F, -1.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 7.0F, -7.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create(), PartPose.offset(1.0F, 7.0F, -7.0F));

		PartDefinition cube_r2 = hair.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(69, 4).addBox(1.0F, -4.0F, -1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.0F, 1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = hair.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(61, 2).addBox(-5.5F, -0.4F, -1.0F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 7.0F));

		PartDefinition cube_r4 = horns.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-1.9489F, -5.9201F, -2.5096F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.4626F, -24.4713F, -9.0956F, 1.0036F, 0.48F, 0.1309F));

		PartDefinition cube_r5 = horns.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(39, 6).addBox(0.9489F, -5.9201F, -2.5096F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4626F, -24.4713F, -9.0956F, 1.0036F, -0.48F, -0.1309F));

		PartDefinition cube_r6 = horns.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(39, 6).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -22.0F, -9.0F, 0.6545F, -0.8727F, 0.3054F));

		PartDefinition cube_r7 = horns.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(39, 6).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -22.0F, -9.0F, 0.6545F, 0.8727F, -0.3054F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		if (entity.isAngry) {
			this.animateWalk(WisentAnimation.walk_angry, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animateWalk(WisentAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		}
		this.animate(((WisentEntity) entity).attackAnimationState, WisentAnimation.attack, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		wisent.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return wisent;
	}
}