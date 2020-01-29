package com.finalkg.wsbim.client.lib.event;

import java.util.ArrayList;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.GuiButtonCustom;
import com.finalkg.wsbim.client.gui.screen.GuiButtonTransparent;
import com.finalkg.wsbim.client.gui.screen.GuiWSBIMPauseMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomButtonsAndMenusEvent {
	private boolean displayedMainMenu = false;
	private int prevWidth;
	private int prevHeight;
	private int prevListSize;
	public static ArrayList newMainMenuButtonList = new ArrayList();
	public static ArrayList newTransparentButtonList = new ArrayList();
	private GuiMainMenu fake1;
	private GuiMainMenu fake2;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END){
			if((Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu) && WSBIM.options.useWSBIMPauseMenu){
				Minecraft mc = Minecraft.getMinecraft();
				//Force display our new pause menu
				mc.displayGuiScreen(new GuiWSBIMPauseMenu());
			}
			if((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1])){
				Minecraft mc = Minecraft.getMinecraft();
				GuiMainMenu main = (GuiMainMenu)mc.currentScreen;
				if(!this.displayedMainMenu || prevWidth != mc.currentScreen.width || prevHeight != mc.currentScreen.height || (prevListSize !=0 && prevListSize !=main.buttonList.size())){
					this.prevListSize = main.buttonList.size();
					main.buttonList.clear();
					newMainMenuButtonList.clear();
					createFakeMainMenuButtonList();
					this.displayedMainMenu = true;
				}
				this.prevWidth = mc.currentScreen.width;
				this.prevHeight = mc.currentScreen.height;
				boolean hasWSBIMButton = false;
				for(int i = 0; i < main.buttonList.size(); i++){
					if(main.buttonList.get(i) instanceof GuiButtonCustom){
						hasWSBIMButton = true;
					}
					if((main.buttonList.get(i) instanceof GuiButton) && !(main.buttonList.get(i) instanceof GuiButtonCustom)){
						main.buttonList.remove(i);
					}
				}
				if(!hasWSBIMButton) createFakeMainMenuButtonList();
				main.buttonList = newMainMenuButtonList;
			}
			if((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2])){
				Minecraft mc = Minecraft.getMinecraft();
				GuiMainMenu main = (GuiMainMenu)mc.currentScreen;
				if(!this.displayedMainMenu || prevWidth != mc.currentScreen.width ||  prevHeight != mc.currentScreen.height || (prevListSize !=0 && prevListSize !=main.buttonList.size())){
					this.prevListSize = main.buttonList.size();
					main.buttonList.clear();
					newTransparentButtonList.clear();
					createFakeTransparentButtonList();
					this.displayedMainMenu = true;
				}
				this.prevWidth = mc.currentScreen.width;
				this.prevHeight = mc.currentScreen.height;
				boolean hasWSBIMButton = false;
				for(int i = 0; i < main.buttonList.size(); i++){
					if(main.buttonList.get(i) instanceof GuiButtonTransparent){
						hasWSBIMButton = true;
					}
					if((main.buttonList.get(i) instanceof GuiButton) && !(main.buttonList.get(i) instanceof GuiButtonTransparent)){
						main.buttonList.remove(i);
					}
				}
				if(!hasWSBIMButton) createFakeTransparentButtonList();
				main.buttonList = newTransparentButtonList;
			}
		}
	}
	
	public void createFakeMainMenuButtonList(){
		GuiMainMenu main = new GuiMainMenu();
		Minecraft mc = Minecraft.getMinecraft();
		main.mc = mc;
		main.fontRenderer = mc.fontRenderer;
		main.initGui();
		fake1 = main;
		for(int i = 0; i < main.buttonList.size(); i++){
			if(main.buttonList.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)main.buttonList.get(i);
				GuiButtonCustom newButt = null;
				int buttonHeight = (current.height / 3) * 2;
				int startYPos = (mc.currentScreen.height / 2) - ((main.buttonList.size() * buttonHeight) / 2) + 20;
				if(current instanceof GuiButtonLanguage){
					current.displayString = "Language selection";
				}
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1]))newButt = new GuiButtonCustom(current.id, 0, startYPos + (buttonHeight * i), mc.currentScreen.width, buttonHeight, current.displayString);
				else newButt = new GuiButtonCustom(current.id, current.x, current.y, current.width, current.height, current.displayString);
				int xpos = 0;
				int ypos = 0;
				newButt.enabled = current.enabled;
				newButt.visible = current.visible;
				newButt.packedFGColour = current.packedFGColour;
				newMainMenuButtonList.add(newButt);
			}
		}
	}
	
	public void createFakeTransparentButtonList(){
		GuiMainMenu main = new GuiMainMenu();
		Minecraft mc = Minecraft.getMinecraft();
		main.mc = mc;
		main.fontRenderer = mc.fontRenderer;
		main.width = Minecraft.getMinecraft().currentScreen.width;
		main.height = Minecraft.getMinecraft().currentScreen.height;
		main.initGui();
		fake2 = main;
		for(int i = 0; i < main.buttonList.size(); i++){
			if(main.buttonList.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)main.buttonList.get(i);
				GuiButtonTransparent newButt = null;
				if(current instanceof GuiButtonLanguage){
					current.displayString = "LANG";
				}
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2]))newButt = new GuiButtonTransparent(current.id, current.x, current.y, current.width, current.height, current.displayString);
				int xpos = 0;
				int ypos = 0;				
				newButt.enabled = current.enabled;
				newButt.visible = current.visible;
				newButt.packedFGColour = current.packedFGColour;
				newTransparentButtonList.add(newButt);
			}
		}
	}
}
