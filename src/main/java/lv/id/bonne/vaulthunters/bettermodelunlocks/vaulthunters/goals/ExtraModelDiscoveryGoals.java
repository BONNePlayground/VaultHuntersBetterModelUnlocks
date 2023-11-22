//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;

import java.util.Map;

import iskallia.vault.discoverylogic.goal.VaultMobKillGoal;
import iskallia.vault.discoverylogic.goal.base.DiscoveryGoal;
import iskallia.vault.dynamodel.model.armor.ArmorPieceModel;
import iskallia.vault.init.*;
import iskallia.vault.item.gear.VaultArmorItem;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.fml.common.Mod;


/**
 * This class registers extra model discovery goals.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraModelDiscoveryGoals
{
    static
    {
        // Register new goal for killing mobs while riding a pig.
        MOBS_KILLED_WHILE_RIDING_PIG = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_killed_while_riding_pig"),
            new VaultMobKillGoal(50).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withKillerPredicate(player -> player.isPassenger() && player.getVehicle() instanceof Pig).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Wands.CARROT_ON_A_WAND.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " mobs while riding a pig in this Vault!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(ModItems.WAND, modelId, player);
                    }
                }));
        // Register new goal for repealing mobs.
        MOBS_SCARED_AWAY_200 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_scared_away_200"),
            new VaultMobEffectGoal(200).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getPotionEffect().getEffect() == ModEffects.TAUNT_REPEL_MOB).
                withPredicate(e -> e.getOldPotionEffect() == null ||
                    e.getOldPotionEffect().getEffect() != ModEffects.TAUNT_REPEL_MOB).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.SCARECROW.getPiece(EquipmentSlot.HEAD).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null &&
                        !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have scared " + (int) goal.getTargetProgress() + " mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.HEAD),
                            modelId,
                            player);
                    }
                }));
        MOBS_SCARED_AWAY_400 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_scared_away_400"),
            new VaultMobEffectGoal(400).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getPotionEffect().getEffect() == ModEffects.TAUNT_REPEL_MOB).
                withPredicate(e -> e.getOldPotionEffect() == null ||
                    e.getOldPotionEffect().getEffect() != ModEffects.TAUNT_REPEL_MOB).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.SCARECROW.getPiece(EquipmentSlot.FEET).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null &&
                        !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have scared " + (int) goal.getTargetProgress() + " mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.FEET),
                            modelId,
                            player);
                    }
                }));
        MOBS_SCARED_AWAY_600 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_scared_away_600"),
            new VaultMobEffectGoal(600).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getPotionEffect().getEffect() == ModEffects.TAUNT_REPEL_MOB).
                withPredicate(e -> e.getOldPotionEffect() == null ||
                    e.getOldPotionEffect().getEffect() != ModEffects.TAUNT_REPEL_MOB).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.SCARECROW.getPiece(EquipmentSlot.LEGS).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null &&
                        !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have scared " + (int) goal.getTargetProgress() + " mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.LEGS),
                            modelId,
                            player);
                    }
                }));
        MOBS_SCARED_AWAY_800 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_scared_away_800"),
            new VaultMobEffectGoal(800).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getPotionEffect().getEffect() == ModEffects.TAUNT_REPEL_MOB).
                withPredicate(e -> e.getOldPotionEffect() == null ||
                    e.getOldPotionEffect().getEffect() != ModEffects.TAUNT_REPEL_MOB).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.SCARECROW.getPiece(EquipmentSlot.CHEST).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null &&
                        !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have scared " + (int) goal.getTargetProgress() + " mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.CHEST),
                            modelId,
                            player);
                    }
                }));
        // Penguin model unlocks
        CHILLED_MOBS_KILLED_200 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "chilled_mobs_killed_200"),
            new VaultMobKillGoal(200).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getEntityLiving().hasEffect(ModEffects.CHILLED)).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.PENGUIN.getPiece(EquipmentSlot.FEET).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null && !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " chilled mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.FEET),
                            modelId,
                            player);
                    }
                }));
        CHILLED_MOBS_KILLED_400 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "chilled_mobs_killed_400"),
            new VaultMobKillGoal(400).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getEntityLiving().hasEffect(ModEffects.CHILLED)).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.PENGUIN.getPiece(EquipmentSlot.LEGS).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null && !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " chilled mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.LEGS),
                            modelId,
                            player);
                    }
                }));
        FREEZE_MOBS_KILLED_50 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "freeze_mobs_killed_50"),
            new VaultMobKillGoal(50).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getEntityLiving().hasEffect(ModEffects.FREEZE)).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.PENGUIN.getPiece(EquipmentSlot.HEAD).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null && !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " frozen mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.HEAD),
                            modelId,
                            player);
                    }
                }));
        FREEZE_MOBS_KILLED_100 = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "freeze_mobs_killed_100"),
            new VaultMobKillGoal(100).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getEntityLiving().hasEffect(ModEffects.FREEZE)).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId =
                        ModDynamicModels.Armor.PENGUIN.getPiece(EquipmentSlot.CHEST).
                            map(ArmorPieceModel::getId).
                            orElse(null);

                    if (modelId != null && !discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " frozen mobs!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(
                            VaultArmorItem.forSlot(EquipmentSlot.CHEST),
                            modelId,
                            player);
                    }
                }));

        MAX_HAMMER_SIZE = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "max_hammer_size"),
            new JewelApplicationGoal(ModGearAttributes.HAMMER_SIZE, 7).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Axes.GREATHAMMER.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have applied max hammer size!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(ModItems.AXE, modelId, player);
                    }
                }));
        KILL_BUNFUNGUS = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "bunfungus_killed"),
            new VaultMobKillGoal(5).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> e.getEntityLiving() instanceof Mob).
                withPredicate(e -> e.getEntityLiving().getType() == AMEntityRegistry.BUNFUNGUS.get()).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Wands.CARRI_KING.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You have killed " + (int) goal.getTargetProgress() + " bunfungus in this Vault!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(ModItems.WAND, modelId, player);
                    }
                }));

        // Jester unlock
        JESTER_SET = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "jester_set"),
            new VaultRequiredBlocksGoal(Map.of(
                ModBlocks.WOODEN_CHEST_PLACEABLE, 1,
                ModBlocks.GILDED_CHEST_PLACEABLE, 2,
                ModBlocks.LIVING_CHEST_PLACEABLE, 4,
                ModBlocks.ORNATE_CHEST_PLACEABLE, 8,
                ModBlocks.TREASURE_CHEST_PLACEABLE, 16),
                31).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Armor.JESTER.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("You are such a troll!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverAllArmorPieceAndBroadcast(player, ModDynamicModels.Armor.JESTER);
                    }
                }));
        // unlock nightfall sword
        NIGHTFALL_DRINKING = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "potion_nightfall"),
            new PotionUseGoal(20).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Swords.NIGHTFALL.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("So much potion, so close to intoxicating!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(ModItems.SWORD, modelId, player);
                    }
                }));
    }


    /**
     * The goal for killing mobs while riding a pig to get CARROT_ON_A_WAND model.
     */
    public final static VaultMobKillGoal MOBS_KILLED_WHILE_RIDING_PIG;

    /**
     * The goal for scaring mobs to get SCARECROW model 1.
     */
    public final static VaultMobEffectGoal MOBS_SCARED_AWAY_200;

    /**
     * The goal for scaring mobs to get SCARECROW model 2.
     */
    public final static VaultMobEffectGoal MOBS_SCARED_AWAY_400;

    /**
     * The goal for scaring mobs to get SCARECROW model 3.
     */
    public final static VaultMobEffectGoal MOBS_SCARED_AWAY_600;

    /**
     * The goal for scaring mobs to get SCARECROW model 4.
     */
    public final static VaultMobEffectGoal MOBS_SCARED_AWAY_800;

    /**
     * The goal for killing mobs while they are chilled.
     */
    public final static VaultMobKillGoal CHILLED_MOBS_KILLED_200;

    /**
     * The goal for killing mobs while they are chilled.
     */
    public final static VaultMobKillGoal CHILLED_MOBS_KILLED_400;

    /**
     * The goal for killing mobs while they are freezed.
     */
    public final static VaultMobKillGoal FREEZE_MOBS_KILLED_50;

    /**
     * The goal for killing mobs while they are freezed.
     */
    public final static VaultMobKillGoal FREEZE_MOBS_KILLED_100;

    /**
     * The goal for getting max Hammer Size Tool
     */
    public final static JewelApplicationGoal MAX_HAMMER_SIZE;

    /**
     * The goal for killing 5 bunfungus mobs.
     */
    public final static VaultMobKillGoal KILL_BUNFUNGUS;

    /**
     * The goal for killing 5 bunfungus mobs.
     */
    public final static VaultRequiredBlocksGoal JESTER_SET;

    /**
     * The goal for drinking vault potion.
     */
    public final static PotionUseGoal NIGHTFALL_DRINKING;

    /**
     * This method registers new vault goal to the ModModelDiscoveryGoals
     * @param id The id of the goal.
     * @param goal The goal.
     * @return The goal.
     * @param <G> The type of the goal.
     */
    private static <G extends DiscoveryGoal<G>> G registerGoal(ResourceLocation id, G goal)
    {
        goal.setId(id);
        ModModelDiscoveryGoals.REGISTRY.put(id, goal);
        return goal;
    }


    /**
     * Just class init to trigger everything.
     */
    public static void init() {}
}
