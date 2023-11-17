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
import java.util.Random;

import iskallia.vault.core.world.data.tile.PartialBlockState;
import iskallia.vault.core.world.data.tile.PartialTile;
import iskallia.vault.core.world.data.tile.TilePredicate;
import iskallia.vault.core.world.template.PlacementSettings;
import iskallia.vault.core.world.template.StructureTemplate;
import iskallia.vault.init.ModBlocks;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.world.level.block.Blocks;


/**
 * This mixin allows to replace SMOOTH_QUARTZ as TOOTH for BAMBOO in Wild West Room.
 * This is very illegal bypass to not create a new structure for wild west room.
 */
@Mixin(value = StructureTemplate.class, remap = false)
public abstract class MixinStructureTemplate
{
    /**
     * Injection happens in StructureTemplate#getTiles method. The lambda is coming from MappingIterator.
     * Injection is done when we assign tile variable from copy value, and after that we implement our
     * code that replaces SMOOTH_QUARTZ with GOLDEN_TOOTH block.
     * @param settings The placement settings
     * @param filter The tile filter
     * @param tile the tile block
     * @param cir Callback info returnable
     */
    @Inject(method = "lambda$getTiles$3(Liskallia/vault/core/world/template/PlacementSettings;Liskallia/vault/core/world/data/tile/TilePredicate;Liskallia/vault/core/world/data/tile/PartialTile;)Liskallia/vault/core/world/data/tile/PartialTile;",
        at = @At(value = "INVOKE_ASSIGN", target = "iskallia/vault/core/world/data/tile/PartialTile.copy()Liskallia/vault/core/world/data/tile/PartialTile;"),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void injectGoldenTeethIntoBamboo(PlacementSettings settings,
        TilePredicate filter,
        PartialTile tile,
        CallbackInfoReturnable<PartialTile> cir)
    {
        // As static cannot check for correct structure, I hope that there are no other structures with
        // a smooth quartz at x = 22 or 24, y = 26, z = 19. Otherwise, it also can be replaced by golden tooth.
        // Anyway... chance is small :)

        if (BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks() &&
            tile.getState().is(Blocks.SMOOTH_QUARTZ) &&
            (tile.getPos().getX() == 22 || tile.getPos().getX() == 24) &&
            tile.getPos().getY() == 26 &&
            tile.getPos().getZ() == 19 &&
            new Random().nextFloat() < BetterModelUnlocks.CONFIGURATION.getChanceToSpawnTeeth())
        {
            tile.setState(PartialBlockState.of(ModBlocks.GOLDEN_TOOTH.defaultBlockState()));
        }
    }
}
