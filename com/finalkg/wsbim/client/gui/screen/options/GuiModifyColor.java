package com.finalkg.wsbim.client.gui.screen.options;

import java.awt.Color;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.client.lib.Position;
import com.finalkg.wsbim.client.lib.option.OptionColorGUI;
import com.finalkg.wsbim.client.lib.option.OptionIntegerColorOptions;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;


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
	
	public void func_73863_a(int mx, int my, float renderTick){
		super.func_73863_a(mx, my, renderTick);
		this.func_146276_q_();
		this.func_73732_a(this.field_146289_q, I18n.func_135052_a("gui.wsbim.options.changeColorOption", new Object[0]) + ": " + this.colorOption.guiName + " (Version "+WSBIM.VERSION+")", this.field_146294_l / 2, 15, 16777215);
		int k;
        for (k = 0; k < this.field_146292_n.size(); ++k){ 
        	((GuiButton)this.field_146292_n.get(k)).func_191745_a(this.field_146297_k, mx, my, renderTick);
        }
        for (k = 0; k < this.field_146292_n.size(); ++k){
        	if(((GuiButton)this.field_146292_n.get(k)) instanceof GuiButtonOption) ((GuiButtonOption)this.field_146292_n.get(k)).drawDescription(field_146297_k, mx, my);
        }
         
        for (k = 0; k < this.field_146293_o.size(); ++k){
             ((GuiLabel)this.field_146293_o.get(k)).func_146159_a(this.field_146297_k, mx, my);
        }
        Position pos = this.getPos(1);
        int x = pos.x + 40;
        int y = pos.y - 32;
        float redf = red/255F;
        float greenf = green/255F;
        float bluef = blue/255F;
        GuiHelper.drawSpecificRect(x, y, x+80, y+80, redf, greenf, bluef, 1F);
	}
	
	public void func_73866_w_(){
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
		this.field_146292_n.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos.x, pos.y-24, redOption));
		Position pos1 = this.getPos(2);
		this.field_146292_n.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos1.x, pos1.y-24, greenOption));
		Position pos2 = this.getPos(4);
		this.field_146292_n.add(new GuiSliderOptionColorOptions(this, getClass(), -1, pos2.x, pos2.y-24, blueOption));
		
		this.field_146292_n.add(new GuiButton(201, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 148, I18n.func_135052_a("gui.done", new Object[0])));
		this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, I18n.func_135052_a("gui.cancel", new Object[0])));
		
	}
	public Position getPos(int num){
		switch(num){
			case 0:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 72 - 6);
			case 1:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 72 - 6);
			case 2:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96 - 6);
			case 3:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 96 - 6);
			case 4:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 120 - 6);
			case 5:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 120 - 6);
			case 6:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 144 - 6);
			case 7:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 144 - 6);
		}
		return null;
	}
	public void func_146284_a(GuiButton b){
		if(b.field_146127_k == 200){
			field_146297_k.func_147108_a(prevGui);
		}
		if(b.field_146127_k == 201){
			this.colorOption.setRGBColor(red, green, blue);
			field_146297_k.func_147108_a(prevGui);
		}
	}
    protected void func_73869_a(char c, int key){
        if (key == 1){
        	this.colorOption.setRGBColor(red, green, blue);
            this.field_146297_k.func_147108_a(prevGui);
            this.field_146297_k.func_71381_h();
        }
    }
}
