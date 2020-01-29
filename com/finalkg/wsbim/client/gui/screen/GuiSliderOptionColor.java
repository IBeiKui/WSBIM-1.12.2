package com.finalkg.wsbim.client.gui.screen;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import com.finalkg.wsbim.client.lib.option.OptionInteger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderOptionColor extends GuiButton
{
    private float dragged;
    public boolean dragging;
    private OptionInteger option;
    public int value;

    public GuiSliderOptionColor(Class varClass, int p_i45017_1_, int p_i45017_2_, int p_i45017_3_, OptionInteger p_i45017_4_)
    {
        super(p_i45017_1_, p_i45017_2_, p_i45017_3_, 150, 20, "");
        this.dragged = 1.0F;
        this.option = p_i45017_4_;
        this.value = p_i45017_4_.getStartingValue();
       Minecraft minecraft = Minecraft.getMinecraft();
        this.dragged = this.normalizeValue((float)option.getStartingValue(), option.getMin(), option.getMax());
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
                int i = (int)f;
                this.value = i;
                this.dragged = this.normalizeValue(f, option.getMin(), option.getMax());
                this.displayString = option.guiName + ": "+i;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float p_148266_1_, int min, int max)
    {
        return MathHelper.clamp((this.snapToStepClamp(p_148266_1_, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float p_148262_1_, int min, int max)
    {
        return this.snapToStepClamp(min + (max - min) * MathHelper.clamp(p_148262_1_, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float p_148268_1_, int min, int max)
    {
        p_148268_1_ = this.snapToStep(p_148268_1_);
        return MathHelper.clamp(p_148268_1_, min, max);
    }
    
    protected float snapToStep(float p_148264_1_)
    {
        if (option.getValueStep() > 0.0F)
        {
            p_148264_1_ = option.getValueStep() * (float)Math.round(p_148264_1_ / option.getValueStep());
        }

        return p_148264_1_;
    }
    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
        if (super.mousePressed(p_146116_1_, p_146116_2_, p_146116_3_))
        {
            this.dragged = (float)(p_146116_2_ - (this.x + 4)) / (float)(this.width - 8);

            if (this.dragged < 0.0F)
            {
                this.dragged = 0.0F;
            }

            if (this.dragged > 1.0F)
            {
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
    public void mouseReleased(int p_146118_1_, int p_146118_2_){
        this.dragging = false;
    }

}