package com.jonas.jojoutils.mixin;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import com.jonas.jojoutils.feature.ShieldStatusFeature;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class ShieldOverlayMixin {
    
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void onRenderFirstPersonItem(ClientPlayerEntity player, float tickDelta, float pitch, Hand hand, 
                                          float swingProgress, ItemStack item, float equipProgress, 
                                          MatrixStack matrices, VertexConsumerProvider vertexConsumers, 
                                          int light, CallbackInfo ci) {
        if (!JojoUtilsConfig.shieldEnabled) return;
        if (!item.isOf(Items.SHIELD)) return;
        
        // Apply position offsets
        matrices.translate(
            ShieldStatusFeature.getOffsetX(),
            ShieldStatusFeature.getOffsetY(),
            ShieldStatusFeature.getOffsetZ()
        );
    }
    
    @Inject(method = "renderFirstPersonItem", at = @At("RETURN"))
    private void afterRenderFirstPersonItem(ClientPlayerEntity player, float tickDelta, float pitch, Hand hand, 
                                             float swingProgress, ItemStack item, float equipProgress, 
                                             MatrixStack matrices, VertexConsumerProvider vertexConsumers, 
                                             int light, CallbackInfo ci) {
        if (!JojoUtilsConfig.shieldEnabled) return;
        if (!item.isOf(Items.SHIELD)) return;
        
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Check shield state
        boolean isOnCooldown = player.getItemCooldownManager().isCoolingDown(Items.SHIELD);
        
        // Render red overlay if on cooldown
        if (isOnCooldown) {
            renderShieldOverlay(matrices, vertexConsumers, ShieldStatusFeature.getRedOverlayOpacity(), 
                              1.0f, 0.0f, 0.0f);
        }
        // Render green overlay if enabled and shield is ready
        else if (ShieldStatusFeature.isGreenOverlayEnabled()) {
            renderShieldOverlay(matrices, vertexConsumers, ShieldStatusFeature.getGreenOverlayOpacity(), 
                              0.0f, 1.0f, 0.0f);
        }
    }
    
    private void renderShieldOverlay(MatrixStack matrices, VertexConsumerProvider vertexConsumers, 
                                     float alpha, float r, float g, float b) {
        // This would need proper vertex rendering for the overlay
        // For now, this is a placeholder for the overlay rendering logic
        // The actual implementation would require rendering a colored quad over the shield
    }
}
