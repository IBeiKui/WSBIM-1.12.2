package com.finalkg.wsbim.client.lib.option;

import net.minecraft.client.gui.GuiScreen;

public class OptionIntegerColor extends OptionInteger {
	
	private final int startingValue;
	
	public OptionIntegerColor(String guiName, int start) {
		super(true, null, guiName, 0, 255, 1);
		// TODO Auto-generated constructor stub
		//this.colorGui = gui;
		this.startingValue = start;
	}
	@Override
	public int getStartingValue(){
		return this.startingValue;
	}

}
