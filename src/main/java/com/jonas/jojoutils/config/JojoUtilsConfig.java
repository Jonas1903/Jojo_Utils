package com.jonas.jojoutils.config;

import com.jonas.jojoutils.JojoUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JojoUtilsConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("jojo-utils.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    // Armor HUD
    public static boolean armorEnabled = false;
    public static HUDElementConfig armorHud = new HUDElementConfig(10, 10, 1.0f, 1.0f, 0.5f);
    public static boolean armorShowPercentage = true;
    
    // Potion HUD
    public static boolean potionEnabled = false;
    public static HUDElementConfig potionHud = new HUDElementConfig(10, 100, 1.0f, 1.0f, 0.5f);
    
    // Shield Status
    public static boolean shieldEnabled = false;
    public static float shieldRedOverlayOpacity = 0.5f;
    public static boolean shieldGreenOverlayEnabled = false;
    public static float shieldGreenOverlayOpacity = 0.3f;
    public static float shieldOffsetX = 0.0f;
    public static float shieldOffsetY = 0.0f;
    public static float shieldOffsetZ = 0.0f;
    
    // Inventory HUD
    public static boolean inventoryEnabled = false;
    public static HUDElementConfig inventoryHud = new HUDElementConfig(10, 200, 1.0f, 1.0f, 0.5f);
    public static boolean inventoryHideEmptySlots = false;
    
    // Health Indicators
    public static boolean healthEnabled = false;
    public static boolean healthShowPlayers = true;
    public static boolean healthShowHostile = true;
    public static boolean healthShowFriendly = true;
    public static float healthVerticalOffset = 0.5f;
    public static float healthDistanceLimit = 32.0f;
    
    // Smart F3
    public static boolean smartF3Enabled = false;
    public static HUDElementConfig smartF3Hud = new HUDElementConfig(10, 300, 1.0f, 1.0f, 0.5f);
    public static boolean smartF3ShowFps = true;
    public static boolean smartF3ShowPing = true;
    public static boolean smartF3ShowDirection = true;
    
    // Fullbright
    public static boolean fullbrightEnabled = false;
    
    // Item Drop Prevention
    public static boolean itemDropEnabled = false;
    public static List<String> itemDropWhitelist = new ArrayList<>();
    
    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }
        
        try {
            String json = Files.readString(CONFIG_FILE.toPath());
            @SuppressWarnings("unchecked")
            Map<String, Object> configMap = GSON.fromJson(json, Map.class);
            
            if (configMap == null) {
                save();
                return;
            }
            
            // Armor HUD
            Map<String, Object> armor = getMap(configMap, "armor");
            if (armor != null) {
                armorEnabled = getBoolean(armor, "enabled", false);
                armorShowPercentage = getBoolean(armor, "showPercentage", true);
                loadHudConfig(getMap(armor, "hud"), armorHud);
            }
            
            // Potion HUD
            Map<String, Object> potion = getMap(configMap, "potion");
            if (potion != null) {
                potionEnabled = getBoolean(potion, "enabled", false);
                loadHudConfig(getMap(potion, "hud"), potionHud);
            }
            
            // Shield Status
            Map<String, Object> shield = getMap(configMap, "shield");
            if (shield != null) {
                shieldEnabled = getBoolean(shield, "enabled", false);
                shieldRedOverlayOpacity = getFloat(shield, "redOverlayOpacity", 0.5f);
                shieldGreenOverlayEnabled = getBoolean(shield, "greenOverlayEnabled", false);
                shieldGreenOverlayOpacity = getFloat(shield, "greenOverlayOpacity", 0.3f);
                shieldOffsetX = getFloat(shield, "offsetX", 0.0f);
                shieldOffsetY = getFloat(shield, "offsetY", 0.0f);
                shieldOffsetZ = getFloat(shield, "offsetZ", 0.0f);
            }
            
            // Inventory HUD
            Map<String, Object> inventory = getMap(configMap, "inventory");
            if (inventory != null) {
                inventoryEnabled = getBoolean(inventory, "enabled", false);
                inventoryHideEmptySlots = getBoolean(inventory, "hideEmptySlots", false);
                loadHudConfig(getMap(inventory, "hud"), inventoryHud);
            }
            
            // Health Indicators
            Map<String, Object> health = getMap(configMap, "health");
            if (health != null) {
                healthEnabled = getBoolean(health, "enabled", false);
                healthShowPlayers = getBoolean(health, "showPlayers", true);
                healthShowHostile = getBoolean(health, "showHostile", true);
                healthShowFriendly = getBoolean(health, "showFriendly", true);
                healthVerticalOffset = getFloat(health, "verticalOffset", 0.5f);
                healthDistanceLimit = getFloat(health, "distanceLimit", 32.0f);
            }
            
            // Smart F3
            Map<String, Object> smartf3 = getMap(configMap, "smartf3");
            if (smartf3 != null) {
                smartF3Enabled = getBoolean(smartf3, "enabled", false);
                smartF3ShowFps = getBoolean(smartf3, "showFps", true);
                smartF3ShowPing = getBoolean(smartf3, "showPing", true);
                smartF3ShowDirection = getBoolean(smartf3, "showDirection", true);
                loadHudConfig(getMap(smartf3, "hud"), smartF3Hud);
            }
            
            // Fullbright
            Map<String, Object> fullbright = getMap(configMap, "fullbright");
            if (fullbright != null) {
                fullbrightEnabled = getBoolean(fullbright, "enabled", false);
            }
            
            // Item Drop Prevention
            Map<String, Object> itemdrop = getMap(configMap, "itemdrop");
            if (itemdrop != null) {
                itemDropEnabled = getBoolean(itemdrop, "enabled", false);
                Object whitelistObj = itemdrop.get("whitelist");
                if (whitelistObj instanceof List) {
                    itemDropWhitelist = new ArrayList<>((List<String>) whitelistObj);
                }
            }
            
            JojoUtils.LOGGER.info("Configuration loaded successfully");
        } catch (Exception e) {
            JojoUtils.LOGGER.error("Failed to load configuration", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getMap(Map<String, Object> map, String key) {
        Object obj = map.get(key);
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        return null;
    }
    
    private static boolean getBoolean(Map<String, Object> map, String key, boolean defaultValue) {
        Object obj = map.get(key);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return defaultValue;
    }
    
    private static float getFloat(Map<String, Object> map, String key, float defaultValue) {
        Object obj = map.get(key);
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        return defaultValue;
    }
    
    private static void loadHudConfig(Map<String, Object> map, HUDElementConfig config) {
        if (map == null) return;
        config.x = getFloat(map, "x", config.x);
        config.y = getFloat(map, "y", config.y);
        config.scale = getFloat(map, "scale", config.scale);
        config.opacity = getFloat(map, "opacity", config.opacity);
        config.backgroundOpacity = getFloat(map, "backgroundOpacity", config.backgroundOpacity);
    }
    
    public static void save() {
        try {
            Map<String, Object> configMap = new HashMap<>();
            
            // Armor HUD
            Map<String, Object> armor = new HashMap<>();
            armor.put("enabled", armorEnabled);
            armor.put("showPercentage", armorShowPercentage);
            armor.put("hud", hudConfigToMap(armorHud));
            configMap.put("armor", armor);
            
            // Potion HUD
            Map<String, Object> potion = new HashMap<>();
            potion.put("enabled", potionEnabled);
            potion.put("hud", hudConfigToMap(potionHud));
            configMap.put("potion", potion);
            
            // Shield Status
            Map<String, Object> shield = new HashMap<>();
            shield.put("enabled", shieldEnabled);
            shield.put("redOverlayOpacity", shieldRedOverlayOpacity);
            shield.put("greenOverlayEnabled", shieldGreenOverlayEnabled);
            shield.put("greenOverlayOpacity", shieldGreenOverlayOpacity);
            shield.put("offsetX", shieldOffsetX);
            shield.put("offsetY", shieldOffsetY);
            shield.put("offsetZ", shieldOffsetZ);
            configMap.put("shield", shield);
            
            // Inventory HUD
            Map<String, Object> inventory = new HashMap<>();
            inventory.put("enabled", inventoryEnabled);
            inventory.put("hideEmptySlots", inventoryHideEmptySlots);
            inventory.put("hud", hudConfigToMap(inventoryHud));
            configMap.put("inventory", inventory);
            
            // Health Indicators
            Map<String, Object> health = new HashMap<>();
            health.put("enabled", healthEnabled);
            health.put("showPlayers", healthShowPlayers);
            health.put("showHostile", healthShowHostile);
            health.put("showFriendly", healthShowFriendly);
            health.put("verticalOffset", healthVerticalOffset);
            health.put("distanceLimit", healthDistanceLimit);
            configMap.put("health", health);
            
            // Smart F3
            Map<String, Object> smartf3 = new HashMap<>();
            smartf3.put("enabled", smartF3Enabled);
            smartf3.put("showFps", smartF3ShowFps);
            smartf3.put("showPing", smartF3ShowPing);
            smartf3.put("showDirection", smartF3ShowDirection);
            smartf3.put("hud", hudConfigToMap(smartF3Hud));
            configMap.put("smartf3", smartf3);
            
            // Fullbright
            Map<String, Object> fullbright = new HashMap<>();
            fullbright.put("enabled", fullbrightEnabled);
            configMap.put("fullbright", fullbright);
            
            // Item Drop Prevention
            Map<String, Object> itemdrop = new HashMap<>();
            itemdrop.put("enabled", itemDropEnabled);
            itemdrop.put("whitelist", itemDropWhitelist);
            configMap.put("itemdrop", itemdrop);
            
            String json = GSON.toJson(configMap);
            Files.writeString(CONFIG_FILE.toPath(), json);
            
            JojoUtils.LOGGER.info("Configuration saved successfully");
        } catch (IOException e) {
            JojoUtils.LOGGER.error("Failed to save configuration", e);
        }
    }
    
    private static Map<String, Object> hudConfigToMap(HUDElementConfig config) {
        Map<String, Object> map = new HashMap<>();
        map.put("x", config.x);
        map.put("y", config.y);
        map.put("scale", config.scale);
        map.put("opacity", config.opacity);
        map.put("backgroundOpacity", config.backgroundOpacity);
        return map;
    }
}
