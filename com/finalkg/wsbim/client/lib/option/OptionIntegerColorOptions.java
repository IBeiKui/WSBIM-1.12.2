package com.finalkg.wsbim.client.lib.option;

import java.lang.reflect.Field;

import com.finalkg.wsbim.client.gui.screen.options.GuiModifyColor;

public class OptionIntegerColorOptions extends OptionInteger {

	
	private GuiModifyColor colorGui;
	private Class varClass;
	
	public OptionIntegerColorOptions(GuiModifyColor gui, Class varClass, String variableName, String guiName) {
		super(true, variableName, guiName, 0, 255, 1);
		// TODO Auto-generated constructor stub
		this.colorGui = gui;
		this.varClass = varClass;
	}
	@Override
	public int getStartingValue(){
		try {
			Field f = varClass.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
				return f.getInt(this.colorGui);
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
