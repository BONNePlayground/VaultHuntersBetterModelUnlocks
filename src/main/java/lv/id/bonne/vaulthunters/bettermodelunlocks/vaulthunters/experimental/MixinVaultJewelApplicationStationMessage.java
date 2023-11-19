//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import iskallia.vault.container.VaultJewelApplicationStationContainer;
import iskallia.vault.network.message.VaultJewelApplicationStationMessage;
import lv.id.bonne.vaulthunters.bettermodelunlocks.utils.ExtraModelDiscoveryGoals;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;


/**
 * This class injects into VaultJewelApplicationStationMessage to detect when player adds jewel to
 * the tool in Jewel Application Station.
 */
@Mixin(value = VaultJewelApplicationStationMessage.class, remap = false)
public class MixinVaultJewelApplicationStationMessage
{
    /**
     * Injects into VaultJewelApplicationStationMessage to detect when player adds jewel to the tool
     * @param context NetworkEvent.Context
     * @param ci CallbackInfo
     * @param requester ServerPlayer who requested the action
     * @param container VaultJewelApplicationStationContainer
     * @param var3 AbstractContainerMenu
     */
    @Inject(method = "lambda$handle$0", at = @At(value = "INVOKE",
        target = "Liskallia/vault/block/entity/VaultJewelApplicationStationTileEntity;applyJewels()V",
        shift = At.Shift.AFTER),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void redirectApplyJewels(NetworkEvent.Context context,
        CallbackInfo ci,
        ServerPlayer requester,
        VaultJewelApplicationStationContainer container,
        AbstractContainerMenu var3)
    {
        ExtraModelDiscoveryGoals.MAX_HAMMER_SIZE.onJewelApply(requester, container.getTileEntity().getToolItem());
    }
}
