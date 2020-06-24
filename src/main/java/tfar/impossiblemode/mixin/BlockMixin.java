package tfar.impossiblemode.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.impossiblemode.ExampleMod;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "updateTick",at = @At("RETURN"))
	private void burnOutTorchesPart2(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
		Block block = (Block)(Object)this;
		if (block == Blocks.TORCH) {
			worldIn.setBlockState(pos, ExampleMod.unlit_torch.getDefaultState()
							.withProperty(BlockTorch.FACING,state.getValue(BlockTorch.FACING)));
		}
	}
	@Inject(method = "onEntityWalk",at = @At("RETURN"))
	private void wee(World worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci){
		Block block = (Block)(Object)this;
		if (block == Blocks.ICE) {
			entityIn.motionY = 5;
		} else if (block == Blocks.PACKED_ICE){
			entityIn.motionY = 10;
		}
	}
}
