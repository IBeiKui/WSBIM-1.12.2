package com.finalkg.wsbim.client.gui.screen.options;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.client.lib.option.OptionColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

public class GuiButtonOptionColor extends GuiButton {
	private OptionColor op;

	public GuiButtonOptionColor(OptionColor option, int id, int x, int y,
			int width, int height, String guiName) {
		super(id, x, y, width, height, guiName);
		this.op = option;
	}

	/**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY){
    	if(this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height){
    		mc.displayGuiScreen(op.getGuiToOpen());
    		return true;
    	}
    	else return false;
    }
    
    public void drawButton(Minecraft mc, int x, int y, float renderTick){
    	super.drawButton(mc, x, y, renderTick);
    	this.enabled = WSBIM.options.colorMode.equals(WSBIM.options.colorModes[0]);
    	List<String> list = OptionDescriptions.getItemDescription(op.variableName);
    	String hex = op.getColor();
    	int r = 0;
    	int g = 0;
    	int b = 0;
    	if(!hex.equals("0")){
    		Color c = new Color((int)Long.parseLong(hex.substring(2), 16));
    		r = c.getRed();
    		g = c.getGreen(); 
    		b = c.getBlue();
    	}
        float red = r/255F;
        float green = g/255F;
        float blue = b/255F;
    	GuiHelper.drawSpecificRect((this.x + width) - 2 - 16,(this.y + height) - 2 - 16, (this.x + width) - 2, (this.y + height) - 2, red, green, blue, 1F);
    	this.drawDescription(mc, x, y);
    }
    long ms = 0L;
    boolean flag = false;
    public boolean showingTooltip;
  
    public void drawDescription(Minecraft mc, int x, int y){
    	List<String> list = OptionDescriptions.getItemDescription(op.variableName);
    	if(!flag){
    		showingTooltip = false;
    	}
    	if(list !=null && list.size() > 0 && this.enabled && this.hovered && flag){
    		
    		if(!(ms+600 >= System.currentTimeMillis())){
    			this.showingTooltip = true;
    			this.drawHoveringText(list, this.x-8, this.y + this.height * 2 - 4, mc.fontRenderer);
    		}
    	}
    	else{
    		flag = false;
    		showingTooltip = false;
    	}
    	
    	if(!flag){
    		ms = System.currentTimeMillis();
    		flag = true;
    	}
    }
    
    protected void drawHoveringText(List list, int x, int y, FontRenderer font){
        if (!list.isEmpty()){
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.pushMatrix();
            int k = 0;
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);
                if (l > k){
                    k = l;
                }
            }
            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;
            if (list.size() > 1){
                i1 += 2 + (list.size() - 1) * 10;
            }
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
            for (int i2 = 0; i2 < list.size(); ++i2){
                String s1 = (String)list.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);
                if (i2 == 0){
                    k2 += 2;
                }
                k2 += 10;
            }
            GlStateManager.popMatrix();
        }
    }
}
