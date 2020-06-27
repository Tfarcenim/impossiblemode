package tfar.impossiblemode.mixin;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin extends EntityMob {
	public EntityEndermanMixin(World worldIn) {
		super(worldIn);
	}

	@Inject(method = "initEntityAI",at = @At("RETURN"))
	private void aggro(CallbackInfo ci){
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}
}
