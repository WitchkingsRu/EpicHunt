package net.epichunt.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DeerEntity;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animations.DeerAnimation;
import net.epichunt.entity.animations.DoeAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class DoeModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart doe;
	private final ModelPart body;
	private final ModelPart head_group;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;

	public DoeModel(ModelPart root) {
		this.doe = root.getChild("doe");
		this.body = this.doe.getChild("body");
		this.head_group = this.doe.getChild("head_group");
		this.neck = this.head_group.getChild("neck");
		this.head = this.head_group.getChild("head");
		this.front_right_leg = this.doe.getChild("front_right_leg");
		this.front_left_leg = this.doe.getChild("front_left_leg");
		this.back_left_leg = this.doe.getChild("back_left_leg");
		this.back_right_leg = this.doe.getChild("back_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition doe = partdefinition.addOrReplaceChild("doe", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -7.0F));

		PartDefinition body = doe.addOrReplaceChild("body", CubeListBuilder.create().texOffs(3, 2).addBox(-3.5F, -3.0F, -10.0F, 7.0F, 7.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(3, 7).addBox(-2.0F, -2.0F, 8.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 8.0F));

		PartDefinition head_group = doe.addOrReplaceChild("head_group", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition neck = head_group.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition neck_r1 = neck.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(25, 30).addBox(-4.0F, -7.2554F, -1.653F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head = head_group.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.0F, -8.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 46).addBox(-6.2F, -6.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 46).mirror().addBox(0.2F, -6.8F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 54).addBox(-4.5F, -5.0F, -4.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -3.0F));

		PartDefinition front_right_leg = doe.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(50, 36).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, 3.0F));

		PartDefinition front_left_leg = doe.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(50, 36).mirror().addBox(-2.5F, -1.0F, -2.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 6.0F, 14.0F));

		PartDefinition back_left_leg = doe.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(50, 36).mirror().addBox(-1.5F, -1.0F, -4.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 6.0F, 3.0F));

		PartDefinition back_right_leg = doe.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(50, 36).addBox(-0.5F, -1.0F, -2.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 6.0F, 14.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(DoeAnimation.Walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DoeEntity) entity).idleAnimationState, DoeAnimation.Idle, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		doe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return doe;
	}
}