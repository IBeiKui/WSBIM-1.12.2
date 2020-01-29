package com.finalkg.wsbim.client.gui;

import java.io.IOException;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.GuiButtonEdit;
import com.finalkg.wsbim.client.gui.screen.GuiButtonRecolor;
import com.finalkg.wsbim.client.gui.screen.GuiRecolorItem;
import com.finalkg.wsbim.client.gui.screen.GuiRenameItem;
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
		this.itemChestItem = (IChestItem) this.chestItemStack.func_77973_b();
		this.chestItemType = this.itemChestItem.getType();
		this.player = player;
		this.field_146999_f = this.chestItemType.getGuiWidth();
		this.field_147000_g = this.chestItemType.getGuiHeight();
		this.CHEST_ITEMSTACK_INDEX = ContainerUtil.getItemStackIndexInPlayerInventory(player.field_71071_by, chestItem);
	}
	
	public void func_73866_w_(){
		super.func_73866_w_();
		if(CHEST_ITEMSTACK_INDEX != -1) {
			this.editButton = new GuiButtonEdit(EDIT_BUTTON_ID, field_147003_i + this.field_146999_f, field_147009_r);
			this.field_146292_n.add(this.editButton);
			if(this.chestItemStack.func_77973_b() instanceof IColoredItem){
				this.recolorButton = new GuiButtonRecolor(RECOLOR_BUTTON_ID, field_147003_i + this.field_146999_f, field_147009_r + 18);
				this.field_146292_n.add(recolorButton);
			}	
		}
	}
	public void func_146284_a(GuiButton b) throws IOException{
		super.func_146284_a(b);
		if(b.field_146127_k == EDIT_BUTTON_ID){
			Minecraft.func_71410_x().func_147108_a(new GuiRenameItem(this.chestItemStack, this.chestItemType.getNameType(), CHEST_ITEMSTACK_INDEX));
		}
		if(b.field_146127_k == RECOLOR_BUTTON_ID) {
			IColoredItem item = (IColoredItem) this.chestItemStack.func_77973_b();
			int c = item.getColor(chestItemStack);
			Minecraft.func_71410_x().func_147108_a(new GuiRecolorItem(this.chestItemStack, (c == -1 || c == item.getDefaultColor())? item.getDefaultColor(): c, CHEST_ITEMSTACK_INDEX));
		}
	}
    /**
     * Draws the screen and all the components in it.
     */
	@Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks){
        this.func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.func_191948_b(mouseX, mouseY);
    }
	
	@Override
	protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
		if(this.chestItemStack.func_77973_b() instanceof IColoredItem && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())) {
			IColoredItem item = (IColoredItem) this.chestItemStack.func_77973_b();
			int color = item.getColor(chestItemStack);
			if(color != item.getDefaultColor()) {
				int[] rgb = ColorHelper.convertIntegerToRGB(color);
				int red = rgb[0];
				int green = rgb[1];
				int blue = rgb[2];
				GlStateManager.func_179131_c((((float)red/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), (((float)green/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), (((float)blue/255F) * RECOLOR_GUI_FACTOR) + (1F-RECOLOR_GUI_FACTOR), 1.0F);
			}
			else {
				GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
		else {
			GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
		}
		int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        if(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture()) {
        	ResourceLocation loc = this.chestItemType.getAlternativeMinecraftGuiTexture();
        	if(this.chestItemType.canRenderWithGeneric54()) {
        		this.field_146297_k.func_110434_K().func_110577_a(loc);
        		int inv_start_y = this.chestItemType.getColumnRenderStart();
        		int rows = this.chestItemType.getNumRows();
        		this.func_73729_b(i, j, 0, 0, this.field_146999_f, inv_start_y + (rows * 18));
        		this.func_73729_b(i, j + (inv_start_y + (rows * 18)), 0, 125, this.field_146999_f, 97);
        	}
        	else {
        		this.field_146297_k.func_110434_K().func_110577_a(this.chestItemType.getAlternativeMinecraftGuiTexture());
        		this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        	}
        }
        else if(WSBIM.options.usingDarkGuiContainerTheme() && this.chestItemType.hasDarkGuiTexture()) {
        	this.field_146297_k.func_110434_K().func_110577_a(this.chestItemType.getDarkGuiTexture());
        	this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        }
        else {
        	this.field_146297_k.func_110434_K().func_110577_a(this.chestItemType.getGuiTexture());
        	this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        }
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void func_146979_b(int mouseX, int mouseY){
        String s = this.chestItemStack.func_82833_r();
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
	    if(this.chestItemStack.func_77973_b() instanceof IColoredItem && !(WSBIM.options.renderBasedOffVanillaTextures() && this.chestItemType.hasAlternativeMinecraftGuiTexture())) {
        	IColoredItem coloreditem = (IColoredItem) this.chestItemStack.func_77973_b();
        	int[] string_rgb = ColorHelper.convertIntegerToRGB(drawColor);
        	int itemColor = coloreditem.getColor(chestItemStack);
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
        this.field_146289_q.func_78276_b(s, this.chestItemType.getRowRenderStart(), this.chestItemType.getColumnRenderStart() - this.field_146289_q.field_78288_b - 2, drawColor);
        int plr_inventory_base_height = (this.chestItemType.getColumnRenderStart() - 1) + (this.chestItemType.getNumRows() * 18);
        this.field_146289_q.func_78276_b(this.player.field_71071_by.func_145748_c_().func_150260_c(), this.chestItemType.getInventoryPlayerRowStart(), plr_inventory_base_height + 3, drawColor);
    }

}
