package com.octopusi.servermod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.octopusi.servermod.entity.RecerdoEntity;

import de.teamlapen.vampirism.entity.DarkBloodProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.ModLoadingContext;

@Mod(ServerMod.MODID)
public class ServerMod {
    public static final String MODID = "servermod";

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);


    public static final RegistryObject<EntityType<DarkBloodProjectileEntity>> DARK_BLOOD_PROJECTILE =
            ENTITIES.register("dark_blood_projectile",
                    () -> EntityType.Builder.<DarkBloodProjectileEntity>of(DarkBloodProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .build("dark_blood_projectile"));

     public static final RegistryObject<EntityType<RecerdoEntity>> RECERDO_ENTITY = ENTITIES.register("recerdo",
            () -> EntityType.Builder.of(RecerdoEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 0.9f)
                    .build("recerdo"));


    public ServerMod() {
        // ✅ Registrar la configuración correctamente
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        System.out.println("[servermod] Mod inicializado correctamente.");
    }
}
