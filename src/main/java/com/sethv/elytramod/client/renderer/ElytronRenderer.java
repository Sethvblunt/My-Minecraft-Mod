package com.sethv.elytramod.client.renderer;

import com.sethv.elytramod.ItemElytron;
import com.sethv.elytramod.client.model.ElytronModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ElytronRenderer extends GeoArmorRenderer<ItemElytron> {
    public ElytronRenderer() {
        super(new ElytronModel());
    }
}
