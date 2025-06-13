package com.sethv.elytramod.registry;

import com.sethv.elytramod.ElytraMod;
import com.sethv.elytramod.ItemElytron;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ElytraMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElytraMod.MODID);

    public static final RegistryObject<Item> ELYTRON = ITEMS.register("elytron",
            () -> new ItemElytron(new Properties()
                    .stacksTo(1)
                    .durability(Items.ELYTRA.getMaxDamage())
                    .rarity(Rarity.EPIC)
                    .tab(CreativeModeTab.TAB_TRANSPORTATION)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
