package com.finalkg.wsbim.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerNormalCrafting extends Container
{
    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private final World world;
    private final EntityPlayer player;

    public ContainerNormalCrafting(InventoryPlayer playerInventory, World worldIn){
        this.world = worldIn;
        this.player = playerInventory.field_70458_d;
        this.func_75146_a(new SlotCrafting(playerInventory.field_70458_d, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.func_75146_a(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.func_75146_a(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.func_75146_a(new Slot(playerInventory, l, 8 + l * 18, 142));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void func_75130_a(IInventory inventoryIn)
    {
        this.func_192389_a(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    /**
     * Called when the container is closed.
     */
    public void func_75134_a(EntityPlayer playerIn)
    {
        super.func_75134_a(playerIn);

        if (!this.world.field_72995_K)
        {
            this.func_193327_a(playerIn, this.world, this.craftMatrix);
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean func_75145_c(EntityPlayer playerIn){return true;}

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

            if (index == 0)
            {
                itemstack1.func_77973_b().func_77622_d(itemstack1, this.world, playerIn);

                if (!this.func_75135_a(itemstack1, 10, 46, true))
                {
                    return ItemStack.field_190927_a;
                }

                slot.func_75220_a(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.func_75135_a(itemstack1, 37, 46, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.func_75135_a(itemstack1, 10, 37, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.func_75135_a(itemstack1, 10, 46, false))
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

            ItemStack itemstack2 = slot.func_190901_a(playerIn, itemstack1);

            if (index == 0)
            {
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
