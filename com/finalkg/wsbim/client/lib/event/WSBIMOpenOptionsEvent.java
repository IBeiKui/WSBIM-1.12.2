package com.finalkg.wsbim.client.lib.event;

import org.lwjgl.input.Keyboard;
import com.finalkg.wsbim.client.gui.screen.GuiWSBIMPauseMenu;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptions;
import com.finalkg.wsbim.common.lib.ColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import com.finalkg.wsbim.client.gui.screen.GuiWSBIMPauseMenu;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptions;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WSBIMOpenOptionsEvent {

	private boolean hasShownOptionsText = false;
	/**Time, in milliseconds, for the hint to stay onscreen.*/
	private long fadeAwayEffect = 10000;
	private long time = 0;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END) {
			GuiScreen screen = Minecraft.func_71410_x().field_71462_r;
			if(!hasShownOptionsText && screen instanceof GuiOptions) {
				String linetodraw = I18n.func_135052_a("gui.wsbim.optionsHint", new Object[0]);
				screen.func_73731_b(Minecraft.func_71410_x().field_71466_p, TextFormatting.YELLOW + linetodraw, 2, 2, ColorHelper.WHITE);
				if(time == 0) time = System.currentTimeMillis();
				if(System.currentTimeMillis() >= (time+fadeAwayEffect)) {
					hasShownOptionsText = true;
				}
			}
		}
		else if (e.phase == Phase.START) {
			Minecraft mc = Minecraft.func_71410_x();
			GuiScreen screen = mc.field_71462_r;
			if(screen instanceof GuiMainMenu || screen instanceof GuiIngameMenu || screen instanceof GuiOptions || screen instanceof GuiWSBIMPauseMenu) {
				if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
					//open options gui
					mc.func_147108_a(new GuiWSBIMOptions(mc, screen, 0, 8));
				}
			}
		}
	}
}
