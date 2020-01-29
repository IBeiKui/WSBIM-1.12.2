package com.finalkg.wsbim.client.gui;

import java.io.IOException;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.GuiButtonEdit;
import com.finalkg.wsbim.client.gui.screen.GuiButtonRecolor;
import com.finalkg.wsbim.client.gui.screen.GuiRecolorItem;
import com.finalkg.wsbim.client.gui.screen.GuiRenameItem;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.inventory.ContainerChestItem;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.lib.ContainerUtil;
import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.lib.IChestItem;
import com.finalkg.wsbim.common.lib.IColoredItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiChestItem extends GuiContainer{

	private final IChestItem itemChestItem;
	private final EnumChestItem chestItemType;
	private final ItemStack chestItemStack;
	private final EntityPlayer player;
	
	private GuiButtonEdit editButton;
	private GuiButtonRecolor recolorButton;
	private final int EDIT_BUTTON_ID = 100;
	private final int RECOLOR_BUTTON_ID = 101;
	private final int CHEST_ITEMSTACK_INDEX;
	private final float RECOLOR_GUI_FACTOR = 0.7F;
	
	public GuiChestItem(EntityPlayer player, ItemStack chestItem) {
		super(new ContainerChestItem(player, chestItem));
		this.chestItemStack = chestItem;
		this.itemChestItem = (IChestItem) this.chestItemStack.getItem();
		this.chestItemType = this.itemChestItem.getType();
		this.player = player;
		this.xSize = this.chestItemType.getGuiWidth();
		this.ySize = this.chestItemType.getGuiHeight();
		this.CHEST_ITEMSTACK_INDEX = ContainerUtil.getItemStackIndexInPlayerInventory(player.inventory, chestItem);
	}
	
	public void initGui(){
		super.initGui();
		if(CHEST_ITEMSTACK_INDEX != -1) {
			this.editButton = new GuiButtonEdit(EDIT_BUTTON_ID, guiLeft + this.xSize, guiTop);
			this.buttonList.add(this.editButton);
			if(this.chestItemStack.getItem() instanceof IColoredItem){
				this.recolorButton = new GuiButtonRecolor(RECOLOR_BUTTON_ID, guiLeft + this.xSize, guiTop + 18);
				this.buttonList.add(recolorButton);
			}	
		}
	}
	public void actionPerformed(GuiButton b) throws IOException{
		super.actionPerformed(b);
		if(b.id == EDIT_BUTTON_ID){
			Minecraft.getMinecraft().displayGuiScreen(new GuiRenameItem(this.chestItemStack, this.chestItemType.getNameType(), CHEST_ITEMSTACK_INDEX));
		}
		if(b.id == RECOLOR_BUTTON_ID) {
			IColoredItem item = (IColoredItem) this.chestItemStack.getItem();
			int c = item.getItemColor(chestItemStack);
			Minecraft.getMinecraft().displayGuiScreen(new GuiRecolorItem(this.chestItemStack, (c == -1 || c == item.getDefaultColor())? item.getDefaultColor(): c, CHEST_ITEMSTACK_INDEX));
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
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if(this.chestItemStack.getItem() instanceof IColoredItem && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())) {
			IColoredItem item = (IColoredItem) this.chestItemStack.getItem();
			int color = item.getItemColor(chestItemStack);
			if(color != item.getDefaultColor()) {
				int[] rgb = ColorHelper.convertIntegerToRGB(color);
				int red = rgb[0];
				int green = rgb[1];
				int blue = rgb[2];
				GlStateManager.color((((float)red/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), (((float)green/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), (((float)blue/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), 1.0F);
			}
			else {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
		else {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        if(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture()) {
        	ResourceLocation loc = this.chestItemType.getAlternativeMinecraftGuiTexture();
        	if(this.chestItemType.canRenderWithGeneric54()) {
        		this.mc.getTextureManager().bindTexture(loc);
        		int inv_start_y = this.chestItemType.getColumnRenderStart();
        		int rows = this.chestItemType.getNumRows();
        		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, inv_start_y + (rows * 18));
        		this.drawTexturedModalRect(i, j + (inv_start_y + (rows * 18)), 0, 125, this.xSize, 97);
        	}
        	else {
        		this.mc.getTextureManager().bindTexture(this.chestItemType.getAlternativeMinecraftGuiTexture());
        		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        	}
        }
        else if(WSBIM.options.usingDarkGuiContainerTheme() && this.chestItemType.hasDarkGuiTexture()) {
        	this.mc.getTextureManager().bindTexture(this.chestItemType.getDarkGuiTexture());
        	this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        }
        else {
        	this.mc.getTextureManager().bindTexture(this.chestItemType.getGuiTexture());
        	this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = this.chestItemStack.getDisplayName();
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
	    if(this.chestItemStack.getItem() instanceof IColoredItem && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())) {
        	IColoredItem coloreditem = (IColoredItem) this.chestItemStack.getItem();
        	int[] string_rgb = ColorHelper.convertIntegerToRGB(drawColor);
        	int itemColor = coloreditem.getItemColor(chestItemStack);
        	if(itemColor != coloreditem.getDefaultColor() && itemColor != -1) {
        		int[] color_rgb = ColorHelper.convertIntegerToRGB(itemColor);
        		float r = (float)color_rgb[0]/255F;
        		float g = (float)color_rgb[1]/255F;
        		float b = (float)color_rgb[2]/255F;
        		if(r < 0.5F && g < 0.5F && b <= 1F) {
            		drawColor = ColorHelper.convertRGBToInteger(230, 230, 230);
        		}
        	}
        }
        this.fontRenderer.drawString(s, this.chestItemType.getRowRenderStart(), this.chestItemType.getColumnRenderStart() - this.fontRenderer.FONT_HEIGHT - 2, drawColor);
        int plr_inventory_base_height = (this.chestItemType.getColumnRenderStart() - 1) + (this.chestItemType.getNumRows() * 18);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), this.chestItemType.getInventoryPlayerRowStart(), plr_inventory_base_height + 3, drawColor);
    }

}
