//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import iskallia.vault.entity.entity.EffectCloudEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;


/**
 * This class is used to call Entity#addEffect with the owner of the EffectCloudEntity.
 */
@Mixin(value = EffectCloudEntity.class, remap = false)
public class MixinEffectCloudEntity
{
    /**
     * The owner of the EffectCloudEntity.
     */
    @Shadow
    private LivingEntity owner;


    /**
     * This method is used to redirect Entity#addEffect to Entity#addEffect with owner.
     * @param instance The instance of the Entity.
     * @param p_21165_ The MobEffectInstance that will be added to the Entity.
     * @return True if the effect was added to the Entity.
     */
    @Redirect(method = "tick",
        at = @At(value = "INVOKE", target = "net/minecraft/world/entity/LivingEntity.addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"),
        remap = true)
    private boolean redirectAddEffect(LivingEntity instance, MobEffectInstance p_21165_)
    {
        return instance.addEffect(p_21165_, this.owner);
    }


    /**
     * This method is used to call event when effect is added to the Entity instantly.
     * @param effectInstance The effect that was added to the Entity.
     * @param livingEntity The Entity that the effect was added to.
     * @param ci The callback info.
     */
    @Inject(method = "lambda$tick$1", at = @At(value = "INVOKE", target = "net/minecraft/world/effect/MobEffect.applyInstantenousEffect (Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"))
    private void cancelEffect(MobEffectInstance effectInstance, LivingEntity livingEntity, CallbackInfo ci)
    {
        MinecraftForge.EVENT_BUS.post(
            new PotionEvent.PotionAddedEvent(livingEntity, null, effectInstance, this.owner));
    }
}
