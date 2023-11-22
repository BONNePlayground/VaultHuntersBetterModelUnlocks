//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import iskallia.vault.discoverylogic.goal.base.DiscoveryGoal;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.data.AttributeGearData;
import iskallia.vault.gear.data.ToolGearData;
import iskallia.vault.item.tool.ToolItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;


/**
 * This Goal allows to detect if Jewel has applied requested attribute with at least given
 * target value.
 */
public class JewelApplicationGoal extends DiscoveryGoal<JewelApplicationGoal>
{
    /**
     * Instantiates a new Jewel application goal.
     *
     * @param integerAttribute the integer attribute
     * @param targetValue the target value
     */
    public JewelApplicationGoal(VaultGearAttribute<?> integerAttribute, int targetValue)
    {
        super((float) targetValue);
        this.attribute = integerAttribute;
    }


    /**
     * This method is used to add predicate to the list of predicates.
     * @param predicate The predicate that will be added to the list of predicates.
     * @return The JewelApplicationGoal goal.
     */
    public JewelApplicationGoal withPredicate(Predicate<ItemStack> predicate)
    {
        this.predicates.add(predicate);
        return this;
    }


    /**
     * This method is triggered on Jewel application. It checks if tool matches minimal requested
     * attribute value.
     * @param player Player who applied Jewel
     * @param toolItem Tool which Jewel was applied to
     */
    public void onJewelApply(ServerPlayer player, ItemStack toolItem)
    {
        if (toolItem.getItem() instanceof ToolItem &&
            this.predicates.stream().allMatch(predicate -> predicate.test(toolItem)))
        {
            ToolGearData data = AttributeGearData.read(toolItem);

            data.getAllAttributes().forEach(attribute ->
            {
                if (attribute.getAttribute() == this.attribute)
                {
                    if (attribute.getValue() instanceof Integer value)
                    {
                        if (value >= this.targetProgress)
                        {
                            this.progress(player, this.targetProgress);
                        }
                    }
                    else if (attribute.getValue() instanceof Float value)
                    {
                        if (value >= this.targetProgress)
                        {
                            this.progress(player, this.targetProgress);
                        }
                    }
                    else if (attribute.getValue() instanceof Double value)
                    {
                        if (value >= this.targetProgress)
                        {
                            this.progress(player, this.targetProgress);
                        }
                    }
                }
            });
        }
    }


    /**
     * The target attribute  that need to be checked.
     */
    private final VaultGearAttribute<?> attribute;


    /**
     * The list of predicates that will be used to check if the event is valid.
     */
    protected List<Predicate<ItemStack>> predicates = new LinkedList<>();
}