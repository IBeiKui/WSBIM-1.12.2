package com.finalkg.wsbim.common.inventory.slot;

import com.finalkg.wsbim.common.tile.TileEntityIceMaker;

import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotWaterBucket extends Slot {

	private final TileEntityIceMaker tile;
	
	public SlotWaterBucket(TileEntityIceMaker inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.tile = inventoryIn;
	}
	
	
    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return tile.isItemWater(stack) || isBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isBucket(ItemStack stack)
    {
        return stack.getItem() == Items.BUCKET;
    }
}
