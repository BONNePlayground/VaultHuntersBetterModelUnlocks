//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.discoverylogic.goal.VaultCompletionGoal;
import iskallia.vault.discoverylogic.goal.VaultMobKillGoal;
import iskallia.vault.discoverylogic.goal.base.DiscoveryGoal;
import iskallia.vault.init.*;
import iskallia.vault.item.gear.TrinketItem;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.logic.CowMobLogic;
import net.blay09.mods.cookingforblockheads.tile.ToasterBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;


/**
 * This class registers extra model discovery goals.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraModelDiscoveryGoals
{
    /**
     * Now register all custom goals to the ModModelDiscoveryGoals.
     */
    public static void init()
    {
        // Register new goal for killing mobs while riding a pig.
        MOBS_KILLED_WHILE_RIDING_PIG = registerGoal(
            BetterModelUnlocks.of("mobs_killed_while_riding_pig"),
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

        KILL_BUNFUNGUS = registerGoal(
            BetterModelUnlocks.of("bunfungus_killed"),
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
            BetterModelUnlocks.of("jester_set"),
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
        // unlock cow armor set
        COW_VAULT = registerGoal(
            BetterModelUnlocks.of("cow_vault"),
            new VaultCompletionGoal().
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(data -> data.getVault().getOptional(Vault.WORLD).
                    map(manager -> manager.get(WorldManager.MOB_LOGIC)).
                    map(logic -> logic instanceof CowMobLogic).
                    orElse(false)).
                setReward((player, goal) ->
                {
                    if (DiscoveredModelsData.get(player.getLevel()).
                        discoverRandomArmorPieceAndBroadcast(player,
                            ModDynamicModels.Armor.HELL_COW,
                            new Random()))
                    {
                        MutableComponent info =
                            new TextComponent("This vault does not exist!!! Forget about it!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);
                    }
                }));

        // unlock scout armor set
        SCOUT_ARMOR = registerGoal(
            BetterModelUnlocks.of("scout_armor"),
            new HunterAbilityGoal(1).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(e -> {
                    int slot = CuriosApi.getSlotHelper().getSlotsForType(e.getPlayer(), "blue_trinket");

                    return CuriosApi.getCuriosHelper().
                        getEquippedCurios(e.getPlayer()).
                        map(handler -> handler.getStackInSlot(slot)).
                        filter(stack -> stack.is(ModItems.TRINKET)).
                        filter(TrinketItem::hasUsesLeft).
                        map(TrinketItem::getTrinket).
                        filter(Optional::isPresent).
                        map(Optional::get).
                        map(trinket -> ModTrinkets.NIGHT_VISION_GOGGLES == trinket).
                        orElse(false);
                }).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Armor.SCOUT.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("Wait, why I can see so far now?").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverAllArmorPieceAndBroadcast(player, ModDynamicModels.Armor.SCOUT);
                    }
                }));

        // Unlock BAGUETTE
        TOAST_BREAD = registerGoal(
            BetterModelUnlocks.of("toast_bread"),
            new BlockUseGoal(net.blay09.mods.cookingforblockheads.block.ModBlocks.toaster, 1).
                withPredicate(e -> BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks()).
                withPredicate(data ->
                    data.getWorld().getBlockEntity(data.getPos()) instanceof ToasterBlockEntity toasterBlock &&
                        toasterBlock.isActive() && !toasterBlock.isBurningToast()).
                withPredicate(data -> {
                    BlockEntity blockEntity = data.getWorld().getBlockEntity(data.getPos());

                    if (blockEntity != null)
                    {
                        return !blockEntity.getTileData().getBoolean("isPlaced");
                    }

                    return true;
                }).
                setReward((player, goal) ->
                {
                    DiscoveredModelsData discoversData = DiscoveredModelsData.get(player.getLevel());
                    ResourceLocation modelId = ModDynamicModels.Wands.BAGUETTE.getId();

                    if (!discoversData.getDiscoveredModels(player.getUUID()).contains(modelId))
                    {
                        MutableComponent info =
                            new TextComponent("Freshly toasted bread... nom!").
                                withStyle(ChatFormatting.GREEN);
                        player.sendMessage(info, Util.NIL_UUID);

                        discoversData.discoverModelAndBroadcast(ModItems.WAND, modelId, player);
                    }
                }));
    }


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
     * The goal for killing mobs while riding a pig to get CARROT_ON_A_WAND model.
     */
    public static VaultMobKillGoal MOBS_KILLED_WHILE_RIDING_PIG;

    /**
     * The goal for killing 5 bunfungus mobs.
     */
    public static VaultMobKillGoal KILL_BUNFUNGUS;

    /**
     * The goal for killing 5 bunfungus mobs.
     */
    public static VaultRequiredBlocksGoal JESTER_SET;

    /**
     * The goal for completing cow vault.
     */
    public static VaultCompletionGoal COW_VAULT;

    /**
     * The goal for scout set.
     */
    public static HunterAbilityGoal SCOUT_ARMOR;

    /**
     * The goal for using toaster.
     */
    public static BlockUseGoal TOAST_BREAD;
}
