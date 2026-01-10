package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ArmorDurabilityHUD extends HUDElement {
    private static final int SLOT_SIZE = 16;
    private static final int PADDING = 2;
    
    public ArmorDurabilityHUD() {
        super("Armor", JojoUtilsConfig.armorHud);
    }
    
    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        
        PlayerEntity player = client.player;
        context.getMatrices().push();
        context.getMatrices().translate(config.x, config.y, 0);
        context.getMatrices().scale(config.scale, config.scale, 1);
        
        // Render background
        int bgColor = (int) (config.backgroundOpacity * 255) << 24;
        context.fill(0, 0, getWidth(), getHeight(), bgColor);
        
        // Render armor slots (helmet, chestplate, leggings, boots)
        for (int i = 0; i < 4; i++) {
            ItemStack armorPiece = player.getInventory().getArmorStack(3 - i);
            int y = i * (SLOT_SIZE + PADDING + 10);
            
            if (!armorPiece.isEmpty()) {
                // Render item icon
                context.drawItem(armorPiece, 0, y);
                
                // Calculate durability
                int maxDurability = armorPiece.getMaxDamage();
                int currentDurability = maxDurability - armorPiece.getDamage();
                float durabilityPercent = (float) currentDurability / maxDurability;
                
                // Determine color based on durability
                int color = getDurabilityColor(durabilityPercent);
                
                // Render durability text
                String durabilityText;
                if (JojoUtilsConfig.armorShowPercentage) {
                    durabilityText = String.format("%.0f%%", durabilityPercent * 100);
                } else {
                    durabilityText = currentDurability + "/" + maxDurability;
                }
                
                int textColor = (color & 0x00FFFFFF) | ((int) (config.opacity * 255) << 24);
                context.drawText(client.textRenderer, durabilityText, SLOT_SIZE + PADDING, y + 4, textColor, true);
            }
        }
        
        context.getMatrices().pop();
    }
    
    private int getDurabilityColor(float percent) {
        if (percent > 0.5f) {
            return 0xFF00FF00; // Green
        } else if (percent > 0.2f) {
            return 0xFFFFFF00; // Yellow
        } else {
            return 0xFFFF0000; // Red
        }
    }
    
    @Override
    public int getWidth() {
        return 100;
    }
    
    @Override
    public int getHeight() {
        return 4 * (SLOT_SIZE + PADDING + 10);
    }
}
