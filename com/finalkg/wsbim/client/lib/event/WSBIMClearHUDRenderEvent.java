package com.finalkg.wsbim.client.lib.event;

import java.awt.Color;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


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
		Minecraft mc = Minecraft.func_71410_x();
		if(mc.field_71441_e != null && mc.field_71439_g !=null) {
			GuiIngameForge.renderCrosshairs = mc.field_71462_r instanceof GuiContainer ? false : WSBIM.options.showCrosshairs;
			GuiIngameForge.renderObjective = WSBIM.options.showScoreboard;
			if(WSBIM.options.useClearHUD) this.disableVanillaClearUIElements(mc);
			else if(!this.hasResetVanillaElements) this.enableVanillaUIElements(mc);
		}
		if(mc.field_71441_e !=null && mc.field_71439_g !=null && WSBIM.options.useClearHUD && e.getType() == RenderGameOverlayEvent.ElementType.ALL) this.renderClearTheme(mc, mc.field_71439_g, e.getPartialTicks());
	}
	private void renderClearTheme(Minecraft mc, EntityPlayerSP renderPlayer, float partialTick) {
		boolean isSurvivalOrAdventure = mc.field_71442_b.func_78755_b();
		GuiIngameForge ingameGUI = (GuiIngameForge)mc.field_71456_v;
		GuiScreen currentGui = mc.field_71462_r;
		NonNullList<ItemStack> inv = renderPlayer.field_71071_by.field_70462_a;
		ItemStack leftHand = renderPlayer.field_71071_by.field_184439_c.get(0);
		ItemStack[] hotbar = new ItemStack[9];
		for(int i = 0; i < hotbar.length; i++) hotbar[i] = inv.get(i);
		ScaledResolution res = (ScaledResolution) GuiHelper.getObjectFromClass("res", GuiIngameForge.class, ingameGUI);
		int width = res !=null? res.func_78326_a() : 0;
		int height = res !=null? res.func_78328_b() : 0;
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
		GlStateManager.func_179094_E();
       	GuiHelper.drawSpecificRect(hotbarx, hotbary, hotbarx+hotbarwidth, hotbary+hotbarheight, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		int sel = renderPlayer.field_71071_by.field_70461_c;
		int selx = hotbarx + 2 + (sel*20);
		int sely = hotbary + 2;
		int selthick = 3;
		int selheight = hotbary + 20;
		GuiHelper.drawSpecificRect(hotbarx + 2 + (sel*20), hotbary + 2, hotbarx + 2 + (sel*20) + 18, hotbary + 2 + 18,  (float)foregroundred/255F, (float)foregroundgreen/255F, (float)foregroundblue/255F, WSBIM.options.defaultForegroundOpacity);
		if(!leftHand.func_190926_b()) GuiHelper.drawSpecificRect(hotbarx - 30, hotbary, hotbarx - 8, hotbary+hotbarheight, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		if(mc.field_71442_b.func_78763_f() && !renderPlayer.func_110317_t()){
			int cap = renderPlayer.func_71050_bK();
            int left = width / 2 - 91;
            if (cap > 0){
                short barWidth = 182;
                int filled = (int)(renderPlayer.field_71106_cc * (float)(barWidth + 1));
                int top = height - 32 + 3;
                GuiHelper.drawSpecificRect(left, top, left+barWidth, top+5, 0, 0, 0, WSBIM.options.defaultBackgroundOpacity);
                if (filled > 0){
                    GuiHelper.drawSpecificRect(left, top, left+filled, top+5, 0.1F, 1, 0, WSBIM.options.defaultForegroundOpacity);
                }
            }
            if (mc.field_71442_b.func_78763_f() && renderPlayer.field_71068_ca > 0){
                mc.field_71424_I.func_76320_a("expLevel");
                boolean flag1 = false;
                int color = flag1 ? 16777215 : 8453920;
                String text = "" + renderPlayer.field_71068_ca;
                int x = (width - mc.field_71466_p.func_78256_a(text)) / 2;
                int y = height - 31 - 4;
                mc.field_71466_p.func_78276_b(text, x + 1, y, 0);
                mc.field_71466_p.func_78276_b(text, x - 1, y, 0);
                mc.field_71466_p.func_78276_b(text, x, y + 1, 0);
                mc.field_71466_p.func_78276_b(text, x, y - 1, 0);
                mc.field_71466_p.func_78276_b(text, x, y, color);
                mc.field_71424_I.func_76319_b();
            }
        }
		GlStateManager.func_179121_F();
		GlStateManager.func_179091_B();
        GlStateManager.func_179147_l();
        GlStateManager.func_179126_j();
        RenderHelper.func_74520_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		for(int i = 0; i < 9; i++) if(!hotbar[i].func_190926_b()) {
			GuiHelper.renderItemStack(mc, renderPlayer, hotbar[i], hotbarx + 2 + (i*20) + 1, hotbary + 2 + 1, partialTick);
		}
		if(!leftHand.func_190926_b()) GuiHelper.renderItemStack(mc, renderPlayer, leftHand, hotbarx - 27, hotbary + 3, partialTick);
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        GlStateManager.func_179097_i();
		mc.func_110434_K().func_110577_a(HUD_ICONS);
	}
	private void enableVanillaUIElements(Minecraft mc) {
		GuiIngameForge.renderAir = true;
		GuiIngameForge.renderArmor = true;
		GuiIngameForge.renderBossHealth = true;
		GuiIngameForge.renderExperiance = true;
		GuiIngameForge.renderHealth = true;
		GuiIngameForge.renderHotbar = true;
		GuiIngameForge.renderHealthMount = mc.field_71439_g.func_184187_bx() instanceof EntityLivingBase;
		GuiIngameForge.renderFood = mc.field_71439_g.func_184187_bx() == null;
		GuiIngameForge.renderJumpBar = mc.field_71439_g.func_110317_t();
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
		GuiIngameForge.renderHealthMount = mc.field_71439_g.func_184187_bx() instanceof EntityLivingBase;
		GuiIngameForge.renderFood = mc.field_71439_g.func_184187_bx() == null;
		GuiIngameForge.renderJumpBar = mc.field_71439_g.func_110317_t();
	}
}
