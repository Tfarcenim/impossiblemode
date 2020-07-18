package tfar.impossiblemode.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
abstract class EntityLivingMixin extends LivingEntity {

	protected EntityLivingMixin(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Inject(method = "onInitialSpawn", at = @At("RETURN"))
	private void alwaysEquip(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag, CallbackInfoReturnable<ILivingEntityData> cir) {
		if ((Object) this instanceof ZombieEntity) {
			int i = this.rand.nextInt(2);

			++i;

			++i;

			++i;

			for (EquipmentSlotType entityequipmentslot : EquipmentSlotType.values()) {
				if (entityequipmentslot.getSlotType() == EquipmentSlotType.Group.ARMOR) {
					ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

					if (itemstack.isEmpty()) {
						Item item = MobEntity.getArmorByChance(entityequipmentslot, i);

						if (item != null) {
							this.setItemStackToSlot(entityequipmentslot, new ItemStack(item));
						}
					}
				}
			}
		}
	}
}
