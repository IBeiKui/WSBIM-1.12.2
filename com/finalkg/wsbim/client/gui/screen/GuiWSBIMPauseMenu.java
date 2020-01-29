package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptions;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.WorldTimeHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.client.GuiModList;

public class GuiWSBIMPauseMenu extends GuiScreen {
	private List fakeButtonList = new ArrayList();
	private GuiIngameMenu fake;
	private int animY1 = 0;
	public void initGui(){
		super.initGui();
		this.fakeButtonList.clear();
		fake = new GuiIngameMenu();
		fake.mc = this.mc;
		fake.width = width;
		fake.height = height;
		fake.fontRenderer = this.fontRenderer;
		fake.eventButton = this.eventButton;
		fake.initGui();
		this.fakeButtonList = fake.buttonList;
		this.fakeButtonList.add(new GuiButton(150, this.width / 2 - 100, this.height / 4 + 120 + 8, I18n.format("gui.wsbim.button.options", new Object[0])));
		
		for(int i = 0; i < fakeButtonList.size(); i++){
			if(fakeButtonList.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)fakeButtonList.get(i);
				GuiButton newButt = null;
				int buttonHeight = (current.height / 3) * 2;
				int startYPos = (height / 2) - ((fakeButtonList.size() * buttonHeight) / 2);
				
				//Lines Theme
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2]))newButt = new GuiButtonTransparent(current.id, current.x, current.y, current.width, current.height, current.displayString);
				//List Theme
				else if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1]))newButt = new GuiButtonCustom(current.id, 0, startYPos + (buttonHeight * i), width, buttonHeight, current.displayString);
				//Vanilla Theme
				else newButt = new GuiButton(current.id, current.x, current.y, current.width, current.height, current.displayString);
				int xpos = 0;
				int ypos = 0;
				if(i < fakeButtonList.size() / 2){
					
				}
				newButt.enabled = current.enabled;
				newButt.visible = current.visible;
				newButt.packedFGColour = current.packedFGColour;
				
				this.buttonList.add(newButt);
			}
		}
	}
	
	public void drawScreen(int mx, int my, float renderTick){
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
    	String hex3 = WSBIM.options.textColor;
    	int textred = 0;
    	int textgreen = 0;
    	int textblue = 0;
    	if(!hex3.equals("0")){
    		Color c3 = new Color((int)Long.parseLong(hex3.substring(2), 16));
    		textred = c3.getRed();
    		textgreen = c3.getGreen(); 
    		textblue = c3.getBlue();
    	}
		this.drawDefaultBackground();
		super.drawScreen(mx, my, renderTick);
		//Draw the default background first
		//this.drawDefaultBackground();
		
		GuiHelper.drawSpecificRect(0, 20, this.width, 40, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		String line1 = I18n.format("menu.game");
		mc.fontRenderer.drawStringWithShadow(line1, this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(line1) / 2), 35  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		String linetodraw = "WSBIM "+WSBIM.VERSION + (WSBIM.updateChecker.updateNeeded ? " (Version "+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+" Available)" : "");
		mc.fontRenderer.drawStringWithShadow(linetodraw, this.width - this.fontRenderer.getStringWidth(linetodraw), this.height  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		String line2 = I18n.format("gui.wsbim.pausemenu.time", new Object[0]) + " " + WorldTimeHelper.getMilitaryTimeStringForMinecraftWorld(mc.world);
		mc.fontRenderer.drawStringWithShadow(line2, width - (Minecraft.getMinecraft().fontRenderer.getStringWidth(line2)) - 10, 35  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		
	}
	protected void actionPerformed(GuiButton button) throws IOException{
		if(button.id == 150) mc.displayGuiScreen(new GuiWSBIMOptions(mc, this, 0, 8));
        switch (button.id){
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                boolean flag = this.mc.isIntegratedServerRunning();
                boolean flag1 = this.mc.isConnectedToRealms();
                button.enabled = false;
                this.mc.world.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                if (flag){
                    this.mc.displayGuiScreen(new GuiMainMenu());
                }
                else if (flag1){
                    RealmsBridge realmsbridge = new RealmsBridge();
                    realmsbridge.switchToRealms(new GuiMainMenu());
                }
                else{
                    this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
                }

            case 2:
            case 3:
            default:
                break;
            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 5:
                if (this.mc.player != null)
                this.mc.displayGuiScreen(new GuiScreenAdvancements(this.mc.player.connection.getAdvancementManager()));
                break;
            case 6:
                if (this.mc.player != null)
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.player.getStatFileWriter()));
                break;
            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
            case 12:
                net.minecraftforge.fml.client.FMLClientHandler.instance().showGuiScreen(new GuiModList(this.fake));
                break;
        }
    }
}
