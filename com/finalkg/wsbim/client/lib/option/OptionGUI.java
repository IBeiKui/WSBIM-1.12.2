package com.finalkg.wsbim.client.lib.option;

import com.finalkg.wsbim.WSBIMOptions.OptionType;
import net.minecraft.client.gui.GuiScreen;


import net.minecraft.client.gui.GuiScreen;

public class OptionGUI extends Option{
	
	private GuiScreen gui;

	public OptionGUI(GuiScreen screenToOpen, String guiName) {
		super(true, "", guiName, OptionType.GUI);
		this.gui = screenToOpen;
	}

	public Object getObjectForCycle(int cycle) {
		return null;
	}

	public int getCycleLength() {
		return 0;
	}

	public int getCycleStart() {
		return 0;
	}

	public String getStringForCycle(int cycle) {
		return "";
	}

	public GuiScreen getGuiToOpen() {
		return this.gui;
	}

}
