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
		this.itemChestItem = (IChestItem)itemChestStack.func_77973_b();
		this.chestItemStack = itemChestStack;
		this.chestItemType = itemChestItem.getType();
		this.chestItemInventory = new InventoryChestItem(itemChestStack, itemChestItem, player);
		this.chestItemInventory.func_174889_b(player);
		int rows = chestItemType.getNumRows();
		int columns = chestItemType.getNumColumns();
		int rows_start = chestItemType.getRowRenderStart();
		int columns_start = chestItemType.getColumnRenderStart();
		int player_inv_rows_start = chestItemType.getInventoryPlayerRowStart();
		int player_inv_columns_start = chestItemType.getInventoryPlayerColumnStart();
		int chestItemIndex = ContainerUtil.getItemStackIndexInPlayerInventory(player.field_71071_by, itemChestStack);
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<columns; j++){
				this.func_75146_a(new Slot(this.chestItemInventory,j + i * columns, rows_start+j*18, columns_start+i*18));
			}
		}
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.func_75146_a((chestItemIndex != -1 && chestItemIndex == j + i * 9 + 9) ? new SlotNoTake(player.field_71071_by, j + i * 9 + 9, player_inv_rows_start + j * 18,  player_inv_columns_start + i * 18) : new Slot(player.field_71071_by, j + i * 9 + 9, player_inv_rows_start + j * 18,  player_inv_columns_start + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i){
            this.func_75146_a((chestItemIndex != -1 && chestItemIndex == i) ? new SlotNoTake(player.field_71071_by, i, player_inv_rows_start + i * 18,  player_inv_columns_start + 58) : new Slot(player.field_71071_by, i, player_inv_rows_start + i * 18,  player_inv_columns_start + 58));
        }
	}

	@Override
	public boolean func_75145_c(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public void func_75134_a(EntityPlayer p){
		super.func_75134_a(p);
		chestItemInventory.func_174886_c(p);
	}
	
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack func_82846_b(EntityPlayer player, int index)
    {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot)this.field_75151_b.get(index);

        if (slot != null && slot.func_75216_d()){
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if(itemstack1.equals(chestItemStack)) return ItemStack.field_190927_a;
            if (index < this.chestItemType.getInventorySize()){
                if (!this.func_75135_a(itemstack1, this.chestItemType.getInventorySize(), this.field_75151_b.size(), true)){
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.func_75135_a(itemstack1, 0, this.chestItemType.getInventorySize(), false)){
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190916_E() == 0){
                slot.func_75215_d(ItemStack.field_190927_a);
            }
            else{
                slot.func_75218_e();
            }
        }
        return itemstack;
    }
}
