package com.finalkg.wsbim.client.lib.event;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.lib.WorldTimeHelper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UpdateUIColorsEvent {
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.END) { 
			Minecraft mc = Minecraft.getMinecraft();
			//Do our color control
			if(WSBIM.options.colorMode.equals(WSBIM.options.colorModes[1])){
				if(WorldTimeHelper.isDayTimeInRealLife()){
					WSBIM.options.backgroundColor = ColorHelper.UI_DAY_BACKGROUND_COLOR;
					WSBIM.options.foregroundColor = ColorHelper.UI_DAY_FOREGROUND_COLOR;
					WSBIM.options.textColor = ColorHelper.UI_DAY_TEXT_COLOR;
				}
				else{
					WSBIM.options.backgroundColor = ColorHelper.UI_NIGHT_BACKGROUND_COLOR;
					WSBIM.options.foregroundColor = ColorHelper.UI_NIGHT_FOREGROUND_COLOR;
					WSBIM.options.textColor = ColorHelper.UI_NIGHT_TEXT_COLOR;
				}
			}
			if(WSBIM.options.colorMode.equals(WSBIM.options.colorModes[2])){
				boolean flag = false;
				if(mc.world !=null) {
					flag = WorldTimeHelper.isDaytime(mc.world);
				}
				else if(WorldTimeHelper.isDayTimeInRealLife()) flag = true;
				if(flag){
					WSBIM.options.backgroundColor = ColorHelper.UI_DAY_BACKGROUND_COLOR;
					WSBIM.options.foregroundColor = ColorHelper.UI_DAY_FOREGROUND_COLOR;
					WSBIM.options.textColor = ColorHelper.UI_DAY_TEXT_COLOR;
				}
				else{
					WSBIM.options.backgroundColor = ColorHelper.UI_NIGHT_BACKGROUND_COLOR;
					WSBIM.options.foregroundColor = ColorHelper.UI_NIGHT_FOREGROUND_COLOR;
					WSBIM.options.textColor = ColorHelper.UI_NIGHT_TEXT_COLOR;
				}
			}
		}
	}
}