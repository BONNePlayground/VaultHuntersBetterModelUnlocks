//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.bettermodelunlocks.vaulthunters.experimental;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import iskallia.vault.entity.entity.FighterEntity;
import lv.id.bonne.vaulthunters.bettermodelunlocks.BetterModelUnlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.ServerLevelAccessor;


/**
 * This mixin changes Vault Fighter entity so it could spawn with pig, instead of chicken.
 */
@Mixin(value = FighterEntity.class, remap = false)
public class MixinSpawnVaultFighter
{
    /**
     * This method injects code to spawn pig with Vault Fighter.
     * @param world The world.
     * @param difficulty The difficulty.
     * @param reason The spawn reason.
     * @param spawnData The spawn data.
     * @param dataTag The data tag.
     * @param cir Callback info.
     */
    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    private void spawnInGroupWithPig(ServerLevelAccessor world,
        DifficultyInstance difficulty,
        MobSpawnType reason,
        SpawnGroupData spawnData,
        CompoundTag dataTag,
        CallbackInfoReturnable<SpawnGroupData> cir)
    {
        FighterEntity fighterEntity = ((FighterEntity) (Object) this);

        if (!fighterEntity.isPassenger() &&
            BetterModelUnlocks.CONFIGURATION.getExperimentalUnlocks() &&
            fighterEntity.getRandom().nextFloat() <= BetterModelUnlocks.CONFIGURATION.getChanceToSpawnPig())
        {
            // Spawn pig with vault fighter riding it.
            Pig pig = EntityType.PIG.create(fighterEntity.getLevel());
            pig.moveTo(fighterEntity.getX(), fighterEntity.getY(), fighterEntity.getZ(), fighterEntity.getYRot(), 0.0F);
            pig.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
            pig.equipSaddle(SoundSource.HOSTILE);
            ((ServerLevel) fighterEntity.getLevel()).addWithUUID(pig);
            fighterEntity.startRiding(pig);
        }
    }
}
