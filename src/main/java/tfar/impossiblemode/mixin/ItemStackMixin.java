package tfar.impossiblemode.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "attemptDamageItem",at = @At("RETURN"),cancellable = true)
	private void onePercentBreakChance(int amount, Random rand, EntityPlayerMP damager, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue())return;
		if (rand.nextDouble() < .01)cir.setReturnValue(true);
	}
}
