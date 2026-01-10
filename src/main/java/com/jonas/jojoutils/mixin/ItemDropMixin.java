package com.jonas.jojoutils.mixin;

import com.jonas.jojoutils.feature.ItemDropPreventionFeature;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ItemDropMixin {
    
    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", 
            at = @At("HEAD"), cancellable = true)
    private void onDropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, 
                           CallbackInfoReturnable<net.minecraft.entity.ItemEntity> cir) {
        if (!ItemDropPreventionFeature.canDropItem(stack)) {
            // Cancel the drop silently
            cir.setReturnValue(null);
        }
    }
}
