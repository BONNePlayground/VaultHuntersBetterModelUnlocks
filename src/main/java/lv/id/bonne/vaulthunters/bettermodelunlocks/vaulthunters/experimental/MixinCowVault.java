//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;
import java.util.Optional;

import iskallia.vault.VaultMod;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.world.storage.VirtualWorld;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.utils.ExtraModFields;


/**
 * This mixin is used to mark vault as cow vault.
 */
@Mixin(value = Vault.class, remap = false)
public class MixinCowVault
{
    /**
     * Injects cow vault field.
     * @param ci Callback info.
     */
    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void injectCowField(CallbackInfo ci)
    {
        ((Vault) (Object) this).setIfAbsent(ExtraModFields.COW_VAULT, false);
    }


    /**
     * Injects if vault is cow vault.
     * @param world Virtual world.
     * @param ci Callback info.
     */
    @Inject(method = "initServer", at = @At("TAIL"), remap = false)
    private void injectInitServer(VirtualWorld world, CallbackInfo ci)
    {
        if (!BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks())
        {
            // Only on experimental settings.
            return;
        }

        if (((Vault) (Object) this).getOptional(Vault.WORLD).
            map(manager -> manager.getOptional(WorldManager.THEME)).
            map(optional -> optional.orElse(VaultMod.id("empty"))).
            map(themeKey -> themeKey.equals(VaultMod.id("classic_vault_chaos"))).
            orElse(false))
        {
            // Only on chaos vaults.
            return;
        }

        ((Vault) (Object) this).ifPresent(Vault.MODIFIERS, modifiers ->
        {
            List<VaultModifier<?>> modifierList = modifiers.getModifiers();

            // infuriated_mobs
            int rapidCount = 0;
            // furious_mobs
            int furiousCount = 0;
            // wild
            int wildCount = 0;

            // Count trigger modifiers.
            for (VaultModifier<?> modifier : modifierList)
            {
                if (modifier.getId().equals(VaultMod.id("infuriated_mobs")))
                {
                    rapidCount++;
                }
                else if (modifier.getId().equals(VaultMod.id("furious_mobs")))
                {
                    furiousCount++;
                }
                else if (modifier.getId().equals(VaultMod.id("wild")))
                {
                    wildCount++;
                }
            }

            // Mark vault as cow vault
            if (rapidCount >= 5 && furiousCount >= 5 && wildCount >= 5)
            {
                ((Vault) (Object) this).setIfPresent(ExtraModFields.COW_VAULT, true);
            }
        });
    }
}
