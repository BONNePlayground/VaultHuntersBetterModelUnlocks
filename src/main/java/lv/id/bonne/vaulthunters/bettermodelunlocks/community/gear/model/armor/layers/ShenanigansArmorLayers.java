package lv.id.bonne.vaulthunters.bettermodelunlocks.community.gear.model.armor.layers;


import java.util.function.Supplier;

import iskallia.vault.dynamodel.model.armor.ArmorLayers;
import iskallia.vault.dynamodel.model.armor.ArmorPieceModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class ShenanigansArmorLayers extends ArmorLayers
{
    public ShenanigansArmorLayers()
    {
    }


    @OnlyIn(Dist.CLIENT)
    public Supplier<LayerDefinition> getGeometrySupplier(EquipmentSlot equipmentSlot)
    {
        return equipmentSlot == EquipmentSlot.LEGS ? LeggingsLayer::createBodyLayer : MainLayer::createBodyLayer;
    }


    @OnlyIn(Dist.CLIENT)
    public ArmorLayers.VaultArmorLayerSupplier<? extends ArmorLayers.BaseLayer> getLayerSupplier(EquipmentSlot equipmentSlot)
    {
        return equipmentSlot == EquipmentSlot.LEGS ? LeggingsLayer::new : MainLayer::new;
    }


    public static class LeggingsLayer extends ArmorLayers.LeggingsLayer
    {
        public LeggingsLayer(ArmorPieceModel definition, ModelPart root)
        {
            super(definition, root);
        }


        public static LayerDefinition createBodyLayer()
        {
            MeshDefinition meshdefinition = createBaseLayer();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().
                texOffs(32, 16).
                addBox(-4.0F, 10.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.26F)).
                texOffs(0, 0).
                addBox(-5.0F, 8.0F, -3.0F, 10.0F, 3.0F, 6.0F, new CubeDeformation(0.26F)).
                texOffs(8, 21).
                addBox(2.25F, 6.25F, -7.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.26F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

            body.addOrReplaceChild("cube_r1", CubeListBuilder.create().
                texOffs(17, 21).
                addBox(-2.0F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.25F, 8.5F, -9.5F, 0.7854F, 0.0F, 0.0F));

            body.addOrReplaceChild("cube_r2", CubeListBuilder.create().
                texOffs(18, 9).
                addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.26F)),
                PartPose.offsetAndRotation(3.75F, 10.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().
                texOffs(32, 0).
                addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)).
                texOffs(0, 9).
                addBox(-3.0F, 2.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).
                texOffs(0, 21).
                addBox(-5.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().
                texOffs(48, 0).
                addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.26F)).
                texOffs(54, 12).
                mirror().
                addBox(-2.0F, 4.0F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.27F)).
                mirror(false),
                PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, -0.1745F, 0.0F, -0.0349F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }
    }


    public static class MainLayer extends ArmorLayers.MainLayer
    {
        public MainLayer(ArmorPieceModel definition, ModelPart root)
        {
            super(definition, root);
        }


        public static LayerDefinition createBodyLayer()
        {
            MeshDefinition meshdefinition = createBaseLayer();
            PartDefinition partdefinition = meshdefinition.getRoot();

            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().
                texOffs(0, 0).
                addBox(-4.0F, -14.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51F)).
                texOffs(0, 16).
                addBox(-5.0F, -6.0F, -3.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).
                texOffs(24, 3).
                addBox(0.0F, -6.0F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).
                texOffs(24, 0).
                addBox(-4.0F, -7.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).
                texOffs(24, 0).
                addBox(1.0F, -7.0F, -6.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).
                texOffs(0, 36).
                addBox(-4.0F, -3.0F, -6.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).
                texOffs(16, 28).
                addBox(4.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)).
                texOffs(0, 28).
                addBox(-8.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)).
                texOffs(12, 28).
                addBox(6.0F, -8.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).
                texOffs(12, 28).
                addBox(-8.0F, -8.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().
                texOffs(20, 38).
                addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.26F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

            body.addOrReplaceChild("cube_r1", CubeListBuilder.create().
                texOffs(0, 38).
                addBox(-2.0F, -2.0F, -3.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.26F)),
                PartPose.offsetAndRotation(0.0F, 2.25F, -0.5F, -0.7854F, -0.3927F, 1.5708F));

            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().
                    texOffs(34, 0).
                    addBox(-3.0F, 1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.26F)).
                    texOffs(32, 6).
                    addBox(-6.0F, -4.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).
                    texOffs(50, 3).
                    addBox(-10.0F, -3.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).
                    texOffs(28, 16).
                    addBox(-3.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).
                    texOffs(36, 24).
                    addBox(-5.0F, -2.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).
                    texOffs(36, 32).
                    addBox(-5.0F, 6.0F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).
                    texOffs(36, 32).
                    addBox(-5.0F, 6.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).
                    texOffs(30, 33).
                    addBox(-5.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().
                    texOffs(28, 16).
                    mirror().
                    addBox(1.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).
                    mirror(false).
                    texOffs(40, 16).
                    addBox(1.0F, -4.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)).
                    texOffs(51, 27).
                    addBox(5.5F, -4.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).
                    texOffs(51, 27).
                    addBox(5.5F, -0.1F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).
                    texOffs(51, 27).
                    addBox(5.5F, 4.25F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().
                texOffs(16, 50).
                addBox(-2.1F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.26F)).
                texOffs(0, 61).
                addBox(-2.0F, 10.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.27F)),
                PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().
                texOffs(0, 48).
                addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.26F)).
                texOffs(0, 61).
                addBox(-1.9F, 10.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.27F)),
                PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, -0.1745F, 0.0F, -0.0349F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }
    }
}