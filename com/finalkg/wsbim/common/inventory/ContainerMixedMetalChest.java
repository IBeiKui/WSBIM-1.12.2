package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMixedMetalChest extends Container {

	public final TileEntityMixedMetalChest tileEntity;
	
	public ContainerMixedMetalChest(InventoryPlayer playerInv, TileEntityMixedMetalChest tile) {
		this.tileEntity = tile;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 13; j++) {
				this.addSlotToContainer(new Slot(tile, j + i * 13, 12 + j * 18, 18 + i * 18));
			}
		}
		
		//Player inventory slots
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 9; ++j){
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 48 + j * 18, 176 + i * 18));
		    }
		}

		for (int k = 0; k < 9; ++k){
			this.addSlotToContainer(new Slot(playerInv, k, 48 + k * 18, 234));
		}
		tileEntity.openInventory(playerInv.player);
	}
	
	 /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 104){
                if (!this.mergeItemStack(itemstack1, 104, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 104, false)){
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    @Override
	public void onContainerClosed(EntityPlayer player){
		this.tileEntity.closeInventory(player);
	}
    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return this.tileEntity.isUsableByPlayer(playerIn);
    }
}
