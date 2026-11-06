package com.example.uprightelytra.mixin.client;

import com.example.uprightelytra.ModConfig;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {

    @Shadow @Final public ModelPart head;
    @Shadow @Final public ModelPart hat;
    @Shadow @Final public ModelPart rightArm;
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightLeg;
    @Shadow @Final public ModelPart leftLeg;

    @Inject(method = "setAngles", at = @At("TAIL"))
    private void upright$pose(T entity, float limbAngle, float limbDistance, float animationProgress,
                              float headYaw, float headPitch, CallbackInfo ci) {
        if (!ModConfig.enabled || !(entity instanceof PlayerEntity) || !entity.isFallFlying()) {
            return;
        }
        this.head.pitch = headPitch * 0.017453292F;
        this.hat.copyTransform(this.head);

        this.rightArm.pitch = 0.0F;
        this.leftArm.pitch = 0.0F;
        this.rightLeg.pitch = 0.0F;
        this.leftLeg.pitch = 0.0F;
    }
}
