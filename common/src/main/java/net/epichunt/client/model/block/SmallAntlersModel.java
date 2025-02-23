package net.epichunt.client.model.block;


import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SmallAntlersModel extends SkullModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//
	private final ModelPart antlers;

	public SmallAntlersModel(ModelPart root) {
        super(root);
        this.antlers = root.getChild("antlers");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition antlers = partdefinition.addOrReplaceChild("antlers", CubeListBuilder.create().texOffs(0, 5).addBox(1.0F, -5.7588F, 0.3681F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-2.0F, -5.7588F, 0.3681F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition cube_r1 = antlers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 0).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r2 = antlers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 4).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 1.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r3 = antlers.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 5).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 1.0F, 0.0F, 0.0F, 0.5236F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}
}