package tfar.impossiblemode.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.impossiblemode.ImpossibleMode;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "onEntityWalk",at = @At("RETURN"))
	private void wee(World worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci){
		Block block = (Block)(Object)this;
		if (block == Blocks.ICE) {
			entityIn.setMotion(entityIn.getMotion().add(0,4,0));
		} else if (block == Blocks.PACKED_ICE){
			entityIn.setMotion(entityIn.getMotion().add(0,8,0));
		}
	}
}
