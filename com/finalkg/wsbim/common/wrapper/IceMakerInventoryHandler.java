package com.finalkg.wsbim.common.wrapper;

import javax.annotation.Nonnull;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

public class IceMakerInventoryHandler implements IItemHandlerModifiable {

	 private final IInventory inv;

	    public IceMakerInventoryHandler(IInventory inv)
	    {
	        this.inv = inv;
	    }

	    @Override
	    public boolean equals(Object o)
	    {
	        if (this == o)
	            return true;
	        if (o == null || getClass() != o.getClass())
	            return false;

	        IceMakerInventoryHandler that = (IceMakerInventoryHandler) o;

	        return getInv().equals(that.getInv());

	    }

	    @Override
	    public int hashCode()
	    {
	        return getInv().hashCode();
	    }

	    @Override
	    public int getSlots()
	    {
	        return getInv().getSizeInventory();
	    }

	    @Override
	    @Nonnull
	    public ItemStack getStackInSlot(int slot)
	    {
	        return getInv().getStackInSlot(slot);
	    }

	    @Override
	    @Nonnull
	    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
	    {
	        if (stack.isEmpty())
	            return ItemStack.EMPTY;

	        ItemStack stackInSlot = getInv().getStackInSlot(slot);

	        int m;
	        if (!stackInSlot.isEmpty())
	        {
	            if (stackInSlot.getCount() >= Math.min(stackInSlot.getMaxStackSize(), getSlotLimit(slot)))
	                return stack;

	            if (!ItemHandlerHelper.canItemStacksStack(stack, stackInSlot))
	                return stack;

	            if (!getInv().isItemValidForSlot(slot, stack))
	                return stack;

	            m = Math.min(stack.getMaxStackSize(), getSlotLimit(slot)) - stackInSlot.getCount();

	            if (stack.getCount() <= m)
	            {
	                if (!simulate)
	                {
	                    ItemStack copy = stack.copy();
	                    copy.grow(stackInSlot.getCount());
	                    getInv().setInventorySlotContents(slot, copy);
	                    getInv().markDirty();
	                }

	                return ItemStack.EMPTY;
	            }
	            else
	            {
	                // copy the stack to not modify the original one
	                stack = stack.copy();
	                if (!simulate)
	                {
	                    ItemStack copy = stack.splitStack(m);
	                    copy.grow(stackInSlot.getCount());
	                    getInv().setInventorySlotContents(slot, copy);
	                    getInv().markDirty();
	                    return stack;
	                }
	                else
	                {
	                    stack.shrink(m);
	                    return stack;
	                }
	            }
	        }
	        else
	        {
	            if (!getInv().isItemValidForSlot(slot, stack))
	                return stack;

	            m = Math.min(stack.getMaxStackSize(), getSlotLimit(slot));
	            if (m < stack.getCount())
	            {
	                // copy the stack to not modify the original one
	                stack = stack.copy();
	                if (!simulate)
	                {
	                    getInv().setInventorySlotContents(slot, stack.splitStack(m));
	                    getInv().markDirty();
	                    return stack;
	                }
	                else
	                {
	                    stack.shrink(m);
	                    return stack;
	                }
	            }
	            else
	            {
	                if (!simulate)
	                {
	                    getInv().setInventorySlotContents(slot, stack);
	                    getInv().markDirty();
	                }
	                return ItemStack.EMPTY;
	            }
	        }

	    }

	    @Override
	    @Nonnull
	    public ItemStack extractItem(int slot, int amount, boolean simulate)
	    {
	        if (amount == 0)
	            return ItemStack.EMPTY;

	      	        
	        ItemStack stackInSlot = getInv().getStackInSlot(slot);
	        
	        
	        //THIS STOPS AUTOMATION FROM TAKING FROM THE FIRST TWO SLOTS, UNLESS THE FIRST SLOT HAS A BUCKET
	        
	        if((slot == 0 || slot == 1) && !(slot == 0 && !stackInSlot.isEmpty() && stackInSlot.isItemEqual(new ItemStack(Items.BUCKET)))) {
	        	return ItemStack.EMPTY;
	        }

	        if (stackInSlot.isEmpty())
	            return ItemStack.EMPTY;

	        if (simulate)
	        {
	            if (stackInSlot.getCount() < amount)
	            {
	                return stackInSlot.copy();
	            }
	            else
	            {
	                ItemStack copy = stackInSlot.copy();
	                copy.setCount(amount);
	                return copy;
	            }
	        }
	        else
	        {
	            int m = Math.min(stackInSlot.getCount(), amount);

	            ItemStack decrStackSize = getInv().decrStackSize(slot, m);
	            getInv().markDirty();
	            return decrStackSize;
	        }
	    }

	    @Override
	    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
	    {
	        getInv().setInventorySlotContents(slot, stack);
	    }

	    @Override
	    public int getSlotLimit(int slot)
	    {
	        return getInv().getInventoryStackLimit();
	    }

	    @Override
	    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
	    {
	        return getInv().isItemValidForSlot(slot, stack);
	    }

	    public IInventory getInv()
	    {
	        return inv;
	    }

}
