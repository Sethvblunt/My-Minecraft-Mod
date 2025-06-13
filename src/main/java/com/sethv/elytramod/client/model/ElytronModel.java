package com.sethv.elytramod.client.model;

import com.sethv.elytramod.ItemElytron;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElytronModel extends AnimatedGeoModel<ItemElytron> {

    @Override
    public ResourceLocation getModelResource(ItemElytron object) {
        return new ResourceLocation("elytramod", "geo/elytronwings.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ItemElytron object) {
        return new ResourceLocation("elytramod", "textures/entity/elytra/dragon_wings.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ItemElytron animatable) {
        return new ResourceLocation("elytramod", "animations/model.animation.json");
    }
}
