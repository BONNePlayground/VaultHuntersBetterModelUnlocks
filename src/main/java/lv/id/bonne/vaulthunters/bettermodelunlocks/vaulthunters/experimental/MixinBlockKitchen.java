//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.blay09.mods.cookingforblockheads.block.BlockKitchen;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;


/**
 * This class is used to inject into BlockKitchen#getStateForPlacement to detect when player places block.
 */
@Mixin(value = BlockKitchen.class)
public abstract class MixinBlockKitchen
{
    /**
     * This method is used to inject into BlockKitchen#getStateForPlacement to detect when player places block.
     * @param context BlockPlaceContext
     * @param cir CallbackInfoReturnable
     */
    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    protected void injectGetStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir)
    {
        //Leave empty
    }
}
