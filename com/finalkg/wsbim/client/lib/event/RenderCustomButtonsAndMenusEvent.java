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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


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
			if((Minecraft.func_71410_x().field_71462_r instanceof GuiIngameMenu) && WSBIM.options.useWSBIMPauseMenu){
				Minecraft mc = Minecraft.func_71410_x();
				//Force display our new pause menu
				mc.func_147108_a(new GuiWSBIMPauseMenu());
			}
			if((Minecraft.func_71410_x().field_71462_r instanceof GuiMainMenu) && WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1])){
				Minecraft mc = Minecraft.func_71410_x();
				GuiMainMenu main = (GuiMainMenu)mc.field_71462_r;
				if(!this.displayedMainMenu || prevWidth != mc.field_71462_r.field_146294_l || prevHeight != mc.field_71462_r.field_146295_m || (prevListSize !=0 && prevListSize !=main.field_146292_n.size())){
					this.prevListSize = main.field_146292_n.size();
					main.field_146292_n.clear();
					newMainMenuButtonList.clear();
					createFakeMainMenuButtonList();
					this.displayedMainMenu = true;
				}
				this.prevWidth = mc.field_71462_r.field_146294_l;
				this.prevHeight = mc.field_71462_r.field_146295_m;
				boolean hasWSBIMButton = false;
				for(int i = 0; i < main.field_146292_n.size(); i++){
					if(main.field_146292_n.get(i) instanceof GuiButtonCustom){
						hasWSBIMButton = true;
					}
					if((main.field_146292_n.get(i) instanceof GuiButton) && !(main.field_146292_n.get(i) instanceof GuiButtonCustom)){
						main.field_146292_n.remove(i);
					}
				}
				if(!hasWSBIMButton) createFakeMainMenuButtonList();
				main.field_146292_n = newMainMenuButtonList;
			}
			if((Minecraft.func_71410_x().field_71462_r instanceof GuiMainMenu) && WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2])){
				Minecraft mc = Minecraft.func_71410_x();
				GuiMainMenu main = (GuiMainMenu)mc.field_71462_r;
				if(!this.displayedMainMenu || prevWidth != mc.field_71462_r.field_146294_l ||  prevHeight != mc.field_71462_r.field_146295_m || (prevListSize !=0 && prevListSize !=main.field_146292_n.size())){
					this.prevListSize = main.field_146292_n.size();
					main.field_146292_n.clear();
					newTransparentButtonList.clear();
					createFakeTransparentButtonList();
					this.displayedMainMenu = true;
				}
				this.prevWidth = mc.field_71462_r.field_146294_l;
				this.prevHeight = mc.field_71462_r.field_146295_m;
				boolean hasWSBIMButton = false;
				for(int i = 0; i < main.field_146292_n.size(); i++){
					if(main.field_146292_n.get(i) instanceof GuiButtonTransparent){
						hasWSBIMButton = true;
					}
					if((main.field_146292_n.get(i) instanceof GuiButton) && !(main.field_146292_n.get(i) instanceof GuiButtonTransparent)){
						main.field_146292_n.remove(i);
					}
				}
				if(!hasWSBIMButton) createFakeTransparentButtonList();
				main.field_146292_n = newTransparentButtonList;
			}
		}
	}
	
	public void createFakeMainMenuButtonList(){
		GuiMainMenu main = new GuiMainMenu();
		Minecraft mc = Minecraft.func_71410_x();
		main.field_146297_k = mc;
		main.field_146289_q = mc.field_71466_p;
		main.func_73866_w_();
		fake1 = main;
		for(int i = 0; i < main.field_146292_n.size(); i++){
			if(main.field_146292_n.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)main.field_146292_n.get(i);
				GuiButtonCustom newButt = null;
				int buttonHeight = (current.field_146121_g / 3) * 2;
				int startYPos = (mc.field_71462_r.field_146295_m / 2) - ((main.field_146292_n.size() * buttonHeight) / 2) + 20;
				if(current instanceof GuiButtonLanguage){
					current.field_146126_j = "Language selection";
				}
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1]))newButt = new GuiButtonCustom(current.field_146127_k, 0, startYPos + (buttonHeight * i), mc.field_71462_r.field_146294_l, buttonHeight, current.field_146126_j);
				else newButt = new GuiButtonCustom(current.field_146127_k, current.field_146128_h, current.field_146129_i, current.field_146120_f, current.field_146121_g, current.field_146126_j);
				int xpos = 0;
				int ypos = 0;
				newButt.field_146124_l = current.field_146124_l;
				newButt.field_146125_m = current.field_146125_m;
				newButt.packedFGColour = current.packedFGColour;
				newMainMenuButtonList.add(newButt);
			}
		}
	}
	
	public void createFakeTransparentButtonList(){
		GuiMainMenu main = new GuiMainMenu();
		Minecraft mc = Minecraft.func_71410_x();
		main.field_146297_k = mc;
		main.field_146289_q = mc.field_71466_p;
		main.field_146294_l = Minecraft.func_71410_x().field_71462_r.field_146294_l;
		main.field_146295_m = Minecraft.func_71410_x().field_71462_r.field_146295_m;
		main.func_73866_w_();
		fake2 = main;
		for(int i = 0; i < main.field_146292_n.size(); i++){
			if(main.field_146292_n.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)main.field_146292_n.get(i);
				GuiButtonTransparent newButt = null;
				if(current instanceof GuiButtonLanguage){
					current.field_146126_j = "LANG";
				}
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2]))newButt = new GuiButtonTransparent(current.field_146127_k, current.field_146128_h, current.field_146129_i, current.field_146120_f, current.field_146121_g, current.field_146126_j);
				int xpos = 0;
				int ypos = 0;				
				newButt.field_146124_l = current.field_146124_l;
				newButt.field_146125_m = current.field_146125_m;
				newButt.packedFGColour = current.packedFGColour;
				newTransparentButtonList.add(newButt);
			}
		}
	}
}
