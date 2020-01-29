package com.finalkg.wsbim.client.lib;


import com.finalkg.wsbim.WSBIM;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = WSBIM.MODID, value = Side.CLIENT)
public class WSBIMColorRegistry {

	/**
	 * Register the {@link IBlockColor} handlers.
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerBlockColourHandlers(final ColorHandlerEvent.Block event) {
		final BlockColors blockColors = event.getBlockColors();
	}

	/**
	 * Register the {@link IItemColor} handlers
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void registerItemColourHandlers(final ColorHandlerEvent.Item event) {
		final BlockColors blockColors = event.getBlockColors();
		final ItemColors itemColors = event.getItemColors();
		IColoredItemColorHandler backpackColorHandler = new IColoredItemColorHandler();
		itemColors.registerItemColorHandler(backpackColorHandler, WSBIM.itemSmallBackpack);
		itemColors.registerItemColorHandler(backpackColorHandler, WSBIM.itemMediumBackpack);
		itemColors.registerItemColorHandler(backpackColorHandler, WSBIM.itemLargeBackpack);
		itemColors.registerItemColorHandler(backpackColorHandler, WSBIM.itemExtraLargeBackpack);
	}
}