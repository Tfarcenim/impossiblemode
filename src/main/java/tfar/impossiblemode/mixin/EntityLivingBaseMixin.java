package tfar.impossiblemode.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(LivingEntity.class)
public class EntityLivingBaseMixin {

	@ModifyArg(method = "baseTick",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/LivingEntity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z",ordinal = 2))
	private float instantDrown(float amount){
		return Float.MAX_VALUE;
	}
}
