package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.HUDElementConfig;
import net.minecraft.client.gui.DrawContext;

public abstract class HUDElement {
    protected HUDElementConfig config;
    protected String name;
    
    public HUDElement(String name, HUDElementConfig config) {
        this.name = name;
        this.config = config;
    }
    
    public abstract void render(DrawContext context, float tickDelta);
    
    public abstract int getWidth();
    
    public abstract int getHeight();
    
    public HUDElementConfig getConfig() {
        return config;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPosition(float x, float y) {
        config.x = x;
        config.y = y;
    }
    
    public void setScale(float scale) {
        config.scale = scale;
    }
    
    public float getX() {
        return config.x;
    }
    
    public float getY() {
        return config.y;
    }
    
    public float getScale() {
        return config.scale;
    }
    
    public float getOpacity() {
        return config.opacity;
    }
    
    public float getBackgroundOpacity() {
        return config.backgroundOpacity;
    }
}
