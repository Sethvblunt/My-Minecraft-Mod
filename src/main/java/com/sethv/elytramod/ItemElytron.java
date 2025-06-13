package com.sethv.elytramod;

import net.minecraft.world.item.ElytraItem;
import com.sethv.elytramod.registry.ModArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class ItemElytron extends GeoArmorItem implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public ItemElytron(Properties properties) {
        super(ModArmorMaterials.ELYTRA, EquipmentSlot.CHEST, properties);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return stack.getItem() == this && ElytraItem.isFlyEnabled(stack);
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level.isClientSide || !(entity instanceof Player)) return true;

        if (FlightHandler.customFlyMode && ((Player) entity).input.forwardImpulse > 0) {
            Vec3 look = entity.getLookAngle().normalize().scale(0.9);
            Vec3 currentMotion = entity.getDeltaMovement();
            entity.setDeltaMovement(look.x, currentMotion.y * 0.95, look.z);
            entity.fallDistance = 0;
            return true;
        } else {
            // Vanilla glide style
            if ((flightTicks + 1) % 20 == 0) {
                stack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
            }
            return true;
        }
    }


    @Override
    public boolean canBeDepleted() {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.PHANTOM_MEMBRANE;
    }
}
