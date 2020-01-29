package com.finalkg.wsbim.common.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorMudPit  implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(random.nextInt(8) == 0){
			if(random.nextInt(6) == 0){
				int x = chunkX * 16 + random.nextInt(6);
				int z = chunkZ * 16 + random.nextInt(6);
				int y = world.getHeight(x, z);
				if(world.getBiome(new BlockPos(x, y, z)).topBlock.getMaterial() == Material.GRASS){
					WorldGenerator gen = new WorldGenMudPit();
					gen.generate(world, random, new BlockPos(x, y, z));
				}	
			}
		}
		
	}

}
