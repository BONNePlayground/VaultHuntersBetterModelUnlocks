//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events;


import java.util.Arrays;

import iskallia.vault.core.event.ForgeEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.EventPriority;


/**
 * This event is fired when entity finishes using item.
 */
public class EntityFinisItemUseEvent extends ForgeEvent<EntityFinisItemUseEvent, LivingEntityUseItemEvent.Finish>
{
    /**
     * Constructor for the event.
     */
    public EntityFinisItemUseEvent()
    {
    }


    /**
     * Constructor for the event.
     * @param parent The parent event.
     */
    protected EntityFinisItemUseEvent(EntityFinisItemUseEvent parent) {
        super(parent);
    }


    /**
     * Initialize the event.
     */
    protected void initialize()
    {
        Arrays.stream(EventPriority.values()).forEach(priority ->
            MinecraftForge.EVENT_BUS.<LivingEntityUseItemEvent.Finish>addListener(priority, true, event -> this.invoke(event)));
    }


    /**
     * This method is used to filter the world for event.
     * @param world The world.
     * @return The filtered event.
     */
    public EntityFinisItemUseEvent in(Level world)
    {
        return this.filter(data -> data.getEntityLiving().getLevel() == world);
    }


    /**
     * This method is used to filter the item for event.
     * @param item The item.
     * @return The filtered event.
     */
    public EntityFinisItemUseEvent of(Item item)
    {
        return this.filter(data -> data.getItem().is(item));
    }


    /**
     * Create child event.
     * @return Child event.
     */
    public EntityFinisItemUseEvent createChild()
    {
        return new EntityFinisItemUseEvent(this);
    }
}
