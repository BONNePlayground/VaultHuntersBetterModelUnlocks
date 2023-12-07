//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import iskallia.vault.skill.ability.effect.spi.HunterAbility;
import iskallia.vault.skill.ability.effect.spi.core.Ability;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;


/**
 * This class is used to detect if HunterAbility is used by player.
 */
@Mixin(value = HunterAbility.class, remap = false)
public class MixinHunterAbility
{
    /**
     * This method is used to redirect Mob#addEffect to Mob#addEffect with player.
     * @param player The player.
     * @param cir Callback info.
     */
    @Inject(method = "lambda$doAction$4",
        at = @At(value = "INVOKE",
            target = "Liskallia/vault/skill/ability/effect/spi/core/Ability$ActionResult;successCooldownImmediate()Liskallia/vault/skill/ability/effect/spi/core/Ability$ActionResult;"),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onAbilityUse(ServerPlayer player,
        CallbackInfoReturnable<Ability.ActionResult> cir,
        Level world)
    {
        ExtraCommonEvents.HUNTER_ABILITY_USE.invoke(player, world);
    }
}
