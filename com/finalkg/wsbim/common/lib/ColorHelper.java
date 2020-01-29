package com.finalkg.wsbim.common.lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**Contains static color values and functions to aid in coloring*/
public class ColorHelper {

	public static final int GUI_CONTAINER_TEXT_COLOR = 4210752;
	public static final int GUI_CONTAINER_TEXT_COLOR_LIGHT = convertRGBToInteger(230, 230, 230);
	public static final int LEATHER_ARMOR_DEFAULT_COLOR = 10511680;
	public static final int WHITE = 16777215;
	public static final int ALL_BLACK = 0;
	public static final int ORANGE = 16351261;
	public static final int MAGENTA = 13061821;
	public static final int LIGHT_BLUE = 3847130;
	public static final int YELLOW = 16701501;
	public static final int LIME = 8439583;
	public static final int PINK = 15961002;
	public static final int GRAY = 4673362;
	public static final int SILVER = 10329495;
	public static final int CYAN = 1481884;
	public static final int PURPLE = 8991416;
	public static final int BLUE = 3949738;
	public static final int BROWN = 8606770;
	public static final int GREEN = 6192150;
	public static final int RED = 11546150;
	public static final int BLACK = 1908001;

	//Default Colors
	public static final String UI_DAY_FOREGROUND_COLOR = "0x4B4B4B";
	public static final String UI_DAY_BACKGROUND_COLOR = "0xC8C8C8";
	public static final String UI_DAY_TEXT_COLOR = "0xFFFFFF";
	public static final String UI_NIGHT_FOREGROUND_COLOR = "0x7D7D7D";
	public static final String UI_NIGHT_BACKGROUND_COLOR = "0x0F0F0F";
	public static final String UI_NIGHT_TEXT_COLOR = "0xFFFFFF";
	
	/**
	* Remove the color from the specified armor ItemStack.
	*/
	public static void removeColor(ItemStack stack){
		NBTTagCompound nbttagcompound = stack.func_77978_p();
		if(nbttagcompound != null){
			NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
		    if(nbttagcompound1.func_74764_b("color")){
		    	nbttagcompound1.func_82580_o("color");
		    }
		}
	}

	/**
	* Sets the color of the specified armor ItemStack
	*/
	public static void setColor(ItemStack stack, int color){
		NBTTagCompound nbttagcompound = stack.func_77978_p();
		if(nbttagcompound == null){
			nbttagcompound = new NBTTagCompound();
		    stack.func_77982_d(nbttagcompound);
		}
		NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
		if(!nbttagcompound.func_150297_b("display", 10)){
			nbttagcompound.func_74782_a("display", nbttagcompound1);
		}
		nbttagcompound1.func_74768_a("color", color);
	}
	
	/**
	 * Converts an integer color to RGB colors in a int array
	 * int[0] = R
	 * int[1] = G
	 * int[2] = B
	 * Simple as that.
	 * @param color
	 * @return
	 */
	public static int[] convertIntegerToRGB(int color) {
		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;
		int[] rgb = {red, green, blue};
		return rgb;
	}
	/**
	 * Converts three r, g, and b values to an integer
	 * for use with minecraft parsing.
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public static int convertRGBToInteger(int red, int green, int blue) {
		int rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		return rgb;
	}
	/**
	 * Converts RGB to hex string
	 * @param r
	 * @param g
	 * @param b
	 * @return
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

	    /**
	     * Return the color for the specified ItemStack. Will return -1 if not found.
	     */
	    public static int getColor(ItemStack stack){
	    	NBTTagCompound nbttagcompound = stack.func_77978_p();
	        if(nbttagcompound != null){
	        	NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
	            if(nbttagcompound1 != null && nbttagcompound1.func_150297_b("color", 3)){
	            	return nbttagcompound1.func_74762_e("color");
	            }
	        }
	        return -1;
	    }	 
}
