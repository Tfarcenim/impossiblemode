package tfar.impossiblemode.mixin;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
abstract class EntityLivingMixin extends EntityLivingBase {

	public EntityLivingMixin(World worldIn) {
		super(worldIn);
	}

	@Inject(method = "onInitialSpawn", at = @At("RETURN"))
	private void alwaysEquip(DifficultyInstance difficulty, IEntityLivingData livingdata, CallbackInfoReturnable<IEntityLivingData> cir) {
		if ((Object) this instanceof EntityZombie) {
			int i = this.rand.nextInt(2);

			++i;

			++i;

			++i;

			for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
				if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
					ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

					if (itemstack.isEmpty()) {
						Item item = EntityLiving.getArmorByChance(entityequipmentslot, i);

						if (item != null) {
							this.setItemStackToSlot(entityequipmentslot, new ItemStack(item));
						}
					}
				}
			}
		}
	}
}
