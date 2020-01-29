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

@SideOnly(Side.CLIENT)
public class WSBIMOpenOptionsEvent {

	private boolean hasShownOptionsText = false;
	/**Time, in milliseconds, for the hint to stay onscreen.*/
	private long fadeAwayEffect = 10000;
	private long time = 0;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END) {
			GuiScreen screen = Minecraft.getMinecraft().currentScreen;
			if(!hasShownOptionsText && screen instanceof GuiOptions) {
				String linetodraw = I18n.format("gui.wsbim.optionsHint", new Object[0]);
				screen.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.YELLOW + linetodraw, 2, 2, ColorHelper.WHITE);
				if(time == 0) time = System.currentTimeMillis();
				if(System.currentTimeMillis() >= (time+fadeAwayEffect)) {
					hasShownOptionsText = true;
				}
			}
		}
		else if (e.phase == Phase.START) {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen screen = mc.currentScreen;
			if(screen instanceof GuiMainMenu || screen instanceof GuiIngameMenu || screen instanceof GuiOptions || screen instanceof GuiWSBIMPauseMenu) {
				if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
					//open options gui
					mc.displayGuiScreen(new GuiWSBIMOptions(mc, screen, 0, 8));
				}
			}
		}
	}
}
