package com.finalkg.wsbim.client.gui.screen.options;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import com.finalkg.wsbim.client.lib.option.OptionInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.finalkg.wsbim.client.lib.option.OptionInteger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderOptionColorOptions extends GuiButton
{
    private float dragged;
    public boolean dragging;
    private OptionInteger option;
    private GuiModifyColor colorGui;
    private Class varClass;
    
    public GuiSliderOptionColorOptions(GuiModifyColor gui, Class varClass, int id, int x, int y, OptionInteger option){
        super(id, x, y, 150, 20, "");
        this.dragged = 1.0F;
        this.option = option;
        Minecraft minecraft = Minecraft.func_71410_x();
        this.dragged = this.normalizeValue((float)option.getStartingValue(), option.getMin(), option.getMax());
        this.field_146126_j = option.guiName + ": "+option.getStartingValue();
        this.colorGui = gui;
        this.varClass = varClass;
    }
    public int func_146114_a(boolean p_146114_1_){
        return 0;
    }

    protected void func_146119_b(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_){
        if (this.field_146125_m){
            if (this.dragging){
                this.dragged = (float)(p_146119_2_ - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);
                if (this.dragged < 0.0F){
                    this.dragged = 0.0F;
                }
                if (this.dragged > 1.0F){
                    this.dragged = 1.0F;
                }
                float f = this.denormalizeValue(this.dragged, option.getMin(), option.getMax());
                int i = (int)f;
                Field field = null;
				try {
					field = varClass.getDeclaredField(option.variableName);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
                field.setAccessible(true);
                try {
					field.setInt(colorGui, i);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
                this.dragged = this.normalizeValue(f, option.getMin(), option.getMax());
                this.field_146126_j = option.guiName + ": "+i;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(this.field_146128_h + (int)(this.dragged * (float)(this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
            this.func_73729_b(this.field_146128_h + (int)(this.dragged * (float)(this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float f, int min, int max){
        return MathHelper.func_76131_a((this.snapToStepClamp(f, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float f, int min, int max){
        return this.snapToStepClamp(min + (max - min) * MathHelper.func_76131_a(f, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float f, int min, int max){
        f = this.snapToStep(f);
        return MathHelper.func_76131_a(f, min, max);
    }
 
    protected float snapToStep(float f){
        if (option.getValueStep() > 0.0F)
        {
            f = option.getValueStep() * (float)Math.round(f / option.getValueStep());
        }
        return f;
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY){
        if (super.func_146116_c(mc, mouseX, mouseY)){
            this.dragged = (float)(mouseX - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);
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
    public void func_146118_a(int p_146118_1_, int p_146118_2_){
        this.dragging = false;
    }
}
