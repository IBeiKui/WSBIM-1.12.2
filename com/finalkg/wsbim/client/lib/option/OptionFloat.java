package com.finalkg.wsbim.client.lib.option;

import java.lang.reflect.Field;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;

import net.minecraft.client.gui.GuiScreen;

public class OptionFloat extends Option{
	private float min;
	private float max;
	private float step;
	
	public OptionFloat(boolean gui, String variableName, String guiName,
			float min, float max, float stepping) {
		super(gui, variableName, guiName, OptionType.FLOAT);
		this.min = min;
		this.max = max;
		this.step = stepping;
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
		return null;
	}

	public GuiScreen getGuiToOpen() {
		return null;
	}
	
	public float getMax(){
		return max;
	}
	
	public float getMin(){
		return min;
	}
	
	public float getValueStep(){
		return step;
	}
	
	public float getStartingValue(){
		try {
			Field f = WSBIMOptions.class.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
				return f.getFloat(WSBIM.options);
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
		
		return 0;
	}
}
