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

public class GreatAukModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart great_auk;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart wing2;
	private final ModelPart wing1;
	private final ModelPart leg1;
	private final ModelPart leg2;

	public GreatAukModel(ModelPart root) {
		this.great_auk = root.getChild("great_auk");
		this.body = this.great_auk.getChild("body");
		this.head = this.body.getChild("head");
		this.tail = this.body.getChild("tail");
		this.wing2 = this.body.getChild("wing2");
		this.wing1 = this.body.getChild("wing1");
		this.leg1 = this.great_auk.getChild("leg1");
		this.leg2 = this.great_auk.getChild("leg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition great_auk = partdefinition.addOrReplaceChild("great_auk", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -1.0F));

		PartDefinition body = great_auk.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-1.49F, -1.6152F, -0.9072F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(2, 11).addBox(0.49F, -1.6152F, -0.9072F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-1.51F, -1.0845F, -1.6027F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2911F, -0.9939F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -7.0F, -1.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, 0.5F, -0.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 28).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(13, 29).addBox(-0.49F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 4.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(25, 4).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5195F, 3.1749F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition wing2 = body.addOrReplaceChild("wing2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 0.0F, 1.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wing2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 17).mirror().addBox(-0.2F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.01F, 2.7239F, -1.3738F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r6 = wing2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(18, 16).mirror().addBox(-0.2F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition wing1 = body.addOrReplaceChild("wing1", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 0.0F, 1.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wing1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 17).addBox(-0.8F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.01F, 2.7239F, -1.3738F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r8 = wing1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(18, 16).addBox(-0.8F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 1.0908F, 0.0F, 0.0F));

		PartDefinition leg1 = great_auk.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(27, 25).addBox(-0.51F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 21).addBox(-1.0F, 2.0F, -2.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, 1.0F));

		PartDefinition leg2 = great_auk.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(27, 25).mirror().addBox(-0.49F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(22, 21).mirror().addBox(-2.0F, 2.0F, -2.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 7.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(GreatAukAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		great_auk.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return great_auk;
	}
}