// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Pollock<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "pollock"), "main");
	private final ModelPart perches;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart fin;
	private final ModelPart body2;
	private final ModelPart tail;

	public Pollock(ModelPart root) {
		this.perches = root.getChild("perches");
		this.head = this.perches.getChild("head");
		this.body1 = this.perches.getChild("body1");
		this.fin = this.body1.getChild("fin");
		this.body2 = this.perches.getChild("body2");
		this.tail = this.body2.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition perches = partdefinition.addOrReplaceChild("perches", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = perches.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 17).addBox(-1.5F, -6.5F, -10.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(-1.0F, -5.5F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 3.0F));

		PartDefinition body1 = perches.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(4, 3).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 27).mirror().addBox(0.5F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 27).addBox(-0.5F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, 3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = body1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 27).addBox(-2.5F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r4 = body1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(2.5F, -2.5F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition fin = body1.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(20, 23).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.0F));

		PartDefinition body2 = perches.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, -2.0F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(4, 18).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(12, 26).addBox(0.0F, 2.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		perches.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}