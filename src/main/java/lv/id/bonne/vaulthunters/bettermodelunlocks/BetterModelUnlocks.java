package lv.id.bonne.vaulthunters.bettermodelunlocks;


import lv.id.bonne.vaulthunters.bettermodelunlocks.config.Configuration;
import lv.id.bonne.vaulthunters.bettermodelunlocks.events.AnvilRepairEventListener;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.goals.ExtraModelDiscoveryGoals;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


/**
 * The main class for Vault Hunters Better Model Unlock mod.
 */
@Mod(BetterModelUnlocks.MOD_ID)
public class BetterModelUnlocks
{
    /**
     * The main class initialization.
     */
    public BetterModelUnlocks()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
            Configuration.GENERAL_SPEC,
            MOD_ID + ".toml");

        // Register event listeners.
        MinecraftForge.EVENT_BUS.register(AnvilRepairEventListener.class);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }


    /**
     * Common setup event.
     * @param event The event.
     */
    private void commonSetup(FMLCommonSetupEvent event)
    {
        ExtraCommonEvents.init();
        ExtraModelDiscoveryGoals.init();
    }


    /**
     * Cleaner resource location creation.
     * @param key The key.
     * @return The resource location.
     */
    public static ResourceLocation of(String key)
    {
        return new ResourceLocation(MOD_ID, key);
    }


    /**
     * The main configuration file.
     */
    public static Configuration CONFIGURATION = new Configuration();


    /**
     * The MOD_ID
     */
    public static final String MOD_ID = "vault_hunters_better_model_unlocks";
}
