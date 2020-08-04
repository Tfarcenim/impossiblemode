package tfar.impossiblemode.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpiderEntity.class)
public class EntitySpiderMixin extends MonsterEntity {

	protected EntitySpiderMixin(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Inject(method = "func_234305_eI_",at = @At("RETURN"))
	private static void fastSpooder(CallbackInfoReturnable<AttributeModifierMap.MutableAttribute> cir){
		cir.getReturnValue().createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.5);
	}
}
