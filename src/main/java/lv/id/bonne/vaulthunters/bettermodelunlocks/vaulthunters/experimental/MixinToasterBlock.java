//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import iskallia.vault.block.discoverable.DiscoverTriggeringBlock;
import net.blay09.mods.cookingforblockheads.block.ToasterBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;


/**
 * This class is used to inject into ToasterBlock to mark when it is placed by player or environment.
 */
@Mixin(value = ToasterBlock.class)
public abstract class MixinToasterBlock extends MixinBlockKitchen
{
    /**
     * This method is used to inject into BlockKitchen#<init> to add DISCOVERED property.
     * @param instance BlockState
     * @param property Property
     * @param comparable Comparable
     * @return BlockState
     */
    @Redirect(method = "<init>",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
    private Object onConstructed(BlockState instance, Property property, Comparable comparable)
    {
        return instance.setValue(property, comparable).setValue(DiscoverTriggeringBlock.DISCOVERED, Boolean.FALSE);
    }


    /**
     * This method is used to inject into BlockKitchen#createBlockStateDefinition to add DISCOVERED property.
     * @param builder StateDefinition.Builder
     * @param ci CallbackInfo
     */
    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void onCreateBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(DiscoverTriggeringBlock.DISCOVERED);
    }


    /**
     * This method is used to inject into BlockKitchen#getStateForPlacement to detect when player places block and
     * mark block as discovered.
     * @param context BlockPlaceContext
     * @param cir CallbackInfoReturnable
     */
    @Override
    protected void injectGetStateForPlacement(BlockPlaceContext context,
        CallbackInfoReturnable<BlockState> cir)
    {
        cir.setReturnValue(cir.getReturnValue().setValue(DiscoverTriggeringBlock.DISCOVERED, Boolean.TRUE));
    }
}
