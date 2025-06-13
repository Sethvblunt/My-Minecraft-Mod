package com.sethv.elytramod;

import com.sethv.elytramod.registry.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ElytraMod.MODID)
public class ElytraMod {
    public static final String MODID = "elytramod";

    public ElytraMod() {
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
