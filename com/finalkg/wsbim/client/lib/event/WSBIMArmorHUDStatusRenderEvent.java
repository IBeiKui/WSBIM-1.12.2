package com.finalkg.wsbim.client.lib.event;

import java.awt.Color;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.ContainerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WSBIMArmorHUDStatusRenderEvent extends Gui{
	
	private static final ResourceLocation ARMOR_SLOTS_BACKGROUND = new ResourceLocation(WSBIM.MODID, "textures/gui/armor_hud.png");
	private static final ResourceLocation ARMOR_SLOTS_BACKGROUND_DARK = new ResourceLocation(WSBIM.MODID, "textures/gui/armor_hud_dark.png");
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END) {
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.world !=null && mc.player !=null && WSBIM.options.showArmorInHUD) {
				WorldClient world = mc.world;
				EntityPlayerSP player = mc.player;
				if(player.isCreative() && !WSBIM.options.drawArmorHudInCreative) {
					return;
				}
				else if((player.isCreative() || mc.playerController.shouldDrawHUD()) && ContainerUtil.isPlayerWearingArmor(player.inventory) && (mc.currentScreen == null || mc.currentScreen instanceof GuiChat) && !mc.gameSettings.showDebugInfo) {
					//draw HUD armor
					if(!WSBIM.options.useClearHUD && !(mc.gameSettings.resourcePacks.size() > 0)) this.renderArmorInHUDVanilla(mc, player, e.renderTickTime);
					else this.renderArmorInHUDClear(mc, player, e.renderTickTime);
				}
			}
		}
	}

	private void renderArmorInHUDVanilla(Minecraft mc, EntityPlayerSP player, float partialTick) {
		int left = 5;
		int top = 5;
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1, 1, 1, 1);
		mc.renderEngine.bindTexture(WSBIM.options.useDarkGuiContainerTheme ? ARMOR_SLOTS_BACKGROUND_DARK : ARMOR_SLOTS_BACKGROUND);
		this.drawTexturedModalRect(left, top, 0, 0, 30, 85);
		RenderHelper.enableGUIStandardItemLighting();
		NonNullList<ItemStack> armorSlots = player.inventory.armorInventory;
		if(armorSlots.get(3) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(3), left + 7, top + 7, partialTick);
		}
		if(armorSlots.get(2) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(2), left + 7, top + 25, partialTick);
		}
		if(armorSlots.get(1) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(1), left + 7, top + 43, partialTick);
		}
		if(armorSlots.get(0) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(0), left + 7, top + 61, partialTick);
		}
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}
	private void renderArmorInHUDClear(Minecraft mc, EntityPlayerSP player, float partialTick) {
		int left = 5;
		int top = 5;
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1, 1, 1, 1);
    	String hex2 = WSBIM.options.backgroundColor;
    	int backgroundred = 0;
    	int backgroundgreen = 0;
    	int backgroundblue = 0;
    	if(!hex2.equals("0")){
    		Color c = new Color((int)Long.parseLong(hex2.substring(2), 16));
    		backgroundred = c.getRed();
    		backgroundgreen = c.getGreen(); 
    		backgroundblue = c.getBlue();
    	}
		GuiHelper.drawSpecificRect(left, top, left + 30, top + 85, backgroundred / 255F, backgroundgreen / 255F, backgroundblue / 255F, WSBIM.options.defaultBackgroundOpacity);
		RenderHelper.enableGUIStandardItemLighting();
		NonNullList<ItemStack> armorSlots = player.inventory.armorInventory;
		if(armorSlots.get(3) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(3), left + 7, top + 7, partialTick);
		}
		if(armorSlots.get(2) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(2), left + 7, top + 25, partialTick);
		}
		if(armorSlots.get(1) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(1), left + 7, top + 43, partialTick);
		}
		if(armorSlots.get(0) != ItemStack.EMPTY){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(0), left + 7, top + 61, partialTick);
		}
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}
}
