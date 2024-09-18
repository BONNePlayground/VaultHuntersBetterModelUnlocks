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
        public static final PlainItemModel LEEK =
            new PlainItemModel(BetterModelUnlocks.of("gear/wand/leek"), "Leek Wand").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }

    public static class Axes
    {
        // skallianchor model by breadcrumb5550
        public static final PlainItemModel SKALLIANCHOR =
            new PlainItemModel(BetterModelUnlocks.of("gear/axe/skallianchor"), "Skallianchor").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }


    public static class Swords
    {
        // glizzy gladius model by breadcrumb5550
        public static final PlainItemModel GLIZZY_GLADIUS =
            new PlainItemModel(BetterModelUnlocks.of("gear/sword/glizzy_gladius"), "Glizzy Gladius").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // slice_of_cheese model by breadcrumb5550
        public static final PlainItemModel SLICE_OF_CHEESE =
            new PlainItemModel(BetterModelUnlocks.of("gear/sword/slice_of_cheese"), "Slice of Cheese").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }
}
