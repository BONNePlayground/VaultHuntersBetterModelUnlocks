//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

import iskallia.vault.config.GearModelRollRaritiesConfig;
import iskallia.vault.gear.data.VaultGearData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;


/**
 * Accessor to the new VH gear method.
 */
@Mixin(GearModelRollRaritiesConfig.class)
public interface GearModelRollRaritiesConfigAccessor
{
    @Invoker
    Set<ResourceLocation> callGetPossibleRolls(ItemStack stack, VaultGearData data, EquipmentSlot slot);
}
