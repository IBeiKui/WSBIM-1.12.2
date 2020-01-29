package com.finalkg.wsbim.common.world.gen;

import java.util.Random;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * @author Jake
 *
 */
public class WorldGeneratorMixedMetalOre  implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			for(int i = 0; i < 2; i++){
				int x =  chunkX * 16 + random.nextInt(16);
				int y = random.nextInt(12);
				int z =  chunkZ * 16 + random.nextInt(16);
			
				WorldGenMinable gen = new WorldGenMinable(WSBIM.blockMixedMetalOre.getDefaultState(), 3);
				gen.generate(world, random, new BlockPos(x, y, z));
			}	
		}
	}
}
