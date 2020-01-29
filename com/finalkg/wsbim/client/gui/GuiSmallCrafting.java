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
    public void func_73863_a(int mouseX, int mouseY, float partialTicks){
        this.func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.func_191948_b(mouseX, mouseY);
    }
	
	protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		int grid_width = 88;
		int grid_height = 48;
		GlStateManager.func_179094_E();
		GlStateManager.func_179131_c(1, 1, 1, 1);
		if(WSBIM.options.renderBasedOffVanillaTextures()){
			Minecraft.func_71410_x().func_110434_K().func_110577_a(VANILLA_BACKGROUND);
			this.func_73729_b(field_147003_i, field_147009_r, 0, 0, field_146999_f, field_147000_g);
			this.func_73729_b(field_147003_i+16, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+32, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+48, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+64, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+80, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+96, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+112, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+128, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+144, field_147009_r+10, 16, 10, 16, 65);
			this.func_73729_b(field_147003_i+160, field_147009_r+10, 16, 10, 8, 65);
		}
		else{
			field_146297_k.func_110434_K().func_110577_a(TAB_TEXTURE);
			this.func_73729_b(field_147003_i, field_147009_r, 0, 0, field_146999_f, field_147000_g);
		}
		boolean flag = WSBIM.options.renderBasedOffVanillaTextures();
		if(flag) {
			grid_width = 74;
			grid_height = 36;
			field_146297_k.func_110434_K().func_110577_a(VANILLA_INVENTORY); 
			this.func_73729_b(field_147003_i + (field_146999_f / 2 - (grid_width / 2)), field_147009_r + 25, 97, 17, grid_width, grid_height);
		}
		else {
			field_146297_k.func_110434_K().func_110577_a(DEFAULT_INVENTORY_PLACEHOLDER); 
			this.func_73729_b(field_147003_i + (field_146999_f / 2 - (grid_width / 2)), field_147009_r + 18, 80, 18, grid_width, grid_height);
		}
		GlStateManager.func_179121_F();
	}
	
	protected void func_146979_b(int mouseX, int mouseY){
		super.func_146979_b(mouseX, mouseY);;
		  String s = I18n.func_135052_a("container.crafting", new Object[0]);
		  int drawColor = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
	      this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, drawColor);
	      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, drawColor);
	}

}
