package com.octopusi.servermod.advancements;

import com.octopusi.servermod.ServerMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = ServerMod.MODID)
public class FourEyesAdvancement {

    private static final String ADVANCEMENT_NAME = "four_eyes";
    private static final ResourceLocation GOGGLES_ID = new ResourceLocation("create", "goggles");

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.getLevel().isClientSide) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        ItemStack headItem = player.getInventory().getArmor(3); // Slot de cabeza
        boolean hasHeadGoggles = isGoggles(headItem);

        Item gogglesItem = ForgeRegistries.ITEMS.getValue(GOGGLES_ID);
        boolean hasCuriosGoggles = gogglesItem != null &&
                CuriosApi.getCuriosHelper().findEquippedCurio(gogglesItem, player).isPresent();

        if (hasHeadGoggles && hasCuriosGoggles) {
            giveAdvancement(player, ADVANCEMENT_NAME);
        }
    }

    private static boolean isGoggles(ItemStack stack) {
        if (stack.isEmpty()) return false;
        ResourceLocation id = stack.getItem().builtInRegistryHolder().key().location();
        return id.equals(GOGGLES_ID);
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
            System.out.println("[DEBUG] Avance 'Cuatro Ojos' otorgado a " + player.getName().getString());
        }
    }
}
