//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import iskallia.vault.block.entity.IdentificationStandTileEntity;
import iskallia.vault.block.entity.base.BookAnimatingTileEntity;
import iskallia.vault.gear.VaultGearState;
import iskallia.vault.gear.item.IdentifiableItem;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedbackpacks.api.CapabilityBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;


/**
 * This allows to enable animation of book, if player has unidentified items in backpack.
 */
@Mixin(value = IdentificationStandTileEntity.class, remap = false)
public class MixinIdentificationStandTileEntity
{
    @Inject(method = "canOpenBookModel", at = @At("RETURN"), cancellable = true)
    private void showOpenedBookModel(Player nearestPlayer,
        Level level,
        BlockPos blockPos,
        BlockState blockState,
        BookAnimatingTileEntity tileEntity,
        CallbackInfoReturnable<Boolean> cir)
    {
        if (!BetterModelUnlocks.CONFIGURATION.getEasierIdentification())
        {
            // Mod is disabled.
            return;
        }

        if (nearestPlayer.getMainHandItem().getItem() instanceof BackpackItem)
        {
            boolean newValue = nearestPlayer.getMainHandItem().
                getCapability(CapabilityBackpackWrapper.getCapabilityInstance()).
                map(wrapper -> {
                    ITrackedContentsItemHandler inventoryForUpgradeProcessing =
                        wrapper.getInventoryForUpgradeProcessing();

                    for (int i = 0; i < inventoryForUpgradeProcessing.getSlots(); i++)
                    {
                        ItemStack stackInSlot = wrapper.getInventoryForUpgradeProcessing().getStackInSlot(i);

                        if (stackInSlot.getCount() == 1 &&
                            stackInSlot.getItem() instanceof IdentifiableItem identifiableItem)
                        {
                            VaultGearState state = identifiableItem.getState(stackInSlot);

                            if (state == VaultGearState.UNIDENTIFIED)
                            {
                                return true;
                            }
                        }
                    }

                    return false;
                }).
                orElse(false);

            cir.setReturnValue(cir.getReturnValue() | newValue);
        }
    }
}
