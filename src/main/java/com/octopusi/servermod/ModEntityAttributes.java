package com.octopusi.servermod;

import com.octopusi.servermod.entity.RecerdoEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

@Mod.EventBusSubscriber(modid = ServerMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {

        event.put(
                ServerMod.RECERDO_ENTITY.get(),                   // tu EntityType real
                RecerdoEntity.createAttributes().build()          // los atributos del mob
        );
    }
}

