package net.epichunt.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.epichunt.EpicHunt;
import net.epichunt.entity.animals.DoeEntity;
import net.epichunt.entity.animals.DuckEntity;
import net.epichunt.entity.animals.GooseEntity;
import net.epichunt.entity.animals.RavenEntity;
import net.epichunt.entity.animations.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Parrot;


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
		this.animate(((RavenEntity) entity).flyAnimationState, RavenAnimation.fly, ageInTicks, 1f);
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

	public void prepareMobModel(RavenEntity raven, float f, float g, float h) {
		this.prepare(getState(raven));
	}

	public void renderOnShoulder(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k, int l) {
		this.prepare(RavenModel.State.ON_SHOULDER);
		this.setupAnim(RavenModel.State.ON_SHOULDER, l, f, g, 0.0F, h, k);
		this.root().render(poseStack, vertexConsumer, i, j);

	}

	private void setupAnim(State state, int i, float f, float g, float h, float j, float k) {
		this.head.xRot = k * ((float)Math.PI / 180F);
		this.head.yRot = j * ((float)Math.PI / 180F);
		this.head.zRot = 0.0F;
		this.head.x = 0.0F;
		this.body.x = 0.0F;
		this.tail.x = 0.0F;
		this.wing1.x = -1.0F;
		this.wing2.x = 1.0F;
		switch (state) {
			case SITTING:
				break;
			case STANDING:
				ModelPart var10000 = this.leg1;
				var10000.xRot += Mth.cos(f * 0.6662F) * 1.4F * g;
				var10000 = this.leg2;
				var10000.xRot += Mth.cos(f * 0.6662F + (float)Math.PI) * 1.4F * g;
			case FLYING:
			case ON_SHOULDER:
				this.body.xRot = 0.3F;
				this.raven.y = 21.0F;
				this.raven.z = -3.5F;

				this.head.xRot = -0.1F;
				this.head.y = -2.0F;
				this.head.z = -1.5F;

				this.wing1.xRot = -0.1F;
				this.wing1.yRot = -0.0F;
				this.wing1.zRot = 0.0F;

				this.wing2.xRot = -0.1F;
				this.wing2.yRot = 0.0F;
				this.wing2.zRot = -0.0F;

				this.tail.xRot = -0.4F;
				this.tail.y = 1.0F;
				this.tail.z = 4.5F;

				this.leg1.xRot = -0.0F;
				this.leg1.y = 0.9F;
				this.leg1.z = 2.5F;

				this.leg2.xRot = -0.0F;
				this.leg2.y = 0.9F;
				this.leg2.z = 2.5F;
				break;
			default:
				float n = h * 0.3F;
				this.head.y = 15.69F + n;
				this.tail.xRot = 1.015F + Mth.cos(f * 0.6662F) * 0.3F * g;
				this.tail.y = 21.07F + n;
				this.body.y = 16.5F + n;
				this.wing2.zRot = -0.0873F - h;
				this.wing2.y = 16.94F + n;
				this.wing1.zRot = 0.0873F + h;
				this.wing1.y = 16.94F + n;
				this.leg1.y = 22.0F + n;
				this.leg2.y = 22.0F + n;
		}

	}

	private void prepare(State state) {
		this.body.xRot = 0.4937F;
		this.wing2.xRot = -0.6981F;
		this.wing2.yRot = -(float)Math.PI;
		this.wing1.xRot = -0.6981F;
		this.wing1.yRot = -(float)Math.PI;
		this.leg1.xRot = -0.0299F;
		this.leg2.xRot = -0.0299F;
		this.leg1.y = 22.0F;
		this.leg2.y = 22.0F;
		this.leg1.zRot = 0.0F;
		this.leg2.zRot = 0.0F;
		switch (state) {
			case SITTING:
				float f = 1.9F;
				this.head.y = 17.59F;
				this.tail.xRot = 1.5388988F;
				this.tail.y = 22.97F;
				this.body.y = 18.4F;
				this.wing2.zRot = -0.0873F;
				this.wing2.y = 18.84F;
				this.wing1.zRot = 0.0873F;
				this.wing1.y = 18.84F;
				++this.leg1.y;
				++this.leg2.y;
				++this.leg1.xRot;
				++this.leg2.xRot;
				break;
			case STANDING:
			case ON_SHOULDER:
				// Подготовка для плеча
				this.body.xRot = 0.3F;
				this.wing1.xRot = -0.2F;
				this.wing2.xRot = -0.2F;
				this.leg1.xRot = -0.8F;
				this.leg2.xRot = -0.8F;
				this.tail.xRot = 0.5F;
				break;
			case FLYING:
				ModelPart var10000 = this.leg1;
				var10000.xRot += 0.6981317F;
				var10000 = this.leg2;
				var10000.xRot += 0.6981317F;
			default:
				break;
		}

	}
	private static State getState(RavenEntity raven) {
		if (raven.isInSittingPose()) {
			return State.SITTING;
		} else {
			return raven.isFlying() ? State.FLYING : State.STANDING;
		}
	}

	@Override
	public ModelPart root() {
		return raven;
	}

	@Environment(EnvType.CLIENT)
	public static enum State {
		FLYING,
		STANDING,
		SITTING,
		ON_SHOULDER;
	}
}