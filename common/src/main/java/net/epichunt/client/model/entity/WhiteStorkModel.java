// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class WhiteStork<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "whitestork"), "main");
	private final ModelPart crane;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart head1;
	private final ModelPart wing1;
	private final ModelPart wing2;

	public WhiteStork(ModelPart root) {
		this.crane = root.getChild("crane");
		this.leg1 = this.crane.getChild("leg1");
		this.leg2 = this.crane.getChild("leg2");
		this.body = this.crane.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = this.crane.getChild("head");
		this.head1 = this.head.getChild("head1");
		this.wing1 = this.crane.getChild("wing1");
		this.wing2 = this.crane.getChild("wing2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition crane = partdefinition.addOrReplaceChild("crane", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg1 = crane.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 24).addBox(-0.01F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-4, 20).addBox(-1.0F, 6.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, 0.0F));

		PartDefinition leg2 = crane.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(-4, 20).mirror().addBox(-2.0F, 6.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 24).mirror().addBox(-0.99F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -6.0F, 0.0F));

		PartDefinition body = crane.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 0).addBox(-1.5F, -2.0855F, -2.5947F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.9145F, -1.6947F, -0.5236F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 7).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.9145F, 5.3053F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(19, 7).mirror().addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(19, 7).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition head = crane.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -3.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 26).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head1 = head.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(24, 21).addBox(-0.99F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(15, 25).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 29).addBox(-0.49F, -1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition wing1 = crane.addOrReplaceChild("wing1", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -11.0F, -1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wing1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 15).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.6443F, 4.4335F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r6 = wing1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(7, 14).addBox(0.0F, -3.5F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.1391F, -2.122F, -0.4363F, 0.0F, 0.0F));

		PartDefinition wing2 = crane.addOrReplaceChild("wing2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -11.0F, -1.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wing2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(7, 14).mirror().addBox(-1.0F, -3.5F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, -2.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r8 = wing2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 4.9278F, 5.4618F, -0.4363F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		crane.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}