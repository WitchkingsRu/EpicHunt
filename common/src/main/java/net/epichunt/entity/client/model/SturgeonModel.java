// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Sturgeon<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "sturgeon"), "main");
	private final ModelPart sturgeon;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart fin3;
	private final ModelPart fin4;
	private final ModelPart body2;
	private final ModelPart tail1;
	private final ModelPart fin;
	private final ModelPart tail2;
	private final ModelPart fin2;
	private final ModelPart tail3;

	public Sturgeon(ModelPart root) {
		this.sturgeon = root.getChild("sturgeon");
		this.head = this.sturgeon.getChild("head");
		this.body1 = this.sturgeon.getChild("body1");
		this.fin3 = this.body1.getChild("fin3");
		this.fin4 = this.body1.getChild("fin4");
		this.body2 = this.sturgeon.getChild("body2");
		this.tail1 = this.sturgeon.getChild("tail1");
		this.fin = this.tail1.getChild("fin");
		this.tail2 = this.sturgeon.getChild("tail2");
		this.fin2 = this.tail2.getChild("fin2");
		this.tail3 = this.sturgeon.getChild("tail3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition sturgeon = partdefinition.addOrReplaceChild("sturgeon", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 7.0F));

		PartDefinition head = sturgeon.addOrReplaceChild("head", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, 0.7F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 4).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 10).addBox(-1.5F, -0.7F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -13.0F));

		PartDefinition body1 = sturgeon.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(23, 28).addBox(-2.0F, -0.3F, -3.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.5F, -1.3F, -1.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(23, 24).addBox(0.0F, -1.8F, 0.0F, 0.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(12, 39).addBox(-2.7F, 0.2F, -0.01F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(12, 39).addBox(1.7F, 0.2F, -0.01F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -10.0F));

		PartDefinition fin3 = body1.addOrReplaceChild("fin3", CubeListBuilder.create(), PartPose.offset(-3.0F, 1.0F, -1.0F));

		PartDefinition cube_r1 = fin3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 19).addBox(1.5F, -4.5F, -0.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

		PartDefinition fin4 = body1.addOrReplaceChild("fin4", CubeListBuilder.create(), PartPose.offset(3.0F, 1.0F, -1.0F));

		PartDefinition cube_r2 = fin4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.5F, -4.5F, -0.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 4.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition body2 = sturgeon.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0F, 0.3F, -0.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 31).addBox(0.0F, -0.2F, -0.5F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 52).addBox(1.3F, 1.2F, -0.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition cube_r3 = body2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-1.0F, -3.2F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 5.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r4 = body2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 42).addBox(1.0F, -3.2F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail1 = sturgeon.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 49).addBox(-1.5F, -0.2F, -1.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 52).addBox(-1.7F, 0.2F, -0.99F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 52).addBox(0.7F, 0.2F, -0.99F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));

		PartDefinition fin = tail1.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 53).addBox(0.0F, -4.2F, -1.5F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition tail2 = sturgeon.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(48, 26).addBox(-1.0F, -0.2F, -1.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 52).addBox(-1.3F, 0.2F, -1.4F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(17, 52).addBox(0.3F, 0.2F, -1.4F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 13.0F));

		PartDefinition fin2 = tail2.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(50, 30).addBox(1.0F, -3.2F, -2.5F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, 1.0F));

		PartDefinition tail3 = sturgeon.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(52, 49).addBox(0.0F, -4.3F, 0.5F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 17.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		sturgeon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}