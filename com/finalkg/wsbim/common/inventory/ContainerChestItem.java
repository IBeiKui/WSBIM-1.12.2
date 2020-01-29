package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.inventory.slot.SlotNoTake;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.lib.IChestItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChestItem extends Container{

	private final IChestItem itemChestItem;
	private final EnumChestItem chestItemType;
	private final IInventory chestItemInventory;
	private final ItemStack chestItemStack;
	
	public ContainerChestItem(EntityPlayer player, ItemStack itemChestStack) {
		this.itemChestItem = (IChestItem)itemChestStack.getItem();
		this.chestItemStack = itemChestStack;
		this.chestItemType = itemChestItem.getType();
		this.chestItemInventory = new InventoryChestItem(itemChestStack, itemChestItem, player);
		this.chestItemInventory.openInventory(player);
		int rows = chestItemType.getNumRows();
		int columns = chestItemType.getNumColumns();
		int rows_start = chestItemType.getRowRenderStart();
		int columns_start = chestItemType.getColumnRenderStart();
		int player_inv_rows_start = chestItemType.getInventoryPlayerRowStart();
		int player_inv_columns_start = chestItemType.getInventoryPlayerColumnStart();
		int chestItemIndex = ContainerUtil.getItemStackIndexInPlayerInventory(player.inventory, itemChestStack);
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<columns; j++){
				this.addSlotToContainer(new Slot(this.chestItemInventory,j + i * columns, rows_start+j*18, columns_start+i*18));
			}
		}
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer((chestItemIndex != -1 && chestItemIndex == j + i * 9 + 9) ? new SlotNoTake(player.inventory, j + i * 9 + 9, player_inv_rows_start + j * 18,  player_inv_columns_start + i * 18) : new Slot(player.inventory, j + i * 9 + 9, player_inv_rows_start + j * 18,  player_inv_columns_start + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i){
            this.addSlotToContainer((chestItemIndex != -1 && chestItemIndex == i) ? new SlotNoTake(player.inventory, i, player_inv_rows_start + i * 18,  player_inv_columns_start + 58) : new Slot(player.inventory, i, player_inv_rows_start + i * 18,  player_inv_columns_start + 58));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer p){
		super.onContainerClosed(p);
		chestItemInventory.closeInventory(p);
	}
	
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(itemstack1.equals(chestItemStack)) return ItemStack.EMPTY;
            if (index < this.chestItemType.getInventorySize()){
                if (!this.mergeItemStack(itemstack1, this.chestItemType.getInventorySize(), this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.chestItemType.getInventorySize(), false)){
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
