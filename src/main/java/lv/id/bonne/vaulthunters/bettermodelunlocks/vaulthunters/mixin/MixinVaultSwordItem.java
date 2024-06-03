//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.*;

import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.gear.VaultSwordItem;
import lv.id.bonne.vaulthunters.bettermodelunlocks.utils.GearHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * This mixin is used to add custom roll to VaultSwordItem.
 */
@Mixin(value = VaultSwordItem.class, remap = false)
@Implements(@Interface(iface = VaultGearItem.class, prefix = "customRoll$"))
public abstract class MixinVaultSwordItem
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

        GearHelper.modelUnlockHelper(stack, serverPlayer, this.getIntendedSlot(stack));
    }
}
