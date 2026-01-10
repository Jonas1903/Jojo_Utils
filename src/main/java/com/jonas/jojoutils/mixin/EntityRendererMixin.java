package com.jonas.jojoutils.mixin;

import com.jonas.jojoutils.feature.HealthIndicatorFeature;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    
    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(Entity entity, float yaw, float tickDelta, MatrixStack matrices, 
                         VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        HealthIndicatorFeature.renderHealthIndicator(entity, matrices, vertexConsumers, light);
    }
}
