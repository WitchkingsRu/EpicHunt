package net.epichunt.sound;
import java.util.stream.IntStream;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.epichunt.EpicHunt;
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

    private static RegistrySupplier<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(EpicHunt.MOD_ID, name)));
    }


}
