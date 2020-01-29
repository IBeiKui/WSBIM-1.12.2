package com.finalkg.wsbim.common.net.server;

import java.io.IOException;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.net.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.lib.ColorHelper;
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
public class ChangeItemStackColorPacket extends AbstractServerMessage<ChangeItemStackColorPacket>
{
	public int itemIndex;
	public String newColor = "";
	public boolean removeColor;
	
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public ChangeItemStackColorPacket() {}

	// if there are any class fields, be sure to provide a constructor that allows
	// for them to be initialized, and use that constructor when sending the packet
	public ChangeItemStackColorPacket(String color, int itemIndex, boolean reset) {
		this.itemIndex = itemIndex;
		this.newColor = color;
		this.removeColor = reset;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataInputStream
		itemIndex = buffer.readInt();
		newColor = buffer.func_150789_c(10000);
		this.removeColor = buffer.readBoolean();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataOutputStream
		buffer.writeInt(itemIndex);
		buffer.func_180714_a(newColor);
		buffer.writeBoolean(removeColor);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ItemStack itemStack = player.field_71071_by.func_70301_a(itemIndex);
		if(this.removeColor)ColorHelper.removeColor(itemStack);
		else {
			int color = Integer.parseInt(newColor);
			ColorHelper.setColor(itemStack, color);
		}
	}
	/**
	 *
	 * 'VANILLA' VERSION of the Message Handler
	 * Straight implementation without any of my personal 'improvements' :P 
	 *
	 */
	
	public static class Handler implements IMessageHandler<ChangeItemStackColorPacket, IMessage> {
		@Override
		public IMessage onMessage(ChangeItemStackColorPacket message, MessageContext ctx) {
			EntityPlayer player = WSBIM.proxy.getPlayerEntity(ctx);
			ItemStack itemStack = player.field_71071_by.func_70301_a(message.itemIndex);
			if(message.removeColor)ColorHelper.removeColor(itemStack);
			else {
				int color = Integer.parseInt(message.newColor);
				ColorHelper.setColor(itemStack, color);
			}
			return null;
		}
	}
	 
}
