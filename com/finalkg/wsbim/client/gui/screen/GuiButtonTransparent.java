package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;

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
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        if (this.visible){
            FontRenderer fontrenderer = mc.fontRenderer;
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
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
        	boolean highlight = this.hovered && this.enabled;
        	float alpha = WSBIM.options.defaultBackgroundOpacity;
        	if (!this.enabled){
                alpha = 0.2F;
            }
            else if (this.hovered){
                alpha = WSBIM.options.defaultForegroundOpacity;
            }
        	boolean flag = false;
        	flag = this.displayString.equals("LANG");
        	if(!flag) GuiHelper.drawSpecificRect(x, y, x + width, y + height, highlight ? (foregroundred / 255F) : (backgroundred / 255F), highlight ? (foregroundgreen / 255F) : (backgroundgreen / 255F), highlight ? (foregroundblue / 255F) : (backgroundblue / 255F), alpha);
        	else {
        		int k = 106;
        		if (this.hovered){
                    k += this.height;
                }
        		mc.renderEngine.bindTexture(BUTTON_TEXTURES);
        		GlStateManager.color(1F, 1F, 1F);
                this.drawTexturedModalRect(this.x, this.y, 0, k, this.width, this.height);
        	}
            this.mouseDragged(mc, mouseX, mouseY);
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
            if (!this.enabled)
            {
                j = ColorHelper.BLACK;
            }

            if(!flag) this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }
    }
}
