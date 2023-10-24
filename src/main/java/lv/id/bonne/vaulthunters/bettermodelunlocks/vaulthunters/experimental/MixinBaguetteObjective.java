//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.event.common.BlockUseEvent;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.init.ModItems;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.blay09.mods.cookingforblockheads.block.ToasterBlock;
import net.blay09.mods.cookingforblockheads.tile.ToasterBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.Event;


/**
 * This mixin adds custom code for toaster to unlock baguette.
 */
@Mixin(value = Objective.class, remap = false)
public abstract class MixinBaguetteObjective
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
        if (!BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks().get())
        {
            // Only on experimental settings.
            return;
        }

        // Custom event handling for toaster
        CommonEvents.BLOCK_USE.in(world).
            at(BlockUseEvent.Phase.HEAD).
            of(ModBlocks.toaster).
            register(this, (data) ->
            {
                BlockEntity blockEntity = world.getBlockEntity(data.getPos());

                if (blockEntity instanceof ToasterBlockEntity toasterBlock)
                {
                    // Unlock once bread is set to toast. Do not wait it.

                    if (!toasterBlock.isActive() &&
                        !toasterBlock.isBurningToast() &&
                        !toasterBlock.getContainer().isEmpty() &&
                        (toasterBlock.getContainer().getItem(1).is(Items.BREAD) ||
                            !data.getPlayer().getMainHandItem().is(Items.BREAD)))
                    {
                        // Award baguette
                        DiscoveredModelsData.get(world.getLevel()).discoverModelAndBroadcast(
                            ModItems.WAND,
                            ModDynamicModels.Wands.BAGUETTE.getId(),
                            data.getPlayer());
                    }
                }
            });

        // Prevent toaster to being place in the vault
        CommonEvents.ENTITY_PLACE.register(((Objective) (Object) this), (event) ->
        {
            if (event.getEntity() instanceof ServerPlayer)
            {
                if (event.getEntity().level == world)
                {
                    if (event.isCancelable())
                    {
                        Entity entity = event.getEntity();

                        if (entity instanceof Player player)
                        {
                            if (player.isCreative())
                            {
                                // allow creative player to place the block
                                return;
                            }
                        }

                        if (event.getPlacedBlock().getBlock() instanceof ToasterBlock)
                        {
                            // Do not allow to place toasters :D No cheezing
                            event.setCanceled(true);
                            event.setResult(Event.Result.DENY);
                        }
                    }
                }
            }
        });
    }
}
