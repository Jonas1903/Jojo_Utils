package com.jonas.jojoutils.config;

import com.jonas.jojoutils.keybind.KeybindManager;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }
    
    private Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.translatable("config.jojoutils.title"))
            .setSavingRunnable(JojoUtilsConfig::save);
        
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        
        // Armor HUD Category
        ConfigCategory armorCategory = builder.getOrCreateCategory(Text.literal("Armor HUD"));
        armorCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.armor.enabled"),
            JojoUtilsConfig.armorEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.armorEnabled = val)
            .build());
        armorCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.armor.scale"),
            JojoUtilsConfig.armorHud.scale)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.armorHud.scale = val)
            .build());
        armorCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.armor.opacity"),
            JojoUtilsConfig.armorHud.opacity)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.armorHud.opacity = val)
            .build());
        armorCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.armor.backgroundOpacity"),
            JojoUtilsConfig.armorHud.backgroundOpacity)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.armorHud.backgroundOpacity = val)
            .build());
        armorCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.armor.showPercentage"),
            JojoUtilsConfig.armorShowPercentage)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.armorShowPercentage = val)
            .build());
        
        // Potion HUD Category
        ConfigCategory potionCategory = builder.getOrCreateCategory(Text.literal("Potion HUD"));
        potionCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.potion.enabled"),
            JojoUtilsConfig.potionEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.potionEnabled = val)
            .build());
        potionCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.potion.scale"),
            JojoUtilsConfig.potionHud.scale)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.potionHud.scale = val)
            .build());
        potionCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.potion.opacity"),
            JojoUtilsConfig.potionHud.opacity)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.potionHud.opacity = val)
            .build());
        potionCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.potion.backgroundOpacity"),
            JojoUtilsConfig.potionHud.backgroundOpacity)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.potionHud.backgroundOpacity = val)
            .build());
        
        // Shield Status Category
        ConfigCategory shieldCategory = builder.getOrCreateCategory(Text.literal("Shield Status"));
        shieldCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.shield.enabled"),
            JojoUtilsConfig.shieldEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldEnabled = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.shield.redOverlayOpacity"),
            JojoUtilsConfig.shieldRedOverlayOpacity)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldRedOverlayOpacity = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.shield.greenOverlayEnabled"),
            JojoUtilsConfig.shieldGreenOverlayEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldGreenOverlayEnabled = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.shield.greenOverlayOpacity"),
            JojoUtilsConfig.shieldGreenOverlayOpacity)
            .setDefaultValue(0.3f)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldGreenOverlayOpacity = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.shield.offsetX"),
            JojoUtilsConfig.shieldOffsetX)
            .setDefaultValue(0.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldOffsetX = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.shield.offsetY"),
            JojoUtilsConfig.shieldOffsetY)
            .setDefaultValue(0.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldOffsetY = val)
            .build());
        shieldCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.shield.offsetZ"),
            JojoUtilsConfig.shieldOffsetZ)
            .setDefaultValue(0.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.shieldOffsetZ = val)
            .build());
        
        // Inventory HUD Category
        ConfigCategory inventoryCategory = builder.getOrCreateCategory(Text.literal("Inventory HUD"));
        inventoryCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.inventory.enabled"),
            JojoUtilsConfig.inventoryEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.inventoryEnabled = val)
            .build());
        inventoryCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.inventory.scale"),
            JojoUtilsConfig.inventoryHud.scale)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.inventoryHud.scale = val)
            .build());
        inventoryCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.inventory.opacity"),
            JojoUtilsConfig.inventoryHud.opacity)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.inventoryHud.opacity = val)
            .build());
        inventoryCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.inventory.backgroundOpacity"),
            JojoUtilsConfig.inventoryHud.backgroundOpacity)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.inventoryHud.backgroundOpacity = val)
            .build());
        inventoryCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.inventory.hideEmptySlots"),
            JojoUtilsConfig.inventoryHideEmptySlots)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.inventoryHideEmptySlots = val)
            .build());
        
        // Health Indicators Category
        ConfigCategory healthCategory = builder.getOrCreateCategory(Text.literal("Health Indicators"));
        healthCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.health.enabled"),
            JojoUtilsConfig.healthEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.healthEnabled = val)
            .build());
        healthCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.health.showPlayers"),
            JojoUtilsConfig.healthShowPlayers)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.healthShowPlayers = val)
            .build());
        healthCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.health.showHostile"),
            JojoUtilsConfig.healthShowHostile)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.healthShowHostile = val)
            .build());
        healthCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.health.showFriendly"),
            JojoUtilsConfig.healthShowFriendly)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.healthShowFriendly = val)
            .build());
        healthCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.health.verticalOffset"),
            JojoUtilsConfig.healthVerticalOffset)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.healthVerticalOffset = val)
            .build());
        healthCategory.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.health.distanceLimit"),
            JojoUtilsConfig.healthDistanceLimit)
            .setDefaultValue(32.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.healthDistanceLimit = val)
            .build());
        
        // Smart F3 Category
        ConfigCategory smartf3Category = builder.getOrCreateCategory(Text.literal("Smart F3"));
        smartf3Category.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.smartf3.enabled"),
            JojoUtilsConfig.smartF3Enabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3Enabled = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.smartf3.scale"),
            JojoUtilsConfig.smartF3Hud.scale)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3Hud.scale = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.smartf3.opacity"),
            JojoUtilsConfig.smartF3Hud.opacity)
            .setDefaultValue(1.0f)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3Hud.opacity = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startFloatField(
            Text.translatable("config.jojoutils.smartf3.backgroundOpacity"),
            JojoUtilsConfig.smartF3Hud.backgroundOpacity)
            .setDefaultValue(0.5f)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3Hud.backgroundOpacity = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.smartf3.showFps"),
            JojoUtilsConfig.smartF3ShowFps)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3ShowFps = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.smartf3.showPing"),
            JojoUtilsConfig.smartF3ShowPing)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3ShowPing = val)
            .build());
        smartf3Category.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.smartf3.showDirection"),
            JojoUtilsConfig.smartF3ShowDirection)
            .setDefaultValue(true)
            .setSaveConsumer(val -> JojoUtilsConfig.smartF3ShowDirection = val)
            .build());
        
        // Fullbright Category
        ConfigCategory fullbrightCategory = builder.getOrCreateCategory(Text.literal("Fullbright"));
        fullbrightCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.fullbright.enabled"),
            JojoUtilsConfig.fullbrightEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.fullbrightEnabled = val)
            .build());
        
        // Item Drop Prevention Category
        ConfigCategory itemdropCategory = builder.getOrCreateCategory(Text.literal("Item Drop Prevention"));
        itemdropCategory.addEntry(entryBuilder.startBooleanToggle(
            Text.translatable("config.jojoutils.itemdrop.enabled"),
            JojoUtilsConfig.itemDropEnabled)
            .setDefaultValue(false)
            .setSaveConsumer(val -> JojoUtilsConfig.itemDropEnabled = val)
            .build());
        itemdropCategory.addEntry(entryBuilder.startStrList(
            Text.translatable("config.jojoutils.itemdrop.whitelist"),
            JojoUtilsConfig.itemDropWhitelist)
            .setDefaultValue(new ArrayList<>())
            .setSaveConsumer(val -> JojoUtilsConfig.itemDropWhitelist = new ArrayList<>(val))
            .build());
        
        // Keybindings Category
        ConfigCategory keybindCategory = builder.getOrCreateCategory(Text.literal("Keybindings"));
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.hud_layout"),
            KeybindManager.hudLayoutKey.boundKey)
            .setDefaultValue(KeybindManager.hudLayoutKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.hudLayoutKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_armor"),
            KeybindManager.toggleArmorKey.boundKey)
            .setDefaultValue(KeybindManager.toggleArmorKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleArmorKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_potion"),
            KeybindManager.togglePotionKey.boundKey)
            .setDefaultValue(KeybindManager.togglePotionKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.togglePotionKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_shield"),
            KeybindManager.toggleShieldKey.boundKey)
            .setDefaultValue(KeybindManager.toggleShieldKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleShieldKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_inventory"),
            KeybindManager.toggleInventoryKey.boundKey)
            .setDefaultValue(KeybindManager.toggleInventoryKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleInventoryKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_health"),
            KeybindManager.toggleHealthKey.boundKey)
            .setDefaultValue(KeybindManager.toggleHealthKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleHealthKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_smartf3"),
            KeybindManager.toggleSmartF3Key.boundKey)
            .setDefaultValue(KeybindManager.toggleSmartF3Key.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleSmartF3Key.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_fullbright"),
            KeybindManager.toggleFullbrightKey.boundKey)
            .setDefaultValue(KeybindManager.toggleFullbrightKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleFullbrightKey.setBoundKey(key))
            .build());
        keybindCategory.addEntry(entryBuilder.startKeyCodeField(
            Text.translatable("key.jojoutils.toggle_itemdrop"),
            KeybindManager.toggleItemDropKey.boundKey)
            .setDefaultValue(KeybindManager.toggleItemDropKey.getDefaultKey())
            .setSaveConsumer(key -> KeybindManager.toggleItemDropKey.setBoundKey(key))
            .build());
        
        return builder.build();
    }
}
