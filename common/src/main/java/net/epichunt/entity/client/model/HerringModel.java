// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Pollock<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "pollock"), "main");
	private final ModelPart herring;
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart fin;
	private final ModelPart tail;

	public Pollock(ModelPart root) {
		this.herring = root.getChild("herring");
		this.head = this.herring.getChild("head");
		this.body1 = this.herring.getChild("body1");
		this.body2 = this.herring.getChild("body2");
		this.fin = this.body2.getChild("fin");
		this.tail = this.body2.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition herring = partdefinition.addOrReplaceChild("herring", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition head = herring.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 4).addBox(-1.0F, -5.5F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 1).addBox(-0.99F, -4.5F, -10.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 3.0F));

		PartDefinition body1 = herring.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 2.0F, 1.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(3.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 2.0F, 1.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition body2 = herring.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(0.0F, 1.5F, 0.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition fin = body2.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition tail = body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -0.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		herring.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}