package tfar.impossiblemode.mixin;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {

	@ModifyConstant(method = "onEntityUpdate",constant = @Constant(intValue = 300))
	private int shortBreath(int old) {
		return 40;
	}

	@ModifyArg(method = "onEntityUpdate",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z",ordinal = 2))
	private float instantDrown(float amount){
		return Float.MAX_VALUE;
	}
}
