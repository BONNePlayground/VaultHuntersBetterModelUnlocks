//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import iskallia.vault.block.entity.SpiritExtractorTileEntity;
import iskallia.vault.init.ModModelDiscoveryGoals;
import iskallia.vault.network.message.SpiritExtractorMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;


/**
 * This mixin fixes an issue were cake model was not unlocked after completing Cake Vault.
 */
@Mixin(value = SpiritExtractorMessage.class, remap = false)
public class MixinSpiritExtractorMessage
{
    /**
     * Fixes the issue with spirit extraction rewards. The order of getting total cost and spewing items was wrong,
     * as spewItem() method cleared the cost.
     */
    @Inject(method = "lambda$handle$0(Lnet/minecraftforge/network/NetworkEvent$Context;Liskallia/vault/network/message/SpiritExtractorMessage;)V",
        at = @At(value = "INVOKE", target = "Liskallia/vault/block/entity/SpiritExtractorTileEntity;spewItems()V"),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void handle(NetworkEvent.Context context,
        SpiritExtractorMessage message,
        CallbackInfo ci,
        ServerPlayer serverPlayer,
        ServerLevel serverWorld,
        SpiritExtractorTileEntity spiritExtractor,
        BlockEntity patt1462$temp)
    {
        int correctCost = spiritExtractor.getRecoveryCost().getTotalCost().getCount();
        ModModelDiscoveryGoals.SPIRIT_EXTRACTION.onSpiritExtracted(serverPlayer, correctCost);
    }
}
