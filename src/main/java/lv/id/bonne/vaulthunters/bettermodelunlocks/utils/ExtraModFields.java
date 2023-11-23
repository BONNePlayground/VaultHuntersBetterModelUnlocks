//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.utils;


import iskallia.vault.core.Version;
import iskallia.vault.core.data.adapter.Adapters;
import iskallia.vault.core.data.key.FieldKey;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;

import static iskallia.vault.core.data.ICompound.DISK;
import static iskallia.vault.core.vault.Vault.FIELDS;


/**
 * This class contains extra fields added by this mod.
 */
public class ExtraModFields
{
    /**
     * This field is used to mark vault as cow vault.
     */
    public static final FieldKey<Boolean> COW_VAULT =
        FieldKey.of(BetterModelUnlocks.of("cow_vault"), Boolean.class).
            with(Version.v1_0, Adapters.BOOLEAN, DISK.all()).
            register(FIELDS);
}
