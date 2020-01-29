package com.finalkg.wsbim.client.model;

import com.finalkg.wsbim.WSBIM;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemColored;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = WSBIM.MODID)
public class ModelRegistrationHandler {
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		//ALL ITEM RENDERS HERE
		registerRender(WSBIM.itemMixedMetalIngot);
		registerRender(WSBIM.itemIronStick);
		registerRender(WSBIM.itemGoldenStick);
		registerRender(WSBIM.itemStonePlate);
		registerRender(WSBIM.itemIronPlate);
		registerRender(WSBIM.itemCobblestonePlate);
		registerRender(WSBIM.itemDiamondPlate);
		registerRender(WSBIM.itemObsidianPlate);
		registerRender(WSBIM.itemWoodenPlate);
		registerRender(WSBIM.itemMixedMetalContents);
		registerRender(WSBIM.itemSack);
		registerRender(WSBIM.itemWallet);
		registerRender(WSBIM.itemSmallBackpack);
		registerRender(WSBIM.itemMediumBackpack);
		registerRender(WSBIM.itemLargeBackpack);
		registerRender(WSBIM.itemExtraLargeBackpack);
		registerArmorSetRender(WSBIM.itemWoodenHelmet, WSBIM.itemWoodenChestplate, WSBIM.itemWoodenLeggings, WSBIM.itemWoodenBoots);
		registerArmorSetRender(WSBIM.itemCobblestoneHelmet, WSBIM.itemCobblestoneChestplate, WSBIM.itemCobblestoneLeggings, WSBIM.itemCobblestoneBoots);
		registerArmorSetRender(WSBIM.itemStoneHelmet, WSBIM.itemStoneChestplate, WSBIM.itemStoneLeggings, WSBIM.itemStoneBoots);
		registerArmorSetRender(WSBIM.itemEmeraldHelmet, WSBIM.itemEmeraldChestplate, WSBIM.itemEmeraldLeggings, WSBIM.itemEmeraldBoots);
		registerArmorSetRender(WSBIM.itemObsidianHelmet, WSBIM.itemObsidianChestplate, WSBIM.itemObsidianLeggings, WSBIM.itemObsidianBoots);
		registerArmorSetRender(WSBIM.itemMixedMetalHelmet, WSBIM.itemMixedMetalChestplate, WSBIM.itemMixedMetalLeggings, WSBIM.itemMixedMetalBoots);
		registerToolSetRender(WSBIM.itemObsidianPickaxe, WSBIM.itemObsidianAxe, WSBIM.itemObsidianShovel, WSBIM.itemObsidianHoe, WSBIM.itemObsidianSword);
		registerToolSetRender(WSBIM.itemEmeraldPickaxe, WSBIM.itemEmeraldAxe, WSBIM.itemEmeraldShovel, WSBIM.itemEmeraldHoe, WSBIM.itemEmeraldSword);
		registerToolSetRender(WSBIM.itemMixedMetalPickaxe, WSBIM.itemMixedMetalAxe, WSBIM.itemMixedMetalShovel, WSBIM.itemMixedMetalHoe, WSBIM.itemMixedMetalSword);
		//ALL BLOCK RENDERS HERE
		registerRender(Item.func_150898_a(WSBIM.blockMixedMetalOre));
		registerRender(Item.func_150898_a(WSBIM.blockMixedMetal));
		registerRender(Item.func_150898_a(WSBIM.blockWetSand));
		registerRender(Item.func_150898_a(WSBIM.blockMud));
		registerRender(Item.func_150898_a(WSBIM.blockLeatherBlock));
		registerRender(Item.func_150898_a(WSBIM.blockIceMaker));
		registerRender(Item.func_150898_a(WSBIM.blockIceMakerActive));
		registerRender(Item.func_150898_a(WSBIM.blockObsidianChest));
		registerRender(Item.func_150898_a(WSBIM.blockMixedMetalChest));
		registerColoredBlockRender(WSBIM.blockLeatherBlockColored);
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
	private static void registerRender(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}

	/**
	 * Registers all renders needed for a colored block. See blockstate leatherblockcolored.json
	 * @param b - Colored Block
	 */
	private static void registerColoredBlockRender(Block b) {
		registerMetadataBlockModel(b, "color", EnumDyeColor.class);
	}
	
	public static <E extends Enum<?>> void registerMetadataBlockModel(Block block, String variantName, Class<E> variants) {
		for(int i = 0; i < variants.getEnumConstants().length; i++) {
			ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(block), i, new ModelResourceLocation(block.getRegistryName(),  variantName + "=" + variants.getEnumConstants()[i].name().toLowerCase()));
		}
	}
	
	private static void registerArmorSetRender(Item h, Item c, Item l, Item b) {
		registerRender(h);
		registerRender(c);
		registerRender(l);
		registerRender(b);
	}
	
	private static void registerToolSetRender(Item p, Item a, Item s, Item h, Item sw) {
		registerRender(p);
		registerRender(a);
		registerRender(s);
		registerRender(h);
		registerRender(sw);
	}
}
