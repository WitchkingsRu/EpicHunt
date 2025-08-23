package net.epichunt.sound;
import java.util.stream.IntStream;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(EpicHunt.MOD_ID, Registries.SOUND_EVENT);
    
    public static final RegistrySupplier<SoundEvent> DEER_AMBIENT = registerSound("deer_ambient");
    public static final RegistrySupplier<SoundEvent> DEER_HURT = registerSound("deer_hurt");
    public static final RegistrySupplier<SoundEvent> DEER_DEATH = registerSound("deer_death");
    public static final RegistrySupplier<SoundEvent> DEER_EAT = registerSound("deer_eat");
    public static final RegistrySupplier<SoundEvent> DUCK_AMBIENT = registerSound("duck_ambient");
    public static final RegistrySupplier<SoundEvent> DUCK_HURT = registerSound("duck_hurt");
    public static final RegistrySupplier<SoundEvent> DUCK_DEATH = registerSound("duck_death");
    public static final RegistrySupplier<SoundEvent> GOOSE_AMBIENT = registerSound("goose_ambient");
    public static final RegistrySupplier<SoundEvent> GOOSE_HURT = registerSound("goose_hurt");
    public static final RegistrySupplier<SoundEvent> GOOSE_DEATH = registerSound("goose_death");
    public static final RegistrySupplier<SoundEvent> PHEASANT_AMBIENT = registerSound("pheasant_ambient");
    public static final RegistrySupplier<SoundEvent> PHEASANT_HURT = registerSound("pheasant_hurt");
    public static final RegistrySupplier<SoundEvent> PHEASANT_DEATH = registerSound("pheasant_death");
    public static final RegistrySupplier<SoundEvent> QUAIL_AMBIENT = registerSound("quail_ambient");
    public static final RegistrySupplier<SoundEvent> QUAIL_HURT = registerSound("quail_hurt");
    public static final RegistrySupplier<SoundEvent> YAK_AMBIENT = registerSound("yak_ambient");
    public static final RegistrySupplier<SoundEvent> YAK_HURT = registerSound("yak_hurt");
    public static final RegistrySupplier<SoundEvent> YAK_DEATH = registerSound("yak_death");
    public static final RegistrySupplier<SoundEvent> WISENT_AMBIENT = registerSound("wisent_ambient");
    public static final RegistrySupplier<SoundEvent> WISENT_HURT = registerSound("wisent_hurt");
    public static final RegistrySupplier<SoundEvent> WISENT_DEATH = registerSound("wisent_death");
    public static final RegistrySupplier<SoundEvent> BOAR_AMBIENT = registerSound("boar_ambient");
    public static final RegistrySupplier<SoundEvent> BOAR_HURT = registerSound("boar_hurt");
    public static final RegistrySupplier<SoundEvent> BOAR_DEATH = registerSound("boar_death");
    public static final RegistrySupplier<SoundEvent> BADGER_AMBIENT = registerSound("badger_ambient");
    public static final RegistrySupplier<SoundEvent> BADGER_HURT = registerSound("badger_hurt");
    public static final RegistrySupplier<SoundEvent> BADGER_DEATH = registerSound("badger_death");
    public static final RegistrySupplier<SoundEvent> WOLF_AMBIENT = registerSound("wolf_ambient");
    public static final RegistrySupplier<SoundEvent> WOLF_HURT = registerSound("wolf_hurt");
    public static final RegistrySupplier<SoundEvent> WOLF_DEATH = registerSound("wolf_death");
    public static final RegistrySupplier<SoundEvent> MOOSE_AMBIENT = registerSound("moose_ambient");
    public static final RegistrySupplier<SoundEvent> MOOSE_HURT = registerSound("moose_hurt");
    public static final RegistrySupplier<SoundEvent> MOOSE_DEATH = registerSound("moose_death");
    public static final RegistrySupplier<SoundEvent> BEAVER_AMBIENT = registerSound("beaver_ambient");
    public static final RegistrySupplier<SoundEvent> BEAVER_HURT = registerSound("beaver_hurt");
    public static final RegistrySupplier<SoundEvent> BEAVER_DEATH = registerSound("beaver_death");
    public static final RegistrySupplier<SoundEvent> NARWHAL_AMBIENT = registerSound("narwhal_ambient");
    public static final RegistrySupplier<SoundEvent> NARWHAL_HURT = registerSound("narwhal_hurt");
    public static final RegistrySupplier<SoundEvent> NARWHAL_DEATH = registerSound("narwhal_death");
    public static final RegistrySupplier<SoundEvent> WHALE_AMBIENT = registerSound("whale_ambient");
    public static final RegistrySupplier<SoundEvent> WHALE_HURT = registerSound("whale_hurt");
    public static final RegistrySupplier<SoundEvent> WHALE_DEATH = registerSound("whale_death");
    public static final RegistrySupplier<SoundEvent> ORCA_AMBIENT = registerSound("orca_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT1 = registerSound("nightingale1_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT2 = registerSound("nightingale2_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT3 = registerSound("nightingale3_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT4 = registerSound("nightingale4_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT5 = registerSound("nightingale5_ambient");
    public static final RegistrySupplier<SoundEvent> NIGHTINGALE_AMBIENT6 = registerSound("nightingale6_ambient");
    public static final RegistrySupplier<SoundEvent> RAVEN_AMBIENT = registerSound("raven_ambient");
    public static final RegistrySupplier<SoundEvent> VASYA_AMBIENT = registerSound("vasya_ambient");
    public static final RegistrySupplier<SoundEvent> RAVEN_HURT = registerSound("raven_hurt");
    public static final RegistrySupplier<SoundEvent> RAVEN_DEATH = registerSound("raven_death");
    public static final RegistrySupplier<SoundEvent> BULLFINCH_AMBIENT = registerSound("bullfinch_ambient");
    public static final RegistrySupplier<SoundEvent> KESTREL_AMBIENT = registerSound("kestrel_ambient");
    public static final RegistrySupplier<SoundEvent> EAGLE_AMBIENT = registerSound("eagle_ambient");
    public static final RegistrySupplier<SoundEvent> EAGLE_HURT = registerSound("eagle_hurt");
    public static final RegistrySupplier<SoundEvent> EAGLE_DEATH = registerSound("eagle_death");
    public static final RegistrySupplier<SoundEvent> HAWK_AMBIENT = registerSound("hawk_ambient");
    public static final RegistrySupplier<SoundEvent> HAWK_HURT = registerSound("hawk_hurt");
    public static final RegistrySupplier<SoundEvent> HAWK_DEATH = registerSound("hawk_death");
    public static final RegistrySupplier<SoundEvent> LOON_AMBIENT = registerSound("loon_ambient");
    public static final RegistrySupplier<SoundEvent> LOON_HURT = registerSound("loon_hurt");
    public static final RegistrySupplier<SoundEvent> LOON_DEATH = registerSound("loon_death");
    public static final RegistrySupplier<SoundEvent> PARTRIDGE_AMBIENT = registerSound("partridge_ambient");
    public static final RegistrySupplier<SoundEvent> PARTRIDGE_HURT = registerSound("partridge_hurt");
    public static final RegistrySupplier<SoundEvent> PARTRIDGE_DEATH = registerSound("partridge_death");


    private static RegistrySupplier<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(EpicHunt.MOD_ID, name)));
    }


}
