package com.finalkg.wsbim.client.gui;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerIceMaker;
import com.finalkg.wsbim.common.inventory.ContainerObsidianChest;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;


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
		this.field_146999_f = 176;
		this.field_147000_g = 186;
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
		int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
		if(WSBIM.options.renderBasedOffVanillaTextures()) {
			this.field_146297_k.func_110434_K().func_110577_a(GENERIC_54);
	        this.func_73729_b(i, j, 0, 0, this.field_146999_f, 89);
	        this.func_73729_b(i, j+89, 0, 125, this.field_146999_f, 97);
		}
		else if(WSBIM.options.usingDarkGuiContainerTheme()) {
			this.field_146297_k.func_110434_K().func_110577_a(OBSIDIAN_CHEST_TEXTURE_DARK);
	        this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
		}
		else {
			this.field_146297_k.func_110434_K().func_110577_a(OBSIDIAN_CHEST_TEXTURE);
	        this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
		}
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
    }
	@Override
    protected void func_146979_b(int mouseX, int mouseY){
        String s = I18n.func_135052_a("gui.obsidianChest", new Object[0]);
        int drawColor = WSBIM.options.usingDarkGuiContainerTheme() && !WSBIM.options.renderBasedOffVanillaTextures()? ColorHelper.GUI_CONTAINER_TEXT_COLOR_LIGHT : ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.field_146289_q.func_78276_b(s, 8, 6, drawColor);
        this.field_146289_q.func_78276_b(this.playerInventory.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, drawColor);
    }

}
