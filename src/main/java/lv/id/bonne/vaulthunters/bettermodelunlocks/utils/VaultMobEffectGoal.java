//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.utils;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.vault.DiscoveryGoalsManager;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.discoverylogic.goal.base.InVaultDiscoveryGoal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.PotionEvent;


/**
 * This Goal is for detecting when player applies effects on Mobs.
 */
public class VaultMobEffectGoal extends InVaultDiscoveryGoal<VaultMobEffectGoal>
{
    /**
     * This method is used to create new instance of VaultMobEffectGoal.
     * @param targetProgress The target progress that will be used to complete this goal.
     */
    public VaultMobEffectGoal(int targetProgress)
    {
        super((float) targetProgress);
    }


    /**
     * This method is used to add predicate to the list of predicates.
     * @param predicate The predicate that will be added to the list of predicates.
     * @return The VaultMobEffectGoal goal.
     */
    public VaultMobEffectGoal withPredicate(Predicate<PotionEvent.PotionAddedEvent> predicate)
    {
        this.predicates.add(predicate);
        return this;
    }


    /**
     * The server init method.
     * @param manager The manager that will be used to register this goal.
     * @param world The world that will be used to register this goal.
     * @param vault The vault that will be used to register this goal.
     */
    public void initServer(DiscoveryGoalsManager manager, VirtualWorld world, Vault vault)
    {
        CommonEvents.EFFECT_ADD.register(manager, event ->
        {
            Entity entity = event.getPotionSource();

            if (entity instanceof ServerPlayer player)
            {
                if (world == player.getLevel() &&
                    this.predicates.stream().allMatch(predicate -> predicate.test(event)))
                {
                    this.progress(player, 1.0F);
                }
            }
        });
    }


    /**
     * The list of predicates that will be used to check if the event is valid.
     */
    protected List<Predicate<PotionEvent.PotionAddedEvent>> predicates = new LinkedList<>();
}
