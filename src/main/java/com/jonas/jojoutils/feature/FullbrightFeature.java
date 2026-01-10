package com.jonas.jojoutils.feature;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;

public class FullbrightFeature {
    private static double previousGamma = 1.0;
    private static boolean wasEnabled = false;
    
    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (JojoUtilsConfig.fullbrightEnabled && !wasEnabled) {
            // Save previous gamma and enable fullbright
            previousGamma = client.options.getGamma().getValue();
            client.options.getGamma().setValue(16.0);
            wasEnabled = true;
        } else if (!JojoUtilsConfig.fullbrightEnabled && wasEnabled) {
            // Restore previous gamma
            client.options.getGamma().setValue(previousGamma);
            wasEnabled = false;
        } else if (JojoUtilsConfig.fullbrightEnabled) {
            // Keep gamma at max while enabled
            client.options.getGamma().setValue(16.0);
        }
    }
    
    public static double getGamma() {
        if (JojoUtilsConfig.fullbrightEnabled) {
            return 16.0;
        }
        return MinecraftClient.getInstance().options.getGamma().getValue();
    }
}
