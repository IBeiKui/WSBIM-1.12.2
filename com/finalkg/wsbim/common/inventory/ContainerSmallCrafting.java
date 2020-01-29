package com.finalkg.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSmallCrafting extends Container {

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    
    private EntityPlayer player; 
    private World world;
    
    public ContainerSmallCrafting(InventoryPlayer playerInventory, World worldIn) {
    	this.world = worldIn;
    	this.player = playerInventory.player;
    	this.addSlotToContainer(new SlotCrafting(player, this.craftMatrix, this.craftResult, 0, (176 / 2 - (88 / 2) + 64), 36));
		for (int i = 0; i < 2; ++i){
			for (int j = 0; j < 2; ++j){
				this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, (176 / 2 - (88 / 2) + 8) + j * 18, 26 + i * 18));
	        }
	    }
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 9; ++j){
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i){
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
		this.onCraftMatrixChanged(this.craftMatrix);
    }
	
    public void onCraftMatrixChanged(IInventory inventoryIn){
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    public void onContainerClosed(EntityPlayer playerIn){
        super.onContainerClosed(playerIn);
        if (!this.world.isRemote){
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }
    }
    
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0){
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);
                if (!this.mergeItemStack(itemstack1, 5, 41, true)){
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 5 && index < 32){
                if(!this.mergeItemStack(itemstack1, 32, 41, false)){
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 32 && index < 41){
                if (!this.mergeItemStack(itemstack1, 5, 32, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 5, 41, false)){
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()){
                return ItemStack.EMPTY;
            }
            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0){
                playerIn.dropItem(itemstack2, false);
            }
        }
        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}
