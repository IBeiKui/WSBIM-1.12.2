package com.finalkg.wsbim.client.lib.option;

import com.finalkg.wsbim.WSBIMOptions.OptionType;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class Option{
	
	public boolean avalableOption;
	public String variableName;
	public OptionType type;
	public String guiName;
	
	public Option(boolean gui, String variableName, String unlocalizedName, OptionType type){
		this.avalableOption = gui;
		this.variableName = variableName;
		this.guiName = I18n.func_135052_a(unlocalizedName, new Object[0]);
		this.type = type;
	}
	
	public abstract Object getObjectForCycle(int cycle);
	public abstract int getCycleLength();
	public abstract int getCycleStart();
	public abstract String getStringForCycle(int cycle);
	@SideOnly(Side.CLIENT)
	public abstract GuiScreen getGuiToOpen();
}

