//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;

import iskallia.vault.VaultMod;
import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;


/**
 * This mixin adds custom code for unlocking bee knight set.
 */
@Mixin(value = Objective.class, remap = false)
public abstract class MixinBeeKnightObjective
{
    /**
     * Add custom event handling for toaster.
     * @param world The world.
     * @param vault The vault.
     * @param ci Callback info.+
     */
    @Inject(method = "initServer", at = @At("HEAD"))
    private void addCustomObjective(VirtualWorld world, Vault vault, CallbackInfo ci)
    {
        if (!BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks())
        {
            // Only on experimental settings.
            return;
        }

        // Check if theme matches lush or living cave
        ResourceLocation themeKey = vault.getOptional(Vault.WORLD).
            flatMap(manager -> manager.getOptional(WorldManager.THEME)).
            orElse(VaultMod.id("empty"));

        if (!themeKey.equals(VaultMod.id("classic_vault_living_cave")))
        {
            return;
        }

        // Prevent toaster to being place in the vault
        CommonEvents.ENTITY_SPAWN.register(((Objective) (Object) this), event ->
        {
            if (event.getWorld()  == world && event.getEntity().getType().equals(EntityType.BEE))
            {
                world.getNearbyPlayers(TargetingConditions.DEFAULT,
                        event.getEntityLiving(),
                        AABB.ofSize(new Vec3(event.getX(), event.getY(), event.getZ()), 5.0d, 5.0d, 5.0d)).
                    forEach(player ->
                    {
                        if (player instanceof ServerPlayer serverPlayer)
                        {
                            DiscoveredModelsData.get(world).
                                discoverRandomArmorPieceAndBroadcast(serverPlayer,
                                    ModDynamicModels.Armor.BEE_KNIGHT,
                                    new Random());
                        }
                    });
            }
        });
    }
}
