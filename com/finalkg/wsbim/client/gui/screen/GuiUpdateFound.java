package com.finalkg.wsbim.client.gui.screen;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.finalkg.wsbim.WSBIM;
import javax.swing.SwingWorker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;


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
	
	public void func_73863_a(int mx, int my, float part){
		this.func_146276_q_();
		super.func_73863_a(mx, my, part);
		String line1 = I18n.func_135052_a("gui.wsbim.update.line1", new Object[0])+" "+TextFormatting.GREEN+WSBIM.NAME+" (Version "+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+")";
		this.func_73731_b(Minecraft.func_71410_x().field_71466_p, line1, this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a(line1) / 2), this.field_146295_m / 2 - 80  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), 16777215);
		// (This will pause your game and bring you to the desktop)
		String line2 = I18n.func_135052_a("gui.wsbim.update.line2", new Object[0]);
		this.func_73731_b(Minecraft.func_71410_x().field_71466_p, line2, this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a(line2) / 2), this.field_146295_m / 2  - 70 - (Minecraft.func_71410_x().field_71466_p.field_78288_b), 16777215);
		String line3 = " "+I18n.func_135052_a("gui.wsbim.update.line3", new Object[0]);
		this.func_73731_b(Minecraft.func_71410_x().field_71466_p, line3, this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a(line3) / 2), this.field_146295_m / 2  - 60 - (Minecraft.func_71410_x().field_71466_p.field_78288_b), 16777215);
		
		this.yesButton.field_146128_h = this.field_146294_l / 2 - (this.yesButton.field_146120_f / 2);
		this.yesButton.field_146129_i = this.field_146295_m / 2 - 20;
		
		this.noButton.field_146128_h = this.field_146294_l / 2 - (this.noButton.field_146120_f / 2);
		this.noButton.field_146129_i = this.field_146295_m / 2;
		
		this.openForums.field_146128_h = this.field_146294_l / 2 - (this.openForums.field_146120_f / 2);
		this.openForums.field_146129_i = this.field_146295_m / 2 + 20;
		
		this.openForums.field_146124_l = WSBIM.updateChecker.hasModForumsLink() && !WSBIM.updateChecker.getModForumsLink().equals("null");
	}
	
	public void func_73866_w_(){
		super.func_73866_w_();
		this.yesButton = new GuiButton(0,0,0,TextFormatting.GREEN+I18n.func_135052_a("gui.wsbim.yes", new Object[0]));
		this.noButton = new GuiButton(1,0,20,TextFormatting.RED+I18n.func_135052_a("gui.wsbim.no", new Object[0]));
		this.openForums = new GuiButton(2,0,40,TextFormatting.GOLD+I18n.func_135052_a("gui.wsbim.openForums", new Object[0]));
		this.field_146292_n.add(yesButton);
		this.field_146292_n.add(noButton);
		this.field_146292_n.add(openForums);
	}
	public void func_146284_a(GuiButton button){
		if(button.field_146127_k == 0){
			Minecraft.func_71410_x().func_147108_a(nextGui);
			try {
				org.lwjgl.opengl.Display.setFullscreen(false);
			} catch (org.lwjgl.LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Minecraft.func_71410_x().func_71364_i();
			SwingWorker thread = new SwingWorker<Object, Object>(){

	    		@Override
	    		protected Object doInBackground() throws Exception {
	    			if(WSBIM.updateChecker.downloadUpdateThroughDesktop(false)){
	    				shutdown = true;
	    				Minecraft.func_71410_x().func_71400_g();
	    				
	    			}
	    			else{
	    				shutdown = false;
	    			}
	    			
					return null;
	    		}
			};
			thread.execute();
		}
		if(button.field_146127_k == 1){
			Minecraft.func_71410_x().func_147108_a(nextGui);
			if(Minecraft.func_71410_x().field_71439_g !=null)Minecraft.func_71410_x().field_71439_g.func_145747_a(new TextComponentString("[WSBIM] Update was cancelled by user."));
		}
		if(button.field_146127_k == 2) {
			try {
				Desktop.getDesktop().browse(new URI(WSBIM.updateChecker.getModForumsLink()));
			} catch (IOException | URISyntaxException e) {
				WSBIM.logger.warn("Unable to open mod forums link.");
				e.printStackTrace();
			}
		}
	}
}
