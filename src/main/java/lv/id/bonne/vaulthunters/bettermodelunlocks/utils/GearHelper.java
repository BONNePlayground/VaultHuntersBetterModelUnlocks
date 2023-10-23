//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.utils;


import java.util.Set;

import iskallia.vault.core.util.WeightedList;
import iskallia.vault.gear.VaultGearRarity;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;


public class GearHelper
{
    public static void modelUnlockHelper(ItemStack stack,
        ServerPlayer player,
        Set<ResourceLocation> gearModelIDs,
        VaultGearRarity rarity,
        VaultGearData data)
    {
        // Create wightList of all items.
        WeightedList<ResourceLocation> weightedList = new WeightedList<>();

        // Get discovered armor sets.
        DiscoveredModelsData worldData = DiscoveredModelsData.get(player.getLevel().getServer());
        Set<ResourceLocation> discoveredModels = worldData.getDiscoveredModels(player.getUUID());

        // Add all items to weighted list.
        gearModelIDs.forEach(key -> weightedList.add(key, discoveredModels.contains(key) ?
            BetterModelUnlocks.CONFIGURATION.getPunishmentValue().get() : 1.0));
        ResourceLocation modelKey = weightedList.getRandom().orElse(null);

        // Try to set model and rarity.
        if (modelKey != null)
        {
            data.updateAttribute(ModGearAttributes.GEAR_MODEL, modelKey);
            data.setRarity(rarity);
            data.write(stack);
        }
    }
}
