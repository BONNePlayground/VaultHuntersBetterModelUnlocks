//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events;


import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.event.Event;
import iskallia.vault.core.vault.VaultRegistry;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.logic.CowMobLogic;


/**
 * Custom Common Events thats hooks into VH Common Event registry :D
 */
public class ExtraCommonEvents
{
    /**
     * This event is fired when entity removes effect
     */
    public static final EntityFinisItemUseEvent FINISH_USE = register(new EntityFinisItemUseEvent());

    /**
     * This event is fired before entity is created via spawner.
     */
    public static final SpawnerEntityCreateEvent SPAWNER_ENTITY_CREATE = register(new SpawnerEntityCreateEvent());

    /**
     * This event is fired when player uses hunter ability.
     */
    public static final HunterAbilityUseEvent HUNTER_ABILITY_USE = register(new HunterAbilityUseEvent());


    /**
     * Register event to VH Common Event registry.
     * @param event The event that will be registered.
     * @return The event that was registered.
     * @param <T> The type of the event.
     */
    private static <T extends Event<?, ?>> T register(T event)
    {
        CommonEvents.REGISTRY.add(event);
        return event;
    }


    /**
     * Just class init to trigger everything.
     */
    public static void init()
    {
        // Adds cow logic to Vault Registry.
        VaultRegistry.MOB_LOGIC.add(CowMobLogic.KEY);
    }
}
