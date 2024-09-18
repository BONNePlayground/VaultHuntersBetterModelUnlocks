//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.community;


import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.PlainItemModel;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;


/**
 * This class contains community models instances. Used for reference in other spots.
 * Actual registry happens in `lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.models.MixinModDynamicModel<item>.
 */
public class CommunityModels
{
    public static class Wands
    {
        // Leek model by gaymersalsa
        public static final PlainItemModel LEEK = new PlainItemModel(BetterModelUnlocks.of("gear/wand/leek"), "Leek Wand").
            properties(new DynamicModelProperties().
                allowTransmogrification().
                discoverOnRoll());
    }
}
