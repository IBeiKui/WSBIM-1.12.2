package com.finalkg.wsbim.common.lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.world.World;


import net.minecraft.world.World;

/**
 * A class for managing minecraft world time.
 * @author finalkg
 */
public class WorldTimeHelper{

	/**
	 * Returns a string, in the format HH:MM for the given minecraft world
	 * 0 ticks = 06:00 sunrise
	 * 6000 ticks = 12:00 noon
	 * 12000 ticks = 18:00 sunset
	 * 18000 ticks = 00:00 midnight
	 * 24000 ticks = 06:00
	 * @param world World to check
	 * @return Militay time string
	 */
	public static String getMilitaryTimeStringForMinecraftWorld(World world) {
		if(world == null) return "00:00";
		String hour;
		String minute;
		long tickTime = world.func_72820_D();
		long hours = tickTime / 1000 + 6;
		hours %=24;
		if(hours == 24) hours = 0;
		if(hours < 10) hour = "0"+hours;
		else hour = hours+"";
		long minutes = (tickTime % 1000) * 60 / 1000;
		if(minutes == 60) minutes = 0;
		if(minutes < 10) minute = "0"+minutes;
		else minute = minutes+"";
		return hour+":"+minute;
	}
	/**
	 * Gets the current hour, between 0 and 23 for the world.
	 * @param world
	 * @return
	 */
	public static long getCurrentHourForWorld(World world) {
		if(world == null) return 0;
		long tickTime = world.func_72820_D();
		long hours = tickTime / 1000 + 6;
		hours %=24;
		if(hours == 24) hours = 0;
		return hours;
	}
	/**
	 * Returns true if the world hour is >=6 and <=18
	 * @param world
	 * @return
	 */
	public static boolean isDaytime(World world) {
		long hour = getCurrentHourForWorld(world);
		return hour >=6 && hour <= 18;
	}
	
	/**
	 * Will use stock JVM Date and DateFormat classes to 
	 * determine whether it is day or night based on real time.
	 * 18:00 hrs. to 7:00 hrs. is considered night, and 7:00 hrs
	 * to 17:59 hrs is considered daytime.
	 * @return Is it daytime in real life?
	 */
	public static boolean isDayTimeInRealLife(){
		DateFormat df = new SimpleDateFormat("HH");
		Date dateobj = new Date();
		String hourNumber = df.format(dateobj);
		int hournum = Integer.parseInt(hourNumber);
		return (hournum > 6) && (hournum<18);
	}
}
