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
				if(!(Minecraft.getMinecraft().currentScreen instanceof GuiSmallCrafting) && !(Minecraft.getMinecraft().currentScreen instanceof GuiNormalCrafting)) {
					this.craftingButton = null;
					this.hasButton = false;
					this.windowResolution = 0;
					this.guiLeft = 0;
					this.guiTop = 0;
					boolean hasCraftingTable = ContainerUtil.hasItemInPlayerInventory(Minecraft.getMinecraft().player.inventory, Item.getItemFromBlock(Blocks.CRAFTING_TABLE));
					if(WSBIM.options.playOpenGUISound) Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundsHandler.WOOD_CLICK, 1.0F));
					ContainerUtil.openGui(hasCraftingTable? 201 : 200);
					return;
				}
			}
			//TODO CHANGE TO BOTTON OF SCREEN AND ADD ARMOR TAB
			boolean canAddButton = Minecraft.getMinecraft().player !=null && Minecraft.getMinecraft().currentScreen !=null && Minecraft.getMinecraft().currentScreen instanceof GuiContainer && !(Minecraft.getMinecraft().currentScreen instanceof GuiCrafting) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && WSBIM.options.showContainerTabs;
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if((!canAddButton || (this.windowResolution != (Minecraft.getMinecraft().displayWidth * Minecraft.getMinecraft().displayHeight) && this.windowResolution !=0) || (Minecraft.getMinecraft().currentScreen instanceof GuiContainer && (this.guiLeft != ((GuiContainer)gui).guiLeft || this.guiTop != ((GuiContainer)gui).guiTop) && this.guiLeft !=0 && this.guiTop !=0)) && hasButton) {
				this.windowResolution = 0;
				this.guiLeft = 0;
				this.guiTop = 0;
				this.hasButton = false;
				if(Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
					GuiContainer current = (GuiContainer) Minecraft.getMinecraft().currentScreen;
					if(current.buttonList.contains(craftingButton)) current.buttonList.remove(craftingButton);
				}
				this.craftingButton = null;
				return;
			}
			if(canAddButton && !hasButton) {
				GuiContainer currentContainer = (GuiContainer) Minecraft.getMinecraft().currentScreen;
				boolean hasCraftingTable = ContainerUtil.hasItemInPlayerInventory(Minecraft.getMinecraft().player.inventory, Item.getItemFromBlock(Blocks.CRAFTING_TABLE));
				if(!(currentContainer instanceof GuiInventory) && !hasCraftingTable) {
					if(this.craftingButton == null) this.craftingButton = new GuiButtonCraftingTable(-100, currentContainer.guiLeft + currentContainer.xSize - 28 - 0, currentContainer.guiTop - 26);
					this.windowResolution = Minecraft.getMinecraft().displayWidth * Minecraft.getMinecraft().displayHeight;
					currentContainer.buttonList.add(craftingButton);
					this.hasButton = true;
					this.guiLeft = currentContainer.guiLeft;
					this.guiTop = currentContainer.guiTop;
				}
				else if(hasCraftingTable && WSBIM.options.showInventoryTabs) {
					if(this.craftingButton == null) this.craftingButton = new GuiButtonCraftingTable(-100, currentContainer.guiLeft + currentContainer.xSize - 28 - 0, currentContainer.guiTop - 26);
					this.windowResolution = Minecraft.getMinecraft().displayWidth * Minecraft.getMinecraft().displayHeight;
					currentContainer.buttonList.add(craftingButton);
					this.hasButton = true;
					this.guiLeft = currentContainer.guiLeft;
					this.guiTop = currentContainer.guiTop;
				}
			}
		}
	}
}
