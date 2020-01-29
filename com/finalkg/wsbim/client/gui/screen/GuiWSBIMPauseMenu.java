package com.finalkg.wsbim.client.gui.screen;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptions;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.WorldTimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.client.GuiModList;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.client.gui.screen.options.GuiWSBIMOptions;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.lib.WorldTimeHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.client.GuiModList;

public class GuiWSBIMPauseMenu extends GuiScreen {
	private List fakeButtonList = new ArrayList();
	private GuiIngameMenu fake;
	private int animY1 = 0;
	public void func_73866_w_(){
		super.func_73866_w_();
		this.fakeButtonList.clear();
		fake = new GuiIngameMenu();
		fake.field_146297_k = this.field_146297_k;
		fake.field_146294_l = field_146294_l;
		fake.field_146295_m = field_146295_m;
		fake.field_146289_q = this.field_146289_q;
		fake.field_146287_f = this.field_146287_f;
		fake.func_73866_w_();
		this.fakeButtonList = fake.field_146292_n;
		this.fakeButtonList.add(new GuiButton(150, this.field_146294_l / 2 - 100, this.field_146295_m / 4 + 120 + 8, I18n.func_135052_a("gui.wsbim.button.options", new Object[0])));
		
		for(int i = 0; i < fakeButtonList.size(); i++){
			if(fakeButtonList.get(i) instanceof GuiButton){
				GuiButton current = (GuiButton)fakeButtonList.get(i);
				GuiButton newButt = null;
				int buttonHeight = (current.field_146121_g / 3) * 2;
				int startYPos = (field_146295_m / 2) - ((fakeButtonList.size() * buttonHeight) / 2);
				
				//Lines Theme
				if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[2]))newButt = new GuiButtonTransparent(current.field_146127_k, current.field_146128_h, current.field_146129_i, current.field_146120_f, current.field_146121_g, current.field_146126_j);
				//List Theme
				else if(WSBIM.options.buttonLayout.equals(WSBIM.options.buttonLayouts[1]))newButt = new GuiButtonCustom(current.field_146127_k, 0, startYPos + (buttonHeight * i), field_146294_l, buttonHeight, current.field_146126_j);
				//Vanilla Theme
				else newButt = new GuiButton(current.field_146127_k, current.field_146128_h, current.field_146129_i, current.field_146120_f, current.field_146121_g, current.field_146126_j);
				int xpos = 0;
				int ypos = 0;
				if(i < fakeButtonList.size() / 2){
					
				}
				newButt.field_146124_l = current.field_146124_l;
				newButt.field_146125_m = current.field_146125_m;
				newButt.packedFGColour = current.packedFGColour;
				
				this.field_146292_n.add(newButt);
			}
		}
	}
	
	public void func_73863_a(int mx, int my, float renderTick){
		String hex = WSBIM.options.foregroundColor;
    	int foregroundred = 0;
    	int foregroundgreen = 0;
    	int foregroundblue = 0;
    	if(!hex.equals("0")){
    		Color c = new Color((int)Long.parseLong(hex.substring(2), 16));
    		foregroundred = c.getRed();
    		foregroundgreen = c.getGreen(); 
    		foregroundblue = c.getBlue();
    	}
    	
    	String hex2 = WSBIM.options.backgroundColor;
    	int backgroundred = 0;
    	int backgroundgreen = 0;
    	int backgroundblue = 0;
    	if(!hex2.equals("0")){
    		Color c2 = new Color((int)Long.parseLong(hex2.substring(2), 16));
    		backgroundred = c2.getRed();
    		backgroundgreen = c2.getGreen(); 
    		backgroundblue = c2.getBlue();
    	}
    	String hex3 = WSBIM.options.textColor;
    	int textred = 0;
    	int textgreen = 0;
    	int textblue = 0;
    	if(!hex3.equals("0")){
    		Color c3 = new Color((int)Long.parseLong(hex3.substring(2), 16));
    		textred = c3.getRed();
    		textgreen = c3.getGreen(); 
    		textblue = c3.getBlue();
    	}
		this.func_146276_q_();
		super.func_73863_a(mx, my, renderTick);
		//Draw the default background first
		//this.drawDefaultBackground();
		
		GuiHelper.drawSpecificRect(0, 20, this.field_146294_l, 40, (float)backgroundred/255F, (float)backgroundgreen/255F, (float)backgroundblue/255F, WSBIM.options.defaultBackgroundOpacity);
		String line1 = I18n.func_135052_a("menu.game");
		field_146297_k.field_71466_p.func_175063_a(line1, this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a(line1) / 2), 35  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		String linetodraw = "WSBIM "+WSBIM.VERSION + (WSBIM.updateChecker.updateNeeded ? " (Version "+WSBIM.updateChecker.updateCheckMCVersion+"-"+WSBIM.updateChecker.updateCheckVersion+" Available)" : "");
		field_146297_k.field_71466_p.func_175063_a(linetodraw, this.field_146294_l - this.field_146289_q.func_78256_a(linetodraw), this.field_146295_m  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		String line2 = I18n.func_135052_a("gui.wsbim.pausemenu.time", new Object[0]) + " " + WorldTimeHelper.getMilitaryTimeStringForMinecraftWorld(field_146297_k.field_71441_e);
		field_146297_k.field_71466_p.func_175063_a(line2, field_146294_l - (Minecraft.func_71410_x().field_71466_p.func_78256_a(line2)) - 10, 35  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), !hex3.equals("0") ? (int)Long.parseLong(hex3.substring(2), 16) : 0);
		
	}
	protected void func_146284_a(GuiButton button) throws IOException{
		if(button.field_146127_k == 150) field_146297_k.func_147108_a(new GuiWSBIMOptions(field_146297_k, this, 0, 8));
        switch (button.field_146127_k){
            case 0:
                this.field_146297_k.func_147108_a(new GuiOptions(this, this.field_146297_k.field_71474_y));
                break;
            case 1:
                boolean flag = this.field_146297_k.func_71387_A();
                boolean flag1 = this.field_146297_k.func_181540_al();
                button.field_146124_l = false;
                this.field_146297_k.field_71441_e.func_72882_A();
                this.field_146297_k.func_71403_a((WorldClient)null);
                if (flag){
                    this.field_146297_k.func_147108_a(new GuiMainMenu());
                }
                else if (flag1){
                    RealmsBridge realmsbridge = new RealmsBridge();
                    realmsbridge.switchToRealms(new GuiMainMenu());
                }
                else{
                    this.field_146297_k.func_147108_a(new GuiMultiplayer(new GuiMainMenu()));
                }

            case 2:
            case 3:
            default:
                break;
            case 4:
                this.field_146297_k.func_147108_a((GuiScreen)null);
                this.field_146297_k.func_71381_h();
                break;
            case 5:
                if (this.field_146297_k.field_71439_g != null)
                this.field_146297_k.func_147108_a(new GuiScreenAdvancements(this.field_146297_k.field_71439_g.field_71174_a.func_191982_f()));
                break;
            case 6:
                if (this.field_146297_k.field_71439_g != null)
                this.field_146297_k.func_147108_a(new GuiStats(this, this.field_146297_k.field_71439_g.func_146107_m()));
                break;
            case 7:
                this.field_146297_k.func_147108_a(new GuiShareToLan(this));
                break;
            case 12:
                net.minecraftforge.fml.client.FMLClientHandler.instance().showGuiScreen(new GuiModList(this.fake));
                break;
        }
    }
}
