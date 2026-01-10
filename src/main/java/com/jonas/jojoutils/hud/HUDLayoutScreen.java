package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class HUDLayoutScreen extends Screen {
    private static final int GRID_SIZE = 8;
    private HUDElement draggedElement = null;
    private boolean resizing = false;
    private double dragStartX, dragStartY;
    private float originalX, originalY, originalScale;
    
    public HUDLayoutScreen() {
        super(Text.literal("HUD Layout Editor"));
    }
    
    @Override
    protected void init() {
        super.init();
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render semi-transparent background
        context.fill(0, 0, this.width, this.height, 0x80000000);
        
        // Render all HUD elements as colored boxes
        for (HUDElement element : HUDManager.getElements()) {
            renderElementBox(context, element, element == draggedElement);
        }
        
        // Render instructions
        context.drawText(this.textRenderer, "Drag elements to reposition | Hold Shift to disable grid snap | Scroll to scale | ESC to save and close", 
            10, this.height - 20, 0xFFFFFFFF, true);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    private void renderElementBox(DrawContext context, HUDElement element, boolean highlight) {
        int x = (int) element.getX();
        int y = (int) element.getY();
        int width = (int) (element.getWidth() * element.getScale());
        int height = (int) (element.getHeight() * element.getScale());
        
        // Different colors for different elements
        int color = getElementColor(element);
        if (highlight) {
            color = 0xFFFFFF00; // Yellow for highlighted
        }
        
        // Render box with border
        context.fill(x, y, x + width, y + height, (color & 0x00FFFFFF) | 0x80000000);
        context.fill(x, y, x + width, y + 2, color);
        context.fill(x, y + height - 2, x + width, y + height, color);
        context.fill(x, y, x + 2, y + height, color);
        context.fill(x + width - 2, y, x + width, y + height, color);
        
        // Render element name
        context.drawText(this.textRenderer, element.getName(), x + 5, y + 5, 0xFFFFFFFF, true);
    }
    
    private int getElementColor(HUDElement element) {
        if (element instanceof ArmorDurabilityHUD) return 0xFFFF0000;
        if (element instanceof PotionHUD) return 0xFF00FF00;
        if (element instanceof InventoryHUD) return 0xFF0000FF;
        if (element instanceof SmartF3HUD) return 0xFFFFFF00;
        return 0xFFFFFFFF;
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left click
            // Find element under mouse
            for (int i = HUDManager.getElements().size() - 1; i >= 0; i--) {
                HUDElement element = HUDManager.getElements().get(i);
                int x = (int) element.getX();
                int y = (int) element.getY();
                int width = (int) (element.getWidth() * element.getScale());
                int height = (int) (element.getHeight() * element.getScale());
                
                if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                    draggedElement = element;
                    dragStartX = mouseX;
                    dragStartY = mouseY;
                    originalX = element.getX();
                    originalY = element.getY();
                    originalScale = element.getScale();
                    resizing = false;
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (draggedElement != null && button == 0) {
            float newX = (float) (originalX + (mouseX - dragStartX));
            float newY = (float) (originalY + (mouseY - dragStartY));
            
            // Apply grid snapping if shift is not held
            if (!Screen.hasShiftDown()) {
                newX = Math.round(newX / GRID_SIZE) * GRID_SIZE;
                newY = Math.round(newY / GRID_SIZE) * GRID_SIZE;
            }
            
            draggedElement.setPosition(newX, newY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            draggedElement = null;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        // Find element under mouse
        for (int i = HUDManager.getElements().size() - 1; i >= 0; i--) {
            HUDElement element = HUDManager.getElements().get(i);
            int x = (int) element.getX();
            int y = (int) element.getY();
            int width = (int) (element.getWidth() * element.getScale());
            int height = (int) (element.getHeight() * element.getScale());
            
            if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                float newScale = element.getScale() + (float) (verticalAmount * 0.1);
                newScale = Math.max(0.1f, Math.min(5.0f, newScale));
                element.setScale(newScale);
                return true;
            }
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }
    
    @Override
    public void close() {
        // Save configuration when closing
        JojoUtilsConfig.save();
        super.close();
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
