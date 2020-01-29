package com.finalkg.wsbim.client.gui.screen;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.GuiNormalCrafting;
import com.finalkg.wsbim.client.gui.GuiSmallCrafting;
import com.finalkg.wsbim.client.lib.GuiHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiButtonCraftingTable extends GuiButton{

	public boolean selected;
	public boolean open;
	
	public final ResourceLocation TAB_TEXTURE_DEFAULT = new ResourceLocation(WSBIM.MODID, "textures/gui/tabs.png");
	public final ResourceLocation TAB_TEXTURE_RESOURCEPACK = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	
	public GuiButtonCraftingTable(int buttonId, int x, int y) {
		super(buttonId, x, y, 30, 26, null);
		selected = false;
		open = false;
	}

	
	/**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
    	if (this.visible){
        	this.open = mc.currentScreen != null && (mc.currentScreen instanceof GuiSmallCrafting || mc.currentScreen instanceof GuiNormalCrafting);
        	GlStateManager.pushMatrix();
        	mc.getTextureManager().bindTexture(WSBIM.options.useResourcePackTabTexture ? TAB_TEXTURE_RESOURCEPACK : TAB_TEXTURE_DEFAULT);
        	GlStateManager.color(1, 1, 1, 1);
        	this.drawTexturedModalRect(x, y - (this.open? 2 : 0), 140, this.open ? 32 : 2, 28, this.open ? 32 : 26);
        	this.drawTexturedModalRect(x + 25, y + 26, 165, 28, 3, 1);
        	this.drawTexturedModalRect(x + 26, y + 27, 166, 28, 2, 1);
        	this.drawTexturedModalRect(x + 27, y + 28, 167, 28, 1, 1);
        	GlStateManager.popMatrix();
        	GlStateManager.pushMatrix();
    		GlStateManager.enableRescaleNormal();
    		GlStateManager.enableBlend();
    		RenderHelper.enableGUIStandardItemLighting();
    		GuiHelper.renderItemStackBasic(new ItemStack(Blocks.CRAFTING_TABLE), x + 6, y + (6), partialTicks);
    		RenderHelper.disableStandardItemLighting();
    		GlStateManager.disableRescaleNormal();
    		GlStateManager.popMatrix();
        }
    }
    
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
    	if(super.mousePressed(mc, mouseX, mouseY)) {
    		this.selected = true;
    		return true;
    	}
    	else {
    		this.selected = false;
    		return false;
    	}
    }
    public void playPressSound(SoundHandler soundHandlerIn){}
}
