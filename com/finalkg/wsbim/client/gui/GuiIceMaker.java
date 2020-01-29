package com.finalkg.wsbim.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerIceMaker;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiIceMaker extends GuiContainer {

	public final TileEntityIceMaker iceMakerTile;
	
	public final List<String> waterToolTip = new ArrayList<String>();
	public final List<String> powerTooltip = new ArrayList<String>();
	public final List<String> waterItemToolTip = new ArrayList<String>();
	public final List<String> powerItemToolTip = new ArrayList<String>();
	
	private static final ResourceLocation ICE_MAKER_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/gui/container/ice_maker.png");
	private static final ResourceLocation ICE_MAKER_TEXTURE_DARK = new ResourceLocation(WSBIM.MODID, "textures/gui/container/dark/ice_maker.png");
	private static final ResourceLocation PROGRESS_BARS = new ResourceLocation(WSBIM.MODID, "textures/gui/container/progress_bars.png");
	
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    
	public GuiIceMaker(InventoryPlayer inv, TileEntityIceMaker iceMakerTile) {
		super(new ContainerIceMaker(inv, iceMakerTile));
		this.iceMakerTile = iceMakerTile;
		this.playerInventory = inv;
		this.xSize = 176;
		this.ySize = 209;
		waterToolTip.add("Current Water Level:");
		waterToolTip.add(iceMakerTile.waterTime+"t/"+iceMakerTile.MAX_WATER_CAPACITY+"t");
		powerTooltip.add("Current Power Level:");
		powerTooltip.add(iceMakerTile.fuelTime+"t/"+iceMakerTile.MAX_POWER_CAPACITY+"t");
		waterItemToolTip.add("Accepted water items:");
		for(ItemStack stack : iceMakerTile.ACCEPTED_WATER_ITEMS) {
			waterItemToolTip.add(stack.getDisplayName());
		}
		powerItemToolTip.add("Accepted power items:");
		for(ItemStack stack : iceMakerTile.ACCEPTED_POWER_ITEMS) {
			powerItemToolTip.add(stack.getDisplayName());
		}
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
	 * A few custom tool tips for helping people know what to do.
	 */
	@Override
    protected void renderHoveredToolTip(int mouseX, int mouseY){
        boolean flag = true; //Render item tool tip?
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        if(mouseX >= (i + 6) && mouseX <= (i + 32) && mouseY >= (j+7) && mouseY <= (j+78)) {
        	waterToolTip.set(1, iceMakerTile.waterTime+"t/"+iceMakerTile.MAX_WATER_CAPACITY+"t");
        	this.drawHoveringText(waterToolTip, mouseX, mouseY, fontRenderer);
        }
        if(mouseX >= (i + 40) && mouseX <= (i + 65) && mouseY >= (j+7) && mouseY <= (j+78)) {
        	powerTooltip.set(1, iceMakerTile.fuelTime+"t/"+iceMakerTile.MAX_POWER_CAPACITY+"t");
        	this.drawHoveringText(powerTooltip, mouseX, mouseY, fontRenderer);
        }
        if(mouseX >= (i + 12) && mouseX <= (i + 28) && mouseY >= (j+84) && mouseY <= (j+101)) {
        	this.drawHoveringText(waterItemToolTip, mouseX, mouseY, fontRenderer);
        	flag = false;
        }
        if(mouseX >= (i + 44) && mouseX <= (i + 60) && mouseY >= (j+84) && mouseY <= (j+101)) {
        	this.drawHoveringText(powerItemToolTip, mouseX, mouseY, fontRenderer);
        	flag = false;
        }
        if(flag) super.renderHoveredToolTip(mouseX, mouseY);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(WSBIM.options.usingDarkGuiContainerTheme() ? ICE_MAKER_TEXTURE_DARK : ICE_MAKER_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(PROGRESS_BARS);
        if (this.iceMakerTile.fuelTime > 0){
            int k = this.getPowerScaled(69);
            this.drawTexturedModalRect(i + 40, j + 7 + 69 - k, 0, 69 - k, 24, k + 1);
        }
        if (this.iceMakerTile.waterTime > 0){
            int m = this.getWaterScaled(69);
            this.drawTexturedModalRect(i + 7, j + 7 + 69 - m, 24, 69 - m, 24, m + 1);
        }
        int l = this.getProgressScaled(160);
        this.drawTexturedModalRect(i + 8, j + 105, 0, 70, l != 0? l + 1 : l, 6);
	}
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = I18n.format("gui.iceMaker", new Object[0]);
        int i = (this.xSize) / 2;
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() ? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.fontRenderer.drawString(s, (i + 32) - this.fontRenderer.getStringWidth(s) / 2, 10, drawColor);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, drawColor);
    }
    private int getProgressScaled(int pixels){
        int i = this.iceMakerTile.getField(2);
        int j = this.iceMakerTile.PROCESS_TIME;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    private int getPowerScaled(int pixels){
        int i = this.iceMakerTile.MAX_POWER_CAPACITY;
        return this.iceMakerTile.getField(0) * pixels / i;
    }
    private int getWaterScaled(int pixels){
        int i = this.iceMakerTile.MAX_WATER_CAPACITY;
        return this.iceMakerTile.waterTime * pixels / i;
    }
}
