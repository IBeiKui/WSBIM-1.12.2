package com.finalkg.wsbim.client.lib.event;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.Profile;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WSBIMClearHUDRenderEvent extends Gui {
	
	private boolean hasResetVanillaElements = false;
	private ResourceLocation HUD_ICONS = new ResourceLocation("textures/gui/icons.png");
	
	@SubscribeEvent
	public void onRenderGameOverlayTick(RenderGameOverlayEvent.Pre e){
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.world != null && mc.player !=null) {
			GuiIngameForge.renderCrosshairs = mc.currentScreen instanceof GuiContainer ? false : WSBIM.options.showCrosshairs;
			GuiIngameForge.renderObjective = WSBIM.options.showScoreboard;
			if(WSBIM.options.useClearHUD) this.disableVanillaClearUIElements(mc);
			else if(!this.hasResetVanillaElements) this.enableVanillaUIElements(mc);
		}
		if(mc.world !=null && mc.player !=null && WSBIM.options.useClearHUD && e.getType() == RenderGameOverlayEvent.ElementType.ALL) this.renderClearTheme(mc, mc.player, e.getPartialTicks());
	}
	private void renderClearTheme(Minecraft mc, EntityPlayerSP renderPlayer, float partialTick) {
		boolean isSurvivalOrAdventure = mc.playerController.shouldDrawHUD();
		GuiIngameForge ingameGUI = (GuiIngameForge)mc.ingameGUI;
		GuiScreen currentGui = mc.currentScreen;
		NonNullList<ItemStack> inv = renderPlayer.inventory.mainInventory;
		ItemStack leftHand = renderPlayer.inventory.offHandInventory.get(0);
		ItemStack[] hotbar = new ItemStack[9];
		for(int i = 0; i < hotbar.length; i++) hotbar[i] = inv.get(i);
		ScaledResolution res = (ScaledResolution) GuiHelper.getObjectFromClass("res", GuiIngameForge.class, ingameGUI);
		int width = res !=null? res.getScaledWidth() : 0;
		int height = res !=null? res.getScaledHeight() : 0;
		String hex = WSBIM.options.foregroundColor;
    	int foregroundred = 0;
    	int foregroundgreen = 0;
    	int foregroundblue = 0;
    	if(!hex.equals("0")){
    		Color c = new Color((int)Long.parseLong(hex.substring(2), 16));
    		foregroundred = c.getRed();
    		foregroundgreen = c.getGreen(); 
    		foregroundblue = c.getBlue();
    	}
    	String hex2 = WSBIM.options.backgroundColor;
    	int backgroundred = 0;
    	int backgroundgreen = 0;
    	int backgroundblue = 0;
    	if(!hex2.equals("0")){
    		Color c2 = new Color((int)Long.parseLong(hex2.substring(2), 16));
    		backgroundred = c2.getRed();
    		backgroundgreen = c2.getGreen(); 
    		backgroundblue = c2.getBlue();
    	}
		int hotbarx = width / 2 - 91;
		int hotbary = height - 22;
		int hotbarwidth = 182;
		int hotbarheight = 22;
		GlStateManager.pushMatrix();
       	GuiHelper.drawSpecificRect(hotbarx, hotbary, hotbarx+hotbarwidth, hotbary+hotbarheight, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		int sel = renderPlayer.inventory.currentItem;
		int selx = hotbarx + 2 + (sel*20);
		int sely = hotbary + 2;
		int selthick = 3;
		int selheight = hotbary + 20;
		GuiHelper.drawSpecificRect(hotbarx + 2 + (sel*20), hotbary + 2, hotbarx + 2 + (sel*20) + 18, hotbary + 2 + 18,  (float)foregroundred/255F, (float)foregroundgreen/255F, (float)foregroundblue/255F, WSBIM.options.defaultForegroundOpacity);
		if(!leftHand.isEmpty()) GuiHelper.drawSpecificRect(hotbarx - 30, hotbary, hotbarx - 8, hotbary+hotbarheight, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		if(mc.playerController.gameIsSurvivalOrAdventure() && !renderPlayer.isRidingHorse()){
			int cap = renderPlayer.xpBarCap();
            int left = width / 2 - 91;
            if (cap > 0){
                short barWidth = 182;
                int filled = (int)(renderPlayer.experience * (float)(barWidth + 1));
                int top = height - 32 + 3;
                GuiHelper.drawSpecificRect(left, top, left+barWidth, top+5, 0, 0, 0, WSBIM.options.defaultBackgroundOpacity);
                if (filled > 0){
                    GuiHelper.drawSpecificRect(left, top, left+filled, top+5, 0.1F, 1, 0, WSBIM.options.defaultForegroundOpacity);
                }
            }
            if (mc.playerController.gameIsSurvivalOrAdventure() && renderPlayer.experienceLevel > 0){
                mc.mcProfiler.startSection("expLevel");
                boolean flag1 = false;
                int color = flag1 ? 16777215 : 8453920;
                String text = "" + renderPlayer.experienceLevel;
                int x = (width - mc.fontRenderer.getStringWidth(text)) / 2;
                int y = height - 31 - 4;
                mc.fontRenderer.drawString(text, x + 1, y, 0);
                mc.fontRenderer.drawString(text, x - 1, y, 0);
                mc.fontRenderer.drawString(text, x, y + 1, 0);
                mc.fontRenderer.drawString(text, x, y - 1, 0);
                mc.fontRenderer.drawString(text, x, y, color);
                mc.mcProfiler.endSection();
            }
        }
		GlStateManager.popMatrix();
		GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		for(int i = 0; i < 9; i++) if(!hotbar[i].isEmpty()) {
			GuiHelper.renderItemStack(mc, renderPlayer, hotbar[i], hotbarx + 2 + (i*20) + 1, hotbary + 2 + 1, partialTick);
		}
		if(!leftHand.isEmpty()) GuiHelper.renderItemStack(mc, renderPlayer, leftHand, hotbarx - 27, hotbary + 3, partialTick);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
		mc.getTextureManager().bindTexture(HUD_ICONS);
	}
	private void enableVanillaUIElements(Minecraft mc) {
		GuiIngameForge.renderAir = true;
		GuiIngameForge.renderArmor = true;
		GuiIngameForge.renderBossHealth = true;
		GuiIngameForge.renderExperiance = true;
		GuiIngameForge.renderHealth = true;
		GuiIngameForge.renderHotbar = true;
		GuiIngameForge.renderHealthMount = mc.player.getRidingEntity() instanceof EntityLivingBase;
		GuiIngameForge.renderFood = mc.player.getRidingEntity() == null;
		GuiIngameForge.renderJumpBar = mc.player.isRidingHorse();
		this.hasResetVanillaElements = true;
	}

	private void disableVanillaClearUIElements(Minecraft mc) {
		this.hasResetVanillaElements = false;
		GuiIngameForge.renderAir = true;
		GuiIngameForge.renderArmor = true;
		GuiIngameForge.renderBossHealth = true;
		GuiIngameForge.renderExperiance = false;
		GuiIngameForge.renderHealth = true;
		GuiIngameForge.renderHotbar = false;
		GuiIngameForge.renderHealthMount = mc.player.getRidingEntity() instanceof EntityLivingBase;
		GuiIngameForge.renderFood = mc.player.getRidingEntity() == null;
		GuiIngameForge.renderJumpBar = mc.player.isRidingHorse();
	}
}
