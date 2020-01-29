package com.finalkg.wsbim.common.item;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.Item.ToolMaterial;


public class ItemModdedAxe extends ItemAxe {

	/**
	 * 
	 * @param material - tool material
	 * @param attackDamageMultiplier - Multiplier for the attack damage for this axe. USUALLY BETWEEN 6.0F and 8.0F
	 */
	public ItemModdedAxe(ToolMaterial material, float attackDamageMultiplier) {
		super(material, attackDamageMultiplier, -3F);
	}
}
