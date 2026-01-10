package com.jonas.jojoutils.feature;

import com.jonas.jojoutils.config.JojoUtilsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class HealthIndicatorFeature {
    private static final Identifier HEART_FULL = new Identifier("textures/gui/icons.png");
    private static final int MAX_ROWS = 8;
    private static final int HEARTS_PER_ROW = 10;
    private static final int HEART_SIZE = 9;
    
    public static void renderHealthIndicator(Entity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (!JojoUtilsConfig.healthEnabled) return;
        if (!(entity instanceof LivingEntity)) return;
        
        LivingEntity livingEntity = (LivingEntity) entity;
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Check if we should render for this entity type
        if (entity instanceof PlayerEntity && !JojoUtilsConfig.healthShowPlayers) return;
        if (entity instanceof HostileEntity && !JojoUtilsConfig.healthShowHostile) return;
        if (entity instanceof PassiveEntity && !JojoUtilsConfig.healthShowFriendly) return;
        
        // Check distance limit
        if (client.player != null) {
            double distance = client.player.distanceTo(entity);
            if (distance > JojoUtilsConfig.healthDistanceLimit) return;
        }
        
        // Don't render for the player themselves
        if (entity == client.player) return;
        
        float health = livingEntity.getHealth();
        float maxHealth = livingEntity.getMaxHealth();
        int hearts = (int) Math.ceil(health / 2.0f);
        int maxHearts = (int) Math.ceil(maxHealth / 2.0f);
        
        // Cap at 8 rows (80 hearts / 160 HP)
        int displayHearts = Math.min(hearts, MAX_ROWS * HEARTS_PER_ROW);
        int displayMaxHearts = Math.min(maxHearts, MAX_ROWS * HEARTS_PER_ROW);
        
        renderHearts(matrices, vertexConsumers, displayHearts, displayMaxHearts, light);
    }
    
    private static void renderHearts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int hearts, int maxHearts, int light) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        
        matrices.push();
        matrices.translate(0, JojoUtilsConfig.healthVerticalOffset, 0);
        matrices.scale(-0.025f, -0.025f, 0.025f);
        
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        
        int rows = (int) Math.ceil((double) maxHearts / HEARTS_PER_ROW);
        
        for (int row = 0; row < rows; row++) {
            int heartsInRow = Math.min(HEARTS_PER_ROW, maxHearts - (row * HEARTS_PER_ROW));
            int renderedHearts = Math.max(0, hearts - (row * HEARTS_PER_ROW));
            renderedHearts = Math.min(renderedHearts, heartsInRow);
            
            int xOffset = -((heartsInRow * HEART_SIZE) / 2);
            int yOffset = row * HEART_SIZE;
            
            for (int i = 0; i < renderedHearts; i++) {
                int x = xOffset + (i * HEART_SIZE);
                int y = yOffset;
                
                // Render heart using text (simplified approach)
                textRenderer.draw("♥", x, y, 0xFFFF0000, false, matrix, vertexConsumers, 
                    TextRenderer.TextLayerType.NORMAL, 0, light);
            }
        }
        
        matrices.pop();
    }
}
