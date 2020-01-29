package com.finalkg.wsbim.common.item;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.lib.IChestItem;
import com.finalkg.wsbim.common.lib.IColoredItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBackpack extends ItemArmor implements IChestItem, IColoredItem {

	public static final ItemArmor.ArmorMaterial BACKPACK_ARMOR_MATERIAL = EnumHelper.addArmorMaterial(WSBIM.MODID+":"+"backpack", WSBIM.MODID+":"+"backpack", 10000000, new int[]{0,0,0,0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0F);;
	private final EnumChestItem backpackType;
	private int defaultColor = 10832645;
	
	public ItemBackpack(EnumChestItem backpackType) {
		super(BACKPACK_ARMOR_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		this.backpackType = backpackType;
		this.setMaxDamage(0);
	}
	
	public ItemBackpack(EnumChestItem backpackType, int default_color) {
		super(BACKPACK_ARMOR_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		this.backpackType = backpackType;
		this.defaultColor = default_color;
		this.setMaxDamage(0);
	}

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(worldIn.isRemote){
			ContainerUtil.openGui(getGuiHandlerCall());
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    	}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
    
    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.getTagCompound();
    	return nbttagcompound != null && nbttagcompound.hasKey("display", 10) ? nbttagcompound.getCompoundTag("display").hasKey("color", 3) : false;
    }
	
    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.getTagCompound();
        if(nbttagcompound != null){
        	NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if(nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3)){
            	return nbttagcompound1.getInteger("color");
            }
        }
        return /**10511680*/ this.defaultColor;
    }
    
    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void removeColor(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.getTagCompound();
        if(nbttagcompound != null){
        	NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
            if(nbttagcompound1.hasKey("color")){
            	nbttagcompound1.removeTag("color");
            }
        }
    }

    /**
     * Sets the color of the specified armor ItemStack
     */
    public void setColor(ItemStack stack, int color){
    	NBTTagCompound nbttagcompound = stack.getTagCompound();
        if(nbttagcompound == null){
        	nbttagcompound = new NBTTagCompound();
        	stack.setTagCompound(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if(!nbttagcompound.hasKey("display", 10)){
        	nbttagcompound.setTag("display", nbttagcompound1);
        }
        nbttagcompound1.setInteger("color", color);
    }
    
    public boolean hasOverlay(ItemStack stack){
        return true;
    }
    
	@Override
	public int getGuiHandlerCall() {
		return 100; //TODO add call 101 to GuiHandler
	}

	@Override
	public EnumChestItem getType() {
		return this.backpackType;
	}

	@Override
	public boolean canChangeColors() {
		return true;
	}

	public int getDefaultColor() {
		return this.defaultColor;
	}

}
