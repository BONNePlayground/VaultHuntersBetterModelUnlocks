//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.config;


import net.minecraftforge.common.ForgeConfigSpec;


/**
 * The configuration handling class. Holds all the config values.
 */
public class Configuration
{
    /**
     * The constructor for the config.
     */
    public Configuration()
    {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        this.punishmentValue = builder.
            comment("The punishment value for already known vault gear model.").
            comment("Trinkets has value 0.16666667F").
            defineInRange("knownModelPunishment", 0.16666667F, 0.0d, 1.0d);

        Configuration.GENERAL_SPEC = builder.build();
    }


    /**
     * Gets punishment value.
     *
     * @return the punishment value
     */
    public ForgeConfigSpec.ConfigValue<Double> getPunishmentValue()
    {
        return this.punishmentValue;
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


    /**
     * The config value for punishing already known gear models.
     */
    private final ForgeConfigSpec.ConfigValue<Double> punishmentValue;

    /**
     * The general config spec.
     */
    public static ForgeConfigSpec GENERAL_SPEC;
}
