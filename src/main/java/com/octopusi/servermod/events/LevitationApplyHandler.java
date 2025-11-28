package com.octopusi.servermod.events;

import com.octopusi.servermod.ServerMod;
import com.octopusi.servermod.util.LevitationTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ServerMod.MODID)
public class LevitationApplyHandler {

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        // 🔹 Log general para ver si entra a este evento
        System.out.println("[DEBUG] MobEffectEvent.Added fired for " + event.getEntity().getName().getString()
                + " with effect " + event.getEffectInstance().getDescriptionId());

        if (event.getEffectInstance().getEffect() == MobEffects.LEVITATION) {
            if (event.getEntity().getLevel().isClientSide) {
                System.out.println("[DEBUG] Ignorado (lado cliente)");
                return;
            }

            if (event.getEffectSource() instanceof ServerPlayer player) {
                LevitationTracker.markLevitationSource(event.getEntity(), player);
                System.out.println("[DEBUG] Registrado jugador " + player.getName().getString() +
                        " como fuente de levitación de " + event.getEntity().getName().getString());
            } else {
                System.out.println("[DEBUG] Levitation aplicada sin fuente o fuente no es jugador");
            }
        }
    }
}
