//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.community;


import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.armor.ArmorModel;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.model.item.PlainItemModel;
import iskallia.vault.dynamodel.model.item.shield.ShieldModel;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.community.gear.model.armor.layers.ShenanigansArmorLayers;
import net.minecraft.world.entity.EquipmentSlot;


/**
 * This class contains community models instances. Used for reference in other spots.
 * Actual registry happens in `lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.models.MixinModDynamicModel<item>.
 */
public class CommunityModels
{
    /**
     * This class holds community Wand models.
     */
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
            new PlainItemModel(BetterModelUnlocks.of("gear/wand/madness_wand"), "pijudabra1grkarm").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }


    /**
     * This class holds community armour models.
     */
    public static class Armor
    {
        // Armor model shenanigans by breadcrumb5550
        public static final ArmorModel SHENANIGANS = new ArmorModel(BetterModelUnlocks.of("gear/armor/shenanigans"), "bAYuOGothquac").
            properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()).
            usingLayers(new ShenanigansArmorLayers()).
            addSlot(EquipmentSlot.HEAD).
            addSlot(EquipmentSlot.CHEST).
            addSlot(EquipmentSlot.LEGS).
            addSlot(EquipmentSlot.FEET);
    }


    /**
     * This class holds community axe models.
     */
    public static class Axes
    {
        // skallianchor model by breadcrumb5550
        public static final HandHeldModel SKALLIANCHOR =
            new HandHeldModel(BetterModelUnlocks.of("gear/axe/skallianchor"), "Skallianchor").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // jogltaurmawipeirja model by breadcrumb5550
        public static final HandHeldModel MADNESS_AXE =
            new HandHeldModel(BetterModelUnlocks.of("gear/axe/madness_axe"), "Jogltaurmawipeirja").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }


    /**
     * This class holds community swords models.
     */
    public static class Swords
    {
        // glizzy gladius model by breadcrumb5550
        public static final HandHeldModel GLIZZY_GLADIUS =
            new HandHeldModel(BetterModelUnlocks.of("gear/sword/glizzy_gladius"), "Glizzy Gladius").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // slice_of_cheese model by breadcrumb5550
        public static final HandHeldModel SLICE_OF_CHEESE =
            new HandHeldModel(BetterModelUnlocks.of("gear/sword/slice_of_cheese"), "Slice of Cheese").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // luter boi model by breadcrumb5550
        public static final HandHeldModel LUTER_BOI =
            new HandHeldModel(BetterModelUnlocks.of("gear/sword/luter_boi"), "Luter Boi").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());

        // slime_buster model by breadcrumb5550
        public static final HandHeldModel SLIME_BUSTER =
            new HandHeldModel(BetterModelUnlocks.of("gear/sword/slime_buster"), "Slime Buster").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }


    /**
     * This class holds community shield models
     */
    public static class Shields
    {
        // bumbo_cutout model by breadcrumb5550
        public static final ShieldModel BUMBO_CUTOUT =
            new ShieldModel(BetterModelUnlocks.of("gear/shield/bumbo_cutout"), "Bumbo Cutout").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }


    /**
     * This class holds community focus models
     */
    public static class Focus
    {
        // madness model by breadcrumb5550
        public static final PlainItemModel MADNESS_FOCUS =
            new PlainItemModel(BetterModelUnlocks.of("gear/focus/madness_focus"), "ToemDenuroLaSiAn").
                properties(new DynamicModelProperties().
                    allowTransmogrification().
                    discoverOnRoll());
    }
}
