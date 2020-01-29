package com.finalkg.wsbim.client.gui;

import org.lwjgl.opengl.GL11;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerNormalCrafting;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiNormalCrafting extends GuiContainer{
	
    private static final ResourceLocation VANILLA_CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/crafting_table.png");
    private static final ResourceLocation CRAFTING_TABLE_TEXTURES = new ResourceLocation(com.finalkg.wsbim.WSBIM.MODID, "textures/gui/container/crafting_table.png");
    
    public GuiNormalCrafting(InventoryPlayer playerInv, World worldIn){
        super(new ContainerNormalCrafting(playerInv, worldIn));
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
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
    	int c = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
    	int drawColor = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.fontRenderer.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, drawColor);
        this.fontRenderer.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, drawColor);
    }

    protected void drawGuiContainerBackgroundLayer(float renderTick, int mouseX, int mouseY){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(WSBIM.options.renderBasedOffVanillaTextures()) this.mc.getTextureManager().bindTexture(VANILLA_CRAFTING_TABLE_GUI_TEXTURES);
        else this.mc.getTextureManager().bindTexture(CRAFTING_TABLE_TEXTURES);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}