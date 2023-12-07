//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import iskallia.vault.core.vault.DiscoveryGoalsManager;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.discoverylogic.goal.base.InVaultDiscoveryGoal;
import iskallia.vault.init.ModItems;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.HunterAbilityUseEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;


/**
 * This Goal is for detecting when player uses Hunter Ability.
 */
public class HunterAbilityGoal extends InVaultDiscoveryGoal<HunterAbilityGoal>
{
    /**
     * Constructor for HunterAbilityGoal.
     * @param targetProgress Target progress of the goal.
     */
    public HunterAbilityGoal(float targetProgress)
    {
        super(targetProgress);
    }


    /**
     * This method is used to add predicate to the list of predicates.
     * @param predicate The predicate that will be added to the list of predicates.
     * @return The PotionUseGoal goal.
     */
    public HunterAbilityGoal withPredicate(Predicate<HunterAbilityUseEvent.Data> predicate)
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
        ExtraCommonEvents.HUNTER_ABILITY_USE.
            in(world).
            register(manager, event ->
            {
                if (this.predicates.stream().allMatch(predicate -> predicate.test(event)))
                {
                    this.progress(event.getPlayer(), 1.0F);
                }
            });
    }


    /**
     * The list of predicates that will be used to check if the event is valid.
     */
    protected List<Predicate<HunterAbilityUseEvent.Data>> predicates = new LinkedList<>();
}