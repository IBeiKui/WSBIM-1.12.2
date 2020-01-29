package com.finalkg.wsbim.client.gui.screen;

import org.lwjgl.input.Keyboard;
import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.client.lib.Position;
import com.finalkg.wsbim.client.lib.option.OptionIntegerColor;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.net.PacketDispatcher;
import com.finalkg.wsbim.common.net.server.ChangeItemStackColorPacket;
import com.finalkg.wsbim.common.net.server.CloseAllIInventoriesOnPlayerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;


import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.client.lib.Position;
import com.finalkg.wsbim.client.lib.option.OptionIntegerColor;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.net.PacketDispatcher;
import com.finalkg.wsbim.common.net.server.ChangeItemStackColorPacket;
import com.finalkg.wsbim.common.net.server.ChangeItemStackNamePacket;
import com.finalkg.wsbim.common.net.server.CloseAllIInventoriesOnPlayerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class GuiRecolorItem extends GuiScreen {

	private OptionIntegerColor redOption;
	private OptionIntegerColor greenOption;
	private OptionIntegerColor blueOption;
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	private final ItemStack itemStack;
	private final int defaultColor;
	private final int ITEM_INDEX;
	private final int[] defaultRGB;
	
	private GuiButton doneButton;
	
	public GuiRecolorItem(ItemStack itemStack, int default_color, int index) {
		this.itemStack = itemStack;
		this.defaultColor = default_color;
		this.ITEM_INDEX = index;
		int[] default_rgb = ColorHelper.convertIntegerToRGB(default_color);
		this.redOption = new OptionIntegerColor("gui.wsbim.colorRed", default_rgb[0]);
		this.greenOption = new OptionIntegerColor("gui.wsbim.colorGreen", default_rgb[1]);
		this.blueOption = new OptionIntegerColor("gui.wsbim.colorBlue", default_rgb[2]);
		this.red = default_rgb[0];
		this.green = default_rgb[1];
		this.blue = default_rgb[2];
		this.defaultRGB = default_rgb;
	}
	
	public boolean func_73868_f(){
		return false;
	}
	
	public void func_73863_a(int mx, int my, float renderTick){
		//Draw the default background first
		this.func_146276_q_();
		super.func_73863_a(mx, my, renderTick);
		this.func_73732_a(this.field_146289_q, "Recolor "+this.itemStack.func_82833_r(), this.field_146294_l / 2, 15, ColorHelper.WHITE);
        Position pos = this.getPos(1);
        this.red = ((GuiSliderOptionColor)this.field_146292_n.get(0)).value;
        this.green = ((GuiSliderOptionColor)this.field_146292_n.get(1)).value;
        this.blue = ((GuiSliderOptionColor)this.field_146292_n.get(2)).value;
        this.doneButton.field_146124_l = (red != defaultRGB[0] || green !=defaultRGB[1] || blue != defaultRGB[2]);
        int x = pos.x + 40;
        int y = pos.y - 32;
        float redf = red/255F;
        float greenf = green/255F;
        float bluef = blue/255F;
        GuiHelper.drawSpecificRect(x, y, x+80, y+80, redf, greenf, bluef, 1F);
	}
	
	public void func_73866_w_(){
		Position pos = this.getPos(0);
		this.field_146292_n.add(new GuiSliderOptionColor(getClass(), -1, pos.x, pos.y-24, redOption));
		Position pos1 = this.getPos(2);
		this.field_146292_n.add(new GuiSliderOptionColor(getClass(), -1, pos1.x, pos1.y-24, greenOption));
		Position pos2 = this.getPos(4);
		this.field_146292_n.add(new GuiSliderOptionColor(getClass(), -1, pos2.x, pos2.y-24, blueOption));
		this.doneButton = new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 148, I18n.func_135052_a("gui.done", new Object[0]));
		this.field_146292_n.add(doneButton);
		this.field_146292_n.add(new GuiButton(201, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, I18n.func_135052_a("gui.cancel", new Object[0])));
		this.field_146292_n.add(new GuiButton(202, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 188, I18n.func_135052_a("Reset Item Color", new Object[0])));
	}
	public Position getPos(int num){
		switch(num){
			case 0:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 72 - 6);
			case 1:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 72 - 6);
			case 2:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96 - 6);
			case 3:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 96 - 6);
			case 4:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 120 - 6);
			case 5:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 120 - 6);
			case 6:
				return new Position(this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 144 - 6);
			case 7:
				return new Position(this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 144 - 6);
		}
		return null;
	}
	public void func_146284_a(GuiButton button){
		if(button.field_146127_k == 200){
			String newColor = ColorHelper.convertRGBToInteger(red, green, blue)+"";
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(newColor, ITEM_INDEX, false);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
		if(button.field_146127_k == 201){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
		if(button.field_146127_k == 202) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(this.defaultColor+"", ITEM_INDEX, true);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
	}
	/**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
	  /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void func_73869_a(char character, int key){
        if(key == Keyboard.KEY_RETURN && this.doneButton.field_146124_l){
			String newColor = ColorHelper.convertRGBToInteger(red, green, blue)+"";
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(newColor, ITEM_INDEX, false);
			PacketDispatcher.sendToServer(packet2);
			Minecraft.func_71410_x().func_147108_a(null);
        }
        if(key == Keyboard.KEY_ESCAPE) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			Minecraft.func_71410_x().func_147108_a(null);
        }
    }
	
}
