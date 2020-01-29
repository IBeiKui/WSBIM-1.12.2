package com.finalkg.wsbim.client.gui;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerSmallCrafting;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiSmallCrafting extends GuiContainer{

	private static ResourceLocation TAB_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/gui/container/tab.png"); 
	private static ResourceLocation VANILLA_INVENTORY = new ResourceLocation("textures/gui/container/inventory.png");
	private static ResourceLocation DEFAULT_INVENTORY_PLACEHOLDER = new ResourceLocation(WSBIM.MODID, "textures/gui/container/inventory.png"); 
	private static ResourceLocation VANILLA_BACKGROUND = new ResourceLocation("textures/gui/container/villager.png");

	public GuiSmallCrafting(InventoryPlayer playerInv, World worldIn) {
		super(new ContainerSmallCrafting(playerInv, worldIn));
	
	}
    /**
     * Draws the screen and all the components in it.
     */
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int grid_width = 88;
		int grid_height = 48;
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1, 1);
		if(WSBIM.options.renderBasedOffVanillaTextures()){
			Minecraft.getMinecraft().getTextureManager().bindTexture(VANILLA_BACKGROUND);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(guiLeft+16, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+32, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+48, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+64, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+80, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+96, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+112, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+128, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+144, guiTop+10, 16, 10, 16, 65);
			this.drawTexturedModalRect(guiLeft+160, guiTop+10, 16, 10, 8, 65);
		}
		else{
			mc.getTextureManager().bindTexture(TAB_TEXTURE);
			this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
		boolean flag = WSBIM.options.renderBasedOffVanillaTextures();
		if(flag) {
			grid_width = 74;
			grid_height = 36;
			mc.getTextureManager().bindTexture(VANILLA_INVENTORY); 
			this.drawTexturedModalRect(guiLeft + (xSize / 2 - (grid_width / 2)), guiTop + 25, 97, 17, grid_width, grid_height);
		}
		else {
			mc.getTextureManager().bindTexture(DEFAULT_INVENTORY_PLACEHOLDER); 
			this.drawTexturedModalRect(guiLeft + (xSize / 2 - (grid_width / 2)), guiTop + 18, 80, 18, grid_width, grid_height);
		}
		GlStateManager.popMatrix();
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);;
		  String s = I18n.format("container.crafting", new Object[0]);
		  int drawColor = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
	      this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, drawColor);
	      this.fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, drawColor);
	}

}
