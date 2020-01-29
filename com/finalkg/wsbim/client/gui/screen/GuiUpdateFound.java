package com.finalkg.wsbim.client.gui.screen;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.SwingWorker;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuiUpdateFound extends GuiScreen{
	public GuiButton yesButton;
	public GuiButton noButton;
	public GuiButton openForums;
	private boolean shutdown;
	public GuiScreen nextGui;
	
	public GuiUpdateFound(GuiScreen nextScreen){
		this.nextGui = nextScreen;
	
	}
	
	public void drawScreen(int mx, int my, float part){
		this.drawDefaultBackground();
		super.drawScreen(mx, my, part);
		String line1 = I18n.format("gui.wsbim.update.line1", new Object[0])+" "+TextFormatting.GREEN+WSBIM.NAME+" (Version "+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+")";
		this.drawString(Minecraft.getMinecraft().fontRenderer, line1, this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(line1) / 2), this.height / 2 - 80  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		// (This will pause your game and bring you to the desktop)
		String line2 = I18n.format("gui.wsbim.update.line2", new Object[0]);
		this.drawString(Minecraft.getMinecraft().fontRenderer, line2, this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(line2) / 2), this.height / 2  - 70 - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		String line3 = " "+I18n.format("gui.wsbim.update.line3", new Object[0]);
		this.drawString(Minecraft.getMinecraft().fontRenderer, line3, this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(line3) / 2), this.height / 2  - 60 - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		
		this.yesButton.x = this.width / 2 - (this.yesButton.width / 2);
		this.yesButton.y = this.height / 2 - 20;
		
		this.noButton.x = this.width / 2 - (this.noButton.width / 2);
		this.noButton.y = this.height / 2;
		
		this.openForums.x = this.width / 2 - (this.openForums.width / 2);
		this.openForums.y = this.height / 2 + 20;
		
		this.openForums.enabled = WSBIM.updateChecker.hasModForumsLink() && !WSBIM.updateChecker.getModForumsLink().equals("null");
	}
	
	public void initGui(){
		super.initGui();
		this.yesButton = new GuiButton(0,0,0,TextFormatting.GREEN+I18n.format("gui.wsbim.yes", new Object[0]));
		this.noButton = new GuiButton(1,0,20,TextFormatting.RED+I18n.format("gui.wsbim.no", new Object[0]));
		this.openForums = new GuiButton(2,0,40,TextFormatting.GOLD+I18n.format("gui.wsbim.openForums", new Object[0]));
		this.buttonList.add(yesButton);
		this.buttonList.add(noButton);
		this.buttonList.add(openForums);
	}
	public void actionPerformed(GuiButton button){
		if(button.id == 0){
			Minecraft.getMinecraft().displayGuiScreen(nextGui);
			try {
				org.lwjgl.opengl.Display.setFullscreen(false);
			} catch (org.lwjgl.LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Minecraft.getMinecraft().setIngameNotInFocus();
			SwingWorker thread = new SwingWorker<Object, Object>(){

	    		@Override
	    		protected Object doInBackground() throws Exception {
	    			if(WSBIM.updateChecker.downloadUpdateThroughDesktop(false)){
	    				shutdown = true;
	    				Minecraft.getMinecraft().shutdown();
	    				
	    			}
	    			else{
	    				shutdown = false;
	    			}
	    			
					return null;
	    		}
			};
			thread.execute();
		}
		if(button.id == 1){
			Minecraft.getMinecraft().displayGuiScreen(nextGui);
			if(Minecraft.getMinecraft().player !=null)Minecraft.getMinecraft().player.sendMessage(new TextComponentString("[WSBIM] Update was cancelled by user."));
		}
		if(button.id == 2) {
			try {
				Desktop.getDesktop().browse(new URI(WSBIM.updateChecker.getModForumsLink()));
			} catch (IOException | URISyntaxException e) {
				WSBIM.logger.warn("Unable to open mod forums link.");
				e.printStackTrace();
			}
		}
	}
}
