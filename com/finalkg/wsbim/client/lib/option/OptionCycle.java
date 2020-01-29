package com.finalkg.wsbim.client.lib.option;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import net.minecraft.client.gui.GuiScreen;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;

import net.minecraft.client.gui.GuiScreen;

public class OptionCycle extends Option {
	private String[] cycleStrings;

	public OptionCycle(boolean gui, String variableName, String guiName,
			String[] cycles) {
		super(gui, variableName, guiName, OptionType.CYCLE);
		this.cycleStrings = cycles;
	}

	public Object getObjectForCycle(int cycle) {
		return cycleStrings[cycle];
	}

	public int getCycleLength() {
		return cycleStrings.length;
	}

	public int getCycleStart() {
		// TODO Auto-generated method stub
		List<String> cycleStringList = new ArrayList<String>();
		for(int i = 0; i < this.cycleStrings.length; i++){
			cycleStringList.add(cycleStrings[i]);
		}
		
		String current = null;
		try {
			Field f = WSBIMOptions.class.getDeclaredField(this.variableName);
			f.setAccessible(true);
			try {
				current = (String)f.get(WSBIM.options);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(current !=null){
			if(cycleStringList.contains(current)){
				return cycleStringList.indexOf(current);
			}
		}
		
		return 0;
	}

	public String getStringForCycle(int cycle) {
		return cycleStrings[cycle];
	}

	@Override
	public GuiScreen getGuiToOpen() {
		return null;
	}

}
