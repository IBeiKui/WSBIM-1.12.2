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
			Minecraft mc = Minecraft.func_71410_x();
			if(mc.field_71441_e !=null && mc.field_71439_g !=null && WSBIM.options.showArmorInHUD) {
				WorldClient world = mc.field_71441_e;
				EntityPlayerSP player = mc.field_71439_g;
				if(player.func_184812_l_() && !WSBIM.options.drawArmorHudInCreative) {
					return;
				}
				else if((player.func_184812_l_() || mc.field_71442_b.func_78755_b()) && ContainerUtil.isPlayerWearingArmor(player.field_71071_by) && (mc.field_71462_r == null || mc.field_71462_r instanceof GuiChat) && !mc.field_71474_y.field_74330_P) {
					//draw HUD armor
					if(!WSBIM.options.useClearHUD && !(mc.field_71474_y.field_151453_l.size() > 0)) this.renderArmorInHUDVanilla(mc, player, e.renderTickTime);
					else this.renderArmorInHUDClear(mc, player, e.renderTickTime);
				}
			}
		}
	}

	private void renderArmorInHUDVanilla(Minecraft mc, EntityPlayerSP player, float partialTick) {
		int left = 5;
		int top = 5;
		GlStateManager.func_179094_E();
		GlStateManager.func_179147_l();
		GlStateManager.func_179091_B();
		GlStateManager.func_179131_c(1, 1, 1, 1);
		mc.field_71446_o.func_110577_a(WSBIM.options.useDarkGuiContainerTheme ? ARMOR_SLOTS_BACKGROUND_DARK : ARMOR_SLOTS_BACKGROUND);
		this.func_73729_b(left, top, 0, 0, 30, 85);
		RenderHelper.func_74520_c();
		NonNullList<ItemStack> armorSlots = player.field_71071_by.field_70460_b;
		if(armorSlots.get(3) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(3), left + 7, top + 7, partialTick);
		}
		if(armorSlots.get(2) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(2), left + 7, top + 25, partialTick);
		}
		if(armorSlots.get(1) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(1), left + 7, top + 43, partialTick);
		}
		if(armorSlots.get(0) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(0), left + 7, top + 61, partialTick);
		}
		RenderHelper.func_74518_a();
		GlStateManager.func_179084_k();
		GlStateManager.func_179101_C();
		GlStateManager.func_179121_F();
	}
	private void renderArmorInHUDClear(Minecraft mc, EntityPlayerSP player, float partialTick) {
		int left = 5;
		int top = 5;
		GlStateManager.func_179094_E();
		GlStateManager.func_179147_l();
		GlStateManager.func_179091_B();
		GlStateManager.func_179131_c(1, 1, 1, 1);
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
		RenderHelper.func_74520_c();
		NonNullList<ItemStack> armorSlots = player.field_71071_by.field_70460_b;
		if(armorSlots.get(3) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(3), left + 7, top + 7, partialTick);
		}
		if(armorSlots.get(2) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(2), left + 7, top + 25, partialTick);
		}
		if(armorSlots.get(1) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(1), left + 7, top + 43, partialTick);
		}
		if(armorSlots.get(0) != ItemStack.field_190927_a){
			GuiHelper.renderItemStack(mc, player, armorSlots.get(0), left + 7, top + 61, partialTick);
		}
		RenderHelper.func_74518_a();
		GlStateManager.func_179084_k();
		GlStateManager.func_179101_C();
		GlStateManager.func_179121_F();
	}
}
