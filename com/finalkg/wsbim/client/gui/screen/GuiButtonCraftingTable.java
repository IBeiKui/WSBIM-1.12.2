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
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks){
    	if (this.field_146125_m){
        	this.open = mc.field_71462_r != null && (mc.field_71462_r instanceof GuiSmallCrafting || mc.field_71462_r instanceof GuiNormalCrafting);
        	GlStateManager.func_179094_E();
        	mc.func_110434_K().func_110577_a(WSBIM.options.useResourcePackTabTexture ? TAB_TEXTURE_RESOURCEPACK : TAB_TEXTURE_DEFAULT);
        	GlStateManager.func_179131_c(1, 1, 1, 1);
        	this.func_73729_b(field_146128_h, field_146129_i - (this.open? 2 : 0), 140, this.open ? 32 : 2, 28, this.open ? 32 : 26);
        	this.func_73729_b(field_146128_h + 25, field_146129_i + 26, 165, 28, 3, 1);
        	this.func_73729_b(field_146128_h + 26, field_146129_i + 27, 166, 28, 2, 1);
        	this.func_73729_b(field_146128_h + 27, field_146129_i + 28, 167, 28, 1, 1);
        	GlStateManager.func_179121_F();
        	GlStateManager.func_179094_E();
    		GlStateManager.func_179091_B();
    		GlStateManager.func_179147_l();
    		RenderHelper.func_74520_c();
    		GuiHelper.renderItemStackBasic(new ItemStack(Blocks.field_150462_ai), field_146128_h + 6, field_146129_i + (6), partialTicks);
    		RenderHelper.func_74518_a();
    		GlStateManager.func_179101_C();
    		GlStateManager.func_179121_F();
        }
    }
    
    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY)
    {
    	if(super.func_146116_c(mc, mouseX, mouseY)) {
    		this.selected = true;
    		return true;
    	}
    	else {
    		this.selected = false;
    		return false;
    	}
    }
    public void func_146113_a(SoundHandler soundHandlerIn){}
}
