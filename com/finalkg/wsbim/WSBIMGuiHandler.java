package com.finalkg.wsbim;

import com.finalkg.wsbim.client.gui.GuiChestItem;
import com.finalkg.wsbim.client.gui.GuiIceMaker;
import com.finalkg.wsbim.client.gui.GuiMixedMetalChest;
import com.finalkg.wsbim.client.gui.GuiNormalCrafting;
import com.finalkg.wsbim.client.gui.GuiObsidianChest;
import com.finalkg.wsbim.client.gui.GuiSmallCrafting;
import com.finalkg.wsbim.common.block.BlockIceMaker;
import com.finalkg.wsbim.common.block.BlockMixedMetalChest;
import com.finalkg.wsbim.common.block.BlockObsidianChest;
import com.finalkg.wsbim.common.inventory.ContainerChestItem;
import com.finalkg.wsbim.common.inventory.ContainerIceMaker;
import com.finalkg.wsbim.common.inventory.ContainerMixedMetalChest;
import com.finalkg.wsbim.common.inventory.ContainerNormalCrafting;
import com.finalkg.wsbim.common.inventory.ContainerObsidianChest;
import com.finalkg.wsbim.common.inventory.ContainerSmallCrafting;
import com.finalkg.wsbim.common.lib.IChestItem;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class WSBIMGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos blockPos = new BlockPos(x,y,z);
		TileEntity tile = world.getTileEntity(blockPos);
		Block block = world.getBlockState(blockPos).getBlock();
		if(ID == 100 && player.getHeldItemMainhand() != ItemStack.EMPTY && player.getHeldItemMainhand().getItem() instanceof IChestItem) return new ContainerChestItem(player, player.getHeldItemMainhand());
		if(ID == 101) {
			ItemStack stack =  (player.inventory.armorInventory.get(2) !=ItemStack.EMPTY && player.inventory.armorInventory.get(2).getItem() instanceof IChestItem? player.inventory.armorInventory.get(2) : ItemStack.EMPTY);
			if(stack != ItemStack.EMPTY) return new ContainerChestItem(player, stack);
		}
		if(ID == 102 && player.getHeldItemOffhand() != ItemStack.EMPTY && player.getHeldItemOffhand().getItem() instanceof IChestItem) return new ContainerChestItem(player, player.getHeldItemOffhand());
		if(ID == 200) return new ContainerSmallCrafting(player.inventory, world);
		if(ID == 201) return new ContainerNormalCrafting(player.inventory, world);
		if(block instanceof BlockIceMaker && tile instanceof TileEntityIceMaker) return new ContainerIceMaker(player.inventory, (TileEntityIceMaker) tile);
		if(block instanceof BlockObsidianChest && tile instanceof TileEntityObsidianChest) return new ContainerObsidianChest(player.inventory, (TileEntityObsidianChest) tile);
		if(block instanceof BlockMixedMetalChest && tile instanceof TileEntityMixedMetalChest) return new ContainerMixedMetalChest(player.inventory, (TileEntityMixedMetalChest) tile);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos blockPos = new BlockPos(x,y,z);
		TileEntity tile = world.getTileEntity(blockPos);
		Block block = world.getBlockState(blockPos).getBlock();
		if(ID == 100 && player.getHeldItemMainhand() != ItemStack.EMPTY && player.getHeldItemMainhand().getItem() instanceof IChestItem) return new GuiChestItem(player, player.getHeldItemMainhand());
		if(ID == 101) {
			ItemStack stack =  (player.inventory.armorInventory.get(2) !=ItemStack.EMPTY && player.inventory.armorInventory.get(2).getItem() instanceof IChestItem? player.inventory.armorInventory.get(2) : ItemStack.EMPTY);
			if(stack != ItemStack.EMPTY) return new GuiChestItem(player, stack);
		}
		if(ID == 102 && player.getHeldItemOffhand() !=ItemStack.EMPTY && player.getHeldItemOffhand().getItem() instanceof IChestItem) return new GuiChestItem(player, player.getHeldItemOffhand());
		if(ID == 200) return new GuiSmallCrafting(player.inventory, world);
		if(ID == 201) return new GuiNormalCrafting(player.inventory, world);
		if(block instanceof BlockIceMaker && tile instanceof TileEntityIceMaker) return new GuiIceMaker(player.inventory, (TileEntityIceMaker)tile);
		if(block instanceof BlockObsidianChest && tile instanceof TileEntityObsidianChest) return new GuiObsidianChest(player.inventory, (TileEntityObsidianChest)tile);
		if(block instanceof BlockMixedMetalChest && tile instanceof TileEntityMixedMetalChest) return new GuiMixedMetalChest(player.inventory, (TileEntityMixedMetalChest) tile);
		return null;
	}

}
