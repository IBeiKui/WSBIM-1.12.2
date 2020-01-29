package com.finalkg.wsbim.client.gui.screen;

import java.lang.reflect.Field;
import com.finalkg.wsbim.client.lib.option.OptionInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;


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
       Minecraft minecraft = Minecraft.func_71410_x();
        this.dragged = this.normalizeValue((float)option.getStartingValue(), option.getMin(), option.getMax());
        this.field_146126_j = option.guiName + ": "+option.getStartingValue();
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int func_146114_a(boolean p_146114_1_)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void func_146119_b(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
    {
        if (this.field_146125_m)
        {
            if (this.dragging)
            {
                this.dragged = (float)(p_146119_2_ - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);

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
                this.field_146126_j = option.guiName + ": "+i;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(this.field_146128_h + (int)(this.dragged * (float)(this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
            this.func_73729_b(this.field_146128_h + (int)(this.dragged * (float)(this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float p_148266_1_, int min, int max)
    {
        return MathHelper.func_76131_a((this.snapToStepClamp(p_148266_1_, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float p_148262_1_, int min, int max)
    {
        return this.snapToStepClamp(min + (max - min) * MathHelper.func_76131_a(p_148262_1_, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float p_148268_1_, int min, int max)
    {
        p_148268_1_ = this.snapToStep(p_148268_1_);
        return MathHelper.func_76131_a(p_148268_1_, min, max);
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
    public boolean func_146116_c(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
        if (super.func_146116_c(p_146116_1_, p_146116_2_, p_146116_3_))
        {
            this.dragged = (float)(p_146116_2_ - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);

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
    public void func_146118_a(int p_146118_1_, int p_146118_2_){
        this.dragging = false;
    }

}
