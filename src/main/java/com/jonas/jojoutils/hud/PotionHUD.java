package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;

import java.util.ArrayList;
import java.util.List;

public class PotionHUD extends HUDElement {
    private static final int EFFECT_SIZE = 18;
    private static final int PADDING = 2;
    
    public PotionHUD() {
        super("Potions", JojoUtilsConfig.potionHud);
    }
    
    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        
        List<StatusEffectInstance> effects = new ArrayList<>(client.player.getStatusEffects());
        if (effects.isEmpty()) return;
        
        context.getMatrices().push();
        context.getMatrices().translate(config.x, config.y, 0);
        context.getMatrices().scale(config.scale, config.scale, 1);
        
        // Render background
        int bgColor = (int) (config.backgroundOpacity * 255) << 24;
        context.fill(0, 0, getWidth(), getHeight(), bgColor);
        
        int yOffset = 0;
        for (StatusEffectInstance effect : effects) {
            // Render effect icon
            renderStatusEffect(context, effect, 0, yOffset, tickDelta);
            yOffset += EFFECT_SIZE + PADDING;
        }
        
        context.getMatrices().pop();
    }
    
    private void renderStatusEffect(DrawContext context, StatusEffectInstance effect, int x, int y, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Get remaining time in seconds
        int duration = effect.getDuration();
        int seconds = duration / 20;
        boolean isFlashing = seconds < 5;
        
        // Calculate flash opacity
        float effectOpacity = config.opacity;
        if (isFlashing) {
            long currentTime = System.currentTimeMillis();
            boolean flashOn = (currentTime / 500) % 2 == 0;
            effectOpacity = flashOn ? config.opacity * 0.5f : config.opacity;
        }
        
        // Draw status effect sprite
        context.getMatrices().push();
        if (isFlashing) {
            // Apply flashing opacity
            int alpha = (int) (effectOpacity * 255);
            context.setShaderColor(1.0f, 1.0f, 1.0f, effectOpacity);
        }
        
        // Draw the effect icon using vanilla rendering
        context.drawGuiTexture(StatusEffectUtil.getParticleSprite(effect), x, y, EFFECT_SIZE, EFFECT_SIZE);
        
        if (isFlashing) {
            context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
        context.getMatrices().pop();
        
        // Format and render time
        String timeText = formatTime(seconds);
        int textColor = 0xFFFFFFFF;
        if (isFlashing) {
            textColor = ((int) (effectOpacity * 255) << 24) | 0x00FFFFFF;
        } else {
            textColor = ((int) (config.opacity * 255) << 24) | 0x00FFFFFF;
        }
        
        context.drawText(client.textRenderer, timeText, x + EFFECT_SIZE + PADDING, y + 5, textColor, true);
    }
    
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
    
    @Override
    public int getWidth() {
        return 80;
    }
    
    @Override
    public int getHeight() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return EFFECT_SIZE;
        
        int effectCount = client.player.getStatusEffects().size();
        if (effectCount == 0) return EFFECT_SIZE;
        
        return effectCount * (EFFECT_SIZE + PADDING);
    }
}
