//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.models;


import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import iskallia.vault.dynamodel.registry.ArmorPieceModelRegistry;
import iskallia.vault.init.ModDynamicModels;
import lv.id.bonne.vaulthunters.bettermodelunlocks.community.CommunityModels;


@Mixin(value = ModDynamicModels.Armor.class, remap = false)
public class MixinModDynamicModelsArmor
{
    @Shadow
    @Final
    public static ArmorPieceModelRegistry PIECE_REGISTRY;


    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void injectClientModels(CallbackInfo ci)
    {
        PIECE_REGISTRY.registerAll(CommunityModels.Armor.SHENANIGANS);
    }
}
