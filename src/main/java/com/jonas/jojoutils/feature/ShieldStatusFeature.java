package com.jonas.jojoutils.feature;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShieldStatusFeature {
    private static final Map<UUID, Long> shieldCooldowns = new HashMap<>();
    private static final int COOLDOWN_TICKS = 5; // 0.25 seconds
    
    public static void onShieldHit(PlayerEntity player) {
        if (!JojoUtilsConfig.shieldEnabled) return;
        
        // Record the time when shield was hit
        shieldCooldowns.put(player.getUuid(), System.currentTimeMillis());
    }
    
    public static boolean isShieldOnCooldown(PlayerEntity player) {
        if (!JojoUtilsConfig.shieldEnabled) return false;
        
        Long hitTime = shieldCooldowns.get(player.getUuid());
        if (hitTime == null) return false;
        
        long elapsed = System.currentTimeMillis() - hitTime;
        long cooldownMs = COOLDOWN_TICKS * 50; // Convert ticks to milliseconds
        
        if (elapsed >= cooldownMs) {
            shieldCooldowns.remove(player.getUuid());
            return false;
        }
        
        return true;
    }
    
    public static float getRedOverlayOpacity() {
        return JojoUtilsConfig.shieldRedOverlayOpacity;
    }
    
    public static boolean isGreenOverlayEnabled() {
        return JojoUtilsConfig.shieldGreenOverlayEnabled;
    }
    
    public static float getGreenOverlayOpacity() {
        return JojoUtilsConfig.shieldGreenOverlayOpacity;
    }
    
    public static float getOffsetX() {
        return JojoUtilsConfig.shieldOffsetX;
    }
    
    public static float getOffsetY() {
        return JojoUtilsConfig.shieldOffsetY;
    }
    
    public static float getOffsetZ() {
        return JojoUtilsConfig.shieldOffsetZ;
    }
}
