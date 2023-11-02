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
            comment("Setting value to 1 would disable this feature.").
            comment("Trinkets has value 0.16666667F").
            defineInRange("knownModelPunishment", 0.16666667F, Double.MIN_VALUE, 1.0d);

        builder.comment("This category holds options that are experimental and not-canon.");
        builder.push("Experimental Settings");

        this.experimentalUnlocks = builder.
            comment("The option to enable experimental, non-canon gear model unlocks.").
            comment("By default it is disabled.").
            define("experimentalUnlocks", false);

        this.chanceToSpawnPig = builder.
            comment("The chance to spawn pig when spawning vault fighter.").
            comment("By default it is 1%.").
            defineInRange("chanceToSpawnPig", 0.01F, 0.0F, 1.0F);

        builder.pop();

        Configuration.GENERAL_SPEC = builder.build();
    }


    /**
     * Gets punishment value.
     *
     * @return the punishment value
     */
    public double getPunishmentValue()
    {
        return this.punishmentValue.get();
    }


    /**
     * Gets experimental unlocks.
     *
     * @return the experimental unlocks.
     */
    public boolean getExperimentalUnlocks()
    {
        return this.experimentalUnlocks.get();
    }


    /**
     * Gets chance to spawn pig when spawning vault fighter.
     * @return the chance to spawn pig when spawning vault fighter.
     */
    public double getChanceToSpawnPig()
    {
        return this.chanceToSpawnPig.get();
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


    /**
     * The config value for punishing already known gear models.
     */
    private final ForgeConfigSpec.ConfigValue<Double> punishmentValue;

    /**
     * The config value for punishing already known gear models.
     */
    private final ForgeConfigSpec.ConfigValue<Boolean> experimentalUnlocks;

    /**
     * The config value for chance to spawn pig when spawning vault fighter.
     */
    private final ForgeConfigSpec.ConfigValue<Double> chanceToSpawnPig;

    /**
     * The general config spec.
     */
    public static ForgeConfigSpec GENERAL_SPEC;
}
