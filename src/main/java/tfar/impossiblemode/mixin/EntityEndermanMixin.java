package tfar.impossiblemode.mixin;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin extends EntityMob {
	public EntityEndermanMixin(World worldIn) {
		super(worldIn);
	}

	@Inject(method = "shouldAttackPlayer",at = @At("RETURN"),cancellable = true)
	private void aggro(EntityPlayer player, CallbackInfoReturnable<Boolean> cir){
		if (cir.getReturnValue())return;
		if(this.getDistanceSq(player) < 4096)cir.setReturnValue(true);
	}
}
