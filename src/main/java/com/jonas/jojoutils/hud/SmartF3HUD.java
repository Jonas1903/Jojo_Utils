package com.jonas.jojoutils.hud;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public class SmartF3HUD extends HUDElement {
    private static final int LINE_HEIGHT = 10;
    private static final int PADDING = 2;
    
    public SmartF3HUD() {
        super("Smart F3", JojoUtilsConfig.smartF3Hud);
    }
    
    @Override
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        
        context.getMatrices().push();
        context.getMatrices().translate(config.x, config.y, 0);
        context.getMatrices().scale(config.scale, config.scale, 1);
        
        // Render background
        int bgColor = (int) (config.backgroundOpacity * 255) << 24;
        context.fill(0, 0, getWidth(), getHeight(), bgColor);
        
        int yOffset = PADDING;
        int textColor = ((int) (config.opacity * 255) << 24) | 0x00FFFFFF;
        
        // FPS
        if (JojoUtilsConfig.smartF3ShowFps) {
            String fpsText = "FPS: " + client.getCurrentFps();
            context.drawText(client.textRenderer, fpsText, PADDING, yOffset, textColor, true);
            yOffset += LINE_HEIGHT;
        }
        
        // Ping (only in multiplayer)
        if (JojoUtilsConfig.smartF3ShowPing && !client.isInSingleplayer()) {
            int ping = getPing();
            String pingText = "Ping: " + ping + "ms";
            context.drawText(client.textRenderer, pingText, PADDING, yOffset, textColor, true);
            yOffset += LINE_HEIGHT;
        }
        
        // Direction
        if (JojoUtilsConfig.smartF3ShowDirection) {
            String direction = getCardinalDirection();
            String directionText = "Direction: " + direction;
            context.drawText(client.textRenderer, directionText, PADDING, yOffset, textColor, true);
            yOffset += LINE_HEIGHT;
        }
        
        context.getMatrices().pop();
    }
    
    private int getPing() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if (networkHandler == null) return 0;
        
        PlayerListEntry playerEntry = networkHandler.getPlayerListEntry(client.player.getUuid());
        if (playerEntry == null) return 0;
        
        return playerEntry.getLatency();
    }
    
    private String getCardinalDirection() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return "N";
        
        float yaw = MathHelper.wrapDegrees(client.player.getYaw());
        Direction direction = Direction.fromRotation(yaw);
        
        switch (direction) {
            case NORTH: return "N";
            case SOUTH: return "S";
            case EAST: return "E";
            case WEST: return "W";
            default: return "N";
        }
    }
    
    @Override
    public int getWidth() {
        return 120;
    }
    
    @Override
    public int getHeight() {
        int lines = 0;
        if (JojoUtilsConfig.smartF3ShowFps) lines++;
        if (JojoUtilsConfig.smartF3ShowPing && !MinecraftClient.getInstance().isInSingleplayer()) lines++;
        if (JojoUtilsConfig.smartF3ShowDirection) lines++;
        return lines * LINE_HEIGHT + PADDING * 2;
    }
}
