package com.octopusi.servermod.advancements;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.octopusi.servermod.ServerMod;

// Vampirism imports (ajusta el paquete real si es distinto)
import de.teamlapen.vampirism.entity.DarkBloodProjectileEntity;


@Mod.EventBusSubscriber(modid = ServerMod.MODID)
public class BloodProjectileKillHandler {

    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {
        if (!(event.getSource().getDirectEntity() instanceof DarkBloodProjectileEntity projectile)) {
            return; // No fue un DarkBloodProjectile
        }

        if (!(projectile.getOwner() instanceof ServerPlayer player)) {
            return; // El proyectil no fue disparado por un jugador
        }

        // ✅ Dar advancement al jugador
        giveAdvancement(player, "blood_kill");
    }

    private static void giveAdvancement(ServerPlayer player, String advancementName) {
        var server = player.getServer();
        if (server == null) return;

        var id = new net.minecraft.resources.ResourceLocation(ServerMod.MODID, advancementName);
        var advancement = server.getAdvancements().getAdvancement(id);
        if (advancement == null) {
            return;
        }

        var progress = player.getAdvancements().getOrStartProgress(advancement);
        if (!progress.isDone()) {
            for (String criterion : progress.getRemainingCriteria()) {
                player.getAdvancements().award(advancement, criterion);
            }
        }
    }
}