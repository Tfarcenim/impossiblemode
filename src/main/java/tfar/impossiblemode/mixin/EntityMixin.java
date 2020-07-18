package tfar.impossiblemode.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "getMaxAir",at = @At("HEAD"),cancellable = true)
	private void shortBreath(CallbackInfoReturnable<Integer> cir){
		if ((Object)this instanceof PlayerEntity)cir.setReturnValue(40);
	}
}
