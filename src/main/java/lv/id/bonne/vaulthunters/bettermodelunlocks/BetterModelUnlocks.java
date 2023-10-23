package lv.id.bonne.vaulthunters.bettermodelunlocks;


import lv.id.bonne.vaulthunters.bettermodelunlocks.config.Configuration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


/**
 * The main class for Vault Hunters Better Model Unlock mod.
 */
@Mod(BetterModelUnlocks.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterModelUnlocks
{
    /**
     * The main class initialization.
     */
    public BetterModelUnlocks()
    {
        BetterModelUnlocks.CONFIGURATION = new Configuration();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
            Configuration.GENERAL_SPEC,
            MOD_ID + ".toml");
    }


    /**
     * The main configuration file.
     */
    public static Configuration CONFIGURATION;


    /**
     * The MOD_ID
     */
    public static final String MOD_ID = "vault_hunters_better_model_unlocks";
}
