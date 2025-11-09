package com.octopusi.servermod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;

@Mod(ServerMod.MODID)
public class ServerMod {
    public static final String MODID = "servermod";

    public ServerMod() {
        // ✅ Registrar la configuración correctamente
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        System.out.println("[servermod] Mod inicializado correctamente.");
    }
}
