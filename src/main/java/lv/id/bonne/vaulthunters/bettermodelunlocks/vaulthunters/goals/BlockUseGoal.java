//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.event.common.BlockUseEvent;
import iskallia.vault.core.vault.DiscoveryGoalsManager;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.discoverylogic.goal.base.InVaultDiscoveryGoal;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.HunterAbilityUseEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;


/**
 * This Goal is for detecting when player uses block.
 */
public class BlockUseGoal extends InVaultDiscoveryGoal<BlockUseGoal>
{
    /**
     * Constructor for BlockUseGoal.
     * @param block Target block.
     * @param targetProgress Target progress of the goal.
     */
    public BlockUseGoal(Block block, float targetProgress)
    {
        super(targetProgress);
        this.block = block;
    }


    /**
     * This method is used to add predicate to the list of predicates.
     * @param predicate The predicate that will be added to the list of predicates.
     * @return The PotionUseGoal goal.
     */
    public BlockUseGoal withPredicate(Predicate<BlockUseEvent.Data> predicate)
    {
        this.predicates.add(predicate);
        return this;
    }



    /**
     * This method is used to initialize the server side of the goal.
     * @param manager DiscoveryGoalsManager of the goal.
     * @param world VirtualWorld of the goal.
     * @param vault Vault of the goal.
     */
    public void initServer(DiscoveryGoalsManager manager, VirtualWorld world, Vault vault)
    {
        CommonEvents.BLOCK_USE.
            in(world).
            at(BlockUseEvent.Phase.RETURN).
            of(this.block).
            register(manager, event ->
            {
                if (event.getPlayer() instanceof ServerPlayer player &&
                    this.predicates.stream().allMatch(predicate -> predicate.test(event)))
                {
                    this.progress(player, 1.0F);
                }
            });
    }


    /**
     * The list of predicates that will be used to check if the event is valid.
     */
    protected List<Predicate<BlockUseEvent.Data>> predicates = new LinkedList<>();

    /**
     * The block that player is using to triggering the goal.
     */
    protected final Block block;
}