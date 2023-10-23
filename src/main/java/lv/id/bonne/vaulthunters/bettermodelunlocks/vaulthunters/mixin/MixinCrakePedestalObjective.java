//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.objective.CakeObjective;
import iskallia.vault.core.vault.objective.CrakePedestalObjective;
import iskallia.vault.core.vault.player.Completion;
import iskallia.vault.core.vault.player.Listener;
import iskallia.vault.core.vault.player.Listeners;
import iskallia.vault.core.vault.stat.StatCollector;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.world.data.DiscoveredModelsData;


/**
 * This mixin fixes an issue were cake model was not unlocked after completing Cake Vault.
 */
@Mixin(value = CrakePedestalObjective.class, remap = false)
public class MixinCrakePedestalObjective
{
    /**
     * This method injects code to register cake model unlock.
     * @param world The world where the vault is.
     * @param vault The vault where the objective is.
     * @param ci CallbackInfo required by every mixin.
     */
    @Inject(method = "initServer", at = @At("HEAD"))
    public void registerCakeModelAward(VirtualWorld world, Vault vault, CallbackInfo ci)
    {
        CommonEvents.LISTENER_LEAVE.register(((CrakePedestalObjective) (Object) this), data ->
        {
            if (data.getVault() == vault)
            {
                vault.getOptional(Vault.STATS).
                    map(stats -> stats.get(data.getListener())).
                    ifPresent(stats ->
                    {
                        if (stats.getCompletion() == Completion.COMPLETED)
                        {
                            data.getListener().getPlayer().ifPresent(player ->
                                DiscoveredModelsData.get(world).discoverRandomArmorPieceAndBroadcast(player,
                                    ModDynamicModels.Armor.CAKE,
                                    new Random()));
                        }
                    });
            }
        });
    }
}
