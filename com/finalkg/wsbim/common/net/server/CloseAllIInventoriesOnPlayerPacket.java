package com.finalkg.wsbim.common.net.server;

import java.util.ArrayList;
import java.util.List;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.net.AbstractMessage.AbstractServerMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;


/**
 * 
 * A simple message telling the server that the client wants to open a GUI.
 * 
 */
public class CloseAllIInventoriesOnPlayerPacket extends AbstractServerMessage<CloseAllIInventoriesOnPlayerPacket>
{
	// this will store the id of the gui to open
	public int id;

	
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public CloseAllIInventoriesOnPlayerPacket() {}

	// if there are any class fields, be sure to provide a constructor that allows
	// for them to be initialized, and use that constructor when sending the packet
	public CloseAllIInventoriesOnPlayerPacket(int id) {
		this.id = id;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		// basic Input/Output operations, very much like DataInputStream
		id = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		// basic Input/Output operations, very much like DataOutputStream
		buffer.writeInt(id);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		// using the message instance gives access to 'this.id'
		//if(player.getCommandSenderName().equals(clientplayer.getCommandSenderName())) 
		//player.closeScreen();
		//player.openGui(WSBIM.instance, 209, player.worldObj, 0, 0, 0);
		
		// because we sent the gui's id with the packet, we can handle all cases with one line:
		//EntityPlayer correctPlayer = player.worldObj.getPlayerEntityByName(player.getDisplayName());
		//if(!player.worldObj.isRemote){
	//	if(side == Side.CLIENT) ((GuiContainerTab)tab).ssct = new ServerSideContainerTab();
		   //ServerSideContainerTab.containerToOpen = this.ssct_2.containerToOpen;
		
			//player.closeScreen();
			//Bug fix for chests. Tells all of the IIventories inside a container that they should open.
			
			Object[] slots = player.openContainer.inventorySlots.toArray();
			List<IInventory> inventories = new ArrayList<IInventory>();
			for(int i = 0; i < slots.length; i++){
				Slot slot = (Slot) slots[i];
				if(slot !=null){
					if(!inventories.contains(slot.inventory)){
						inventories.add(slot.inventory);
					}
				}
			}
			for(int k = 0; k < inventories.size(); k++){
				IInventory inv = inventories.get(k);
				if(inv !=null){
					inv.closeInventory(player);
				}
			}
			player.openContainer.onContainerClosed(player);
			player.closeScreen();
		//}
			//player.openGui(WSBIM.instance, this.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

	/**
	 *
	 * 'VANILLA' VERSION of the Message Handler
	 * Straight implementation without any of my personal 'improvements' :P 
	 *
	 */
	
	public static class Handler implements IMessageHandler<CloseAllIInventoriesOnPlayerPacket, IMessage> {
		@Override
		public IMessage onMessage(CloseAllIInventoriesOnPlayerPacket message, MessageContext ctx) {
			// You could use ctx.getServerHandler().playerEntity directly, but using the
			// the proxy method everywhere keeps you safe from mundane mistakes
			EntityPlayer player = WSBIM.proxy.getPlayerEntity(ctx);
			// because we sent the gui's id with the packet, we can handle all cases with one line:
			EntityPlayer correctPlayer = ctx.getServerHandler().player.world.getPlayerEntityByName(player.getDisplayNameString());
			//ServerSideContainerTab.containerToOpen = ssct_2.containerToOpen;
			//if(ctx.side == Side.CLIENT) ((GuiContainerTab)tab).ssct = new ServerSideContainerTab();
			//correctPlayer.closeScreen();
			//Bugfix for renaming bags or backpacks
			Object[] slots = player.openContainer.inventorySlots.toArray();
			List<IInventory> inventories = new ArrayList<IInventory>();
			for(int i = 0; i < slots.length; i++){
				Slot slot = (Slot) slots[i];
				if(slot !=null){
					if(!inventories.contains(slot.inventory)){
						inventories.add(slot.inventory);
					}
				}
			}
			for(int k = 0; k < inventories.size(); k++){
				IInventory inv = inventories.get(k);
				if(inv !=null){
					inv.closeInventory(player);
				}
			}
			player.openContainer.detectAndSendChanges();
			player.openContainer.onContainerClosed(player);
			player.closeScreen();
			return null;
		}
	}
	 
}