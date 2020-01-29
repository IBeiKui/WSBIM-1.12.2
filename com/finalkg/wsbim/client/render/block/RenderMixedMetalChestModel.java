package com.finalkg.wsbim.client.render.block;

import com.finalkg.wsbim.client.lib.CustomBakedModel;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class RenderMixedMetalChestModel extends CustomBakedModel {

	
	public RenderMixedMetalChestModel(IBakedModel model) {
		super(model);
	}

	@Override
	public void render(IBakedModel model) {
		TileEntityRendererDispatcher.instance.render(new TileEntityMixedMetalChest(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}