package com.finalkg.wsbim.common.inventory;

import com.finalkg.wsbim.common.inventory.slot.SlotNoInput;
import com.finalkg.wsbim.common.inventory.slot.SlotPowerItem;
import com.finalkg.wsbim.common.inventory.slot.SlotWaterBucket;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerIceMaker extends Container {
	
	public final TileEntityIceMaker tileEntity;
	
	/** Current updated fuel time remaining*/
	public int fuelTime = 0;
	/** Current updated water time remaining*/
	public int waterTime = 0;
	/** Current updated progress time*/
	public int progressTime = 0;

	public ContainerIceMaker(InventoryPlayer playerInv, TileEntityIceMaker tile) {
		this.tileEntity = tile;
		this.func_75146_a(new SlotWaterBucket(tile, tile.WATER_INPUT_SLOT, 12, 84));
		this.func_75146_a(new SlotPowerItem(tile, tile.POWER_INPUT_SLOT, 44, 84));
		for(int i = 0; i < tile.OUTPUT_INVENTORY_HEIGHT; i++) {
			for(int j = 0; j < tile.OUTPUT_INVENTORY_WIDTH; j++) {
				this.func_75146_a(new SlotNoInput(tile, 2 + j + i * tile.OUTPUT_INVENTORY_WIDTH, 75 + j * 18, 25 + i * 18));
			}
		}
		//Player inventory slots
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.func_75146_a(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k){
            this.func_75146_a(new Slot(playerInv, k, 8 + k * 18, 185));
        }
	}

    public void func_75132_a(IContainerListener listener){
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.tileEntity);
    }
    
    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void func_75142_b(){
        super.func_75142_b();

        for (int i = 0; i < this.field_75149_d.size(); ++i){
            IContainerListener icontainerlistener = this.field_75149_d.get(i);
            if (this.fuelTime != this.tileEntity.func_174887_a_(0)){
                icontainerlistener.func_71112_a(this, 0, this.tileEntity.func_174887_a_(0));
            }

            if (this.waterTime != this.tileEntity.func_174887_a_(1)){
                icontainerlistener.func_71112_a(this, 1, this.tileEntity.func_174887_a_(1));
            }

            if (this.progressTime != this.tileEntity.func_174887_a_(2)){
                icontainerlistener.func_71112_a(this, 2, this.tileEntity.func_174887_a_(2));
            }
        }

        this.fuelTime = this.tileEntity.func_174887_a_(0);
        this.waterTime = this.tileEntity.func_174887_a_(1);
        this.progressTime = this.tileEntity.func_174887_a_(2);
    }
    @SideOnly(Side.CLIENT) @Override
    public void func_75137_b(int id, int data){
        this.tileEntity.func_174885_b(id, data);
    }
    

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack func_82846_b(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = this.field_75151_b.get(index);

        if (slot != null && slot.func_75216_d())
        {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();

            if (index >= 2 && index < 22)
            {
                if (!this.func_75135_a(itemstack1, 22, 58, true))
                {
                    return ItemStack.field_190927_a;
                }

                slot.func_75220_a(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (tileEntity.isItemWater(itemstack1))
                {
                    if (!this.func_75135_a(itemstack1, tileEntity.WATER_INPUT_SLOT, tileEntity.WATER_INPUT_SLOT+1, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (tileEntity.isItemPower(itemstack1))
                {
                    if (!this.func_75135_a(itemstack1, tileEntity.POWER_INPUT_SLOT, tileEntity.POWER_INPUT_SLOT+1, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 22 && index < 49)
                {
                    if (!this.func_75135_a(itemstack1, 49, 58, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 49 && index < 58 && !this.func_75135_a(itemstack1, 22, 49, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.func_75135_a(itemstack1, 22, 58, false))
            {
                return ItemStack.field_190927_a;
            }

            if (itemstack1.func_190926_b())
            {
                slot.func_75215_d(ItemStack.field_190927_a);
            }
            else
            {
                slot.func_75218_e();
            }

            if (itemstack1.func_190916_E() == itemstack.func_190916_E())
            {
                return ItemStack.field_190927_a;
            }

            slot.func_190901_a(playerIn, itemstack1);
        }

        return itemstack;
    }
    
    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean func_75145_c(EntityPlayer playerIn){
        return this.tileEntity.func_70300_a(playerIn);
    }
}
