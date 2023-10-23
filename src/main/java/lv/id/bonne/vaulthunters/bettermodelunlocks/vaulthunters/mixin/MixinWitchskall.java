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

import iskallia.vault.easteregg.Witchskall;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.world.data.DiscoveredModelsData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;


/**
 * This mixin fixes an issue were ISKALL_IBE model was not unlocked after killing Witchskall.
 */
@Mixin(value = Witchskall.class, remap = false)
public class MixinWitchskall
{
    /**
     * This method awards ISKALL_IBE model at the same time when advancement is awarded.
     * @param event LivingDeathEvent is used to get the player.
     * @param ci CallbackInfo required by every mixin.
     * @param entity Witchskall entity.
     * @param trueSource Player who killed Witchskall.
     * @param player Player who killed Witchskall.
     */
    @Inject(method = "onWitchskallDeath",
        at = @At(value = "INVOKE", target = "Liskallia/vault/util/AdvancementHelper;grantCriterion(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/resources/ResourceLocation;Ljava/lang/String;)Z"),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void onWitchskallDeath(LivingDeathEvent event,
        CallbackInfo ci,
        Entity entity,
        Entity trueSource,
        ServerPlayer player)
    {
        DiscoveredModelsData.get(player.getLevel()).discoverAllArmorPieceAndBroadcast(
            player,
            ModDynamicModels.Armor.ISKALL_IBE);
    }
}
