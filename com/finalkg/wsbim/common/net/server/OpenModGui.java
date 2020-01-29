package com.finalkg.wsbim.common.net.server;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.net.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class OpenModGui extends AbstractServerMessage<OpenModGui>{
	public int id;

	public EntityPlayer clientplayer;
	
	public OpenModGui() {}

	public OpenModGui(int id, EntityPlayer client_player) {
		this.id = id;
		this.clientplayer = client_player;
	}

	@Override
	protected void read(PacketBuffer buffer) {
		id = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(id);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		player.openGui(WSBIM.instance, this.id, player.field_70170_p, (int) player.field_70165_t, (int) player.field_70163_u, (int) player.field_70161_v);
	}

	public static class Handler implements IMessageHandler<OpenModGui, IMessage> {
		@Override
		public IMessage onMessage(OpenModGui message, MessageContext ctx) {
			EntityPlayer player = WSBIM.proxy.getPlayerEntity(ctx);
			player.openGui(WSBIM.instance, message.id, player.field_70170_p, (int) player.field_70165_t, (int) player.field_70163_u, (int) player.field_70161_v);
			return null;
		}
	}

}
