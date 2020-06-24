package tfar.impossiblemode.mixin;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySpider.class)
public class EntitySpiderMixin extends EntityMob {
	public EntitySpiderMixin(World worldIn) {
		super(worldIn);
	}

	@Inject(method = "applyEntityAttributes",at = @At("RETURN"))
	private void fastSpooder(CallbackInfo ci){
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5);
	}
}
