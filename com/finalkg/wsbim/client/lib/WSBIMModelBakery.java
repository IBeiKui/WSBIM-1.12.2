package com.finalkg.wsbim.client.lib;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.render.block.RenderMixedMetalChestModel;
import com.finalkg.wsbim.client.render.block.RenderObsidianChestModel;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = WSBIM.MODID, value = Side.CLIENT)
public class WSBIMModelBakery {
	
	private static final ModelResourceLocation MODEL_RESOURCE_OBSIDIAN_CHEST = new ModelResourceLocation(WSBIM.blockObsidianChest.getRegistryName(), "inventory");
	private static final ModelResourceLocation MODEL_RESOURCE_MIXED_METAL_CHEST = new ModelResourceLocation(WSBIM.blockMixedMetalChest.getRegistryName(), "inventory");

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
		IBakedModel obsidianChestModel = event.getModelRegistry().func_82594_a(MODEL_RESOURCE_OBSIDIAN_CHEST);
		event.getModelRegistry().func_82595_a(MODEL_RESOURCE_OBSIDIAN_CHEST, new RenderObsidianChestModel(obsidianChestModel));
		IBakedModel mixedMetalChestModel = event.getModelRegistry().func_82594_a(MODEL_RESOURCE_MIXED_METAL_CHEST);
		event.getModelRegistry().func_82595_a(MODEL_RESOURCE_MIXED_METAL_CHEST, new RenderMixedMetalChestModel(mixedMetalChestModel));
	}
}
