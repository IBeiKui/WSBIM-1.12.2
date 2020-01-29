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
import net.minecraft.util.ResourceLocation;


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
		this.field_146999_f = 176;
		this.field_147000_g = 209;
		waterToolTip.add("Current Water Level:");
		waterToolTip.add(iceMakerTile.waterTime+"t/"+iceMakerTile.MAX_WATER_CAPACITY+"t");
		powerTooltip.add("Current Power Level:");
		powerTooltip.add(iceMakerTile.fuelTime+"t/"+iceMakerTile.MAX_POWER_CAPACITY+"t");
		waterItemToolTip.add("Accepted water items:");
		for(ItemStack stack : iceMakerTile.ACCEPTED_WATER_ITEMS) {
			waterItemToolTip.add(stack.func_82833_r());
		}
		powerItemToolTip.add("Accepted power items:");
		for(ItemStack stack : iceMakerTile.ACCEPTED_POWER_ITEMS) {
			powerItemToolTip.add(stack.func_82833_r());
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
	
	/**
	 * A few custom tool tips for helping people know what to do.
	 */
	@Override
    protected void func_191948_b(int mouseX, int mouseY){
        boolean flag = true; //Render item tool tip?
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        if(mouseX >= (i + 6) && mouseX <= (i + 32) && mouseY >= (j+7) && mouseY <= (j+78)) {
        	waterToolTip.set(1, iceMakerTile.waterTime+"t/"+iceMakerTile.MAX_WATER_CAPACITY+"t");
        	this.drawHoveringText(waterToolTip, mouseX, mouseY, field_146289_q);
        }
        if(mouseX >= (i + 40) && mouseX <= (i + 65) && mouseY >= (j+7) && mouseY <= (j+78)) {
        	powerTooltip.set(1, iceMakerTile.fuelTime+"t/"+iceMakerTile.MAX_POWER_CAPACITY+"t");
        	this.drawHoveringText(powerTooltip, mouseX, mouseY, field_146289_q);
        }
        if(mouseX >= (i + 12) && mouseX <= (i + 28) && mouseY >= (j+84) && mouseY <= (j+101)) {
        	this.drawHoveringText(waterItemToolTip, mouseX, mouseY, field_146289_q);
        	flag = false;
        }
        if(mouseX >= (i + 44) && mouseX <= (i + 60) && mouseY >= (j+84) && mouseY <= (j+101)) {
        	this.drawHoveringText(powerItemToolTip, mouseX, mouseY, field_146289_q);
        	flag = false;
        }
        if(flag) super.func_191948_b(mouseX, mouseY);
    }

	@Override
	protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(WSBIM.options.usingDarkGuiContainerTheme() ? ICE_MAKER_TEXTURE_DARK : ICE_MAKER_TEXTURE);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
		GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(PROGRESS_BARS);
        if (this.iceMakerTile.fuelTime > 0){
            int k = this.getPowerScaled(69);
            this.func_73729_b(i + 40, j + 7 + 69 - k, 0, 69 - k, 24, k + 1);
        }
        if (this.iceMakerTile.waterTime > 0){
            int m = this.getWaterScaled(69);
            this.func_73729_b(i + 7, j + 7 + 69 - m, 24, 69 - m, 24, m + 1);
        }
        int l = this.getProgressScaled(160);
        this.func_73729_b(i + 8, j + 105, 0, 70, l != 0? l + 1 : l, 6);
	}
	@Override
    protected void func_146979_b(int mouseX, int mouseY){
        String s = I18n.func_135052_a("gui.iceMaker", new Object[0]);
        int i = (this.field_146999_f) / 2;
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() ? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.field_146289_q.func_78276_b(s, (i + 32) - this.field_146289_q.func_78256_a(s) / 2, 10, drawColor);
        this.field_146289_q.func_78276_b(this.playerInventory.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, drawColor);
    }
    private int getProgressScaled(int pixels){
        int i = this.iceMakerTile.func_174887_a_(2);
        int j = this.iceMakerTile.PROCESS_TIME;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    private int getPowerScaled(int pixels){
        int i = this.iceMakerTile.MAX_POWER_CAPACITY;
        return this.iceMakerTile.func_174887_a_(0) * pixels / i;
    }
    private int getWaterScaled(int pixels){
        int i = this.iceMakerTile.MAX_WATER_CAPACITY;
        return this.iceMakerTile.waterTime * pixels / i;
    }
}
