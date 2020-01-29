package com.finalkg.wsbim.common.tile;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.finalkg.wsbim.common.block.BlockIceMaker;
import com.finalkg.wsbim.common.inventory.ContainerIceMaker;
import com.finalkg.wsbim.common.sound.SoundsHandler;
import com.finalkg.wsbim.common.wrapper.IceMakerInventoryHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityIceMaker extends TileEntityLockable implements IInventory, ITickable {
	
	public static final ItemStack[] ACCEPTED_POWER_ITEMS = {new ItemStack(Items.REDSTONE), new ItemStack(Blocks.REDSTONE_BLOCK)};
	public static final ItemStack[] ACCEPTED_WATER_ITEMS = {new ItemStack(Items.WATER_BUCKET)};
	
	/** Maximum water capacity, in ticks*/
	public final int MAX_WATER_CAPACITY = 20000;
	/** Maximum fuel capacity, in ticks*/
	public final int MAX_POWER_CAPACITY = 10000;
	/**Number of inventory slots for the output of ice*/
	public final int OUTPUT_INVENTORY_WIDTH = 5;
	public final int OUTPUT_INVENTORY_HEIGHT = 4;
	public final int OUTPUT_INVENTORY_CAPACITY = OUTPUT_INVENTORY_WIDTH * OUTPUT_INVENTORY_HEIGHT;
	/**Slot index for water input*/
	public final int WATER_INPUT_SLOT = 0;
	/**Slot index for power input*/
	public final int POWER_INPUT_SLOT = 1;
	/** Number of ticks one water bucket lasts for*/
	public final int WATER_BUCKET_TICKS = 2500;
	public final int REDSTONE_POWER_TICKS = 1000;
	public final int REDSTONE_BLOCK_POWER_TICKS = REDSTONE_POWER_TICKS * 9;
	/** Current updated progress time*/
	public int progressTime = 0;
	/**Time, in ticks, it takes to process one ice block*/
	public final int PROCESS_TIME = 100;
	/** Current updated fuel time remaining*/
	public int fuelTime = 0;
	/** Current updated water time remaining*/
	public int waterTime = 0;
	/**Inventory stacks*/
	public NonNullList<ItemStack> INVENTORY = NonNullList.<ItemStack>withSize(OUTPUT_INVENTORY_CAPACITY+2, ItemStack.EMPTY);
	public final int MAX_ENERGY_RECIEVE = 20;
	private Random rand = new Random();
	private int soundLoop = 0;
	
	@Override
	public void update() {
		ItemStack waterStack = INVENTORY.get(WATER_INPUT_SLOT);
		ItemStack powerStack = INVENTORY.get(POWER_INPUT_SLOT);
		boolean flag = this.progressTime > 0;
		boolean flag1 = false;
		int xpos = this.pos.getX();
		int ypos = this.pos.getY();
		int zpos = this.pos.getZ();
		//Do ice processing
		if(!this.world.isRemote) {
			//Check to put water in
			if(!waterStack.isEmpty() && this.isItemWater(waterStack) && (waterTime <= (MAX_WATER_CAPACITY-WATER_BUCKET_TICKS))) {
				//Put water in
				waterTime +=WATER_BUCKET_TICKS;
				this.setInventorySlotContents(WATER_INPUT_SLOT, new ItemStack(Items.BUCKET));
			}
			//Check to put power in
			if(!powerStack.isEmpty() && this.isItemPower(powerStack)) {
				//Put power in if possible and delete item
				if(powerStack.isItemEqual(ACCEPTED_POWER_ITEMS[0])) {
					if(fuelTime <=(MAX_POWER_CAPACITY-REDSTONE_POWER_TICKS)) {
						//Put power in
						fuelTime +=REDSTONE_POWER_TICKS;
						flag1 = true;
						if (!powerStack.isEmpty()){
	                        Item item = powerStack.getItem();
	                        powerStack.shrink(1);
	                        if (powerStack.isEmpty()){
	                            ItemStack item1 = item.getContainerItem(powerStack);
	                            INVENTORY.set(POWER_INPUT_SLOT, item1);
	                        }
	                    }
					}
				}
				if(powerStack.isItemEqual(ACCEPTED_POWER_ITEMS[1])) {
					if(fuelTime <=(MAX_POWER_CAPACITY-REDSTONE_BLOCK_POWER_TICKS)) {
						//Put power in
						fuelTime +=REDSTONE_BLOCK_POWER_TICKS;
						flag1 = true;
						if (!powerStack.isEmpty()){
	                        Item item = powerStack.getItem();
	                        powerStack.shrink(1);
	                        if (powerStack.isEmpty()){
	                            ItemStack item1 = item.getContainerItem(powerStack);
	                            INVENTORY.set(POWER_INPUT_SLOT, item1);
	                        }
	                    }
					}
				}
			}
			boolean hasRoomForIce = this.hasRoomForIce();
			if(this.fuelTime > 0 && this.waterTime > 0 && this.progressTime > 0 && this.progressTime < PROCESS_TIME && hasRoomForIce) {
				this.fuelTime--;
				this.waterTime--;
				this.progressTime ++;
				this.soundLoop ++;
			}
			if(this.fuelTime > 0 && this.waterTime > 0 && this.progressTime == 0 && hasRoomForIce) {
				this.fuelTime--;
				this.waterTime--;
				this.progressTime ++;
				this.soundLoop ++;
				if(flag != true) {
					flag1 = true;
					BlockIceMaker.setState(true, world, pos);
				}
			}
			if(this.progressTime == PROCESS_TIME && hasRoomForIce) {
				this.addItemStackToNextAvailableInventorySlot(2, new ItemStack(Blocks.ICE));
				if(rand.nextInt(8) == 0)world.playSound((EntityPlayer)null, xpos+0.5D, ypos+0.5D, zpos+0.5D, SoundsHandler.BLOCK_ICE_MAKER_DISPENSE, SoundCategory.BLOCKS, 0.05F, (this.rand.nextFloat() / 10) + 0.9F);
				
			}
			if(this.fuelTime == 0 || this.waterTime == 0 || !hasRoomForIce || this.progressTime == PROCESS_TIME) {
				if((flag !=false && !(this.progressTime == PROCESS_TIME)) || !(this.progressTime == PROCESS_TIME)) {
					flag1 = true;
					BlockIceMaker.setState(false, world, pos);
					this.soundLoop = 0;
				}
				this.progressTime = 0;
			}	
			if(this.soundLoop > 0) {
				if(this.soundLoop == 1) world.playSound((EntityPlayer)null, xpos+0.5D, ypos+0.5D, zpos+0.5D, SoundsHandler.BLOCK_ICE_MAKER_LOOP, SoundCategory.BLOCKS, 0.1F, (this.rand.nextFloat() / 10) + 0.9F);
				if(this.soundLoop == 106) this.soundLoop = 0;
			}
		}
		
		//Update block state, tell minecraft to save this block's state
		if(flag1 != this.fuelTime > 0 && this.waterTime > 0 && this.progressTime > 0) {
			this.markDirty();
		}
	}
	
	public void addItemStackToNextAvailableInventorySlot(int startIndex, ItemStack stack) {
		for(int i = startIndex; i < INVENTORY.size(); i++) {
			ItemStack s = INVENTORY.get(i);
			if(s.isEmpty()) {
				INVENTORY.set(i, stack);
				return;
			}
			else {
				if(s.isItemEqual(stack) && s.getCount() < s.getMaxStackSize()) {
					INVENTORY.set(i, new ItemStack(stack.getItem(), s.getCount() + 1));
					return;
				}
			}
		}
	}
	
	public boolean hasRoomForIce() {
		boolean flag = false;
		for(int i = 2; i < INVENTORY.size(); i++) {
			ItemStack s = INVENTORY.get(i);
			if((!s.isEmpty() && s.getCount() != s.getMaxStackSize()) || s.isEmpty()) {
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public String getName() {
		return "container.blockicemaker";
	}

	@Override
	public boolean hasCustomName() {
		return false;
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
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == this.WATER_INPUT_SLOT) {
			if(this.isItemWater(stack)) return true;
			else return false;
		}
		else if(index == this.POWER_INPUT_SLOT) {
			if(this.isItemPower(stack)) return true;
			else return false;
		}
		else {
			return false;
		}
	}
	
	public boolean isItemWater(ItemStack stack) {
		for(ItemStack waterStack : this.ACCEPTED_WATER_ITEMS) {
			if(stack.isItemEqual(waterStack)) return true;
		}
		return false;
	}
	
	public boolean isItemPower(ItemStack stack) {
		for(ItemStack powerStack : this.ACCEPTED_POWER_ITEMS) {
			if(stack.isItemEqual(powerStack)) return true;
		}
		return false;
	}
	
	@Override
	public int getField(int id) {
        switch (id)
        {
            case 0:
                return this.fuelTime;
            case 1:
                return this.waterTime;
            case 2:
                return this.progressTime;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
        switch (id)
        {
            case 0:
                this.fuelTime = value;
                break;
            case 1:
                this.waterTime = value;
                break;
            case 2:
                this.progressTime = value;
                break;
        }
	}

	@Override
	public int getFieldCount() {
		return 3;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.INVENTORY = NonNullList.<ItemStack>withSize(OUTPUT_INVENTORY_CAPACITY+2, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, INVENTORY);
        this.fuelTime = compound.getInteger("FuelTime");
        this.progressTime = compound.getInteger("ProgressTime");
        this.waterTime = compound.getInteger("WaterTime");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("FuelTime", this.fuelTime);
        compound.setInteger("ProgressTime", this.progressTime);
        compound.setInteger("WaterTime", this.waterTime);
        ItemStackHelper.saveAllItems(compound, INVENTORY);
        return compound;
    }
	
	@Override
	public void clear() {
		for(ItemStack stack : INVENTORY) {
			stack = ItemStack.EMPTY;
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerIceMaker(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "wsbim:blockicemaker";
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return super.hasCapability(capability, facing);
		
	}
	
	@Override
	public <T>T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing){
            return super.getCapability(capability, facing);
	}

	/**
	 * Custom item handling for this block, for use with automation in buildcraft and other mods like redpower, etc.
	 */
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler()
    {
        return new IceMakerInventoryHandler(this);
    }


}
