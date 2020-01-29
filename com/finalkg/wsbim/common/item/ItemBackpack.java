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

	public static final ItemArmor.ArmorMaterial BACKPACK_ARMOR_MATERIAL = EnumHelper.addArmorMaterial(WSBIM.MODID+":"+"backpack", WSBIM.MODID+":"+"backpack", 10000000, new int[]{0,0,0,0}, 0, SoundEvents.field_187728_s, 0F);;
	private final EnumChestItem backpackType;
	private int defaultColor = 10832645;
	
	public ItemBackpack(EnumChestItem backpackType) {
		super(BACKPACK_ARMOR_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		this.backpackType = backpackType;
		this.func_77656_e(0);
	}
	
	public ItemBackpack(EnumChestItem backpackType, int default_color) {
		super(BACKPACK_ARMOR_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		this.backpackType = backpackType;
		this.defaultColor = default_color;
		this.func_77656_e(0);
	}

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(worldIn.field_72995_K){
			ContainerUtil.openGui(getGuiHandlerCall());
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.func_184586_b(handIn));
    	}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.func_184586_b(handIn));
    }
    
    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean func_82816_b_(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.func_77978_p();
    	return nbttagcompound != null && nbttagcompound.func_150297_b("display", 10) ? nbttagcompound.func_74775_l("display").func_150297_b("color", 3) : false;
    }
	
    /**
     * Return the color for the specified armor ItemStack.
     */
    public int func_82814_b(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.func_77978_p();
        if(nbttagcompound != null){
        	NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
            if(nbttagcompound1 != null && nbttagcompound1.func_150297_b("color", 3)){
            	return nbttagcompound1.func_74762_e("color");
            }
        }
        return /**10511680*/ this.defaultColor;
    }
    
    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void func_82815_c(ItemStack stack){
    	NBTTagCompound nbttagcompound = stack.func_77978_p();
        if(nbttagcompound != null){
        	NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
            if(nbttagcompound1.func_74764_b("color")){
            	nbttagcompound1.func_82580_o("color");
            }
        }
    }

    /**
     * Sets the color of the specified armor ItemStack
     */
    public void func_82813_b(ItemStack stack, int color){
    	NBTTagCompound nbttagcompound = stack.func_77978_p();
        if(nbttagcompound == null){
        	nbttagcompound = new NBTTagCompound();
        	stack.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

        if(!nbttagcompound.func_150297_b("display", 10)){
        	nbttagcompound.func_74782_a("display", nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", color);
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
