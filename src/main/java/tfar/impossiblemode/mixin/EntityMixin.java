package tfar.impossiblemode.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

	@Shadow private int fire;

	@Inject(method = "setFire",at = @At("RETURN"))
	private void everburn(int seconds, CallbackInfo ci) {
		this.fire = 2000000000;
	}
}
