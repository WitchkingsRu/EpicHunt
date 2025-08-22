package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animals.NightingaleEntity;
import net.epichunt.entity.animations.DoeAnimation;
import net.epichunt.entity.animations.DuckAnimation;
import net.epichunt.entity.animations.GooseAnimation;
import net.epichunt.entity.animations.NightingaleAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class NightingaleModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart nightingale;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public NightingaleModel(ModelPart root) {
		this.nightingale = root.getChild("nightingale");
		this.head = this.nightingale.getChild("head");
		this.body = this.nightingale.getChild("body");
		this.leftwing = this.nightingale.getChild("leftwing");
		this.rightwing = this.nightingale.getChild("rightwing");
		this.leftleg = this.nightingale.getChild("leftleg");
		this.rightleg = this.nightingale.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition nightingale = partdefinition.addOrReplaceChild("nightingale", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, -1.0F));

		PartDefinition head = nightingale.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 1).addBox(-1.0F, 0.3F, -1.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 6).addBox(-1.0F, -1.7F, -1.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(27, 13).addBox(-0.5F, -1.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition body = nightingale.addOrReplaceChild("body", CubeListBuilder.create().texOffs(4, 3).addBox(-1.5F, -1.2443F, -1.7786F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-1.0F, -1.2443F, 2.2214F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-1.0F, -1.2443F, 3.2214F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition leftwing = nightingale.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(19, 18).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 21).addBox(-0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition rightwing = nightingale.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(28, 21).mirror().addBox(-0.5F, -0.4763F, 2.7836F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(19, 18).mirror().addBox(-0.5F, -0.4763F, -0.2164F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -1.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition leftleg = nightingale.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 12).addBox(-0.51F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-2, 18).addBox(-1.0F, 2.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 1.0F));

		PartDefinition rightleg = nightingale.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-0.49F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-2, 18).mirror().addBox(-2.0F, 2.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 1.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(NightingaleAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((NightingaleEntity) entity).flyAnimationState, NightingaleAnimation.fly, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		nightingale.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return nightingale;
	}
}