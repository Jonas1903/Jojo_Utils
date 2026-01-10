package com.jonas.jojoutils.keybind;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import com.jonas.jojoutils.hud.HUDLayoutScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {
    public static KeyBinding hudLayoutKey;
    public static KeyBinding toggleArmorKey;
    public static KeyBinding togglePotionKey;
    public static KeyBinding toggleShieldKey;
    public static KeyBinding toggleInventoryKey;
    public static KeyBinding toggleHealthKey;
    public static KeyBinding toggleSmartF3Key;
    public static KeyBinding toggleFullbrightKey;
    public static KeyBinding toggleItemDropKey;
    
    public static void register() {
        // Register keybindings with no default keys (GLFW.GLFW_KEY_UNKNOWN)
        hudLayoutKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.hud_layout",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleArmorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_armor",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        togglePotionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_potion",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleShieldKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_shield",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleInventoryKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_inventory",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleHealthKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_health",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleSmartF3Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_smartf3",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleFullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_fullbright",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        toggleItemDropKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojoutils.toggle_itemdrop",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.jojoutils.category"
        ));
        
        // Register key press handlers
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudLayoutKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new HUDLayoutScreen());
                }
            }
            
            while (toggleArmorKey.wasPressed()) {
                JojoUtilsConfig.armorEnabled = !JojoUtilsConfig.armorEnabled;
                JojoUtilsConfig.save();
            }
            
            while (togglePotionKey.wasPressed()) {
                JojoUtilsConfig.potionEnabled = !JojoUtilsConfig.potionEnabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleShieldKey.wasPressed()) {
                JojoUtilsConfig.shieldEnabled = !JojoUtilsConfig.shieldEnabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleInventoryKey.wasPressed()) {
                JojoUtilsConfig.inventoryEnabled = !JojoUtilsConfig.inventoryEnabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleHealthKey.wasPressed()) {
                JojoUtilsConfig.healthEnabled = !JojoUtilsConfig.healthEnabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleSmartF3Key.wasPressed()) {
                JojoUtilsConfig.smartF3Enabled = !JojoUtilsConfig.smartF3Enabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleFullbrightKey.wasPressed()) {
                JojoUtilsConfig.fullbrightEnabled = !JojoUtilsConfig.fullbrightEnabled;
                JojoUtilsConfig.save();
            }
            
            while (toggleItemDropKey.wasPressed()) {
                JojoUtilsConfig.itemDropEnabled = !JojoUtilsConfig.itemDropEnabled;
                JojoUtilsConfig.save();
            }
        });
    }
}
