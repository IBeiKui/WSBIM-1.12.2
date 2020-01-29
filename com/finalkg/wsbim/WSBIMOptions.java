package com.finalkg.wsbim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptionsExtended;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptionsExtendedSmallList;
import com.finalkg.wsbim.client.lib.option.Option;
import com.finalkg.wsbim.client.lib.option.OptionBoolean;
import com.finalkg.wsbim.client.lib.option.OptionColorGUI;
import com.finalkg.wsbim.client.lib.option.OptionCycle;
import com.finalkg.wsbim.client.lib.option.OptionFloat;
import com.finalkg.wsbim.client.lib.option.OptionGUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WSBIMOptions {

	/**WSBIM Folder*/ 
	public File wsbimFolder = new File(FMLCommonHandler.instance().getSide() == Side.SERVER?FMLCommonHandler.instance().getMinecraftServerInstance().getFile("") : Minecraft.getMinecraft().mcDataDir, "config");
	
	/**Option File*/
	public final File optionsFile = new File(wsbimFolder, "wsbim_options.config");
	
	/**Options List*/
	public final List optionsList;

	public boolean checkForUpdatesOnServer;
	
	public boolean showScoreboard;
	public boolean showCrosshairs;
	public boolean showArmorInHUD;
	public boolean showContainerTabs;
	public boolean showInventoryTabs;
	
	public boolean useAdvancedResourcePackSupport;
	public boolean useResourcePackTabTexture;
	
	public boolean playOpenGUISound;
	
	public boolean drawArmorHudInCreative;
	
	public boolean useWSBIMPauseMenu;
	
	public boolean useClearHUD;
	
	//1/9/2020: Custom Item tool-tip settings
	public boolean enableNBTDataTooltips;
	public boolean enableDetailedTooltips;
	public boolean enableWSBIMItemTooltips;
	
	public String updateMode;
	public String buttonLayout;
	public String colorMode;
	public boolean useDarkGuiContainerTheme;
	
	public String backgroundColor;
	public String foregroundColor;
	public String textColor;
	
	public float defaultBackgroundOpacity;
	public float defaultForegroundOpacity;
	
	//Array Lists	
	public static final String[] updateModes = new String[]{"Join world", "On startup", "Off"};
	public static final String[] buttonLayouts = new String[]{"Vanilla", "List", "Transparent"};
	public static final String[] colorModes = new String[]{"Manual", "Real Time", "MC-World Time"};
	
	public WSBIMOptions(){
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			this.optionsList = new ArrayList<Option>();
			for(int i = 0; i < 100; i++){
				this.optionsList.add(i, null);
			}
		}
		else {
			this.optionsList = null;
		}
	}
	
	public void createOptions() throws IOException{

	
		if(!wsbimFolder.exists()){
			wsbimFolder.mkdir();
		}
		if(!optionsFile.exists()){
			this.setDefaults();
			try {
				this.saveOptions(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			boolean writeNewFile = false;
			try {
				BufferedReader br = new BufferedReader(new FileReader(this.optionsFile));
				 String s = "";
			       
		         while ((s = br.readLine()) != null)
		         {
		             try{
		            	 CharSequence charseq = ":";
		            	 if(s.contains(charseq)){
		            		String[] line = s.split(":");
		            		if(line[0].equals("Mod Version")){
		            			if(!line[1].equals(WSBIM.VERSION)){
		            				//This would reset every option when an update is installed, but thats dumb. I was probably coding this at like 3 am lmao.
		            				//So now, if the variable is not in the file it will use defaults. Makes more sense.
		            				this.setDefaults();
		            				writeNewFile = true;
		            			}
		            		}
		            	 }
		             }
		             catch(Exception e){
		            	 e.printStackTrace();
		             }
		             
		         }
				br.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				this.loadOptions();
				if(writeNewFile) this.saveOptions(false);//We will now write a new file based on loaded values
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			this.registerOptions();
		}
		
	}
	
	public void setDefaults(){
		//TODO Set all of the default values here.
		this.checkForUpdatesOnServer = true;
		this.showScoreboard = true;
		this.showCrosshairs = true;
		this.showArmorInHUD = true;
		this.showContainerTabs = true;
		showInventoryTabs = true;
		useAdvancedResourcePackSupport = true;
		playOpenGUISound = false;
		this.useResourcePackTabTexture = true;
		drawArmorHudInCreative = true;
		this.updateMode = this.updateModes[0];
		this.useWSBIMPauseMenu = true;
		this.defaultBackgroundOpacity = 0.35F;
		this.defaultForegroundOpacity = 0.6F;
		this.backgroundColor = "0";
		this.foregroundColor = "0xC8C8C8";
		this.textColor = "0";
		this.buttonLayout = this.buttonLayouts[2];
		this.colorMode = this.colorModes[2];
		this.enableDetailedTooltips = true;
		this.enableNBTDataTooltips = true;
		this.enableWSBIMItemTooltips = true;
		this.useDarkGuiContainerTheme = false;
		this.useClearHUD = true;
	}
	
	public void saveOptions(boolean loadOptionsWhenDone)throws IOException{
		if(this.optionsFile.exists()){
			this.optionsFile.delete();
		}
		if(!this.optionsFile.exists()){
			this.optionsFile.createNewFile();
		}
		PrintWriter pw = new PrintWriter(new FileWriter(this.optionsFile));
		pw.println("*****WSBIM Options File*****");
		pw.println("Below are all of the options for WSBIM,");
		pw.println("each option is in the following format:");
		pw.println("OptionName:variable");
		pw.println("If there are boolean variables, change from");
		pw.println("true to false, or false to true if you want");
		pw.println("to change the variable.");
		pw.println("Written: "+new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss").format(Calendar.getInstance().getTime()));
		pw.println("Mod Version:"+WSBIM.VERSION);
		pw.println("********Options Start********");
		//TODO Put all options here to be written to the file.
		pw.println("Update Mode (Client Only):"+this.updateMode);
		pw.println("------Item Tooltip Settings------");
		pw.println("***This is for when you hover your mouse over items***");
		pw.println("Show NBT Data tags on Item Tooltips:"+this.enableNBTDataTooltips);
		pw.println("Show detailed and helpful information on Item Tooltips:"+this.enableDetailedTooltips);
		pw.println("Show extra information on WSBIM items:"+this.enableWSBIMItemTooltips);
		pw.println("------HUD Customization Settings------");
		pw.println("Render Scoreboard:"+this.showScoreboard);
		pw.println("Render Crosshairs:"+this.showCrosshairs);
		pw.println("Show Armor in HUD:"+this.showArmorInHUD);
		pw.println("Play Soud when a GUI is Opened:"+this.playOpenGUISound);
		pw.println("Draw the armor overlay in Creative Mode:"+this.drawArmorHudInCreative);
		pw.println("Use clear HUD theme?:"+this.useClearHUD);
		pw.println("----Gui and Pause Menu Customization----");
		pw.println("Use the special wsbim pause menu:"+this.useWSBIMPauseMenu);
		pw.println("Default Background Opacity:"+this.defaultBackgroundOpacity);
		pw.println("Default Foreground Opacity:"+this.defaultForegroundOpacity);
		pw.println("Background Color:"+this.backgroundColor);
		pw.println("Foreground Color:"+this.foregroundColor);
		pw.println("Text Color:"+this.textColor);
		pw.println("Button Layout:"+this.buttonLayout);
		pw.println("Color Mode:"+this.colorMode);
		pw.println("Use Dark Gui Container Theme:"+this.useDarkGuiContainerTheme);
		pw.println("----Gui Container Tab Customization----");
		pw.println("Show Tabs in Containers:"+this.showContainerTabs);
		pw.println("Show Tabs in Inventory:"+this.showInventoryTabs);
		pw.println("----Gui Container Tab Customization End----");
		pw.println("Advanced Resource Pack Support:"+this.useAdvancedResourcePackSupport);
		pw.println("Use Original Tab Textures for Container Tabs:"+this.useResourcePackTabTexture);
		pw.println("----Server Options Start----");
		pw.println("Check for Mod Updates (On server):"+this.checkForUpdatesOnServer);
		//Register server only options (ie. worldgen)
		pw.println("----Server Options End----");
		pw.println("********Options End********");
		pw.close();
		if(loadOptionsWhenDone){
			this.loadOptions();
		}
	}
	
	public void loadOptions() throws IOException{
		 if (!this.optionsFile.exists())
         {
             return;
         }

         BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
         String s = "";
       
         while ((s = bufferedreader.readLine()) != null)
         {
             try{
            	 CharSequence charseq = ":";
            	 if(s.contains(charseq)){
            		String[] line = s.split(":");
            		//TODO Put all loads in here.
            		//Example:
            		/**
            		 * if(line[0].equals("TestBoolean")){this.testBoolean = Boolean.getBoolean(line[1]);}
            		 * 
            		 * 
            		 */
            		if(line[0].equals("Check for Mod Updates (On server)")){
            			this.checkForUpdatesOnServer = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Update Mode (Client Only)")){
            			this.updateMode = line[1];
            		}
            		if(line[0].equals("Render Scoreboard")){
            			this.showScoreboard = Boolean.parseBoolean(line[1]);
            		}
            		
            		if(line[0].equals("Render Crosshairs")){
            			this.showCrosshairs = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Use Dark Gui Container Theme")){
            			this.useDarkGuiContainerTheme = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Use clear HUD theme?")) {
            			this.useClearHUD = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Show Armor in HUD")){
            			this.showArmorInHUD = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Show Tabs in Containers")){
            			this.showContainerTabs = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Max Height for Tabs")){
            		//	this.maxHeightForTabs = Integer.parseInt(line[1]);
            		}
            		if(line[0].equals("Show Tabs in Inventory")){
            			this.showInventoryTabs = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Advanced Resource Pack Support")){
            			this.useAdvancedResourcePackSupport = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Play Soud when a GUI is Opened")){
            			this.playOpenGUISound = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Use Original Tab Textures for Container Tabs")){
            			this.useResourcePackTabTexture = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Draw the armor overlay in Creative Mode")){
            			this.drawArmorHudInCreative = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Use the special wsbim pause menu")){
            			this.useWSBIMPauseMenu = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Default Background Opacity")){
            			this.defaultBackgroundOpacity = Float.parseFloat(line[1]);
            		}
            		if(line[0].equals("Default Foreground Opacity")){
            			this.defaultForegroundOpacity = Float.parseFloat(line[1]);
            		}
            		if(line[0].equals("Background Color")){
            			this.backgroundColor = line[1];
            		}
            		if(line[0].equals("Foreground Color")){
            			this.foregroundColor = line[1];
            		}
            		if(line[0].equals("Text Color")){
            			this.textColor = line[1];
            		}
            		if(line[0].equals("Button Layout")){
            			this.buttonLayout = line[1];
            		}
            		if(line[0].equals("Color Mode")){
            			this.colorMode = line[1];
            		}
            		if(line[0].equals("Show NBT Data tags on Item Tooltips")) {
            			this.enableNBTDataTooltips = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Show detailed and helpful information on Item Tooltips")) {
            			this.enableDetailedTooltips = Boolean.parseBoolean(line[1]);
            		}
            		if(line[0].equals("Show extra information on WSBIM items")) {
            			this.enableWSBIMItemTooltips = Boolean.parseBoolean(line[1]);
            		}
            	 }
             }
             catch(Exception e){
            	 
             }
         }
         bufferedreader.close();
	}
	
	public String convertArrayListToStrings(Object[] arr){
		String str = "";
		for(int i = 0; i < arr.length; i++){
			if(arr[i] !=null){
				if(i == 0){
					str = arr[i].toString();
				}
				else{
					str = str+", "+arr[i].toString();
				}
			}
		}
		
		return str;
	}
	@SideOnly(Side.CLIENT)
	public boolean usingDarkGuiContainerTheme() {return this.useDarkGuiContainerTheme;}
	
	@SideOnly(Side.CLIENT)
	public boolean renderBasedOffVanillaTextures() {return net.minecraft.client.Minecraft.getMinecraft().gameSettings.resourcePacks.size() > 0 && this.useAdvancedResourcePackSupport;}
	
	@SideOnly(Side.CLIENT)
	public void registerOptions(){
		//TODO add options here
		//Main Page
		this.addOption(0, new OptionGUI(new GuiWSBIMOptionsExtended(Minecraft.getMinecraft(), 8, 16, "gui.wsbim.options.hudCustomization.title"), "wsbim.option.hudCustomization"));
		this.addOption(1, new OptionGUI(new GuiWSBIMOptionsExtended(Minecraft.getMinecraft(), 16, 24, "gui.wsbim.options.guiOptions.title"),"wsbim.option.guiSettings"));
		this.addOption(2, new OptionGUI(new GuiWSBIMOptionsExtendedSmallList(Minecraft.getMinecraft(), 24, 28, "wsbim.option.tooltipSettings"), "wsbim.option.tooltipSettings"));
		this.addOption(3, new OptionGUI(new GuiWSBIMOptionsExtendedSmallList(Minecraft.getMinecraft(), 28, 33, "wsbim.option.guiContainerSettings"), "wsbim.option.guiContainerSettings"));
		this.addOption(4, new OptionGUI(new GuiWSBIMOptionsExtendedSmallList(Minecraft.getMinecraft(), 33, 38, "gui.wsbim.options.guiOtherSettings.title"), "wsbim.option.otherSettings"));
		//HUD Customization 8-16
		this.addOption(8, new OptionBoolean(true, "useClearHUD", "wsbim.option.useClearHUD"));
		this.addOption(9, new OptionBoolean(true, "showScoreboard", "wsbim.option.showScoreboard"));
		this.addOption(10, new OptionBoolean(true, "showCrosshairs", "wsbim.option.showCrosshairs"));
		this.addOption(11, new OptionBoolean(true, "showArmorInHUD", "wsbim.option.showArmorInHUD"));
		this.addOption(12, new OptionBoolean(true, "drawArmorHudInCreative", "wsbim.option.drawArmorHudInCreative"));
		//Gui Customization use ids 16 - 23
		this.addOption(16, new OptionFloat(true, "defaultBackgroundOpacity", "wsbim.option.defaultBackgroundOpacity", 0.25F, 1F, 0.01F));
		this.addOption(17, new OptionFloat(true, "defaultForegroundOpacity", "wsbim.option.defaultForegroundOpacity", 0.25F, 1F, 0.01F));
		this.addOption(18, new OptionColorGUI(true, "textColor", "wsbim.option.textColor"));
		this.addOption(19, new OptionColorGUI(true, "backgroundColor", "wsbim.option.backgroundColor"));
		this.addOption(20, new OptionColorGUI(true, "foregroundColor", "wsbim.option.foregroundColor"));
		this.addOption(21, new OptionCycle(true, "buttonLayout", "wsbim.option.buttonLayout", this.buttonLayouts));
		this.addOption(22, new OptionCycle(true, "colorMode", "wsbim.option.colorMode", this.colorModes));
		this.addOption(23, new OptionBoolean(true, "useWSBIMPauseMenu", "wsbim.option.useWSBIMPauseMenu"));
		//Item Tooltip Settings 24-28
		this.addOption(24, new OptionBoolean(true, "enableDetailedTooltips", "wsbim.option.enableDetailedTooltips"));
		this.addOption(25, new OptionBoolean(true, "enableNBTDataTooltips", "wsbim.option.enableNBTDataTooltips"));
		this.addOption(26, new OptionBoolean(true, "enableWSBIMItemTooltips", "wsbim.option.enableWSBIMItemTooltips"));
		//Gui Continer Customization 28-36
		this.addOption(28, new OptionBoolean(true, "showContainerTabs", "wsbim.option.showContainerTabs"));
		this.addOption(29, new OptionBoolean(true, "showInventoryTabs", "wsbim.option.showInventoryTabs"));
		this.addOption(30, new OptionBoolean(true, "useAdvancedResourcePackSupport", "wsbim.option.useAdvancedResourcePackSupport"));
		this.addOption(31, new OptionBoolean(true, "useDarkGuiContainerTheme", "wsbim.option.useDarkGuiContainerTheme"));
		this.addOption(32, new OptionBoolean(true, "useResourcePackTabTexture", "wsbim.option.useResourcePackTabTexture"));	
		//Other settings 33-38
		this.addOption(33, new OptionBoolean(true, "playOpenGUISound", "wsbim.option.playOpenGUISound"));
		this.addOption(34, new OptionCycle(true, "updateMode", "wsbim.option.updateCheck", this.updateModes));
		

		
	}
	
	@SideOnly(Side.CLIENT)
	public void addOption(int id, Option option){
		this.optionsList.set(id, option);
	}
	
	
	public enum OptionType{
		INT,BOOLEAN,FLOAT,STRING,CYCLE,GUI;
		
		private OptionType(){
			
		}
	}
	
}
