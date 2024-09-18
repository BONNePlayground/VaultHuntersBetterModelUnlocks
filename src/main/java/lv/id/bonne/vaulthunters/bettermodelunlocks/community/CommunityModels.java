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
            new PlainItemModel(BetterModelUnlocks.of("gear/wand/leek"), "Leek").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // madness model by breadcrumb5550
        public static final PlainItemModel MADNESS_WAND =
            new PlainItemModel(BetterModelUnlocks.of("gear/wand/madness"), "Madness Wand").
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

        // luter boi model by breadcrumb5550
        public static final PlainItemModel LUTER_BOI =
            new PlainItemModel(BetterModelUnlocks.of("gear/sword/luter_boi"), "Luter Boi").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // slime_buster model by breadcrumb5550
        public static final PlainItemModel SLIME_BUSTER =
            new PlainItemModel(BetterModelUnlocks.of("gear/sword/slime_buster"), "Slime Buster").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }

    public static class Shields
    {
        // bumbo_cutout model by breadcrumb5550
        public static final PlainItemModel BUMBO_CUTOUT =
            new PlainItemModel(BetterModelUnlocks.of("gear/shield/bumbo_cutout"), "Bumbo Cutout").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }
}
