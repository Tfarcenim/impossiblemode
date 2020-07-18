package tfar.impossiblemode.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpiderEntity.class)
public class EntitySpiderMixin extends MonsterEntity {

	protected EntitySpiderMixin(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Inject(method = "registerAttributes",at = @At("RETURN"))
	private void fastSpooder(CallbackInfo ci){
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5);
	}
}
