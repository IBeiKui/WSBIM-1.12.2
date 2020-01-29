package com.finalkg.wsbim.client.lib.event;

import javax.swing.SwingWorker;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.GuiUpdateFound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class WSBIMUpdateCheckerLogic {
	
	private boolean firstTime = true;
	private boolean firstTime2 = true;
	private boolean updateFound = false;
	private boolean hasShownStatus = false;
	private boolean showStatus = true;
	private int updateState = -1;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.START) {
			//Checking for WSBIM updates upon entering a minecraft word.
			if(Minecraft.getMinecraft().world !=null && firstTime && WSBIM.options.updateMode.equals(WSBIM.options.updateModes[0]) && Minecraft.getMinecraft().world !=null && Minecraft.getMinecraft().currentScreen == null){
				//Do the check in the background so that the game can actually load.
				SwingWorker thread = new SwingWorker<Object, Object>(){
		    		@Override
		    		protected Object doInBackground() throws Exception {
		    			WSBIM.updateChecker.resetData();
						Minecraft.getMinecraft().player.sendMessage(new TextComponentString("[WSBIM] Checking for updates."));
						int result = WSBIM.updateChecker.updateCheckInit(false);
						if(result == 0){
							Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.RED+"[WSBIM] Update check failed for an unknown reason."));
						}
						if(result == 1){
							Minecraft.getMinecraft().player.sendMessage(new TextComponentString("[WSBIM] No update needed."));
						}
						if(result == 2){
							//Do update stuff
							updateFound = true;
							Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN+"[WSBIM] An update <"+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+"> was found for WSBIM."));
						}
						if(result == 3){
							Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.DARK_RED+"[WSBIM] Update check metadata file failed to download."));
						}
		    			
						return null;
		    		}
				};
				thread.execute();
				
				firstTime = false;
			}
			//Checking for updates when the player first logs into a world.
			if((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && firstTime2 && WSBIM.options.updateMode.equals(WSBIM.options.updateModes[1])){
				updateState = 4;
				final GuiScreen currentGui = Minecraft.getMinecraft().currentScreen;
				//Do the check in the background so that the game can actually load.
				SwingWorker thread = new SwingWorker<Object, Object>(){

		    		@Override
		    		protected Object doInBackground() throws Exception {
		    			WSBIM.updateChecker.resetData();
						int result = WSBIM.updateChecker.updateCheckInit(false);
						updateState = result;
						if(result == 0){
							//Failed for an unknown reason
						}
						if(result == 1){
							//No update needed.						
						}
						if(result == 2){
							//Do update stuff
							Minecraft.getMinecraft().displayGuiScreen(new GuiUpdateFound(currentGui));
							
						}
						if(result == 3){
							//Update failed to download
						}
						return null;
		    		}
				};
				thread.execute();
				firstTime2 = false;
			}
		}
		else if(e.phase == Phase.END) {
			if(updateFound) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiUpdateFound(null));
				updateFound = false;
			}
			if(Minecraft.getMinecraft().currentScreen !=null && (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && !WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[0])){
				GuiScreen screen = Minecraft.getMinecraft().currentScreen;
				String linetodraw = "WSBIM "+WSBIM.VERSION + (WSBIM.updateChecker.updateNeeded ? " (Version "+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+" Available)" : "");
				Minecraft.getMinecraft().currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, linetodraw, (screen.width - 2) - Minecraft.getMinecraft().fontRenderer.getStringWidth(linetodraw), (WSBIM.updateChecker.updateNeeded ? 1 : screen.height - 20), 16777215);
			}
			if(Minecraft.getMinecraft().currentScreen !=null && (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && showStatus && updateState !=1 && updateState != -1){
				GuiScreen screen = Minecraft.getMinecraft().currentScreen;
				if(updateState == 0){
					Minecraft.getMinecraft().currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.RED+"[WSBIM] Update check failed.", 0, 0, 16777215);
				}
				if(updateState == 2){
					Minecraft.getMinecraft().currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN+"[WSBIM] Update found for WSBIM", 0, 0, 16777215);
				}
				if(updateState == 3){
					Minecraft.getMinecraft().currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.DARK_RED+"[WSBIM] Update check file failed to download.", 0, 0, 16777215);
				}
				if(updateState == 4){
					Minecraft.getMinecraft().currentScreen.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GOLD+"[WSBIM] Update check in progress.", 0, 0, 16777215);
				}
				hasShownStatus = true;
			}
			if(!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) && hasShownStatus){
				showStatus = false;
			}
		}
	}
}
