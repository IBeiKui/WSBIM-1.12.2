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
    	this.player = playerInventory.field_70458_d;
    	this.func_75146_a(new SlotCrafting(player, this.craftMatrix, this.craftResult, 0, (176 / 2 - (88 / 2) + 64), 36));
		for (int i = 0; i < 2; ++i){
			for (int j = 0; j < 2; ++j){
				this.func_75146_a(new Slot(this.craftMatrix, j + i * 2, (176 / 2 - (88 / 2) + 8) + j * 18, 26 + i * 18));
	        }
	    }
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 9; ++j){
				this.func_75146_a(new Slot(player.field_71071_by, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i){
			this.func_75146_a(new Slot(player.field_71071_by, i, 8 + i * 18, 142));
		}
		this.func_75130_a(this.craftMatrix);
    }
	
    public void func_75130_a(IInventory inventoryIn){
        this.func_192389_a(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    public void func_75134_a(EntityPlayer playerIn){
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K){
            this.func_193327_a(playerIn, this.world, this.craftMatrix);
        }
    }
    
	@Override
	public boolean func_75145_c(EntityPlayer playerIn) {
		return true;
	}
    public ItemStack func_82846_b(EntityPlayer playerIn, int index){
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = this.field_75151_b.get(index);
        if(slot != null && slot.func_75216_d()){
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index == 0){
                itemstack1.func_77973_b().func_77622_d(itemstack1, this.world, playerIn);
                if (!this.func_75135_a(itemstack1, 5, 41, true)){
                    return ItemStack.field_190927_a;
                }
                slot.func_75220_a(itemstack1, itemstack);
            }
            else if (index >= 5 && index < 32){
                if(!this.func_75135_a(itemstack1, 32, 41, false)){
                    return ItemStack.field_190927_a;
                }
            }
            else if (index >= 32 && index < 41){
                if (!this.func_75135_a(itemstack1, 5, 32, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.func_75135_a(itemstack1, 5, 41, false)){
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190926_b()){
                slot.func_75215_d(ItemStack.field_190927_a);
            }
            else{
                slot.func_75218_e();
            }
            if (itemstack1.func_190916_E() == itemstack.func_190916_E()){
                return ItemStack.field_190927_a;
            }
            ItemStack itemstack2 = slot.func_190901_a(playerIn, itemstack1);
            if (index == 0){
                playerIn.func_71019_a(itemstack2, false);
            }
        }
        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean func_94530_a(ItemStack stack, Slot slotIn)
    {
        return slotIn.field_75224_c != this.craftResult && super.func_94530_a(stack, slotIn);
    }
}
