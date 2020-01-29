package com.finalkg.wsbim.common.lib;

import com.finalkg.wsbim.common.net.PacketDispatcher;
import com.finalkg.wsbim.common.net.server.OpenModGui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Provides useful utilities for handling item stacks and containers
 * @author finalkg
 *
 */
public class ContainerUtil {

	
	/**
	 * Used for opening a inventory GUI on the client side by sending a custom Gui open request packet to the integrated or dedicated server.
	 * Also, it automatically will open the gui from the gui handler in this mod already.
	 * I might add a parameter for your own instance of your mod maybe later.
	 * @param call - GUI-ID for the Gui Handler
	 */
	public static void openGui(int call){
		OpenModGui gui_packet = new OpenModGui();
		gui_packet.id = call;
		PacketDispatcher.sendToServer(gui_packet);
	}
	
	/**
	 * Checks the player inventory for an itemstack. used with chest items.
	 * Only can the stack be exactly the same with stack.equals();
	 * @param playerInv - player inventory to check
	 * @param stack - Stack to check
	 * @return The index of the itemstack that we are looking for. Will return -1 if the itemstack could not be found.
	 */
	public static int getItemStackIndexInPlayerInventory(InventoryPlayer playerInv, ItemStack stack) {
		int index = -1;
		for(int i = 0; i < playerInv.getSizeInventory(); i++) {
			ItemStack check = playerInv.getStackInSlot(i);
			if(!check.isEmpty()) {
				if(check.equals(stack)) index = playerInv.getSlotFor(check);
			}
		}
		for(int i = 0; i < playerInv.armorInventory.size(); i++) {
			ItemStack check = playerInv.armorItemInSlot(i);
			if(!check.isEmpty()) if(check.equals(stack)) index = i + playerInv.mainInventory.size();
		}
		for(int i = 0; i < playerInv.offHandInventory.size(); i++) {
			ItemStack check = playerInv.offHandInventory.get(i);
			if(!check.isEmpty()) if(check.equals(stack)) index = i + playerInv.mainInventory.size() + playerInv.armorInventory.size();
		}
		return index;
	}
	/**
	 * Checks the IInventory to see if it contains an item
	 * @param inventory - IInventory to check
	 * @param itemToCheck - Item instance of the item to search for
	 * @return
	 */
	public static boolean hasItemInInventory(IInventory inventory, Item itemToCheck) {
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			if(!inventory.getStackInSlot(i).isEmpty()) if(inventory.getStackInSlot(i).getItem().equals(itemToCheck)) return true;
		}
		return false;
	}
	/**
	 * Checks the player inventory for an item. Will search mainInventory, armorInventory, and second hand
	 * @param playerInv - InventoryPlayer to check
	 * @param check - Item to check for
	 * @return Item found.
	 */
	public static boolean hasItemInPlayerInventory(InventoryPlayer playerInv, Item check) {
		for(int i = 0; i < playerInv.mainInventory.size(); i++) {
			ItemStack checkstack = playerInv.mainInventory.get(i);
			if(!checkstack.isEmpty()) if(checkstack.getItem().equals(check)) return true;
		}
		for(int i = 0; i < playerInv.armorInventory.size(); i++) {
			ItemStack checkstack = playerInv.armorInventory.get(i);
			if(!checkstack.isEmpty()) if(checkstack.getItem().equals(check)) return true;
		}
		for(int i = 0; i < playerInv.offHandInventory.size(); i++) {
			ItemStack checkstack = playerInv.offHandInventory.get(i);
			if(!checkstack.isEmpty()) if(checkstack.getItem().equals(check)) return true;
		}
		return false;
	}
	/**
	 * Checks to see if the given player inventory has armor inside.
	 * @param playerInv
	 * @return
	 */
	public static boolean isPlayerWearingArmor(InventoryPlayer playerInv) {
		boolean flag = false;
		for(int i = 0; i < playerInv.armorInventory.size(); i++) {
			if(!playerInv.armorInventory.get(i).isEmpty()) flag = true;
		}
		return flag;
	}
}
