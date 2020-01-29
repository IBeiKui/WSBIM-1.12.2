package com.finalkg.wsbim.client.lib;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.proxy.ClientProxy;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.IChestItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;

public class WSBIMKeyHandler {

	/** Key index for easy handling */
	public static final int BACKPACK = 0;
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(ClientProxy.keyBindings[BACKPACK].func_151468_f()){
			EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
			if(player == null) return;
			if(Minecraft.func_71410_x().field_71462_r == null && player.func_184592_cb() != ItemStack.field_190927_a) {
				if(player.func_184592_cb().func_77973_b() instanceof IChestItem) {
					ContainerUtil.openGui(102);
					return;
				}
			}
			ItemStack stack = player.field_71071_by.field_70460_b.get(2) !=ItemStack.field_190927_a && player.field_71071_by.field_70460_b.get(2).func_77973_b() instanceof IChestItem? player.field_71071_by.field_70460_b.get(2) : ItemStack.field_190927_a;
			if(Minecraft.func_71410_x().field_71462_r == null && stack !=ItemStack.field_190927_a){
				if(stack.func_77973_b() instanceof IChestItem) {
					IChestItem item = (IChestItem)stack.func_77973_b();
					ContainerUtil.openGui(101);
					return;
				}
			}
		}
	}
}
