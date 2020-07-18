package tfar.impossiblemode.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonMixin extends MonsterEntity {

	protected AbstractSkeletonMixin(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Redirect(method = "livingTick",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/monster/AbstractSkeletonEntity;isInDaylight()Z"))
	private boolean noBurn(AbstractSkeletonEntity abstractSkeletonEntity) {
		return false;
	}

	@ModifyConstant(method = "attackEntityWithRangedAttack",constant = @Constant(floatValue = 1.6f))
	private float doubleDamage(float old) {
		return old * 2;
	}

	@Inject(method = "fireArrow",at = @At(value = "RETURN"))
	private void flameArrows(ItemStack arrowStack, float distanceFactor, CallbackInfoReturnable<AbstractArrowEntity> cir) {
		cir.getReturnValue().setFire(100);
	}
}
