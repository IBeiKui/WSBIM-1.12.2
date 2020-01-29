package com.finalkg.wsbim.client.gui;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerIceMaker;
import com.finalkg.wsbim.common.inventory.ContainerObsidianChest;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiObsidianChest extends GuiContainer {

	public final TileEntityObsidianChest tileEntity;
	
	private static final ResourceLocation GENERIC_54 = new ResourceLocation("textures/gui/container/generic_54.png");
	private static final ResourceLocation OBSIDIAN_CHEST_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/gui/container/medium_chestitem.png");
	private static final ResourceLocation OBSIDIAN_CHEST_TEXTURE_DARK = new ResourceLocation(WSBIM.MODID, "textures/gui/container/dark/medium_chestitem.png");
		
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    
	public GuiObsidianChest(InventoryPlayer inv, TileEntityObsidianChest iceMakerTile) {
		super(new ContainerObsidianChest(inv, iceMakerTile));
		this.tileEntity = iceMakerTile;
		this.playerInventory = inv;
		this.xSize = 176;
		this.ySize = 186;
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
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		if(WSBIM.options.renderBasedOffVanillaTextures()) {
			this.mc.getTextureManager().bindTexture(GENERIC_54);
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 89);
	        this.drawTexturedModalRect(i, j+89, 0, 125, this.xSize, 97);
		}
		else if(WSBIM.options.usingDarkGuiContainerTheme()) {
			this.mc.getTextureManager().bindTexture(OBSIDIAN_CHEST_TEXTURE_DARK);
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		}
		else {
			this.mc.getTextureManager().bindTexture(OBSIDIAN_CHEST_TEXTURE);
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		}
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = I18n.format("gui.obsidianChest", new Object[0]);
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() && !WSBIM.options.renderBasedOffVanillaTextures()? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.fontRenderer.drawString(s, 8, 6, drawColor);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, drawColor);
    }

}
