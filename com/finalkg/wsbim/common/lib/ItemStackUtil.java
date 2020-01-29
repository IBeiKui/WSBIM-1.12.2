package com.finalkg.wsbim.common.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

/**
 * Alternative for net.minecraft.inventory.ItemStackHelper.class
 * @author ARlO-DESKTOP
 *
 */
public class ItemStackUtil {
	
	
	public static void loadAllItems(NBTTagCompound tag, ItemStack[] stacks)
    {
        NBTTagList nbttaglist = tag.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < stacks.length)
            {
                stacks[j] = new ItemStack(nbttagcompound);
            }
        }
    }
	
    public static NBTTagCompound saveAllItems(NBTTagCompound tag, ItemStack[] stacks, boolean saveEmpty)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < stacks.length; ++i)
        {
            ItemStack itemstack = stacks[i];

            if (!itemstack.isEmpty())
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        if (!nbttaglist.hasNoTags() || saveEmpty)
        {
            tag.setTag("Items", nbttaglist);
        }

        return tag;
    }
    
    public static NBTTagCompound saveAllItems(NBTTagCompound tag, ItemStack[] list)
    {
        return saveAllItems(tag, list, true);
    }
}
