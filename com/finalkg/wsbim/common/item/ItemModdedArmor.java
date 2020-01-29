package com.finalkg.wsbim.common.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;


public class ItemModdedArmor extends ItemArmor {

	public ItemModdedArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, 0, equipmentSlotIn);
	}
}
