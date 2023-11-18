//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import iskallia.vault.skill.ability.effect.TauntRepelAbility;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;


/**
 * This class is used to redirect Mob#addEffect to Mob#addEffect with player so repealing effect
 * would send an event with a player who triggered taunt.
 */
@Mixin(value = TauntRepelAbility.class, remap = false)
public class MixinTauntRepealAbility
{
    /**
     * This method is used to redirect Mob#addEffect to Mob#addEffect with player.
     * @param instance The instance of the Mob.
     * @param effectInstance The effect that will be added to the Mob.
     * @param player The player that will be used to add effect to the Mob.
     * @return True if the effect was added to the Mob.
     */
    @Redirect(method = "lambda$doAction$1",
        at = @At(value = "INVOKE", target = "net/minecraft/world/entity/Mob.addEffect (Lnet/minecraft/world/effect/MobEffectInstance;)Z"))
    private boolean redirectAddEffect(Mob instance, MobEffectInstance effectInstance, ServerPlayer player)
    {
        return instance.addEffect(effectInstance, player);
    }
}
