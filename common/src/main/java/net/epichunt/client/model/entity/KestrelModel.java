// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Hawk<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hawk"), "main");
	private final ModelPart kestrel;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart wing1;
	private final ModelPart wing1_2;
	private final ModelPart wing2;
	private final ModelPart wing2_2;

	public Hawk(ModelPart root) {
		this.kestrel = root.getChild("kestrel");
		this.head = this.kestrel.getChild("head");
		this.body = this.kestrel.getChild("body");
		this.tail = this.body.getChild("tail");
		this.leg1 = this.kestrel.getChild("leg1");
		this.leg2 = this.kestrel.getChild("leg2");
		this.wing1 = this.kestrel.getChild("wing1");
		this.wing1_2 = this.wing1.getChild("wing1_2");
		this.wing2 = this.kestrel.getChild("wing2");
		this.wing2_2 = this.wing2.getChild("wing2_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kestrel = partdefinition.addOrReplaceChild("kestrel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = kestrel.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.5F, -1.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -5.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 4).mirror().addBox(-0.3F, -0.51F, -0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition body = kestrel.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(17, 4).mirror().addBox(-0.99F, -2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.7F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(1, 10).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 10).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(1, 10).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition leg1 = kestrel.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 29).addBox(-0.51F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(-4, 24).addBox(-1.0F, 2.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, -1.0F));

		PartDefinition leg2 = kestrel.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(-4, 24).mirror().addBox(-2.0F, 2.0F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 29).mirror().addBox(-0.49F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -2.0F, -1.0F));

		PartDefinition wing1 = kestrel.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(20, 23).mirror().addBox(0.0F, -0.51F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 19).mirror().addBox(0.0F, -0.3F, 1.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -5.0F, -4.0F));

		PartDefinition wing1_2 = wing1.addOrReplaceChild("wing1_2", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 1.0F));

		PartDefinition cube_r6 = wing1_2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 26).mirror().addBox(-1.0F, -0.51F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.366F, 0.01F, 0.366F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r7 = wing1_2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(18, 14).mirror().addBox(-1.0F, 0.71F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition wing2 = kestrel.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(20, 23).addBox(-4.0F, -0.51F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(21, 19).addBox(-4.0F, -0.3F, 1.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, -4.0F));

		PartDefinition wing2_2 = wing2.addOrReplaceChild("wing2_2", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 1.0F));

		PartDefinition cube_r8 = wing2_2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 26).addBox(-3.0F, -0.51F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.366F, 0.01F, 0.366F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r9 = wing2_2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(18, 14).addBox(-4.0F, 0.71F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, 0.6109F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		kestrel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}