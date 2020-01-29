package com.finalkg.wsbim.common.block;

import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeatherColored extends BlockColored {

	public BlockLeatherColored() {
		super(Material.CLOTH);
		this.setSoundType(SoundType.CLOTH);
	}
    
}
