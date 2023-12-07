//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events;


import iskallia.vault.core.event.Event;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;


/**
 * This event is fired before entity is created via spawner.
 */
public class SpawnerEntityCreateEvent extends Event<SpawnerEntityCreateEvent, SpawnerEntityCreateEvent.Data>
{
    /**
     * This constructor is used to create event.
     */
    public SpawnerEntityCreateEvent() { }


    /**
     * This constructor is used to create event.
     * @param parent The parent event.
     */
    protected SpawnerEntityCreateEvent(SpawnerEntityCreateEvent parent)
    {
        super(parent);
    }


    /**
     * This method is used to create child event.
     * @return The child event.
     */
    public SpawnerEntityCreateEvent createChild()
    {
        return new SpawnerEntityCreateEvent(this);
    }


    /**
     * This method is used to invoke event.
     * @param entityType The entity type that will be spawned by spawner.
     * @param level The level where entity will be spawned.
     * @return The event.
     */
    public SpawnerEntityCreateEvent.Data invoke(EntityType<?> entityType, Level level)
    {
        return this.invoke(new SpawnerEntityCreateEvent.Data(entityType, level));
    }


    /**
     * This method is used to filter event by level.
     * @param level The level where entity will be spawned.
     * @return The event.
     */
    public SpawnerEntityCreateEvent in(Level level)
    {
        return this.filter((data) -> level == data.level);
    }


    /**
     * This class is used to store data for the event.
     */
    public static class Data
    {
        /**
         * The method is used to get level where entity will be spawned.
         * @param entityType The entity type that will be spawned by spawner.
         * @param level The level where entity will be spawned.
         */
        protected Data(EntityType<?> entityType, Level level)
        {
            this.entityType = entityType;
            this.level = level;
        }


        /**
         * The method is used to get entity type that will be spawned by spawner.
         * @return The entity type that will be spawned by spawner.
         */
        public EntityType<?> getEntityType()
        {
            return this.entityType;
        }


        /**
         * The method is used to set entity type that will be spawned by spawner.
         * @param entityType The entity type that will be spawned by spawner.
         */
        public void setEntityType(EntityType<?> entityType)
        {
            this.entityType = entityType;
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
         * Sets level.
         *
         * @param level the level
         */
        public void setLevel(Level level)
        {
            this.level = level;
        }


        /**
         * The entity type that will be spawned by spawner.
         */
        private EntityType<?> entityType;

        /*
         * The level where entity will be spawned.
         */
        private Level level;
    }
}
