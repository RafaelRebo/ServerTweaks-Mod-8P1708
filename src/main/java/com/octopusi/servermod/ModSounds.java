package com.octopusi.servermod;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "servermod");

    static void register(IEventBus bus) {
        SOUNDS.register(bus);
    }

    private static RegistryObject<SoundEvent> create(String soundNameIn) {
        ResourceLocation resourcelocation = new ResourceLocation(ServerMod.MODID, soundNameIn);
        return SOUNDS.register(soundNameIn, () -> new SoundEvent(resourcelocation));
    }

    public static final RegistryObject<SoundEvent> RECERDO_AMBIENT = create("recerdo.ambient");
    public static final RegistryObject<SoundEvent> RECERDO_HURT = create("recerdo.hurt");
    public static final RegistryObject<SoundEvent> RECERDO_DEATH = create("recerdo.death");
}
