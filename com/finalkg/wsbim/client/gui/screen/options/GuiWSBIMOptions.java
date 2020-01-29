package com.finalkg.wsbim.client.gui.screen.options;

import java.io.IOException;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.lib.Position;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.client.lib.option.OptionFloat;
import com.finalkg.wsbim.client.lib.option.OptionInteger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWSBIMOptions extends GuiScreen{
	private Minecraft mc;
	private GuiScreen prevScreen;
	private int optionStart;
	private int optionEnd;
	public GuiWSBIMOptions(Minecraft minecraft, GuiScreen prevScreen, int optionIDStart, int optionIDEnd){
		this.mc = minecraft;
		this.prevScreen = prevScreen;
		this.optionStart = optionIDStart;
		this.optionEnd = optionIDEnd;
	}
    public void onGuiClosed() {
    	super.onGuiClosed();
    	try {
			WSBIM.options.saveOptions(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void drawScreen(int x, int y, float renderTick){
    	 this.drawDefaultBackground();
    	 int k;
    	 for (k = 0; k < this.labelList.size(); ++k){
             ((GuiLabel)this.labelList.get(k)).drawLabel(this.mc, x, y);
         }
         for (k = 0; k < this.buttonList.size(); ++k){
             ((GuiButton)this.buttonList.get(k)).drawButton(this.mc, x, y, renderTick);
         }
         for (k = 0; k < this.buttonList.size(); ++k){
        	 if(((GuiButton)this.buttonList.get(k)) instanceof GuiButtonOption) ((GuiButtonOption)this.buttonList.get(k)).drawDescription(mc, x, y);
         }
         this.drawCenteredString(this.fontRenderer, I18n.format("gui.wsbim.options.title", new Object[0]) + " (Version "+WSBIM.VERSION+")", this.width / 2, 15, 16777215);
    }

	public void initGui(){
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
		int pos_num = 0;
		for(int i = this.optionStart; i < this.optionEnd; i++){
			Option option = (Option) WSBIM.options.optionsList.get(i);
			if(option !=null){
				if(pos_num < 8){
					Position pos = this.getPos(pos_num);
					if(pos !=null && option.avalableOption){
						if((option.type !=OptionType.INT) && (option.type !=OptionType.FLOAT) && (option.type != OptionType.STRING) && (option.type !=OptionType.GUI)){
							this.buttonList.add(new GuiButtonOption((Option)WSBIM.options.optionsList.get(i), -1, pos.x, pos.y-24, 150, 20, ((Option)WSBIM.options.optionsList.get(i)).guiName));
						}
						else if(option.type == OptionType.GUI){
							this.buttonList.add(new GuiButtonOpenGUI(option, -1, pos.x, pos.y-24, 150, 20, option.guiName));
						}
						else if(option.type == OptionType.INT){
							this.buttonList.add(new GuiSliderOptionInteger(-1, pos.x, pos.y-24, (OptionInteger) option));
						}
						else if(option.type == OptionType.FLOAT){
							this.buttonList.add(new GuiSliderOptionFloat(-1, pos.x, pos.y-24, (OptionFloat)option));
						}
					}
				}
				pos_num++;
			}
		}
		Position position = this.getPos(7);
		this.buttonList.add(new GuiButton(201, this.width/2-100, this.height/6 +142, 200, 20, I18n.format("gui.wsbim.options.setDefaults", new Object[0]))); //IGNORE: - was moved above the done button tomporary because I thought the bag slot option worked. Im now disabling it entirely (version 1.7.3).
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
			this.openPrevGui(mc);
		}
		if(b.id == 201){
			this.openPrevGui(mc);
			WSBIM.options.setDefaults();
			mc.displayGuiScreen(this);
		}
	}
	
	public void openPrevGui(Minecraft mc){
		mc.displayGuiScreen(prevScreen);
	}
}
