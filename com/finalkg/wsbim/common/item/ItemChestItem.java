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

public class ItemChestItem extends Item implements IChestItem{

	private final EnumChestItem chestItem;
	
	public ItemChestItem(EnumChestItem type) {
		this.chestItem = type;
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	/**Returns the chest item type for this item*/
	public EnumChestItem getType() {return this.chestItem;}
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(worldIn.isRemote){
			ContainerUtil.openGui(getGuiHandlerCall());
			return super.onItemRightClick(worldIn, playerIn, handIn);
    	}
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
