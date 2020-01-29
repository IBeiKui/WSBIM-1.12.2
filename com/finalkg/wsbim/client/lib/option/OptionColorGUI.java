package com.finalkg.wsbim.client.lib.option;

import java.lang.reflect.Field;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.gui.screen.options.GuiModifyColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.WSBIMOptions.OptionType;
import com.finalkg.wsbim.client.gui.screen.options.GuiModifyColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class OptionColorGUI extends Option {

	public OptionColorGUI(boolean gui, String variableName, String guiName) {
		super(gui, variableName, guiName, OptionType.GUI);
	}

	@Override
	public Object getObjectForCycle(int cycle) {
		return null;
	}

	@Override
	public int getCycleLength() {
		return 0;
	}

	@Override
	public int getCycleStart() {
		return 0;
	}

	@Override
	public String getStringForCycle(int cycle) {
		return null;
	}

	@Override
	public GuiScreen getGuiToOpen() {
		//TODO Color modifier GUI
		return new GuiModifyColor(this, Minecraft.func_71410_x().field_71462_r);
	}

	public String getColor(){
		try {
			Field f = WSBIMOptions.class.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
				String colorstr = (String)f.get(WSBIM.options);
				
				return colorstr;
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
		
		
		return null;
	}
	
	public void setHexColor(int color){
		try {
			Field f = WSBIMOptions.class.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
			f.set(WSBIM.options, color+"");
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
		
		
		
	}
	public void setRGBColor(int red, int green, int blue){
		try {
			Field f = WSBIMOptions.class.getDeclaredField(variableName);
			f.setAccessible(true);
			try {
				//String hex = String.format("%02x%02x%02x", red, green, blue);
				String hex = toHex(red,green,blue);
			f.set(WSBIM.options, hex);
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
		
		
		
	}
	  /**
	   * Returns a web browser-friendly HEX value representing the colour in the default sRGB
	   * ColorModel.
	   *
	   * @param r red
	   * @param g green
	   * @param b blue
	   * @return a browser-friendly HEX value
	   */
	  public static String toHex(int r, int g, int b) {
	    return "0x" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
	  }

	  private static String toBrowserHexValue(int number) {
	    StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
	    while (builder.length() < 2) {
	      builder.append("0");
	    }
	    return builder.toString();
	  }
}
