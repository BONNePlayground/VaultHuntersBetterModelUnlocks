//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.utils;


import java.util.Random;
import java.util.Set;

import iskallia.vault.core.util.WeightedList;
import iskallia.vault.gear.VaultGearRarity;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin.GearModelRollRaritiesConfigAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;


public class GearHelper
{
    public static void modelUnlockHelper(ItemStack stack,
        ServerPlayer player,
        EquipmentSlot slot)
    {
        VaultGearData data = VaultGearData.read(stack);
        VaultGearRarity rarity = data.getFirstValue(ModGearAttributes.GEAR_ROLL_TYPE).
            flatMap((rollTypeStr) -> ModConfigs.VAULT_GEAR_TYPE_CONFIG.getRollPool(rollTypeStr)).
            orElse(ModConfigs.VAULT_GEAR_TYPE_CONFIG.getDefaultRoll()).
            getRandom(new Random());
        data.setRarity(rarity);
        data.write(stack);

        // Create wightList of all items.
        WeightedList<ResourceLocation> weightedList = new WeightedList<>();

        // Get discovered armor sets.
        DiscoveredModelsData worldData = DiscoveredModelsData.get(player.getLevel().getServer());
        Set<ResourceLocation> discoveredModels = worldData.getDiscoveredModels(player.getUUID());

        Set<ResourceLocation> gearModelIDs =
            ((GearModelRollRaritiesConfigAccessor) ModConfigs.GEAR_MODEL_ROLL_RARITIES).
                callGetPossibleRolls(stack, data, slot);

        // Add all items to weighted list.
        gearModelIDs.forEach(key -> weightedList.add(key, discoveredModels.contains(key) ?
            BetterModelUnlocks.CONFIGURATION.getPunishmentValue() : 1.0));
        weightedList.getRandom().ifPresent(modelKey ->
            data.updateAttribute(ModGearAttributes.GEAR_MODEL, modelKey));

        data.write(stack);
    }
}
