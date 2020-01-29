package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.ColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonTransparent extends GuiButton{

	public GuiButtonTransparent(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
    /**
     * Draws this button to the screen.
     */
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        if (this.field_146125_m){
            FontRenderer fontrenderer = mc.field_71466_p;
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int i = this.func_146114_a(this.field_146123_n);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
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
        	//this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        	boolean highlight = this.field_146123_n && this.field_146124_l;
        	float alpha = WSBIM.options.defaultBackgroundOpacity;
        	if (!this.field_146124_l){
                alpha = 0.2F;
            }
            else if (this.field_146123_n){
                alpha = WSBIM.options.defaultForegroundOpacity;
            }
        	boolean flag = false;
        	flag = this.field_146126_j.equals("LANG");
        	if(!flag) GuiHelper.drawSpecificRect(field_146128_h, field_146129_i, field_146128_h + field_146120_f, field_146129_i + field_146121_g, highlight ? (foregroundred / 255F) : (backgroundred / 255F), highlight ? (foregroundgreen / 255F) : (backgroundgreen / 255F), highlight ? (foregroundblue / 255F) : (backgroundblue / 255F), alpha);
        	else {
        		int k = 106;
        		if (this.field_146123_n){
                    k += this.field_146121_g;
                }
        		mc.field_71446_o.func_110577_a(field_146122_a);
        		GlStateManager.func_179124_c(1F, 1F, 1F);
                this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, k, this.field_146120_f, this.field_146121_g);
        	}
            this.func_146119_b(mc, mouseX, mouseY);
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
            int j = ColorHelper.convertRGBToInteger(textred, textgreen, textblue);
            if (!this.field_146124_l)
            {
                j = ColorHelper.BLACK;
            }

            if(!flag) this.func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, j);
        }
    }
}
