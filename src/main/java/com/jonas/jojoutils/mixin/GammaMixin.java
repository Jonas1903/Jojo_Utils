package com.jonas.jojoutils.mixin;

import com.jonas.jojoutils.feature.FullbrightFeature;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleOption.class)
public class GammaMixin {
    @Inject(method = "getValue", at = @At("RETURN"), cancellable = true)
    private void onGetValue(CallbackInfoReturnable<Object> cir) {
        // Check if this is the gamma option
        SimpleOption<?> option = (SimpleOption<?>) (Object) this;
        if (option.toString().contains("gamma") || option.toString().contains("Gamma")) {
            double gamma = FullbrightFeature.getGamma();
            if (gamma > 1.0) {
                cir.setReturnValue(gamma);
            }
        }
    }
}
