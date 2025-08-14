package net.epichunt.client;

import dev.architectury.platform.Mod;
import net.epichunt.EpicHunt;
import net.epichunt.block.ModBlockEntities;
import net.epichunt.client.model.block.*;
import net.epichunt.client.model.entity.*;
import net.epichunt.client.render.block.*;
import net.epichunt.client.render.entity.*;
import net.epichunt.entity.ModEntities;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

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


    public static final ModelLayerLocation SMALL_ANTLERS_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "small_antlers"), "main");
    public static final ModelLayerLocation MEDIUM_ANTLERS_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "medium_antlers"), "main");
    public static final ModelLayerLocation LARGE_ANTLERS_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "large_antlers"), "main");

    public static final ModelLayerLocation MUSSEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "mussel_block"), "main");
    public static final ModelLayerLocation OYSTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "oyster_block"), "main");
    public static final ModelLayerLocation CLAM_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "clam_block"), "main");

    public static final ModelLayerLocation NARWHAL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "narwhal"), "main");
    public static final ModelLayerLocation SHARK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "white_shark"), "main");
    public static final ModelLayerLocation SWORDFISH_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "swordfish"), "main");
    public static final ModelLayerLocation WHALE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "whale"), "main");
    public static final ModelLayerLocation ORCA_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "orca"), "main");

    public static final ModelLayerLocation EAGLE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "eagle"), "main");
    public static final ModelLayerLocation BULLFINCH_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "bullfinch"), "main");
    public static final ModelLayerLocation COMMON_CRANE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "crane"), "main");
    public static final ModelLayerLocation GREAT_AUK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "great_auk"), "main");
    public static final ModelLayerLocation HAWK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "hawk"), "main");
    public static final ModelLayerLocation KESTREL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "kestrel"), "main");
    public static final ModelLayerLocation LOON_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "loon"), "main");
    public static final ModelLayerLocation NIGHTINGALE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "nightingale"), "main");
    public static final ModelLayerLocation OWL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "owl"), "main");
    public static final ModelLayerLocation PARTRIDGE_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "partridge"), "main");
    public static final ModelLayerLocation PIGEON_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "pigeon"), "main");
    public static final ModelLayerLocation RAVEN_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "raven"), "main");
    public static final ModelLayerLocation RAZORBILL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "razorbill"), "main");
    public static final ModelLayerLocation WHITE_STORK_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EpicHunt.MOD_ID, "stork"), "main");



    public static void registerLayers(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition> > consumer) {
        consumer.accept(ModModels.DEER_LAYER_LOCATION, DeerModel::createBodyLayer);
        consumer.accept(ModModels.DOE_LAYER_LOCATION, DoeModel::createBodyLayer);
        consumer.accept(ModModels.CARIBOU_LAYER_LOCATION, CaribouModel::createBodyLayer);
        consumer.accept(ModModels.ROE_DEER_LAYER_LOCATION, RoeDeerModel::createBodyLayer);
        consumer.accept(ModModels.DUCK_LAYER_LOCATION, DuckModel::createBodyLayer);
        consumer.accept(ModModels.DRAKE_LAYER_LOCATION, DrakeModel::createBodyLayer);
        consumer.accept(ModModels.GOOSE_LAYER_LOCATION, GooseModel::createBodyLayer);
        consumer.accept(ModModels.PHEASANT_LAYER_LOCATION, PheasantModel::createBodyLayer);
        consumer.accept(ModModels.QUAIL_LAYER_LOCATION, QuailModel::createBodyLayer);
        consumer.accept(ModModels.YAK_LAYER_LOCATION, YakModel::createBodyLayer);
        consumer.accept(ModModels.YAK_CARPET_LAYER_LOCATION, YakModel::createBodyLayer);
        consumer.accept(ModModels.HIGHLAND_COW_LAYER_LOCATION, HighlandCowModel::createBodyLayer);
        consumer.accept(ModModels.WISENT_LAYER_LOCATION, WisentModel::createBodyLayer);
        consumer.accept(ModModels.GOAT_LAYER_LOCATION, GoatModel::createBodyLayer);
        consumer.accept(ModModels.MOOSE_LAYER_LOCATION, MooseModel::createBodyLayer);
        consumer.accept(ModModels.BOAR_LAYER_LOCATION, BoarModel::createBodyLayer);
        consumer.accept(ModModels.BADGER_LAYER_LOCATION, BadgerModel::createBodyLayer);
        consumer.accept(ModModels.LYNX_LAYER_LOCATION, () -> LayerDefinition.create(OcelotModel.createBodyMesh(CubeDeformation.NONE), 64, 32));
        consumer.accept(ModModels.BEAR_LAYER_LOCATION, PolarBearModel::createBodyLayer);
        consumer.accept(ModModels.WOLF_LAYER_LOCATION, WolfModel::createBodyLayer);
        consumer.accept(ModModels.BEAVER_LAYER_LOCATION, BeaverModel::createBodyLayer);
        consumer.accept(ModModels.HARE_LAYER_LOCATION, RabbitModel::createBodyLayer);

        consumer.accept(ModModels.BASS_LAYER_LOCATION, BassModel::createBodyLayer);
        consumer.accept(ModModels.CARP_LAYER_LOCATION, CarpModel::createBodyLayer);
        consumer.accept(ModModels.CATFISH_LAYER_LOCATION, CatfishModel::createBodyLayer);
        consumer.accept(ModModels.EEL_LAYER_LOCATION, EelModel::createBodyLayer);
        consumer.accept(ModModels.HALIBUT_LAYER_LOCATION, HalibutModel::createBodyLayer);
        consumer.accept(ModModels.HERRING_LAYER_LOCATION, HerringModel::createBodyLayer);
        consumer.accept(ModModels.MACKEREL_LAYER_LOCATION, MackerelModel::createBodyLayer);
        consumer.accept(ModModels.PERCHES_LAYER_LOCATION, PerchesModel::createBodyLayer);
        consumer.accept(ModModels.PIKEFISH_LAYER_LOCATION, PikefishModel::createBodyLayer);
        consumer.accept(ModModels.POLLOCK_LAYER_LOCATION, PollockModel::createBodyLayer);
        consumer.accept(ModModels.ROACH_LAYER_LOCATION, RoachModel::createBodyLayer);
        consumer.accept(ModModels.SARDINE_LAYER_LOCATION, SardineModel::createBodyLayer);
        consumer.accept(ModModels.STURGEON_LAYER_LOCATION, SturgeonModel::createBodyLayer);
        consumer.accept(ModModels.TROUT_LAYER_LOCATION, TroutModel::createBodyLayer);
        consumer.accept(ModModels.ZANDER_LAYER_LOCATION, ZanderModel::createBodyLayer);

        consumer.accept(ModModels.NARWHAL_LAYER_LOCATION, NarwhalModel::createBodyLayer);
        consumer.accept(ModModels.SHARK_LAYER_LOCATION, SharkModel::createBodyLayer);
        consumer.accept(ModModels.SWORDFISH_LAYER_LOCATION, SwordfishModel::createBodyLayer);
        consumer.accept(ModModels.WHALE_LAYER_LOCATION, WhaleModel::createBodyLayer);
        consumer.accept(ModModels.ORCA_LAYER_LOCATION, OrcaModel::createBodyLayer);

        consumer.accept(ModModels.EAGLE_LAYER_LOCATION, EagleModel::createBodyLayer);
        consumer.accept(ModModels.BULLFINCH_LAYER_LOCATION, BullfinchModel::createBodyLayer);
        consumer.accept(ModModels.COMMON_CRANE_LAYER_LOCATION, CommonCraneModel::createBodyLayer);
        consumer.accept(ModModels.GREAT_AUK_LAYER_LOCATION, GreatAukModel::createBodyLayer);
        consumer.accept(ModModels.HAWK_LAYER_LOCATION, HawkModel::createBodyLayer);
        consumer.accept(ModModels.KESTREL_LAYER_LOCATION, KestrelModel::createBodyLayer);
        consumer.accept(ModModels.LOON_LAYER_LOCATION, LoonModel::createBodyLayer);
        consumer.accept(ModModels.NIGHTINGALE_LAYER_LOCATION, NightingaleModel::createBodyLayer);
        consumer.accept(ModModels.OWL_LAYER_LOCATION, OwlModel::createBodyLayer);
        consumer.accept(ModModels.PARTRIDGE_LAYER_LOCATION, PartridgeModel::createBodyLayer);
        consumer.accept(ModModels.PIGEON_LAYER_LOCATION, PigeonModel::createBodyLayer);
        consumer.accept(ModModels.RAVEN_LAYER_LOCATION, RavenModel::createBodyLayer);
        consumer.accept(ModModels.RAZORBILL_LAYER_LOCATION, RazorbillModel::createBodyLayer);
        consumer.accept(ModModels.WHITE_STORK_LAYER_LOCATION, WhiteStorkModel::createBodyLayer);

        consumer.accept(ModModels.SMALL_ANTLERS_LAYER_LOCATION, SmallAntlersModel::createBodyLayer);
        consumer.accept(ModModels.MEDIUM_ANTLERS_LAYER_LOCATION, MediumAntlersModel::createBodyLayer);
        consumer.accept(ModModels.LARGE_ANTLERS_LAYER_LOCATION, LargeAntlersModel::createBodyLayer);
        consumer.accept(ModModels.MUSSEL_LAYER_LOCATION, MusselBlockModel::createBodyLayer);
        consumer.accept(ModModels.OYSTER_LAYER_LOCATION, OysterBlockModel::createBodyLayer);
        consumer.accept(ModModels.CLAM_LAYER_LOCATION, ClamBlockModel::createBodyLayer);
    }

    public static void renderRegistry() {
        EntityRenderers.register(ModEntities.DEER_ENTITY.get(), DeerRender::new);
        EntityRenderers.register(ModEntities.DOE_ENTITY.get(), DoeRender::new);
        EntityRenderers.register(ModEntities.CARIBOU_ENTITY.get(), CaribouRender::new);
        EntityRenderers.register(ModEntities.ROE_DEER_ENTITY.get(), RoeDeerRender::new);
        EntityRenderers.register(ModEntities.DUCK_ENTITY.get(), DuckRender::new);
        EntityRenderers.register(ModEntities.DRAKE_ENTITY.get(), DrakeRender::new);
        EntityRenderers.register(ModEntities.THROWN_DUCK_EGG.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.GOOSE_ENTITY.get(), GooseRender::new);
        EntityRenderers.register(ModEntities.THROWN_GOOSE_EGG.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.PHEASANT_ENTITY.get(), PheasantRender::new);
        EntityRenderers.register(ModEntities.THROWN_PHEASANT_EGG.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.QUAIL_ENTITY.get(), QuailRender::new);
        EntityRenderers.register(ModEntities.THROWN_QUAIL_EGG.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.YAK_ENTITY.get(), YakRender::new);
        EntityRenderers.register(ModEntities.HIGHLAND_COW_ENTITY.get(), HighlandCowRender::new);
        EntityRenderers.register(ModEntities.WISENT_ENTITY.get(), WisentRender::new);
        EntityRenderers.register(ModEntities.GOAT_ENTITY.get(), GoatRender::new);
        EntityRenderers.register(ModEntities.MOOSE_ENTITY.get(), MooseRender::new);
        EntityRenderers.register(ModEntities.BADGER_ENTITY.get(), BadgerRender::new);
        EntityRenderers.register(ModEntities.BOAR_ENTITY.get(), BoarRender::new);
        EntityRenderers.register(ModEntities.LYNX_ENTITY.get(), LynxRender::new);
        EntityRenderers.register(ModEntities.BEAR_ENTITY.get(), BearRender::new);
        EntityRenderers.register(ModEntities.WOLF_ENTITY.get(), WolfRender::new);
        EntityRenderers.register(ModEntities.BEAVER_ENTITY.get(), BeaverRender::new);
        EntityRenderers.register(ModEntities.HARE_ENTITY.get(), HareRender::new);

        EntityRenderers.register(ModEntities.BASS_ENTITY.get(), BassRender::new);
        EntityRenderers.register(ModEntities.CARP_ENTITY.get(), CarpRender::new);
        EntityRenderers.register(ModEntities.CATFISH_ENTITY.get(), CatfishRender::new);
        EntityRenderers.register(ModEntities.EEL_ENTITY.get(), EelRender::new);
        EntityRenderers.register(ModEntities.HALIBUT_ENTITY.get(), HalibutRender::new);
        EntityRenderers.register(ModEntities.HERRING_ENTITY.get(), HerringRender::new);
        EntityRenderers.register(ModEntities.MACKEREL_ENTITY.get(), MackerelRender::new);
        EntityRenderers.register(ModEntities.PERCHES_ENTITY.get(), PerchesRender::new);
        EntityRenderers.register(ModEntities.PIKEFISH_ENTITY.get(), PikefishRender::new);
        EntityRenderers.register(ModEntities.POLLOCK_ENTITY.get(), PollockRender::new);
        EntityRenderers.register(ModEntities.ROACH_ENTITY.get(), RoachRender::new);
        EntityRenderers.register(ModEntities.SARDINE_ENTITY.get(), SardineRender::new);
        EntityRenderers.register(ModEntities.STURGEON_ENTITY.get(), SturgeonRender::new);
        EntityRenderers.register(ModEntities.TROUT_ENTITY.get(), TroutRender::new);
        EntityRenderers.register(ModEntities.ZANDER_ENTITY.get(), ZanderRender::new);

        EntityRenderers.register(ModEntities.NARWHAL_ENTITY.get(), NarwhalRender::new);
        EntityRenderers.register(ModEntities.WHALE_ENTITY.get(), WhaleRender::new);
        EntityRenderers.register(ModEntities.ORCA_ENTITY.get(), OrcaRender::new);
        EntityRenderers.register(ModEntities.WHITE_SHARK_ENTITY.get(), WhiteSharkRender::new);
        EntityRenderers.register(ModEntities.SWORDFISH_ENTITY.get(), SwordfishRender::new);

        EntityRenderers.register(ModEntities.EAGLE_ENTITY.get(), EagleRender::new);
        EntityRenderers.register(ModEntities.HAWK_ENTITY.get(), HawkRender::new);
        EntityRenderers.register(ModEntities.KESTREL_ENTITY.get(), KestrelRender::new);
        EntityRenderers.register(ModEntities.LOON_ENTITY.get(), LoonRender::new);
        EntityRenderers.register(ModEntities.PARTRIDGE_ENTITY.get(), PartridgeRender::new);
        EntityRenderers.register(ModEntities.PIGEON_ENTITY.get(), PigeonRender::new);
        EntityRenderers.register(ModEntities.WHITE_STORK_ENTITY.get(), WhiteStorkRender::new);
        EntityRenderers.register(ModEntities.COMMON_CRANE_ENTITY.get(), CommonCraneRender::new);
    }

    public static void blockEntityRender() {
        BlockEntityRenderers.register(ModBlockEntities.SMALL_ANTLERS_ENTITY.get(), SmallAntlersRender::new);
        BlockEntityRenderers.register(ModBlockEntities.MEDIUM_ANTLERS_ENTITY.get(), MediumAntlersRender::new);
        BlockEntityRenderers.register(ModBlockEntities.LARGE_ANTLERS_ENTITY.get(), LargeAntlersRender::new);
        BlockEntityRenderers.register(ModBlockEntities.MUSSEL_BLOCK_ENTITY.get(), MusselBlockRender::new);
        BlockEntityRenderers.register(ModBlockEntities.OYSTER_BLOCK_ENTITY.get(), OysterBlockRender::new);
        BlockEntityRenderers.register(ModBlockEntities.CLAM_BLOCK_ENTITY.get(), ClamBlockRender::new);
    }
}
