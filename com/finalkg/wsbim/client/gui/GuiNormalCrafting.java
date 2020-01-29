package com.finalkg.wsbim.client.gui;

import org.lwjgl.opengl.GL11;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerNormalCrafting;
import com.finalkg.wsbim.common.lib.ColorHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.inventory.ContainerNormalCrafting;
import com.finalkg.wsbim.common.lib.ColorHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiNormalCrafting extends GuiContainer{
	
    private static final ResourceLocation VANILLA_CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/crafting_table.png");
    private static final ResourceLocation CRAFTING_TABLE_TEXTURES = new ResourceLocation(com.finalkg.wsbim.WSBIM.MODID, "textures/gui/container/crafting_table.png");
    
    public GuiNormalCrafting(InventoryPlayer playerInv, World worldIn){
        super(new ContainerNormalCrafting(playerInv, worldIn));
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
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void func_146979_b(int mouseX, int mouseY){
    	int c = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
    	int drawColor = ColorHelper.GUI_CONTAINER_TEXT_COLOR;
        this.field_146289_q.func_78276_b(I18n.func_135052_a("container.crafting", new Object[0]), 28, 6, drawColor);
        this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, drawColor);
    }

    protected void func_146976_a(float renderTick, int mouseX, int mouseY){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(WSBIM.options.renderBasedOffVanillaTextures()) this.field_146297_k.func_110434_K().func_110577_a(VANILLA_CRAFTING_TABLE_GUI_TEXTURES);
        else this.field_146297_k.func_110434_K().func_110577_a(CRAFTING_TABLE_TEXTURES);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
