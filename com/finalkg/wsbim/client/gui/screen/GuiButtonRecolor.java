package com.finalkg.wsbim.client.gui.screen;

import com.finalkg.wsbim.WSBIM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonRecolor extends GuiButton {

	private final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/gui/icons.png");
	
	public GuiButtonRecolor(int buttonId, int x, int y) {
		super(buttonId, x, y, 18, 18, null);
	}

	
	/**
     * Draws this button to the screen.
     */
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks){
    	super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        if (this.field_146125_m){
            mc.func_110434_K().func_110577_a(BUTTON_TEXTURE);
            GlStateManager.func_179097_i();
            int i = 0;
            int j = 16;
            this.func_73729_b(this.field_146128_h + 1, this.field_146129_i + 1, i, j, this.field_146120_f - 2, this.field_146121_g - 2);
            GlStateManager.func_179126_j();
        }
    }
}
