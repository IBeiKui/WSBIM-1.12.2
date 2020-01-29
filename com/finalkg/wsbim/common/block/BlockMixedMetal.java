package com.finalkg.wsbim.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockMixedMetal extends Block {

	public BlockMixedMetal(Material materialIn) {
		super(materialIn, MapColor.MAGENTA);
		this.setSoundType(SoundType.METAL);
	}

}
