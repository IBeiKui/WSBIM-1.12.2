package com.finalkg.wsbim.common.item;

import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.lib.IChestItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemChestItem extends Item implements IChestItem{

	private final EnumChestItem chestItem;
	
	public ItemChestItem(EnumChestItem type) {
		this.chestItem = type;
		this.func_77656_e(0);
		this.func_77625_d(1);
	}
	/**Returns the chest item type for this item*/
	public EnumChestItem getType() {return this.chestItem;}
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(worldIn.field_72995_K){
			ContainerUtil.openGui(getGuiHandlerCall());
			return super.func_77659_a(worldIn, playerIn, handIn);
    	}
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    /**Returns the integer that should be sent to the gui handler when the item is right clicked*/
    public int getGuiHandlerCall() {return 100;}

	public boolean canChangeColors() {
		return false;
	}
	
	public int getDefaultColor() {
		return ColorHelper.WHITE;
	}
}
