package com.jonas.jojoutils;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import com.jonas.jojoutils.feature.FullbrightFeature;
import com.jonas.jojoutils.hud.HUDManager;
import com.jonas.jojoutils.keybind.KeybindManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class JojoUtilsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Load config
        JojoUtilsConfig.load();
        
        // Initialize managers
        KeybindManager.register();
        HUDManager.init();
        
        // Register HUD rendering
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            HUDManager.render(drawContext, tickDelta);
        });
        
        // Register fullbright tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            FullbrightFeature.tick();
        });
        
        JojoUtils.LOGGER.info("Jojo Utils Client initialized!");
    }
}
