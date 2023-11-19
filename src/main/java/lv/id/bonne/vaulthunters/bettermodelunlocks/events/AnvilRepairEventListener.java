//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.events;


import iskallia.vault.init.ModItems;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals.ExtraModelDiscoveryGoals;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


/**
 * This event listener is used to detect when player adds jewel to the tool in anvil.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilRepairEventListener
{
    /**
     * This method is triggered when player adds jewel to the tool in anvil.
     * @param event AnvilRepairEvent
     */
    @SubscribeEvent
    public static void onAnvilRepair(AnvilRepairEvent event)
    {
        if (event.getItemResult().is(ModItems.TOOL) &&
            event.getIngredientInput().is(ModItems.JEWEL) &&
            event.getPlayer() instanceof ServerPlayer serverPlayer)
        {
            ExtraModelDiscoveryGoals.MAX_HAMMER_SIZE.onJewelApply(serverPlayer,
                event.getItemResult());
        }
    }
}