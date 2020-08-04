package tfar.impossiblemode.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.impossiblemode.ImpossibleMode;

import java.util.Random;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
	@Inject(method = "tick",at = @At("RETURN"))
	private void burnOutTorchesPart2(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand, CallbackInfo ci) {
		Block block = (Block)(Object)this;
		if (block == Blocks.TORCH) {
			worldIn.setBlockState(pos, ImpossibleMode.unlit_torch.getDefaultState());
		}
	}
}
