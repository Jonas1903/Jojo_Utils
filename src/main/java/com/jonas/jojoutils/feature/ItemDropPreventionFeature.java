package com.jonas.jojoutils.feature;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ItemDropPreventionFeature {
    
    public static boolean canDropItem(ItemStack stack) {
        if (!JojoUtilsConfig.itemDropEnabled) {
            return true; // Feature disabled, allow all drops
        }
        
        if (stack.isEmpty()) {
            return true; // Empty stack can always be dropped
        }
        
        // Get item ID
        Identifier itemId = Registries.ITEM.getId(stack.getItem());
        String itemIdString = itemId.toString();
        
        // Check if item is in whitelist
        for (String whitelistedId : JojoUtilsConfig.itemDropWhitelist) {
            if (itemIdString.equals(whitelistedId)) {
                return false; // Item is protected, cannot drop
            }
        }
        
        return true; // Item not in whitelist, can drop
    }
}
