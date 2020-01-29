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
		this.INVENTORY = NonNullList.<ItemStack>withSize(type.getInventorySize(), ItemStack.EMPTY);
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return type.getInventorySize();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : INVENTORY) {
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return INVENTORY.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(INVENTORY, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(INVENTORY, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = INVENTORY.get(index);
        INVENTORY.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
	}

	@Override
	public int getInventoryStackLimit() {return 64;}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if(this.chestItemStack.hasTagCompound()){
			if(this.chestItemStack.getTagCompound().hasKey("Items")){
				this.readFromNBT(this.chestItemStack.getTagCompound());
			}
		}
		
		else{
			this.chestItemStack.setTagCompound(new NBTTagCompound());
		}
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		this.writeToNBT(this.chestItemStack.getTagCompound());
	}

	public void readFromNBT(NBTTagCompound compound){
        this.INVENTORY = NonNullList.<ItemStack>withSize(this.type.getInventorySize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, INVENTORY);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        ItemStackHelper.saveAllItems(compound, INVENTORY);
        return compound;
    }
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {return 0;}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {return 0;}

	@Override
	public void clear() {
		for(ItemStack stack : INVENTORY) {
			stack = ItemStack.EMPTY;
		}
	}

}
