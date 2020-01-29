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
        NBTTagList nbttaglist = tag.func_150295_c("Items", 10);

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 255;

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

            if (!itemstack.func_190926_b())
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.func_74774_a("Slot", (byte)i);
                itemstack.func_77955_b(nbttagcompound);
                nbttaglist.func_74742_a(nbttagcompound);
            }
        }

        if (!nbttaglist.func_82582_d() || saveEmpty)
        {
            tag.func_74782_a("Items", nbttaglist);
        }

        return tag;
    }
    
    public static NBTTagCompound saveAllItems(NBTTagCompound tag, ItemStack[] list)
    {
        return saveAllItems(tag, list, true);
    }
}
