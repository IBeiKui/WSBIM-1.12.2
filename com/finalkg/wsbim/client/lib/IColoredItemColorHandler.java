package com.finalkg.wsbim.client.lib;

import com.finalkg.wsbim.common.lib.IColoredItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;


import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class IColoredItemColorHandler implements IItemColor {

	@Override
	public int func_186726_a(ItemStack stack, int tintIndex) {
		if(stack !=null || stack != ItemStack.field_190927_a) {
			if(stack.func_77973_b() instanceof IColoredItem) {
				IColoredItem backpack = (IColoredItem)stack.func_77973_b();
				return backpack.getColor(stack);
			}
		}
		return 16777215;
	}

}
