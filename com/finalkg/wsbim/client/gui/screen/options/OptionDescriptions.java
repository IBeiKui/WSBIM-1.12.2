package com.finalkg.wsbim.client.gui.screen.options;

import java.util.ArrayList;
import java.util.List;

public class OptionDescriptions {
	public static List<String> getItemDescription(String valName){
		List<String> desc = new ArrayList<String>();
		
		if(valName.equals("minHeightForTabs")){
			return getStringListFromArray(
					"Do not change unless you know what",
					"you are doing, this controlls",
					"the minium height a gui must be",
					"for tabs to appear.");
		}
		
		if(valName.equals("maxSlotsForTabs")){
			return getStringListFromArray(
					"Controlls the limit for",
					"how many slots (including",
					"the player's inventory)",
					"that can be in this",
					"container for the tabs",
					"to appear. Set to 80 or ",
					"below to exclude any",
					"chests or large containers."
					);
		}
		if(valName.equals("showContainerTabs")){
			return getStringListFromArray(
					"When enabled, a crafting table tab will appear in the top right",
					"corner of any GUI with slots. Clicking on it will open the corresponding",
					"crafting GUI depending on whether you have a crafting table in your",
					"inventory."
					);
		}
		if(valName.equals("currentTheme")){
			return getStringListFromArray(
					"Changes the current",
					"theme for the ingame GUI."
					
					);
		}
		
		if(valName.equals("showCrosshairs")){
			return getStringListFromArray(
					"Option for rendering the",
					"crosshairs in the HUD."
					);
		}
		
		if(valName.equals("showScoreboard")){
			return getStringListFromArray(
					"Option for rendering the",
					"scoreboard in the HUD."
					);
		}
		if(valName.equals("showArmorInHUD")){
			return getStringListFromArray(
					"Show the player's armor in",
					"the ingame GUI."
					);
		}
		if(valName.equals("themeBrightness")){
			return getStringListFromArray(
					"Adjusts the transparency behind",
					"elements in the HUD."
					);
		}
		if(valName.equals("showInventoryTabs")){
			return getStringListFromArray(
					"Display the crafting table tab in your inventory?",
					"It will only show up if you have a crafting table in your inventory.",
					"DISABLE IF MODS ARE CONFLICTING WITH INVENTORY TABS."
					);
		}
		
		if(valName.equals("useAdvancedResourcePackSupport")){
			return getStringListFromArray(
					"If enabled, WSBIM will do it's best to render out",
					"it's inventories, such as backpacks, with vanilla GUI",
					"textures. Will only have an effect if a resourcepack is loaded.",
					"DISABLE IF ANYTHING LOOKS OUT OF PLACE!"
					);
		}
		
		if(valName.equals("playOpenGUISound")){
			return getStringListFromArray(
					"Option for playing the 'wood click' sound effect",
					"whenever any inventory is entered."
					);
		}
		if(valName.equals("useResourcePackTabTexture")){
			return getStringListFromArray(
					"If enabled, WSBIM will use the currently loaded",
					"tabs.png file for rending the Crafting Table Tab.",
					"DISABLE IF the resource pack makes the tab look out of place."
					);
		}
		if(valName.equals("openBagButtonInGuiContainers")){
			return getStringListFromArray(
					"Enable or disable a button",
					"that will allow you to open",
					"the first bag detected in your",
					"inventory. This button will show up",
					"below your inventory in most guis",
					"when you have a bag in your inventory."
					);
		}
		if(valName.equals("drawClearChestInFastGraphics")){
			return getStringListFromArray(
					"Render the diamond chest with",
					"clear textures and show items",
					"in both fast and fancy graphics."
					);
		}
		if(valName.equals("updateMode")){
			return getStringListFromArray(
					"Controls the way WSBIM will check for updates."
					);
		}
		if(valName.equals("useWSBIMPauseMenu")){
			return getStringListFromArray(
					"Should WSBIM use a custom in-game",
					"pause menu, or should it only use",
					"the default pause menu (other mods",
					"may need it!)"
					);
		}
		if(valName.equals("defaultForegroundOpacity")){
			return getStringListFromArray(
					"The foreground opacity used on the",
					"elements in the clear HUD, along with",
					"buttons and labels in the WSBIM pause",
					"menu."
					);
		}
		if(valName.equals("defaultBackgroundOpacity")){
			return getStringListFromArray(
					"The background opacity used on the",
					"background elements of the WSBIM",
					"pause menu."
					);
		}
		if(valName.equals("buttonLayout")){
			return getStringListFromArray(
					"The button layout to use on the",
					"special WSBIM menus, the pause menu",
					"and the main menu."
					);
		}
		if(valName.equals("colorMode")){
			return getStringListFromArray(
					"Autmatically control the color",
					"mode by the time or allow for",
					"manual control. 'Real Time' will",
					"automatically make the color scheme",
					"white on black to make it easier on",
					"the eyes when it is night time in",
					"real life. MC Time will simply do this",
					"based on the time in the minecraft",
					"world."
					);
		}
		if(valName.equals("enableRainInAllBiomes")){
			return getStringListFromArray(
					"Enables the rain effect in all biomes.",
					"This is used for shaders that do not",
					"disable rain in desert (warm) biomes."
					);
		}
		if(valName.equals("enableDetailedTooltips")) {
			return getStringListFromArray(
					"Shows detailed item tooltips for all items such as",
					"hunger on food or if the item can be repaired in an anvil.",
					"(Item Tooltips are when you hover over an item in your inventory)"
					);
		}
		if(valName.equals("enableUnlocalizedItemToolTips")) {
			return getStringListFromArray(
					"Shows the unlocalized name of an item or block.",
					"Other mods may already do this. Disable this option if so.",
					"This tooltip will only show up after F3+H has been pressed.",
					"(Item Tooltips are when you hover over an item in your inventory)"
					);
		}
		if(valName.equals("enableNBTDataTooltips")) {
			return getStringListFromArray(
					"Shows the NBT Tag data for the given item or block.",
					"Pressing shift will show you a list of tags and values.",
					"(Item Tooltips are when you hover over an item in your inventory)"
					);
		}
		if(valName.equals("enableWSBIMItemTooltips")) {
			return getStringListFromArray(
					"Shows special item information for items within the WSBIM mod.",
					"(Item Tooltips are when you hover over an item in your inventory)"
					);
		}
		if(valName.equals("useClearHUD")) {
			return getStringListFromArray(
					"When enabled, WSBIM will render the",
					"HUD with the set GUI colors and a",
					"sleek translucentcy."
					
					);
		}
		if(valName.equals("drawArmorHudInCreative")) {
			return getStringListFromArray(
					"Show the Armor Status HUD in",
					"creative mode?"
					);
			
		}
		if(valName.equals("defaultBackgroundOpacity")) {
			return getStringListFromArray(
					"Background opacity for rending GUI",
					"elements."
					);
			
		}
		if(valName.equals("useDarkGuiContainerTheme")) {
			return getStringListFromArray(
					"Render internal WSBIM GUI textures with the dark theme?",
					"If Advanced Resource Pack Support is enabled, it will",
					"override the use of the dark textures when a Resource",
					"Pack is loaded."
					);
			
		}
		return desc;
	}
		
	private static List<String> getStringListFromArray(String... desc){
		List<String> strs = new ArrayList<String>();
		for(int i = 0; i < desc.length; i++){
			strs.add(desc[i]);
		}
		
		return strs;
	}
}
