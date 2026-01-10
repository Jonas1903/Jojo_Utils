package com.jonas.jojoutils.config;

public class HUDElementConfig {
    public float x;
    public float y;
    public float scale;
    public float opacity;
    public float backgroundOpacity;
    
    public HUDElementConfig() {
        this(0, 0, 1.0f, 1.0f, 0.5f);
    }
    
    public HUDElementConfig(float x, float y, float scale, float opacity, float backgroundOpacity) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.opacity = opacity;
        this.backgroundOpacity = backgroundOpacity;
    }
    
    public HUDElementConfig copy() {
        return new HUDElementConfig(x, y, scale, opacity, backgroundOpacity);
    }
}
