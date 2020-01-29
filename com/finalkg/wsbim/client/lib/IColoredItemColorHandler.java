package com.finalkg.wsbim.client.lib;

import com.finalkg.wsbim.common.lib.IColoredItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class IColoredItemColorHandler implements IItemColor {

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		if(stack !=null || stack != ItemStack.EMPTY) {
			if(stack.getItem() instanceof IColoredItem) {
				IColoredItem backpack = (IColoredItem)stack.getItem();
				return backpack.getItemColor(stack);
			}
		}
		return 16777215;
	}

}
