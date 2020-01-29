package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.lib.IChestItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class InventoryChestItem implements IInventory {
	
	private final ItemStack chestItemStack;
	private final IChestItem itemChestItem;
	private final EnumChestItem type;
	private final EntityPlayer player;
	
	public NonNullList<ItemStack> INVENTORY;
	
	public InventoryChestItem(ItemStack cIS, IChestItem iCI, EntityPlayer plr) {
		this.chestItemStack = cIS;
		this.itemChestItem = iCI;
		this.player = plr;
		this.type = this.itemChestItem.getType();
		this.INVENTORY = NonNullList.<ItemStack>func_191197_a(type.getInventorySize(), ItemStack.field_190927_a);
	}

	@Override
	public String func_70005_c_() {
		return "";
	}

	@Override
	public boolean func_145818_k_() {
		return false;
	}

	@Override
	public ITextComponent func_145748_c_() {
		return null;
	}

	@Override
	public int func_70302_i_() {
		return type.getInventorySize();
	}

	@Override
	public boolean func_191420_l() {
		for(ItemStack stack : INVENTORY) {
			if(!stack.func_190926_b()) return false;
		}
		return true;
	}

	@Override
	public ItemStack func_70301_a(int index) {
		return INVENTORY.get(index);
	}

	@Override
	public ItemStack func_70298_a(int index, int count) {
		return ItemStackHelper.func_188382_a(INVENTORY, index, count);
	}

	@Override
	public ItemStack func_70304_b(int index) {
		return ItemStackHelper.func_188383_a(INVENTORY, index);
	}

	@Override
	public void func_70299_a(int index, ItemStack stack) {
        ItemStack itemstack = INVENTORY.get(index);
        INVENTORY.set(index, stack);
        if (stack.func_190916_E() > this.func_70297_j_()){
            stack.func_190920_e(this.func_70297_j_());
        }
	}

	@Override
	public int func_70297_j_() {return 64;}

	@Override
	public void func_70296_d() {}

	@Override
	public boolean func_70300_a(EntityPlayer player) {
		return true;
	}

	@Override
	public void func_174889_b(EntityPlayer player) {
		if(this.chestItemStack.func_77942_o()){
			if(this.chestItemStack.func_77978_p().func_74764_b("Items")){
				this.readFromNBT(this.chestItemStack.func_77978_p());
			}
		}
		
		else{
			this.chestItemStack.func_77982_d(new NBTTagCompound());
		}
	}

	@Override
	public void func_174886_c(EntityPlayer player) {
		this.writeToNBT(this.chestItemStack.func_77978_p());
	}

	public void readFromNBT(NBTTagCompound compound){
        this.INVENTORY = NonNullList.<ItemStack>func_191197_a(this.type.getInventorySize(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, INVENTORY);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        ItemStackHelper.func_191282_a(compound, INVENTORY);
        return compound;
    }
	
	@Override
	public boolean func_94041_b(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int func_174887_a_(int id) {return 0;}

	@Override
	public void func_174885_b(int id, int value) {}

	@Override
	public int func_174890_g() {return 0;}

	@Override
	public void func_174888_l() {
		for(ItemStack stack : INVENTORY) {
			stack = ItemStack.field_190927_a;
		}
	}

}
