package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters;


import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import java.util.List;
import java.util.Set;

import net.minecraftforge.fml.loading.LoadingModList;


/**
 * This Mixin configuration checks if VaultHunters mod is loaded.
 */
public class VaultHuntersIntegrationModConfiguration implements IMixinConfigPlugin
{
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        return LoadingModList.get().getModFileById("the_vault") != null;
    }


    @Override
    public void onLoad(String mixinPackage)
    {
    }


    @Override
    public String getRefMapperConfig()
    {
        return null;
    }


    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
    {
    }


    @Override
    public List<String> getMixins()
    {
        return null;
    }


    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
    }


    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
    }
}
