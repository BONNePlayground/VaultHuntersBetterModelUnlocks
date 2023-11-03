//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import iskallia.vault.block.IdentificationStandBlock;
import iskallia.vault.gear.VaultGearState;
import iskallia.vault.gear.item.IdentifiableItem;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.p3pp3rf1y.sophisticatedbackpacks.api.CapabilityBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;


/**
 * This mixin allows to identify vault items inside backpack by clicking on identification stand.
 */
@Mixin(value = IdentificationStandBlock.class)
public class MixinIdentificationStandBlock
{
    /**
     * This mixin allows to identify vault items inside backpack by clicking on identification stand.
     * @param value Original value.
     * @param blockState Block state of identification stand.
     * @param level Level where identification stand is located.
     * @param pos Position of the stand.
     * @param player The Player who clicks.
     * @param hand The hand player clicks on.
     * @param hit The hit result.
     * @return New value.
     */
    @ModifyVariable(method = "use", ordinal = 0, at = @At(value = "STORE"))
    private boolean mainHandIdentification(boolean value, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (!BetterModelUnlocks.CONFIGURATION.getEasierIdentification())
        {
            // Mod is disabled.
            return value;
        }

        // Get item from player main hand.
        ItemStack itemStack = player.getMainHandItem();

        if (itemStack.getItem() instanceof BackpackItem)
        {
            // Try backpack item first.
            value |= itemStack.
                getCapability(CapabilityBackpackWrapper.getCapabilityInstance()).
                map(wrapper -> {
                    ITrackedContentsItemHandler inventoryForUpgradeProcessing =
                        wrapper.getInventoryForUpgradeProcessing();

                    boolean hasUnidentified = false;

                    for (int i = 0; i < inventoryForUpgradeProcessing.getSlots(); i++)
                    {
                        ItemStack stackInSlot = wrapper.getInventoryForUpgradeProcessing().getStackInSlot(i);

                        // Check if item is vault gear item.
                        if (stackInSlot.getCount() == 1 &&
                            stackInSlot.getItem() instanceof IdentifiableItem identifiableItem)
                        {
                            VaultGearState state = identifiableItem.getState(stackInSlot);

                            if (state == VaultGearState.UNIDENTIFIED)
                            {
                                // Try to identify the item
                                if (player instanceof ServerPlayer serverPlayer)
                                {
                                    identifiableItem.instantIdentify(serverPlayer, stackInSlot);
                                }

                                hasUnidentified = true;
                            }
                        }
                    }

                    return hasUnidentified;
                }).
                orElse(false);
        }
        else if (itemStack.getItem().getRegistryName() != null &&
            itemStack.getItem().getRegistryName().getPath().endsWith("shulker_box"))
        {
            // Now try to handle shulker boxes in player inventory.
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
                                    // Try to identify it.
                                    if (player instanceof ServerPlayer serverPlayer)
                                    {
                                        identifiableItem.instantIdentify(serverPlayer, stackInSlot);

                                        if (stackInSlot.getTag() != null)
                                        {
                                            // Update item tag in the items list.
                                            items.getCompound(i).put("tag", stackInSlot.getTag());
                                        }
                                    }

                                    value = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return value;
    }
}
