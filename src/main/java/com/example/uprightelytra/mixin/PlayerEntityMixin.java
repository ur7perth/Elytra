package com.example.uprightelytra.mixin;

import com.example.uprightelytra.ModConfig;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Unique
    private static final EntityDimensions UPRIGHT_DIMENSIONS =
            EntityDimensions.changing(0.6F, 1.8F).withEyeHeight(1.62F);

    @Inject(method = "getBaseDimensions", at = @At("HEAD"), cancellable = true)
    private void upright$dimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if (ModConfig.enabled && pose == EntityPose.FALL_FLYING) {
            cir.setReturnValue(UPRIGHT_DIMENSIONS);
        }
    }
}
