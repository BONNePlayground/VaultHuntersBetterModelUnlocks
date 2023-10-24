//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.utils;


import iskallia.vault.discoverylogic.goal.VaultMobKillGoal;
import iskallia.vault.discoverylogic.goal.base.DiscoveryGoal;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.init.ModItems;
import iskallia.vault.init.ModModelDiscoveryGoals;
import iskallia.vault.world.data.DiscoveredModelsData;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
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
        /**
         * Register new goal for killing mobs while riding a pig.
         */
        MOBS_KILLED_WHILE_RIDING_PIG = registerGoal(
            new ResourceLocation(BetterModelUnlocks.MOD_ID, "mobs_killed_while_riding_pig"),
            new VaultMobKillGoal(1).
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
    }


    /**
     * The goal for killing mobs while riding a pig to get CARROT_ON_A_WAND model.
     */
    public final static VaultMobKillGoal MOBS_KILLED_WHILE_RIDING_PIG;


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
}
