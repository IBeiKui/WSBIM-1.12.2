package com.finalkg.wsbim.common.lib;

/**Interface for use with chest items. Must extend an inheritance of net.minecraft.item.Item*/
public interface IChestItem {
	 /**Returns the integer that should be sent to the gui handler when the item is right clicked*/
	public abstract int getGuiHandlerCall();
	/**Returns the chest item type for this item*/
	public abstract EnumChestItem getType();
	//TODO: rewrite interface to have IChestItemColored, make bag accessable from armor slot too
}
