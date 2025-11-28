package com.octopusi.servermod.client;

import com.octopusi.servermod.ServerMod;
import com.octopusi.servermod.client.model.RecerdoModel;
import com.octopusi.servermod.client.renderer.RecerdoRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ServerMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    // REGISTRA EL MODELO DEL RECERDO
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RecerdoModel.LAYER_LOCATION, RecerdoModel::createBodyLayer);
    }

    // REGISTRA EL RENDERER
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ServerMod.RECERDO_ENTITY.get(), RecerdoRenderer::new);
    }
}

