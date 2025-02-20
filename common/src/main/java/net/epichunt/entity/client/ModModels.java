package net.epichunt.entity.client;

import dev.architectury.platform.Mod;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.epichunt.EpicHunt;
import net.epichunt.entity.client.model.*;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.PolarBearModel;
import net.epichunt.entity.client.model.WolfModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModModels {
    public static final ModelLayerLocation DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "deer"), "main");
    public static final ModelLayerLocation DOE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "doe"), "main");
    public static final ModelLayerLocation CARIBOU_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "caribou"), "main");
    public static final ModelLayerLocation ROE_DEER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "roe_deer"), "main");

    public static final ModelLayerLocation DUCK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "duck"), "main");
    public static final ModelLayerLocation DRAKE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "drake"), "main");
    public static final ModelLayerLocation GOOSE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "goose"), "main");
    public static final ModelLayerLocation PHEASANT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "pheasant"), "main");
    public static final ModelLayerLocation QUAIL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "quail"), "main");

    public static final ModelLayerLocation YAK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "yak"), "main");
    public static final ModelLayerLocation YAK_CARPET_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "yak"), "carpet");

    public static final ModelLayerLocation HIGHLAND_COW_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "highland_cow"), "main");
    public static final ModelLayerLocation WISENT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "wisent"), "main");

    public static final ModelLayerLocation GOAT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "goat"), "main");

    public static final ModelLayerLocation MOOSE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "moose"), "main");
    public static final ModelLayerLocation BOAR_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "boar"), "main");
    public static final ModelLayerLocation BADGER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "badger"), "main");

    public static final ModelLayerLocation LYNX_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "lynx"), "main");
    public static final ModelLayerLocation BEAR_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "bear"), "main");
    public static final ModelLayerLocation WOLF_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "wolf"), "main");

    public static final ModelLayerLocation BEAVER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "beaver"), "main");
    public static final ModelLayerLocation HARE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "hare"), "main");


    public static final ModelLayerLocation BASS_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "bass"), "main");
    public static final ModelLayerLocation CARP_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "carp"), "main");
    public static final ModelLayerLocation CATFISH_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "catfish"), "main");
    public static final ModelLayerLocation EEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "eel"), "main");
    public static final ModelLayerLocation HALIBUT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "halibut"), "main");
    public static final ModelLayerLocation HERRING_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "herring"), "main");
    public static final ModelLayerLocation MACKEREL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "mackerel"), "main");
    public static final ModelLayerLocation PERCHES_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "perches"), "main");
    public static final ModelLayerLocation PIKEFISH_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "pikefish"), "main");
    public static final ModelLayerLocation POLLOCK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "pollock"), "main");
    public static final ModelLayerLocation ROACH_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "roach"), "main");
    public static final ModelLayerLocation SARDINE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "sardine"), "main");
    public static final ModelLayerLocation STURGEON_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "sturgeon"), "main");
    public static final ModelLayerLocation TROUT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "trout"), "main");
    public static final ModelLayerLocation ZANDER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "zander"), "main");



    public static void registerLayers() {
        EntityModelLayerRegistry.register(ModModels.DEER_LAYER_LOCATION, DeerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DOE_LAYER_LOCATION, DoeModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.CARIBOU_LAYER_LOCATION, CaribouModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.ROE_DEER_LAYER_LOCATION, RoeDeerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DUCK_LAYER_LOCATION, DuckModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.DRAKE_LAYER_LOCATION, DrakeModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.GOOSE_LAYER_LOCATION, GooseModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.PHEASANT_LAYER_LOCATION, PheasantModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.QUAIL_LAYER_LOCATION, QuailModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.YAK_LAYER_LOCATION, YakModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.YAK_CARPET_LAYER_LOCATION, YakModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.HIGHLAND_COW_LAYER_LOCATION, HighlandCowModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.WISENT_LAYER_LOCATION, WisentModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.GOAT_LAYER_LOCATION, GoatModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.MOOSE_LAYER_LOCATION, MooseModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.BOAR_LAYER_LOCATION, BoarModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.BADGER_LAYER_LOCATION, BadgerModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.LYNX_LAYER_LOCATION, () -> LayerDefinition.create(OcelotModel.createBodyMesh(CubeDeformation.NONE), 64, 32));
        EntityModelLayerRegistry.register(ModModels.BEAR_LAYER_LOCATION, PolarBearModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.WOLF_LAYER_LOCATION, WolfModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.BEAVER_LAYER_LOCATION, BeaverModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.HARE_LAYER_LOCATION, RabbitModel::createBodyLayer);

        EntityModelLayerRegistry.register(ModModels.BASS_LAYER_LOCATION, BassModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.CARP_LAYER_LOCATION, CarpModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.CATFISH_LAYER_LOCATION, CatfishModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.EEL_LAYER_LOCATION, EelModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.HALIBUT_LAYER_LOCATION, HalibutModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.HERRING_LAYER_LOCATION, HerringModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.MACKEREL_LAYER_LOCATION, MackerelModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.PERCHES_LAYER_LOCATION, PerchesModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.PIKEFISH_LAYER_LOCATION, PikefishModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.POLLOCK_LAYER_LOCATION, PollockModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.ROACH_LAYER_LOCATION, RoachModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.SARDINE_LAYER_LOCATION, SardineModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.STURGEON_LAYER_LOCATION, SturgeonModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.TROUT_LAYER_LOCATION, TroutModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModModels.ZANDER_LAYER_LOCATION, ZanderModel::createBodyLayer);
        
    }

}
