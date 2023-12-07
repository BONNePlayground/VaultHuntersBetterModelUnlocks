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

import iskallia.vault.VaultMod;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.world.storage.VirtualWorld;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.logic.CowMobLogic;


/**
 * This mixin is used to mark vault as cow vault.
 */
@Mixin(value = WorldManager.class, remap = false)
public abstract class MixinCowVault
{
    /**
     * Injects if vault is cow vault.
     * @param world Virtual world.
     * @param ci Callback info.
     */
    @Inject(method = "initServer", at = @At(value = "INVOKE",
        target = "Liskallia/vault/core/vault/WorldManager;ifPresent(Liskallia/vault/core/data/key/FieldKey;Ljava/util/function/Consumer;)V",
        ordinal = 3),
        remap = false)
    private void injectInitServer(VirtualWorld world, Vault vault, CallbackInfo ci)
    {
        // Check if experimental unlocks are enabled.
        if (!BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks())
        {
            // Only on experimental settings.
            return;
        }

        // Check the theme section. Only in chaos vaults.
        if (!((WorldManager) (Object) this).getOptional(WorldManager.THEME).
            orElse(VaultMod.id("empty")).
            equals(VaultMod.id("classic_vault_chaos")))
        {
            // Only on chaos vaults.
            return;
        }

        // Now count modifiers.
        vault.ifPresent(Vault.MODIFIERS, modifiers ->
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
                // Change to CowMobLogic
                ((WorldManager) (Object) this).
                    setIfPresent(WorldManager.MOB_LOGIC, new CowMobLogic());
            }
        });
    }
}
