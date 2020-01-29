package com.finalkg.wsbim.common.world.gen;

import java.util.Random;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
/**
 * Deprecated, creates too much world gen lag
 * @author finalkg
 *
 */
@Deprecated
public class WorldGeneratorWetSand implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		for(int i = 0; i < 16; i++){
			for(int j = 45; j < 90; j++){
				for(int k = 0; k < 16; k++){
					int x1 = i + x;
					int y = j;
					int z1 = z + k;
					BlockPos blockInQuestion = new BlockPos(x1, y, z1);
					IBlockState blockState = world.getBlockState(blockInQuestion);
					if(blockState !=null && blockState.getBlock() != null){
						if(blockState.getBlock() == Blocks.SAND){
							Block pos1 = world.getBlockState(new BlockPos(x1+1, y, z1)).getBlock();
							Block pos2 = world.getBlockState(new BlockPos(x1-1, y, z1)).getBlock();
							Block pos3 = world.getBlockState(new BlockPos(x1, y, z1+1)).getBlock();
							Block pos4 = world.getBlockState(new BlockPos(x1, y, z1-1)).getBlock();
							if(this.isUnderWater(world, x1, y, z1, 1) ||(pos1 == Blocks.WATER || pos1 == Blocks.FLOWING_WATER)||(pos2 == Blocks.WATER || pos2 == Blocks.FLOWING_WATER)||(pos3 == Blocks.WATER || pos3 == Blocks.FLOWING_WATER)||(pos4 == Blocks.WATER || pos4 == Blocks.FLOWING_WATER)){
								world.setBlockState(blockInQuestion, WSBIM.blockWetSand.getDefaultState());
							}
						}
					}
				}
			}
		}
	}
	
	public boolean isUnderWater(World world, int x, int y, int z, int max_depth){
		for(int i = 0; i < max_depth; i++){
			if(world.getBlockState( new BlockPos(x, y + i, z)).getBlock() == Blocks.WATER || world.getBlockState( new BlockPos(x, y + i, z)).getBlock() == Blocks.FLOWING_WATER){
				return true;
			}
		}
		return false;
	}
}
