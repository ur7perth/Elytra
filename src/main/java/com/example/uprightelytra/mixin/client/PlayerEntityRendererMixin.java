package com.example.uprightelytra.mixin.client;

import com.example.uprightelytra.ModConfig;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Redirect(
            method = "setupTransforms",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isFallFlying()Z"
            )
    )
    private boolean upright$noTilt(AbstractClientPlayerEntity player) {
        if (ModConfig.enabled) {
            return false;
        }
        return player.isFallFlying();
    }
}
