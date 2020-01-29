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
		this.addSlotToContainer(new SlotWaterBucket(tile, tile.WATER_INPUT_SLOT, 12, 84));
		this.addSlotToContainer(new SlotPowerItem(tile, tile.POWER_INPUT_SLOT, 44, 84));
		for(int i = 0; i < tile.OUTPUT_INVENTORY_HEIGHT; i++) {
			for(int j = 0; j < tile.OUTPUT_INVENTORY_WIDTH; j++) {
				this.addSlotToContainer(new SlotNoInput(tile, 2 + j + i * tile.OUTPUT_INVENTORY_WIDTH, 75 + j * 18, 25 + i * 18));
			}
		}
		//Player inventory slots
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k){
            this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 185));
        }
	}

    public void addListener(IContainerListener listener){
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }
    
    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i){
            IContainerListener icontainerlistener = this.listeners.get(i);
            if (this.fuelTime != this.tileEntity.getField(0)){
                icontainerlistener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
            }

            if (this.waterTime != this.tileEntity.getField(1)){
                icontainerlistener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
            }

            if (this.progressTime != this.tileEntity.getField(2)){
                icontainerlistener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
            }
        }

        this.fuelTime = this.tileEntity.getField(0);
        this.waterTime = this.tileEntity.getField(1);
        this.progressTime = this.tileEntity.getField(2);
    }
    @SideOnly(Side.CLIENT) @Override
    public void updateProgressBar(int id, int data){
        this.tileEntity.setField(id, data);
    }
    

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index >= 2 && index < 22)
            {
                if (!this.mergeItemStack(itemstack1, 22, 58, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (tileEntity.isItemWater(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, tileEntity.WATER_INPUT_SLOT, tileEntity.WATER_INPUT_SLOT+1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (tileEntity.isItemPower(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, tileEntity.POWER_INPUT_SLOT, tileEntity.POWER_INPUT_SLOT+1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 22 && index < 49)
                {
                    if (!this.mergeItemStack(itemstack1, 49, 58, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 49 && index < 58 && !this.mergeItemStack(itemstack1, 22, 49, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 22, 58, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
    
    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return this.tileEntity.isUsableByPlayer(playerIn);
    }
}
