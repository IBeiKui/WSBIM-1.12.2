package com.finalkg.wsbim.client.gui.screen.options;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

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
        Minecraft minecraft = Minecraft.getMinecraft();
        this.dragged = this.normalizeValue((float)option.getStartingValue(), option.getMin(), option.getMax());
        this.displayString = option.guiName + ": "+option.getStartingValue();
        this.colorGui = gui;
        this.varClass = varClass;
    }
    public int getHoverState(boolean p_146114_1_){
        return 0;
    }

    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_){
        if (this.visible){
            if (this.dragging){
                this.dragged = (float)(p_146119_2_ - (this.x + 4)) / (float)(this.width - 8);
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
                this.displayString = option.guiName + ": "+i;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int)(this.dragged * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }
    
    public float normalizeValue(float f, int min, int max){
        return MathHelper.clamp((this.snapToStepClamp(f, min, max) - min) / (max - min), 0.0F, 1.0F);
    }
    
    public float denormalizeValue(float f, int min, int max){
        return this.snapToStepClamp(min + (max - min) * MathHelper.clamp(f, 0.0F, 1.0F), min, max);
    }

    public float snapToStepClamp(float f, int min, int max){
        f = this.snapToStep(f);
        return MathHelper.clamp(f, min, max);
    }
 
    protected float snapToStep(float f){
        if (option.getValueStep() > 0.0F)
        {
            f = option.getValueStep() * (float)Math.round(f / option.getValueStep());
        }
        return f;
    }

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
    public void mouseReleased(int p_146118_1_, int p_146118_2_){
        this.dragging = false;
    }
}