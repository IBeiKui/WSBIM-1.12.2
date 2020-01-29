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
		this.field_146999_f = 256;
		this.field_147000_g = 256;
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
		GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(WSBIM.options.usingDarkGuiContainerTheme() ? MIXED_METAL_CHEST_GUI_DARK : MIXED_METAL_CHEST_GUI);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void func_146979_b(int mouseX, int mouseY){
        String s = I18n.func_135052_a("gui.mixedMetalChest", new Object[0]);
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() ? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.field_146289_q.func_78276_b(s, 12, 6, drawColor);
        this.field_146289_q.func_78276_b(this.playerInventory.func_145748_c_().func_150260_c(), 48, this.field_147000_g - 92, drawColor);
    }

}
