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
import net.mehvahdjukaar.supplementaries.setup.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedbackpacks.api.CapabilityBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
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

        // Get item from nearest player main hand.
        ItemStack itemStack = nearestPlayer.getMainHandItem();

        if (itemStack.getItem() instanceof BackpackItem)
        {
            // Now try to handle backpack in player main hand.
            boolean newValue = itemStack.
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
        else if ((itemStack.getItem().getRegistryName() != null &&
            itemStack.getItem().getRegistryName().getPath().endsWith("shulker_box")) ||
            itemStack.is(ModRegistry.SACK_ITEM.get()))
        {
            // Now try to handle shulker boxes in player main hand.
            CompoundTag originalTag = itemStack.getTag();

            // Find BlockEntityTag
            if (originalTag != null &&
                originalTag.contains("BlockEntityTag") &&
                originalTag.getTagType("BlockEntityTag") == CompoundTag.TAG_COMPOUND)
            {
                CompoundTag blockEntityTag = originalTag.getCompound("BlockEntityTag");

                // Find items in shulker box
                if (blockEntityTag.contains("Items") &&
                    blockEntityTag.getTagType("Items") == CompoundTag.TAG_LIST)
                {
                    ListTag items = blockEntityTag.getList("Items", CompoundTag.TAG_COMPOUND);

                    if (!items.isEmpty())
                    {
                        for (int i = 0; i < items.size(); i++)
                        {
                            // Now for each item try to parse it.
                            ItemStack stackInSlot = ItemStack.of(items.getCompound(i));

                            if (stackInSlot.getCount() == 1 &&
                                stackInSlot.getItem() instanceof IdentifiableItem identifiableItem)
                            {
                                // Parse as vault gear item.
                                VaultGearState state = identifiableItem.getState(stackInSlot);

                                if (state == VaultGearState.UNIDENTIFIED)
                                {
                                    // Item found. Return true and cancel search.
                                    cir.setReturnValue(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
