package com.finalkg.wsbim.client.gui.screen;

import com.finalkg.wsbim.WSBIM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonEdit extends GuiButton {

	private final ResourceLocation EDIT_BUTTON_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/gui/icons.png");
	
	public GuiButtonEdit(int buttonId, int x, int y) {
		super(buttonId, x, y, 18, 18, null);
	}

	
	/**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
    	super.drawButton(mc, mouseX, mouseY, partialTicks);
        if (this.visible){
            mc.getTextureManager().bindTexture(EDIT_BUTTON_TEXTURE);
            GlStateManager.disableDepth();
            int i = 0;
            int j = 0;
            this.drawTexturedModalRect(this.x + 1, this.y + 1, i, j, this.width - 2, this.height - 2);
            GlStateManager.enableDepth();
        }
    }
}
