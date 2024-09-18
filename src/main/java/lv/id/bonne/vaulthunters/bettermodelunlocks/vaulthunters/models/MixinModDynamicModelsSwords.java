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

import iskallia.vault.dynamodel.model.item.PlainItemModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;
import iskallia.vault.init.ModDynamicModels;
import lv.id.bonne.vaulthunters.bettermodelunlocks.community.CommunityModels;


@Mixin(value = ModDynamicModels.Swords.class, remap = false)
public class MixinModDynamicModelsSwords
{
    @Shadow
    @Final
    public static DynamicModelRegistry<PlainItemModel> REGISTRY;


    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void injectClientModels(CallbackInfo ci)
    {
        REGISTRY.register(CommunityModels.Swords.GLIZZY_GLADIUS);
        REGISTRY.register(CommunityModels.Swords.SLICE_OF_CHEESE);
        REGISTRY.register(CommunityModels.Swords.LUTER_BOI);
        REGISTRY.register(CommunityModels.Swords.SLIME_BUSTER);
    }
}
