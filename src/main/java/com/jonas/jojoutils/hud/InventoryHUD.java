package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class InventoryHUD extends HUDElement {
    private static final int SLOT_SIZE = 16;
    private static final int PADDING = 2;
    private static final int SLOTS_PER_ROW = 9;
    private static final int ROWS = 3;
    
    public InventoryHUD() {
        super("Inventory", JojoUtilsConfig.inventoryHud);
    }
    
    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        
        context.getMatrices().push();
        context.getMatrices().translate(config.x, config.y, 0);
        context.getMatrices().scale(config.scale, config.scale, 1);
        
        // Render background (full inventory grid)
        if (!JojoUtilsConfig.inventoryHideEmptySlots) {
            int bgColor = (int) (config.backgroundOpacity * 255) << 24;
            context.fill(0, 0, getWidth(), getHeight(), bgColor);
        }
        
        // Render each slot in the main inventory (slots 9-35, excluding hotbar)
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < SLOTS_PER_ROW; col++) {
                int slotIndex = 9 + (row * SLOTS_PER_ROW) + col;
                ItemStack stack = client.player.getInventory().getStack(slotIndex);
                
                int x = col * (SLOT_SIZE + PADDING);
                int y = row * (SLOT_SIZE + PADDING);
                
                if (!stack.isEmpty()) {
                    // Render slot background if item exists or if empty slots are visible
                    if (!JojoUtilsConfig.inventoryHideEmptySlots) {
                        int slotBgColor = (int) (config.backgroundOpacity * 255) << 24 | 0x008B8B8B;
                        context.fill(x - 1, y - 1, x + SLOT_SIZE + 1, y + SLOT_SIZE + 1, slotBgColor);
                    }
                    
                    // Render item
                    context.drawItem(stack, x, y);
                    context.drawItemInSlot(client.textRenderer, stack, x, y);
                } else if (!JojoUtilsConfig.inventoryHideEmptySlots) {
                    // Render empty slot background
                    int slotBgColor = (int) (config.backgroundOpacity * 255) << 24 | 0x008B8B8B;
                    context.fill(x - 1, y - 1, x + SLOT_SIZE + 1, y + SLOT_SIZE + 1, slotBgColor);
                }
            }
        }
        
        context.getMatrices().pop();
    }
    
    @Override
    public int getWidth() {
        return SLOTS_PER_ROW * (SLOT_SIZE + PADDING);
    }
    
    @Override
    public int getHeight() {
        return ROWS * (SLOT_SIZE + PADDING);
    }
}
