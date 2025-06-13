package com.sethv.elytramod;

import com.sethv.elytramod.ElytraMod;
import com.sethv.elytramod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ElytraMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FlightHandler {
    private static boolean wasSpaceDown = false;
    private static boolean elytraToggled = false;
    private static boolean togglePressed = false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == GLFW.GLFW_KEY_R && event.getAction() == GLFW.GLFW_PRESS) {
            if (!togglePressed) {
                elytraToggled = !elytraToggled;
                togglePressed = true;
            }
        }
        if (event.getKey() == GLFW.GLFW_KEY_R && event.getAction() == GLFW.GLFW_RELEASE) {
            togglePressed = false;
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof LocalPlayer player))
            return;

        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chestItem.getItem() != ModItems.ELYTRON.get()) return;

        Minecraft mc = Minecraft.getInstance();

        boolean isSpaceDown = mc.options.keyJump.isDown();

        // Handle double-tap space
        if (isSpaceDown && !wasSpaceDown) {
            if (!player.isFallFlying() && player.isOnGround()) {
                player.setDeltaMovement(player.getDeltaMovement().x, 1.2, player.getDeltaMovement().z); // lift off
                player.startFallFlying();
            }
        }

        wasSpaceDown = isSpaceDown;

        if (player.isFallFlying()) {
            if (elytraToggled && mc.options.keyUp.isDown()) {
                // Infinite momentum flight mode
                double speed = 0.03;
                double yaw = Math.toRadians(player.getYRot());
                double xSpeed = -Math.sin(yaw) * speed;
                double zSpeed = Math.cos(yaw) * speed;
                player.setDeltaMovement(player.getDeltaMovement().x + xSpeed, player.getDeltaMovement().y, player.getDeltaMovement().z + zSpeed);
            }
        }
    }
}
