package com.finalkg.wsbim.common.tile;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerMixedMetalChest;
import com.finalkg.wsbim.common.inventory.ContainerObsidianChest;
import com.finalkg.wsbim.common.wrapper.NoInsertExtractInventoryHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;

public class TileEntityMixedMetalChest extends TileEntityLockable implements IInventory, ITickable {
	
	public NonNullList<ItemStack> INVENTORY = NonNullList.<ItemStack>withSize(104, ItemStack.EMPTY);
    public float lidAngle;
    /** The angle of the ender chest lid last tick */
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;

	@Override
	public void update() {
		if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.world.addBlockEvent(this.pos, WSBIM.blockMixedMetalChest, 1, this.numPlayersUsing);
        }

        this.prevLidAngle = this.lidAngle;
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        float f = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F){
            double d0 = (double)i + 0.5D;
            double d1 = (double)k + 0.5D;
            this.world.playSound((EntityPlayer)null, d0, (double)j + 0.5D, d1, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, (this.world.rand.nextFloat() * 0.1F + 0.9F));
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F){
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0){
                this.lidAngle += 0.1F;
            }
            else{
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F){
                this.lidAngle = 1.0F;
            }

            float f1 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F){
                double d3 = (double)i + 0.5D;
                double d2 = (double)k + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)j + 0.5D, d2, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, (this.world.rand.nextFloat() * 0.1F + 0.9F));
            }

            if (this.lidAngle < 0.0F){
                this.lidAngle = 0.0F;
            }
        }
	}

	@Override
    public boolean receiveClientEvent(int id, int type){
        if (id == 1){
            this.numPlayersUsing = type;
            return true;
        }
        else{
            return super.receiveClientEvent(id, type);
        }
    }
	
	 /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        this.updateContainingBlockInfo();
        super.invalidate();
    }
	
	@Override
	public int getSizeInventory() {
		return INVENTORY.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : INVENTORY) {
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.INVENTORY = NonNullList.<ItemStack>withSize(104, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, INVENTORY);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, INVENTORY);
        return compound;
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this){
            return false;
        }
        else{
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

	
	public void openChest(){
        ++this.numPlayersUsing;
        this.world.addBlockEvent(this.pos, WSBIM.blockMixedMetalChest, 1, this.numPlayersUsing);
    }

    public void closeChest(){
        --this.numPlayersUsing;
        this.world.addBlockEvent(this.pos, WSBIM.blockMixedMetalChest, 1, this.numPlayersUsing);
    }
    
	@Override
	public void openInventory(EntityPlayer player) {
		this.openChest();
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		this.closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(ItemStack stack : INVENTORY) {
			stack = ItemStack.EMPTY;
		}
	}

	@Override
	public String getName() {
		return "wsbim:container.mixedmetalchest";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerMixedMetalChest(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return this.getName();
	}

}
