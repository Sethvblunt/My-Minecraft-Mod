package com.sethv.elytramod;

import com.sethv.elytramod.client.renderer.ElytronRenderer;
import com.sethv.elytramod.ItemElytron;
import com.sethv.elytramod.FlightHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ElytraMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    // Removed ElytraLayer injection — not needed for GeckoLib-based custom elytra

    @SubscribeEvent
    public static void onClientSetup(net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            GeoArmorRenderer.registerArmorRenderer(ItemElytron.class, ElytronRenderer::new);
        });
    }
}
