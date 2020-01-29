package com.finalkg.wsbim.common.inventory.slot;

import com.finalkg.wsbim.common.tile.TileEntityIceMaker;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


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
    public boolean func_75214_a(ItemStack stack)
    {
        return tile.isItemWater(stack) || isBucket(stack);
    }

    public int func_178170_b(ItemStack stack)
    {
        return isBucket(stack) ? 1 : super.func_178170_b(stack);
    }

    public static boolean isBucket(ItemStack stack)
    {
        return stack.func_77973_b() == Items.field_151133_ar;
    }
}
