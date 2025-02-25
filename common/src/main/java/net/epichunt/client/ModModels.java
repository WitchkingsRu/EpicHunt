package net.epichunt.client;

import dev.architectury.platform.Mod;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.epichunt.EpicHunt;
import net.epichunt.block.AbstractAntlersBlock;
import net.epichunt.block.ModBlockEntities;
import net.epichunt.client.model.block.SmallAntlersModel;
import net.epichunt.client.render.block.SmallAntlersRender;
import net.epichunt.client.render.item.SmallAntlersItemRender;
import net.epichunt.entity.ModEntities;
import net.epichunt.entity.client.model.*;
import net.epichunt.entity.client.render.*;
import net.epichunt.item.ModItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.PolarBearModel;
import net.epichunt.entity.client.model.WolfModel;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.model.geom.EntityModelSet;

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

        consumer.accept(ModModels.SMALL_ANTLERS_LAYER_LOCATION, SmallAntlersModel::createBodyLayer);
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

    }

    public static void blockEntityRender() {
        BlockEntityRenderers.register(ModBlockEntities.SMALL_ANTLERS_ENTITY.get(), SmallAntlersRender::new);
    }
}
