package com.finalkg.wsbim.client.lib;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.proxy.ClientProxy;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.IChestItem;

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
		if(ClientProxy.keyBindings[BACKPACK].isPressed()){
			EntityPlayer player = Minecraft.getMinecraft().player;
			if(player == null) return;
			if(Minecraft.getMinecraft().currentScreen == null && player.getHeldItemOffhand() != ItemStack.EMPTY) {
				if(player.getHeldItemOffhand().getItem() instanceof IChestItem) {
					ContainerUtil.openGui(102);
					return;
				}
			}
			ItemStack stack = player.inventory.armorInventory.get(2) !=ItemStack.EMPTY && player.inventory.armorInventory.get(2).getItem() instanceof IChestItem? player.inventory.armorInventory.get(2) : ItemStack.EMPTY;
			if(Minecraft.getMinecraft().currentScreen == null && stack !=ItemStack.EMPTY){
				if(stack.getItem() instanceof IChestItem) {
					IChestItem item = (IChestItem)stack.getItem();
					ContainerUtil.openGui(101);
					return;
				}
			}
		}
	}
}
