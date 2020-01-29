package com.finalkg.wsbim.common.inventory.slot;

import com.finalkg.wsbim.common.tile.TileEntityIceMaker;

import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPowerItem extends Slot {

	private final TileEntityIceMaker tile;
	
	public SlotPowerItem(TileEntityIceMaker inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.tile = inventoryIn;
	}
	
	
    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return tile.isItemPower(stack);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}
