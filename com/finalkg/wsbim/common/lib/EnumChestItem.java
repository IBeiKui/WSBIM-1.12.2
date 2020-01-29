package com.finalkg.wsbim.common.lib;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumChestItem {

	SACK("Sack",176,149,2,9,8,18,8,67,WSBIM.MODID+":"+"textures/gui/container/small_chestitem.png", WSBIM.MODID + ":" + "textures/gui/container/dark/small_chestitem.png", "textures/gui/container/generic_54.png"),
	WALLET("Wallet",176,133,1,5,44,20,8,51,WSBIM.MODID+":"+"textures/gui/container/tiny_chestitem.png", WSBIM.MODID + ":" + "textures/gui/container/dark/tiny_chestitem.png", "textures/gui/container/hopper.png"),
	SMALL_BACKPACK("Backpack", 176, 167, 3, 9, 8, 18, 8, 85, WSBIM.MODID + ":" + "textures/gui/container/default_chestitem.png", WSBIM.MODID + ":" + "textures/gui/container/dark/default_chestitem.png", "textures/gui/container/generic_54.png"),
	MEDIUM_BACKPACK("Backpack", 176, 185, 4, 9, 8, 18, 8, 103, WSBIM.MODID + ":" + "textures/gui/container/medium_chestitem.png", WSBIM.MODID + ":" + "textures/gui/container/dark/medium_chestitem.png", "textures/gui/container/generic_54.png"),
	LARGE_BACKPACK("Backpack", 176, 222, 6, 9, 8, 18, 8, 140, WSBIM.MODID + ":" + "textures/gui/container/generic_54.png", WSBIM.MODID + ":" + "textures/gui/container/dark/generic_54.png", "textures/gui/container/generic_54.png"),
	EXTRA_LARGE_BACKPACK("Backpack", 194, 240, 7, 10, 8, 18, 17, 158, WSBIM.MODID + ":" + "textures/gui/container/extralarge_chestitem.png", WSBIM.MODID + ":" + "textures/gui/container/dark/extralarge_chestitem.png", null);
	
	private final String nameType;
	private final int inv_width;
	private final int inv_height;
	private final int rows;
	private final int columns;
	private final int rows_start;
	private final int colums_start;
	private final int player_inv_rows_start;
	private final int player_inv_columns_start;
	private final String gui_texture;
	private final String dark_gui_texture;
	private final String minecraft_alternative_texture;
	
	private EnumChestItem(String nametype, int inv_width, int inv_height, int rows, int colums, int rows_start, int colums_start, int player_inv_rows_start, int player_inv_columns_start, String gui_texture, String darkGuiTexture, String minecraftAlternative){
		this.inv_width = inv_width;
		this.inv_height = inv_height;
		this.rows = rows;
		this.columns = colums;
		this.rows_start = rows_start;
		this.colums_start = colums_start;
		this.gui_texture = gui_texture;
		this.dark_gui_texture = darkGuiTexture;
		this.player_inv_rows_start = player_inv_rows_start;
		this.player_inv_columns_start = player_inv_columns_start;
		this.nameType = nametype;
		this.minecraft_alternative_texture = minecraftAlternative;
	}
	
	/** GUI xSize of the inventory for this sack item */
	public int getGuiWidth() {return inv_width;}
	/** GUI ySize of the inventory for this sack item */
	public int getGuiHeight() {return inv_height;}
	/** Number of rows in the item inventory*/
	public int getNumRows() {return rows;}
	/** Number of columns in the item inventory*/
	public int getNumColumns() {return columns;}
	/** Number of slots in the item inventory*/
	public int getInventorySize() {return getNumRows() * getNumColumns();}
	/**Returns the location, in terms of the GUI texture, where the rows for this inventory will start*/
	public int getRowRenderStart() {return rows_start; }
	/**Returns the location, in terms of the GUI texture, where the columns for this inventory will start*/
	public int getColumnRenderStart() {return colums_start;}
	/**Returns the location, in terms of the GUI texture, where the rows start for the player inventory*/
	public int getInventoryPlayerRowStart() {return player_inv_rows_start;}
	/**Returns the location, in terms of the GUI texture, where the columns start for the player inventory*/
	public int getInventoryPlayerColumnStart() {return player_inv_columns_start;}
	/**Gets the name, for code purposes (gui, container naming), of this Enum Type*/
	public String getNameType() {return nameType;}
	/**Does this enum type have a dark gui texture for use when enabled?*/
	public boolean hasDarkGuiTexture() { return dark_gui_texture !=null; }
	/**Can this chest item render using a vanilla minecraft texture?*/
	public boolean hasAlternativeMinecraftGuiTexture() {return minecraft_alternative_texture != null;}
	/**Can we use basic math to render this chest item's inventory using the generic_54 texture provided with resource packs and vanilla minecraft? Only works if advanced resourcepack support is enabled in the WSBIM settings.*/
	public boolean canRenderWithGeneric54() {return minecraft_alternative_texture.equalsIgnoreCase("textures/gui/container/generic_54.png") && this.getNumRows() <=6;}
	/**Items that are considered to be a backpack will be treated like one. Must have nametype equal to "Backpack" ignoring case. May need to have implemented functionality for the item class*/
	public boolean isBackpack() {return getNameType().equalsIgnoreCase("Backpack");}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getGuiTexture(){
		String[] str = this.gui_texture.split(":");
		return new ResourceLocation(str !=null && str.length > 1? str[0] : "minecraft", str !=null && str.length > 1? str[1] : this.gui_texture);
	}
	@SideOnly(Side.CLIENT)
	public ResourceLocation getDarkGuiTexture(){
		String[] str = this.dark_gui_texture.split(":");
		return new ResourceLocation(str !=null && str.length > 1? str[0] : "minecraft", str !=null && str.length > 1? str[1] : this.dark_gui_texture);
	}
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getAlternativeMinecraftGuiTexture() {
		String[] str = this.minecraft_alternative_texture.split(":");
		return new ResourceLocation(str !=null && str.length > 1? str[0] : "minecraft", str !=null && str.length > 1? str[1] : this.minecraft_alternative_texture);
	}
}
