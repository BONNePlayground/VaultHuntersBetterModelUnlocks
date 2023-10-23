//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.*;
import java.util.Set;

import iskallia.vault.gear.GearRollHelper;
import iskallia.vault.gear.VaultGearRarity;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.item.gear.WandItem;
import lv.id.bonne.vaulthunters.bettermodelunlocks.utils.GearHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * This mixin is used to add custom roll to WandItem.
 */
@Mixin(value = WandItem.class, remap = false)
@Implements(@Interface(iface = VaultGearItem.class, prefix = "customRoll$"))
public abstract class MixinWandItem
{
    /**
     * The shadow method is used to get the slot of the item.
     * @param stack ItemStack of the item.
     * @return EquipmentSlot of the item.
     */
    @Shadow
    public abstract EquipmentSlot getIntendedSlot(ItemStack stack);


    /**
     * This method overwrites original tickRoll method to allow better gear model unlocks.
     * @param stack ItemStack of the item.
     * @param player Player identifies the item.
     * @author BONNe
     */
    @Intrinsic(displace = true)
    public void customRoll$tickRoll(ItemStack stack, Player player)
    {
        if (!(player instanceof ServerPlayer serverPlayer))
        {
            return;
        }

        VaultGearData data = VaultGearData.read(stack);

        VaultGearRarity rarity = data.getFirstValue(ModGearAttributes.GEAR_ROLL_TYPE).
            flatMap((rollTypeStr) -> ModConfigs.VAULT_GEAR_TYPE_CONFIG.getRollPool(rollTypeStr)).
            orElse(ModConfigs.VAULT_GEAR_TYPE_CONFIG.getDefaultRoll()).
            getRandom(GearRollHelper.rand);

        // Get all possible models for this item
        Set<ResourceLocation> gearModelIDs =
            ModConfigs.GEAR_MODEL_ROLL_RARITIES.getPossibleRolls(((WandItem) (Object) this),
                rarity,
                this.getIntendedSlot(stack));

        GearHelper.modelUnlockHelper(stack, serverPlayer, gearModelIDs, rarity, data);
    }
}
