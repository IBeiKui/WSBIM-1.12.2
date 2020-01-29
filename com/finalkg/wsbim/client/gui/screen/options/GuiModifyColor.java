package com.finalkg.wsbim.client.gui.screen.options;

import java.awt.Color;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.GuiSliderOptionColor;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.client.lib.Position;
import com.finalkg.wsbim.client.lib.option.OptionColorGUI;
import com.finalkg.wsbim.client.lib.option.OptionIntegerColor;
import com.finalkg.wsbim.client.lib.option.OptionIntegerColorOptions;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiModifyColor extends GuiScreen {

	private OptionColorGUI colorOption;
	private GuiScreen prevGui;
	private OptionIntegerColorOptions redOption;
	private OptionIntegerColorOptions greenOption;
	private OptionIntegerColorOptions blueOption;
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	public GuiModifyColor(OptionColorGUI optionColor, GuiScreen prevGui) {
		this.colorOption = optionColor;
		this.prevGui = prevGui;
	}
	
	public void drawScreen(int mx, int my, float renderTick){
		super.drawScreen(mx, my, renderTick);
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, I18n.format("gui.wsbim.options.changeColorOption", new Object[0]) + ": " + this.colorOption.guiName + " (Version "+WSBIM.VERSION+")", this.width / 2, 15, 16777215);
		int k;
        for (k = 0; k < this.buttonList.size(); ++k){ 
        	((GuiButton)this.buttonList.get(k)).drawButton(this.mc, mx, my, renderTick);
        }
        for (k = 0; k < this.buttonList.size(); ++k){
        	if(((GuiButton)this.buttonList.get(k)) instanceof GuiButtonOption) ((GuiButtonOption)this.buttonList.get(k)).drawDescription(mc, mx, my);
        }
         
        for (k = 0; k < this.labelList.size(); ++k){
             ((GuiLabel)this.labelList.get(k)).drawLabel(this.mc, mx, my);
        }
        Position pos = this.getPos(1);
        int x = pos.x + 40;
        int y = pos.y - 32;
        float redf = red/255F;
        float greenf = green/255F;
        float bluef = blue/255F;
        GuiHelper.drawSpecificRect(x, y, x+80, y+80, redf, greenf, bluef, 1F);
	}
	
	public void initGui(){
		String hex = this.colorOption.getColor();
    	int r = 0;
    	int g = 0;
    	int b = 0;
    	if(!hex.equals("0")){
    		Color c = new Color((int)Long.parseLong(hex.substring(2), 16));
    		r = c.getRed();
    		g = c.getGreen(); 
    		b = c.getBlue();
    	}
    	red = r;
    	green = g;
    	blue = b;
		redOption = new OptionIntegerColorOptions(this, getClass(), "red", "gui.wsbim.colorRed");
		greenOption = new OptionIntegerColorOptions(this, getClass(), "green", "gui.wsbim.colorGreen");
		blueOption = new OptionIntegerColorOptions(this, getClass(), "blue", "gui.wsbim.colorBlue");
		Position pos = this.getPos(0);
		this.buttonList.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos.x, pos.y-24, redOption));
		Position pos1 = this.getPos(2);
		this.buttonList.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos1.x, pos1.y-24, greenOption));
		Position pos2 = this.getPos(4);
		this.buttonList.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos2.x, pos2.y-24, blueOption));
		
		this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 6 + 148, I18n.format("gui.done", new Object[0])));
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.cancel", new Object[0])));
		
	}
	public Position getPos(int num){
		switch(num){
			case 0:
				return new Position(this.width / 2 - 155, this.height / 6 + 72 - 6);
			case 1:
				return new Position(this.width / 2 + 5, this.height / 6 + 72 - 6);
			case 2:
				return new Position(this.width / 2 - 155, this.height / 6 + 96 - 6);
			case 3:
				return new Position(this.width / 2 + 5, this.height / 6 + 96 - 6);
			case 4:
				return new Position(this.width / 2 - 155, this.height / 6 + 120 - 6);
			case 5:
				return new Position(this.width / 2 + 5, this.height / 6 + 120 - 6);
			case 6:
				return new Position(this.width / 2 - 155, this.height / 6 + 144 - 6);
			case 7:
				return new Position(this.width / 2 + 5, this.height / 6 + 144 - 6);
		}
		return null;
	}
	public void actionPerformed(GuiButton b){
		if(b.id == 200){
			mc.displayGuiScreen(prevGui);
		}
		if(b.id == 201){
			this.colorOption.setRGBColor(red, green, blue);
			mc.displayGuiScreen(prevGui);
		}
	}
    protected void keyTyped(char c, int key){
        if (key == 1){
        	this.colorOption.setRGBColor(red, green, blue);
            this.mc.displayGuiScreen(prevGui);
            this.mc.setIngameFocus();
        }
    }
}
