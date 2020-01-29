package com.finalkg.wsbim.common.lib;

import net.minecraft.item.ItemStack;

/**Interface for use with colored items. Made to be called in the item class*/
public interface IColoredItem{
	/**Can this item have multiple colors? will add the color customization menu to the GUI if applicable*/
	public abstract boolean canChangeColors();
	/**Gets the default color of this chest item, USE ColorHelper.WHITE if your item will not have a default color*/
	public abstract int getDefaultColor();
	/**Gets the color for the itemstack. Return getDefaultColor() or -1 if it does not have a color*/
	public abstract int getColor(ItemStack colorStack);
	/**Sets the color for the chest itemstack*/
	public abstract void setColor(ItemStack colorStack, int color);
	/**Remove the color for the chest itemstack*/
	public abstract void removeColor(ItemStack colorStack);
	/**Returns true if the colored itemstack has a color. Use NBT parsing for all of these methods. See ItemArmor for examples.*/
	public abstract boolean hasColor(ItemStack colorStack);
}
