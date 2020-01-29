package com.finalkg.wsbim.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockLeather extends Block {

	public BlockLeather() {
		super(Material.CLOTH, MapColor.BROWN);
		this.setSoundType(SoundType.CLOTH);
	}

}
