//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import iskallia.ispawner.world.spawner.SpawnerAction;
import iskallia.ispawner.world.spawner.SpawnerContext;
import lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.events.ExtraCommonEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


/**
 * This mixin replaces spawned entity in ISpawner blocks.
 */
@Mixin(value = SpawnerAction.class, remap = false)
public class MixinCowSpawnerAction
{
    /**
     * This method redirects entity spawn to cow if vault is cow vault.
     * @param value Entity type instance.
     * @param world World.
     * @param stack Item stack.
     * @param context Spawner context.
     * @return The cow entity or original entity instance.
     */
    @ModifyVariable(method = "applyEggOverride",
        at = @At(value = "STORE",
            target = "Lnet/minecraft/world/item/SpawnEggItem;getType(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/entity/EntityType;"))
    private EntityType modifyVar10000(EntityType value, Level world, ItemStack stack, SpawnerContext context)
    {
        return ExtraCommonEvents.SPAWNER_ENTITY_CREATE.
            invoke(value, world).
            getEntityType();
    }
}