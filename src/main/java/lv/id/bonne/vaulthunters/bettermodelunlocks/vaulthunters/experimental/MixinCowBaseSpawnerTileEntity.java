//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import iskallia.vault.block.entity.BaseSpawnerTileEntity;
import iskallia.vault.init.ModEntities;
import iskallia.vault.world.data.ServerVaults;
import lv.id.bonne.vaulthunters.bettermodelunlocks.utils.ExtraModFields;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * This mixin replaces spawned entity with cow if vault is cow vault.
 */
@Mixin(value = BaseSpawnerTileEntity.class, remap = false)
public abstract class MixinCowBaseSpawnerTileEntity
{
    /**
     * This method redirects entity spawn to cow if vault is cow vault.
     * @param instance Entity type instance.
     * @param serverLevel Server level.
     * @param itemStack Item stack.
     * @param player Player.
     * @param blockPos Block position.
     * @param spawnType Spawn type.
     * @param p_20598_ boolean flag.
     * @param p_20599_ boolean flag.
     * @return The cow entity or original entity instance.
     */
    @Redirect(method = "spawnEntity",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/EntityType;spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;"))
    private static Entity changeEntity(EntityType<?> instance,
        ServerLevel serverLevel,
        ItemStack itemStack,
        Player player,
        BlockPos blockPos,
        MobSpawnType spawnType,
        boolean p_20598_,
        boolean p_20599_)
    {
        EntityType<?> returnInstance;

        if (ServerVaults.get(serverLevel).map(vault -> vault.getOr(ExtraModFields.COW_VAULT, false)).orElse(false))
        {
            returnInstance = ModEntities.AGGRESSIVE_COW;
        }
        else
        {
            returnInstance = instance;
        }

        return returnInstance.spawn(serverLevel, itemStack, player, blockPos, spawnType, p_20598_, p_20599_);
    }
}
