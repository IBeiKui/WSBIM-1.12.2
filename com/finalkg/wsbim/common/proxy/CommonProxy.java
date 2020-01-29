package com.finalkg.wsbim.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {
	
	public void registerProxies(){
		
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}
	public WorldServer getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player.getServerWorld();
	}
}
