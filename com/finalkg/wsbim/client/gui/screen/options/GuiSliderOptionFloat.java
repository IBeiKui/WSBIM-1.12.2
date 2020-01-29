package com.finalkg.wsbim.client.gui.screen.options;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.WSBIMOptions;
import com.finalkg.wsbim.client.lib.option.OptionFloat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderOptionFloat extends GuiButton
{
    private float dragged;
    public boolean dragging;
    private OptionFloat option;
    private static final String __OBFID = "CL_00000680";


    public GuiSliderOptionFloat(int id, int x, int y, OptionFloat optionf)
    {
        super(id, x, y, 150, 20, "");
        this.dragged = 1.0F;
        this.option = optionf;
       Minecraft minecraft = Minecraft.getMinecraft();
        this.dragged = this.normalizeValue(option.getStartingValue(), option.getMin(), option.getMax());
        this.displayString = option.guiName + ": "+option.getStartingValue();
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean p_146114_1_)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.dragged = (float)(p_146119_2_ - (this.x + 4)) / (float)(this.width - 8);

                if (this.dragged < 0.0F)
                {
                    this.dragged = 0.0F;
                }

                if (this.dragged > 1.0F)
                {
                    this.dragged = 1.0F;
                }

                float f = this.denormalizeValue(this.dragged, option.getMin(), option.getMax());
                Field field = null;
				try {
					field = WSBIMOptions.class.getDeclaredField(option.variableName);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                field.setAccessible(true);
                try {
					field.setFloat(WSBIM.options, f);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                this.dragged = this.normalizeValue(f, option.getMin(), option.getMax());
                this.displayString = option.guiName + ": "+f;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float f, float min, float max){
        return MathHelper.clamp((this.snapToStepClamp(f, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float f, float min, float max){
        return this.snapToStepClamp(min + (max - min) * MathHelper.clamp(f, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float f, float min, float max){
        f = this.snapToStep(f);
        return MathHelper.clamp(f, min, max);
    }
    protected float snapToStep(float f){
        if (option.getValueStep() > 0.0F){
            f = option.getValueStep() * (float)Math.round(f / option.getValueStep());
        }
        return f;
    }
    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY){
        if (super.mousePressed(mc, mouseX, mouseY)){
            this.dragged = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            if (this.dragged < 0.0F){
                this.dragged = 0.0F;
            }
            if (this.dragged > 1.0F){
                this.dragged = 1.0F;
            }
            this.dragging = true;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int p_146118_1_, int p_146118_2_)
    {
        this.dragging = false;
    }
    long ms = 0L;
    boolean flag = false;
    public boolean showingTooltip;
  
    public void drawDescription(Minecraft mc, int x, int y){
    	List<String> list = OptionDescriptions.getItemDescription(option.variableName);
    	if(!flag){
    		showingTooltip = false;
    	}
    	
    	if(list !=null && list.size() > 0 && ((x >= x && x <= this.x + this.width) && (y >= this.y && y <= this.y + this.height)) && flag){
    		if(!(ms+600 >= System.currentTimeMillis())){
    			this.showingTooltip = true;
    			this.drawHoveringText(list, this.x-8, this.y + this.height * 2 - 4, mc.fontRenderer);
    		}
    	}
    	else{
    		flag = false;
    		showingTooltip = false;
    	}
    	
    	if(!flag){
    		ms = System.currentTimeMillis();
    		flag = true;
    	}
    }
    protected void drawHoveringText(List list, int x, int y, FontRenderer font){
        if (!list.isEmpty()){
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.pushMatrix();
            int k = 0;
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);
                if (l > k){
                    k = l;
                }
            }
            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;
            if (list.size() > 1){
                i1 += 2 + (list.size() - 1) * 10;
            }
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
            for (int i2 = 0; i2 < list.size(); ++i2){
                String s1 = (String)list.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);
                if (i2 == 0){
                    k2 += 2;
                }
                k2 += 10;
            }
            GlStateManager.popMatrix();
        }
    }
}