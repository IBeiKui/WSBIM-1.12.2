package com.finalkg.wsbim.client.lib.event;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.sound.SoundsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class WSBIMPlayOpenGUIEvent {
	
	private boolean played = false;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END) {
			if(Minecraft.getMinecraft().currentScreen !=null && Minecraft.getMinecraft().currentScreen instanceof GuiContainer && WSBIM.options.playOpenGUISound && !played){
				System.out.println("playsound");
				Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundsHandler.WOOD_CLICK, 1.0F));
				played = true;
			}
			else if(!(Minecraft.getMinecraft().currentScreen instanceof GuiContainer)){
				played = false;
			}
		}
	}
}
