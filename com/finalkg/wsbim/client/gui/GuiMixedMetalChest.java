package com.finalkg.wsbim.client.gui;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerMixedMetalChest;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMixedMetalChest extends GuiContainer {

	public final TileEntityMixedMetalChest tileEntity;
	
	private static final ResourceLocation MIXED_METAL_CHEST_GUI = new ResourceLocation(WSBIM.MODID, "textures/gui/container/mixed_metal_chest.png");
	private static final ResourceLocation MIXED_METAL_CHEST_GUI_DARK = new ResourceLocation(WSBIM.MODID, "textures/gui/container/dark/mixed_metal_chest.png");
		
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    
	public GuiMixedMetalChest(InventoryPlayer inv, TileEntityMixedMetalChest tile) {
		super(new ContainerMixedMetalChest(inv, tile));
		this.tileEntity = tile;
		this.playerInventory = inv;
		this.xSize = 256;
		this.ySize = 256;
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
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(WSBIM.options.usingDarkGuiContainerTheme() ? MIXED_METAL_CHEST_GUI_DARK : MIXED_METAL_CHEST_GUI);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = I18n.format("gui.mixedMetalChest", new Object[0]);
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() ? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.fontRenderer.drawString(s, 12, 6, drawColor);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 48, this.ySize - 92, drawColor);
    }

}
