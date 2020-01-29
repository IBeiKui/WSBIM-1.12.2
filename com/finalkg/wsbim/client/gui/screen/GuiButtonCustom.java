package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;

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
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float renderTick){
        if (this.visible){
            FontRenderer fontrenderer = mc.fontRenderer;
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int k = this.getHoverState(this.hovered);
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
            else if (!this.enabled){
                l = 10526880;
                alpha = 0.2F;
            }
            else if (this.hovered){
                l = 16777120;
                alpha = WSBIM.options.defaultForegroundOpacity;
            }
            int j = ColorHelper.convertRGBToInteger(textred, textgreen, textblue);
            if (!this.enabled)
            {
                j = ColorHelper.BLACK;
            }
            GuiHelper.drawSpecificRect(x, y, x+width, y+height, this.hovered ? (foregroundred / 255F) : (backgroundred / 255F), this.hovered ? (foregroundgreen / 255F) : (backgroundgreen / 255F), this.hovered ? (foregroundblue / 255F) : (backgroundblue / 255F), alpha);
            this.mouseDragged(mc, mouseX, mouseY);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.displayString, this.x + width/2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(this.displayString) / 2), this.y + (this.height - 8) / 2, j);
        }
    }
}
