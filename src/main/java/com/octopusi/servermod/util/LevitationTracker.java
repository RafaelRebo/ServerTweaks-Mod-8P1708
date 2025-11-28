package com.octopusi.servermod.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;
import java.util.WeakHashMap;

public class LevitationTracker {
    // Guarda qué jugador hizo levitar a qué entidad
    private static final Map<LivingEntity, ServerPlayer> levitationSources = new WeakHashMap<>();

    public static void markLevitationSource(LivingEntity target, ServerPlayer source) {
        levitationSources.put(target, source);
    }

    public static ServerPlayer getLevitationSource(LivingEntity entity) {
        return levitationSources.get(entity);
    }

    public static void clear(LivingEntity entity) {
        levitationSources.remove(entity);
    }
}
