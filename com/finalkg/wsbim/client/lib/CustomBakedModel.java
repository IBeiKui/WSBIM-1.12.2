package com.finalkg.wsbim.client.lib;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;


import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public abstract class CustomBakedModel implements IBakedModel {

	private final IBakedModel model;

	public CustomBakedModel(IBakedModel model) {
		this.model = model;
	}

	@Override
	public List<BakedQuad> func_188616_a(IBlockState state, EnumFacing side, long rand) {
		return model.func_188616_a(state, side, rand);
	}

	@Override
	public boolean func_177555_b() {
		return model.func_177555_b();
	}

	@Override
	public boolean func_177556_c() {
		return model.func_177556_c();
	}

	@Override
	public boolean func_188618_c() {
		render(model);
		return true;
	}

	public abstract void render(IBakedModel model);

	@Override
	public TextureAtlasSprite func_177554_e() {
		return model.func_177554_e();
	}

	@Override
	@Deprecated
	public ItemCameraTransforms func_177552_f() {
		return model.func_177552_f();
	}

	@Override
	public ItemOverrideList func_188617_f() {
		return model.func_188617_f();
	}
}
