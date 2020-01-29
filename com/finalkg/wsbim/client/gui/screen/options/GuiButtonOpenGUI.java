package com.finalkg.wsbim.client.gui.screen.options;

import java.util.Iterator;
import java.util.List;
import com.finalkg.wsbim.client.lib.option.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.finalkg.wsbim.client.lib.option.Option;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;

public class GuiButtonOpenGUI extends GuiButton {
	protected Option op;

	public GuiButtonOpenGUI(Option option, int id, int x, int y,
			int w, int h, String formattedText) {
		super(id, x, y, w, h, formattedText);
		this.op = option;
	}
    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY){
    	if(this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g){
    		mc.func_147108_a(op.getGuiToOpen());
    		return true;
    	}
    	else return false;
    }
    public void func_191745_a(Minecraft mc, int x, int y, float renderTick){
    	super.func_191745_a(mc, x, y, renderTick);
    	List<String> list = OptionDescriptions.getItemDescription(op.variableName);
    	if(list !=null && list.size() > 0){
    		this.drawHoveringText(list, x + this.field_146120_f, y, mc.field_71466_p);
    	}
    }
    protected void drawHoveringText(List list, int x, int y, FontRenderer font){
        if (!list.isEmpty()){
            GlStateManager.func_179101_C();
            RenderHelper.func_74518_a();
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            GlStateManager.func_179094_E();
            int k = 0;
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                String s = (String)iterator.next();
                int l = font.func_78256_a(s);
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
            this.func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
            for (int i2 = 0; i2 < list.size(); ++i2){
                String s1 = (String)list.get(i2);
                font.func_175063_a(s1, j2, k2, -1);
                if (i2 == 0){
                    k2 += 2;
                }
                k2 += 10;
            }
            GlStateManager.func_179121_F();
        }
    }
}
