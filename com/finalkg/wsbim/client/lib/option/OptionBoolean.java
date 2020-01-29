package com.finalkg.wsbim.client.lib.option;

import java.lang.reflect.Field;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import net.minecraft.client.gui.GuiScreen;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;

import net.minecraft.client.gui.GuiScreen;

public class OptionBoolean extends Option{

	public OptionBoolean(boolean gui, String variableName, String guiName) {
		super(gui, variableName, guiName, OptionType.BOOLEAN);
		
	}

	public Object getObjectForCycle(int cycle) {
		if(cycle == 0){
			return true;
		}
		else if(cycle == 1){
			return false;
		}
		else return null;
	}

	public int getCycleLength() {
		return 2;
	}

	public boolean getBooleanForCycle(int cycle){
		if(cycle == 0){
			return true;
		}
		else if(cycle == 1){
			return false;
		}
		else return false;
	}
	
	public String getStringForCycle(int cycle) {
		return cycle == 0 ? "Yes":"No";
	}

	public int getCycleStart() {
		Field f = null;
		try {
			f = WSBIMOptions.class.getDeclaredField(variableName);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setAccessible(true);
		boolean bool = false;
		try {
			bool = f.getBoolean(WSBIM.options);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(bool == true){
			return 0;
		}
		else if(bool == false){
			return 1;
		}
		return 0;
	}

	@Override
	public GuiScreen getGuiToOpen() {
		return null;
	}

}
