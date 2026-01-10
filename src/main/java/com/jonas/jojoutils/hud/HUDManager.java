package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class HUDManager {
    private static final List<HUDElement> elements = new ArrayList<>();
    private static ArmorDurabilityHUD armorHud;
    private static PotionHUD potionHud;
    private static InventoryHUD inventoryHud;
    private static SmartF3HUD smartF3Hud;
    
    public static void init() {
        armorHud = new ArmorDurabilityHUD();
        potionHud = new PotionHUD();
        inventoryHud = new InventoryHUD();
        smartF3Hud = new SmartF3HUD();
        
        elements.add(armorHud);
        elements.add(potionHud);
        elements.add(inventoryHud);
        elements.add(smartF3Hud);
    }
    
    public static void render(DrawContext context, float tickDelta) {
        if (JojoUtilsConfig.armorEnabled) {
            armorHud.render(context, tickDelta);
        }
        if (JojoUtilsConfig.potionEnabled) {
            potionHud.render(context, tickDelta);
        }
        if (JojoUtilsConfig.inventoryEnabled) {
            inventoryHud.render(context, tickDelta);
        }
        if (JojoUtilsConfig.smartF3Enabled) {
            smartF3Hud.render(context, tickDelta);
        }
    }
    
    public static List<HUDElement> getElements() {
        return elements;
    }
    
    public static ArmorDurabilityHUD getArmorHud() {
        return armorHud;
    }
    
    public static PotionHUD getPotionHud() {
        return potionHud;
    }
    
    public static InventoryHUD getInventoryHud() {
        return inventoryHud;
    }
    
    public static SmartF3HUD getSmartF3Hud() {
        return smartF3Hud;
    }
}
