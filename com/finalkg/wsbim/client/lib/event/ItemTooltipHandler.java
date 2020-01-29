package com.finalkg.wsbim.client.lib.event;

import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.block.BlockMixedMetalChest;
import com.finalkg.wsbim.common.block.BlockObsidianChest;
import com.finalkg.wsbim.common.lib.IChestItem;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemTooltipHandler {
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event){
		if(event.getItemStack() !=null){
			if(event.getItemStack().getItem() !=null){
				Item item = event.getItemStack().getItem();
				ItemStack stack = event.getItemStack();
				if(item instanceof ItemBlock){
					Block block = Block.getBlockFromItem(item);
					if(block !=null){
						if(block instanceof BlockObsidianChest && WSBIM.options.enableWSBIMItemTooltips){
							BlockObsidianChest chest = (BlockObsidianChest) block;
							event.getToolTip().add(TextFormatting.DARK_PURPLE+I18n.format("wsbim.tooltip.obsidianChest.line1", new Object[0]));
							event.getToolTip().add(TextFormatting.DARK_PURPLE+I18n.format("wsbim.tooltip.obsidianChest.line2", new Object[0]));
							event.getToolTip().add(TextFormatting.DARK_PURPLE+I18n.format("wsbim.tooltip.obsidianChest.line3", new Object[0]));
							event.getToolTip().add(TextFormatting.DARK_PURPLE+I18n.format("wsbim.tooltip.obsidianChest.line4", new Object[0]));
						}
						if(block instanceof BlockMixedMetalChest && WSBIM.options.enableWSBIMItemTooltips) {
							BlockMixedMetalChest chest = (BlockMixedMetalChest) block;
							event.getToolTip().add(TextFormatting.LIGHT_PURPLE+I18n.format("wsbim.tooltip.mixedMetalChest.line1", new Object[0]));
						}
					}
				}
				if(item instanceof ItemFood && WSBIM.options.enableDetailedTooltips){
					ItemFood food = (ItemFood)item;
					event.getToolTip().add(TextFormatting.DARK_GREEN+I18n.format("wsbim.tooltip.foodStats.line1", new Object[0])+":");
					event.getToolTip().add(TextFormatting.GREEN+"+"+((double)(food.getHealAmount(stack))) + " " + I18n.format("wsbim.tooltip.foodStats.line2", new Object[0]));
					event.getToolTip().add(TextFormatting.GREEN+"+"+String.format("%.1f", ((double)(food.getSaturationModifier(stack))))+" " + I18n.format("wsbim.tooltip.foodStats.line3", new Object[0]));
					if(food.isWolfsFavoriteMeat()) event.getToolTip().add(TextFormatting.GOLD+ I18n.format("wsbim.tooltip.foodStats.line4", new Object[0]));
				}
				
				if(item instanceof IChestItem && WSBIM.options.enableWSBIMItemTooltips){
					IChestItem chestitem = (IChestItem)item;
					event.getToolTip().add(TextFormatting.DARK_GREEN+I18n.format("wsbim.tooltip.chestItem.part1", new Object[0])+" "+chestitem.getType().getInventorySize()+" "+I18n.format("wsbim.tooltip.chestItem.part2", new Object[0]));
				}
				if(item.isRepairable() && WSBIM.options.enableDetailedTooltips){
					if(!(item instanceof IChestItem))event.getToolTip().add(TextFormatting.BLUE+I18n.format("wsbim.tooltip.repairable", new Object[0]));
				}
				if(item.getItemEnchantability() !=0 && WSBIM.options.enableDetailedTooltips) {
					boolean flag = true;
					if(item instanceof ItemArmor) {
						ItemArmor armor = (ItemArmor) item;
						if(armor.getArmorMaterial().getEnchantability() == 0) flag = false;
					}
					if(item instanceof ItemTool) {
						ItemTool tool = (ItemTool) item;
						if(tool.getItemEnchantability() == 0) flag = false;
					}
					if(flag)event.getToolTip().add(TextFormatting.DARK_BLUE+I18n.format("wsbim.tooltip.enchantable", new Object[0]));
				}
				if(item.canItemEditBlocks() && WSBIM.options.enableDetailedTooltips) {
					event.getToolTip().add(TextFormatting.GOLD+I18n.format("wsbim.tooltip.editBlocks", new Object[0]));
				}
				if(item.getRarity(stack) !=null && WSBIM.options.enableDetailedTooltips) {
					if(item.getRarity(stack) != EnumRarity.COMMON) {
						EnumRarity rarity = item.getRarity(stack);
						if(rarity == EnumRarity.UNCOMMON) {event.getToolTip().add(rarity.rarityColor+I18n.format("wsbim.tooltip.uncommon", new Object[0]));}
						else if (rarity == EnumRarity.RARE) {event.getToolTip().add(rarity.rarityColor+I18n.format("wsbim.tooltip.rare", new Object[0]));}
						else if (rarity == EnumRarity.EPIC) {event.getToolTip().add(rarity.rarityColor+""+TextFormatting.BOLD+I18n.format("wsbim.tooltip.epic", new Object[0]));}
						else {event.getToolTip().add(rarity.rarityColor+""+rarity.rarityName);}
					}
				}
				if(stack.hasTagCompound() && WSBIM.options.enableNBTDataTooltips){
					NBTTagCompound tagCompound = stack.getTagCompound();
					int tags = 0;
					Set tag_set = null;
					if(tagCompound !=null){
						tags = tagCompound.getSize();
						tag_set = tagCompound.getKeySet();
					}
					String format = TextFormatting.ITALIC+""+TextFormatting.DARK_PURPLE;
					event.getToolTip().add(format+"+NBT ("+(tags>0?tags:"")+")");
					if(tags > 0 && tagCompound !=null){
						if((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
							Object[] set_data = tag_set.toArray();
							String[] string_tags = new String[set_data.length];
							for(int i = 0; i < string_tags.length; i++){
								if(set_data[i] !=null && set_data[i] instanceof String){
									string_tags[i] = (i+1)+". "+I18n.format("wsbim.tooltip.nbt.part1", new Object[0])+": "+(String)set_data[i]+" "+I18n.format("wsbim.tooltip.nbt.part2", new Object[0])+": "+tagCompound.getTag((String)set_data[i]);
								}
							}
							for(int i = 0; i < string_tags.length; i++){
								event.getToolTip().add(string_tags[i]);
							}
						}
						else{
							event.getToolTip().add(TextFormatting.DARK_GRAY+I18n.format("wsbim.tooltip.nbt.part3", new Object[0])+" "+TextFormatting.YELLOW+"'Shift' "+TextFormatting.DARK_GRAY+I18n.format("wsbim.tooltip.nbt.part4", new Object[0]));
						}
					}
				}
			}
		}
	}
}
