package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerObsidianChest extends Container {

	public final TileEntityObsidianChest tileEntity;
	
	public ContainerObsidianChest(InventoryPlayer playerInv, TileEntityObsidianChest tile) {
		this.tileEntity = tile;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(tile, j + i * 9, 8 + j * 18, 18 + i * 18));
			}
		}
		
		//Player inventory slots
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 9; ++j){
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 104 + i * 18));
		    }
		}

		for (int k = 0; k < 9; ++k){
			this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 162));
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
            if (index < 36){
                if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 36, false)){
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
