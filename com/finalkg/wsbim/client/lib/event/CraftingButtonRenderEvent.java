package com.finalkg.wsbim.client.lib.event;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.GuiNormalCrafting;
import com.finalkg.wsbim.client.gui.GuiSmallCrafting;
import com.finalkg.wsbim.client.gui.screen.GuiButtonCraftingTable;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.sound.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CraftingButtonRenderEvent {
	
	protected GuiButtonCraftingTable craftingButton = null;
	private int windowResolution = 0;
	private int guiLeft = 0;
	private int guiTop = 0;
	private boolean hasButton = false;
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent e){
		if(e.phase == Phase.START) {
			if(hasButton && craftingButton !=null && craftingButton.selected) {
				this.craftingButton.selected = false;
				if(!(Minecraft.func_71410_x().field_71462_r instanceof GuiSmallCrafting) && !(Minecraft.func_71410_x().field_71462_r instanceof GuiNormalCrafting)) {
					this.craftingButton = null;
					this.hasButton = false;
					this.windowResolution = 0;
					this.guiLeft = 0;
					this.guiTop = 0;
					boolean hasCraftingTable = ContainerUtil.hasItemInPlayerInventory(Minecraft.func_71410_x().field_71439_g.field_71071_by, Item.func_150898_a(Blocks.field_150462_ai));
					if(WSBIM.options.playOpenGUISound) Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_184371_a(SoundsHandler.WOOD_CLICK, 1.0F));
					ContainerUtil.openGui(hasCraftingTable? 201 : 200);
					return;
				}
			}
			//TODO CHANGE TO BOTTON OF SCREEN AND ADD ARMOR TAB
			boolean canAddButton = Minecraft.func_71410_x().field_71439_g !=null && Minecraft.func_71410_x().field_71462_r !=null && Minecraft.func_71410_x().field_71462_r instanceof GuiContainer && !(Minecraft.func_71410_x().field_71462_r instanceof GuiCrafting) && !(Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative) && WSBIM.options.showContainerTabs;
			GuiScreen gui = Minecraft.func_71410_x().field_71462_r;
			if((!canAddButton || (this.windowResolution != (Minecraft.func_71410_x().field_71443_c * Minecraft.func_71410_x().field_71440_d) && this.windowResolution !=0) || (Minecraft.func_71410_x().field_71462_r instanceof GuiContainer && (this.guiLeft != ((GuiContainer)gui).field_147003_i || this.guiTop != ((GuiContainer)gui).field_147009_r) && this.guiLeft !=0 && this.guiTop !=0)) && hasButton) {
				this.windowResolution = 0;
				this.guiLeft = 0;
				this.guiTop = 0;
				this.hasButton = false;
				if(Minecraft.func_71410_x().field_71462_r instanceof GuiContainer) {
					GuiContainer current = (GuiContainer) Minecraft.func_71410_x().field_71462_r;
					if(current.field_146292_n.contains(craftingButton)) current.field_146292_n.remove(craftingButton);
				}
				this.craftingButton = null;
				return;
			}
			if(canAddButton && !hasButton) {
				GuiContainer currentContainer = (GuiContainer) Minecraft.func_71410_x().field_71462_r;
				boolean hasCraftingTable = ContainerUtil.hasItemInPlayerInventory(Minecraft.func_71410_x().field_71439_g.field_71071_by, Item.func_150898_a(Blocks.field_150462_ai));
				if(!(currentContainer instanceof GuiInventory) && !hasCraftingTable) {
					if(this.craftingButton == null) this.craftingButton = new GuiButtonCraftingTable(-100, currentContainer.field_147003_i + currentContainer.field_146999_f - 28 - 0, currentContainer.field_147009_r - 26);
					this.windowResolution = Minecraft.func_71410_x().field_71443_c * Minecraft.func_71410_x().field_71440_d;
					currentContainer.field_146292_n.add(craftingButton);
					this.hasButton = true;
					this.guiLeft = currentContainer.field_147003_i;
					this.guiTop = currentContainer.field_147009_r;
				}
				else if(hasCraftingTable && WSBIM.options.showInventoryTabs) {
					if(this.craftingButton == null) this.craftingButton = new GuiButtonCraftingTable(-100, currentContainer.field_147003_i + currentContainer.field_146999_f - 28 - 0, currentContainer.field_147009_r - 26);
					this.windowResolution = Minecraft.func_71410_x().field_71443_c * Minecraft.func_71410_x().field_71440_d;
					currentContainer.field_146292_n.add(craftingButton);
					this.hasButton = true;
					this.guiLeft = currentContainer.field_147003_i;
					this.guiTop = currentContainer.field_147009_r;
				}
			}
		}
	}
}
