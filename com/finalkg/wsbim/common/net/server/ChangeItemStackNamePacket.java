package com.finalkg.wsbim.common.net.server;

import java.io.IOException;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.net.AbstractMessage.AbstractServerMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
public class ChangeItemStackNamePacket extends AbstractServerMessage<ChangeItemStackNamePacket>
{
	public int itemIndex;
	public String textToUse = "";
	public boolean resetName;
	
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public ChangeItemStackNamePacket() {}

	// if there are any class fields, be sure to provide a constructor that allows
	// for them to be initialized, and use that constructor when sending the packet
	public ChangeItemStackNamePacket(String textToUse, int itemIndex, boolean reset) {
		this.itemIndex = itemIndex;
		this.textToUse = textToUse;
		this.resetName = reset;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataInputStream
		itemIndex = buffer.readInt();
		textToUse = buffer.readString(10000);
		this.resetName = buffer.readBoolean();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataOutputStream
		buffer.writeInt(itemIndex);
		buffer.writeString(textToUse);
		buffer.writeBoolean(resetName);
	}

	@Override
	public void process(EntityPlayer player, Side side) {

		ItemStack itemStack = player.inventory.getStackInSlot(itemIndex);
		if(itemStack !=null){
			if(!itemStack.hasTagCompound()){
				itemStack.setTagCompound(new NBTTagCompound());
			}
			if(itemStack.getTagCompound().hasKey("display")){
				if(itemStack.getTagCompound().getCompoundTag("display").hasKey("Name")) itemStack.getTagCompound().getCompoundTag("display").removeTag("Name");
			}
			if(itemStack.getTagCompound().hasKey("RepairCost")){
				itemStack.getTagCompound().removeTag("RepairCost");
			}

			if(!this.resetName) itemStack.setStackDisplayName(this.textToUse);
		}
			
		//}
			//player.openGui(WSBIM.instance, this.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
	/**
	 *
	 * 'VANILLA' VERSION of the Message Handler
	 * Straight implementation without any of my personal 'improvements' :P 
	 *
	 */
	
	public static class Handler implements IMessageHandler<ChangeItemStackNamePacket, IMessage> {
		@Override
		public IMessage onMessage(ChangeItemStackNamePacket message, MessageContext ctx) {
			// You could use ctx.getServerHandler().playerEntity directly, but using the
			// the proxy method everywhere keeps you safe from mundane mistakes
			EntityPlayer player = WSBIM.proxy.getPlayerEntity(ctx);
			// because we sent the gui's id with the packet, we can handle all cases with one line:
			EntityPlayer correctPlayer = ctx.getServerHandler().player.world.getPlayerEntityByName(player.getDisplayNameString());
			//ServerSideContainerTab.containerToOpen = ssct_2.containerToOpen;
			//if(ctx.side == Side.CLIENT) ((GuiContainerTab)tab).ssct = new ServerSideContainerTab();
			//correctPlayer.closeScreen();
			//Bugfix for renaming bags or backpacks
			ItemStack itemStack = player.inventory.getStackInSlot(message.itemIndex);
			if(itemStack !=null){
				if(!itemStack.hasTagCompound()){
					itemStack.setTagCompound(new NBTTagCompound());
				}
				if(itemStack.getTagCompound().hasKey("display")){
					if(itemStack.getTagCompound().getCompoundTag("display").hasKey("Name")) itemStack.getTagCompound().getCompoundTag("display").removeTag("Name");
				}
				if(itemStack.getTagCompound().hasKey("RepairCost")){
					itemStack.getTagCompound().removeTag("RepairCost");
				}

				if(!message.resetName) itemStack.setStackDisplayName(message.textToUse);
			}
			return null;
		}
	}
	 
}