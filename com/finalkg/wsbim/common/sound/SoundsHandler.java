package com.finalkg.wsbim.common.sound;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {

	public static SoundEvent BLOCK_ICE_MAKER_LOOP,BLOCK_ICE_MAKER_DISPENSE,WOOD_CLICK;
	
	public static void registerSounds() {
		BLOCK_ICE_MAKER_LOOP = registerSound("tile.blockicemaker.loop");
		BLOCK_ICE_MAKER_DISPENSE = registerSound("tile.blockicemaker.dispense");
		WOOD_CLICK = registerSound("random.wood_click");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(WSBIM.MODID, name);
		SoundEvent e = new SoundEvent(loc);
		e.setRegistryName(WSBIM.MODID, name);
		ForgeRegistries.SOUND_EVENTS.register(e);
		return e;
	}
	
}
