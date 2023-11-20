//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import java.util.Map;

import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.vault.DiscoveryGoalsManager;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.discoverylogic.goal.base.InVaultDiscoveryGoal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;


/**
 * This goal allows to define unique blocks that player must place at least once in the vault
 * for completing the goal.
 */
public class VaultRequiredBlocksGoals extends InVaultDiscoveryGoal<VaultRequiredBlocksGoals>
{
    /**
     * Constructor for VaultRequiredBlocksGoals.
     * @param blockValue Map of blocks and their values.
     * @param targetProgress Target progress of the goal.
     */
    public VaultRequiredBlocksGoals(Map<Block, Integer> blockValue, float targetProgress)
    {
        super(targetProgress);
        this.blockValue = blockValue;
    }


    /**
     * This method is used to initialize the server side of the goal.
     * @param manager DiscoveryGoalsManager of the goal.
     * @param world VirtualWorld of the goal.
     * @param vault Vault of the goal.
     */
    public void initServer(DiscoveryGoalsManager manager, VirtualWorld world, Vault vault)
    {
        CommonEvents.ENTITY_PLACE.register(manager, (event) ->
        {
            if (event.getEntity() instanceof ServerPlayer player)
            {
                if (player.getLevel() == world)
                {
                    if (this.blockValue.containsKey(event.getPlacedBlock().getBlock()))
                    {
                        int value = this.blockValue.get(event.getPlacedBlock().getBlock());
                        int currentValue = (int) this.getCurrentProgress(player);

                        if ((currentValue & value) == 0)
                        {
                            this.progress(player, value | currentValue);
                        }
                    }
                }
            }
        });
    }


    /**
     * The variable that stores unique block id's.
     */
    private final Map<Block, Integer> blockValue;
}