//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.logic;


import iskallia.vault.core.Version;
import iskallia.vault.core.data.key.SupplierKey;
import iskallia.vault.core.data.key.registry.FieldRegistry;
import iskallia.vault.core.vault.ClassicMobLogic;
import iskallia.vault.core.vault.MobLogic;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.init.ModEntities;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;


/**
 * This class is used to add cow logic to the vault.
 * The logic just adds SpawnerEntityCreateEvent that will change entity type to aggressive cow.
 */
public class CowMobLogic extends ClassicMobLogic
{
    /**
     * Constructor for the logic.
     */
    public CowMobLogic()
    {
        super();
    }


    /**
     * This method is used to init server side logic.
     * @param world Virtual world.
     * @param vault Vault.
     */
    @Override
    public void initServer(VirtualWorld world, Vault vault)
    {
        ExtraCommonEvents.SPAWNER_ENTITY_CREATE.
            in(world).
            register(this, data -> data.setEntityType(ModEntities.AGGRESSIVE_COW));

        // Register all classic mob logic events.
        super.initServer(world, vault);
    }


    /**
     * This method is used to init client side logic.
     */
    public static final SupplierKey<MobLogic> KEY;

    /**
     * This field is used to register fields.
     */
    public static final FieldRegistry FIELDS;


    static
    {
        // Create key for the cow logic.
        KEY = SupplierKey.of(BetterModelUnlocks.of("cow_logic"), MobLogic.class).with(Version.v1_0, CowMobLogic::new);
        // Merge fields with the classic mob logic fields.
        FIELDS = ClassicMobLogic.FIELDS.merge(new FieldRegistry());
    }
}
