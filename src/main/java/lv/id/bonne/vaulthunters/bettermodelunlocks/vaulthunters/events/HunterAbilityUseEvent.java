//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events;


import iskallia.vault.core.event.Event;
import iskallia.vault.skill.source.SkillSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;


/**
 * This event is fired after hunter ability is used.
 */
public class HunterAbilityUseEvent extends Event<HunterAbilityUseEvent, HunterAbilityUseEvent.Data>
{
    /**
     * This constructor is used to create event.
     */
    public HunterAbilityUseEvent() { }


    /**
     * This constructor is used to create event.
     * @param parent The parent event.
     */
    protected HunterAbilityUseEvent(HunterAbilityUseEvent parent)
    {
        super(parent);
    }


    /**
     * This method is used to create child event.
     * @return The child event.
     */
    public HunterAbilityUseEvent createChild()
    {
        return new HunterAbilityUseEvent(this);
    }


    /**
     * This method is used to invoke event.
     * @param player The player who used hunter ability.
     * @param level The level where ability was used.
     * @return The event.
     */
    public HunterAbilityUseEvent.Data invoke(ServerPlayer player, Level level)
    {
        return this.invoke(new HunterAbilityUseEvent.Data(player, level));
    }


    /**
     * This method is used to filter event by level.
     * @param level The level where entity will be spawned.
     * @return The event.
     */
    public HunterAbilityUseEvent in(Level level)
    {
        return this.filter((data) -> level == data.level);
    }


    /**
     * This class is used to store data for the event.
     */
    public static class Data
    {
        /**
         * The initialization for the class.
         * @param player The player who used hunter ability
         * @param level The level where ability was used.
         */
        protected Data(ServerPlayer player, Level level)
        {
            this.player = player;
            this.level = level;
        }


        /**
         * The player who used hunter ability.
         * @return The player who used hunter ability.
         */
        public ServerPlayer getPlayer()
        {
            return this.player;
        }


        /**
         * Gets level.
         *
         * @return the level
         */
        public Level getLevel()
        {
            return this.level;
        }


        /**
         * The player that is triggered.
         */
        private final ServerPlayer player;

        /*
         * The level where entity will be spawned.
         */
        private final Level level;
    }
}
