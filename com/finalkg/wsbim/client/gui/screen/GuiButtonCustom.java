package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.ColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;


import org.lwjgl.opengl.GL11;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonCustom extends GuiButton{

	public GuiButtonCustom(int id, int x, int y,
			int width, int height, String p_i1021_6_) {
		super(id, x, y, width, height, p_i1021_6_);
		// TODO Auto-generated constructor stub
	}
	/**
     * Draws this button to the screen.
     */
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float renderTick){
        if (this.field_146125_m){
            FontRenderer fontrenderer = mc.field_71466_p;
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int k = this.func_146114_a(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
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
        		Color c = new Color((int)Long.parseLong(hex2.substring(2), 16));
        		backgroundred = c.getRed();
        		backgroundgreen = c.getGreen(); 
        		backgroundblue = c.getBlue();
        	}
        	int l = 14737632;
            float alpha = WSBIM.options.defaultBackgroundOpacity;
            if (packedFGColour != 0){
                l = packedFGColour;
            }
            else if (!this.field_146124_l){
                l = 10526880;
                alpha = 0.2F;
            }
            else if (this.field_146123_n){
                l = 16777120;
                alpha = WSBIM.options.defaultForegroundOpacity;
            }
            int j = ColorHelper.convertRGBToInteger(textred, textgreen, textblue);
            if (!this.field_146124_l)
            {
                j = ColorHelper.BLACK;
            }
            GuiHelper.drawSpecificRect(field_146128_h, field_146129_i, field_146128_h+field_146120_f, field_146129_i+field_146121_g, this.field_146123_n ? (foregroundred / 255F) : (backgroundred / 255F), this.field_146123_n ? (foregroundgreen / 255F) : (backgroundgreen / 255F), this.field_146123_n ? (foregroundblue / 255F) : (backgroundblue / 255F), alpha);
            this.func_146119_b(mc, mouseX, mouseY);
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.field_146126_j, this.field_146128_h + field_146120_f/2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a(this.field_146126_j) / 2), this.field_146129_i + (this.field_146121_g - 8) / 2, j);
        }
    }
}
