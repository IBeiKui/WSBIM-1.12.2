package com.finalkg.wsbim.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockMixedMetalOre extends Block {

	public BlockMixedMetalOre(Material materialIn) {
		super(materialIn, MapColor.STONE);
		setSoundType(SoundType.STONE);
	}
}
