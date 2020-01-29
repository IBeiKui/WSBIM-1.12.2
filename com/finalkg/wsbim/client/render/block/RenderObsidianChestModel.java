package com.finalkg.wsbim.client.render.block;

import com.finalkg.wsbim.client.lib.CustomBakedModel;
import com.finalkg.wsbim.client.render.tileentity.TileEntityObsidianChestRenderer;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;


import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderObsidianChestModel extends CustomBakedModel {

	
	public RenderObsidianChestModel(IBakedModel model) {
		super(model);
	}

	@Override
	public void render(IBakedModel model) {
		TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileEntityObsidianChest(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}
