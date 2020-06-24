package tfar.impossiblemode.mixin;

import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin extends EntityMob {
	public AbstractSkeletonMixin(World worldIn) {
		super(worldIn);
	}

	@Redirect(method = "onLivingUpdate",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/monster/AbstractSkeleton;setFire(I)V"))
	private void noBurn(AbstractSkeleton abstractSkeleton, int seconds) {
	}

	@ModifyConstant(method = "attackEntityWithRangedAttack",constant = @Constant(floatValue = 1.6f))
	private float doubleDamage(float old) {
		return old * 2;
	}

	@Inject(method = "getArrow",at = @At(value = "RETURN"))
	private void flameArrows(float p_190726_1_, CallbackInfoReturnable<EntityArrow> cir) {
		cir.getReturnValue().setFire(100);
	}
}
