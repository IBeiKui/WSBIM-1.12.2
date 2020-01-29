package com.finalkg.wsbim.client.proxy;

import org.lwjgl.input.Keyboard;

import com.finalkg.wsbim.client.lib.WSBIMKeyHandler;
import com.finalkg.wsbim.client.lib.event.CraftingButtonRenderEvent;
import com.finalkg.wsbim.client.lib.event.ItemTooltipHandler;
import com.finalkg.wsbim.client.lib.event.RenderCustomButtonsAndMenusEvent;
import com.finalkg.wsbim.client.lib.event.UpdateUIColorsEvent;
import com.finalkg.wsbim.client.lib.event.WSBIMArmorHUDStatusRenderEvent;
import com.finalkg.wsbim.client.lib.event.WSBIMClearHUDRenderEvent;
import com.finalkg.wsbim.client.lib.event.WSBIMOpenOptionsEvent;
import com.finalkg.wsbim.client.lib.event.WSBIMPlayOpenGUIEvent;
import com.finalkg.wsbim.client.lib.event.WSBIMUpdateCheckerLogic;
import com.finalkg.wsbim.client.render.tileentity.TileEntityMixedMetalChestRenderer;
import com.finalkg.wsbim.client.render.tileentity.TileEntityObsidianChestRenderer;
import com.finalkg.wsbim.common.proxy.CommonProxy;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	public static final KeyBinding[] keyBindings = {new KeyBinding("key.openBackpack.desc", Keyboard.KEY_B, "key.wsbim.category")};
	
	public void registerProxies(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObsidianChest.class, new TileEntityObsidianChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMixedMetalChest.class, new TileEntityMixedMetalChestRenderer());
		MinecraftForge.EVENT_BUS.register(new WSBIMKeyHandler());
		MinecraftForge.EVENT_BUS.register(new CraftingButtonRenderEvent());
		MinecraftForge.EVENT_BUS.register(new WSBIMUpdateCheckerLogic());
		MinecraftForge.EVENT_BUS.register(new WSBIMOpenOptionsEvent());
		MinecraftForge.EVENT_BUS.register(new WSBIMPlayOpenGUIEvent());
		MinecraftForge.EVENT_BUS.register(new ItemTooltipHandler());
		MinecraftForge.EVENT_BUS.register(new UpdateUIColorsEvent());
		MinecraftForge.EVENT_BUS.register(new RenderCustomButtonsAndMenusEvent());
		MinecraftForge.EVENT_BUS.register(new WSBIMArmorHUDStatusRenderEvent());
		MinecraftForge.EVENT_BUS.register(new WSBIMClearHUDRenderEvent());
		for(KeyBinding keyBinding : keyBindings) ClientRegistry.registerKeyBinding(keyBinding);
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx));
	}
}
