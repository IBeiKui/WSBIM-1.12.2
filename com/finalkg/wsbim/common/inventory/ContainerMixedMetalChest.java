package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


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
				this.func_75146_a(new Slot(tile, j + i * 13, 12 + j * 18, 18 + i * 18));
			}
		}
		
		//Player inventory slots
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 9; ++j){
				this.func_75146_a(new Slot(playerInv, j + i * 9 + 9, 48 + j * 18, 176 + i * 18));
		    }
		}

		for (int k = 0; k < 9; ++k){
			this.func_75146_a(new Slot(playerInv, k, 48 + k * 18, 234));
		}
		tileEntity.func_174889_b(playerInv.field_70458_d);
	}
	
	 /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack func_82846_b(EntityPlayer playerIn, int index){
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = this.field_75151_b.get(index);

        if (slot != null && slot.func_75216_d()){
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index < 104){
                if (!this.func_75135_a(itemstack1, 104, this.field_75151_b.size(), true)){
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.func_75135_a(itemstack1, 0, 104, false)){
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190926_b()){
                slot.func_75215_d(ItemStack.field_190927_a);
            }
            else{
                slot.func_75218_e();
            }
        }

        return itemstack;
    }
    @Override
	public void func_75134_a(EntityPlayer player){
		this.tileEntity.func_174886_c(player);
	}
    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean func_75145_c(EntityPlayer playerIn){
        return this.tileEntity.func_70300_a(playerIn);
    }
}
