package com.octopusi.servermod.advancements;

import com.octopusi.servermod.ServerMod;
import com.octopusi.servermod.util.LevitationTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ServerMod.MODID)
public class PigLevitationAdvancement {

    private static final String ADVANCEMENT_NAME = "pig_levitation_crash";

    @SubscribeEvent
    public static void onPigDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Pig)) return;
        if (!event.getSource().isFall()) return;

        System.out.println("[DEBUG] Cerdo murió por caída");

        // Buscar si estaba levitando y quién causó la levitación
        ServerPlayer player = LevitationTracker.getLevitationSource(entity);
        if (player != null) {
            System.out.println("[DEBUG] Cerdo tenía origen de levitación: " + player.getName().getString());
            giveAdvancement(player, ADVANCEMENT_NAME);
        } else {
            System.out.println("[DEBUG] Cerdo sin registro de levitación");
        }
    }

    private static void giveAdvancement(ServerPlayer player, String advancementName) {
        var server = player.getServer();
        if (server == null) return;

        var id = new ResourceLocation(ServerMod.MODID, advancementName);
        var advancement = server.getAdvancements().getAdvancement(id);
        if (advancement == null) return;

        var progress = player.getAdvancements().getOrStartProgress(advancement);
        if (!progress.isDone()) {
            for (String criterion : progress.getRemainingCriteria()) {
                player.getAdvancements().award(advancement, criterion);
            }
            System.out.println("[DEBUG] Avance otorgado a " + player.getName().getString());
        }
    }
}
