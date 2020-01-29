package com.finalkg.wsbim.client.gui.screen;

import org.lwjgl.input.Keyboard;

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
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	public void drawScreen(int mx, int my, float renderTick){
		//Draw the default background first
		this.drawDefaultBackground();
		super.drawScreen(mx, my, renderTick);
		this.drawCenteredString(this.fontRenderer, "Recolor "+this.itemStack.getDisplayName(), this.width / 2, 15, ColorHelper.WHITE);
        Position pos = this.getPos(1);
        this.red = ((GuiSliderOptionColor)this.buttonList.get(0)).value;
        this.green = ((GuiSliderOptionColor)this.buttonList.get(1)).value;
        this.blue = ((GuiSliderOptionColor)this.buttonList.get(2)).value;
        this.doneButton.enabled = (red != defaultRGB[0] || green !=defaultRGB[1] || blue != defaultRGB[2]);
        int x = pos.x + 40;
        int y = pos.y - 32;
        float redf = red/255F;
        float greenf = green/255F;
        float bluef = blue/255F;
        GuiHelper.drawSpecificRect(x, y, x+80, y+80, redf, greenf, bluef, 1F);
	}
	
	public void initGui(){
		Position pos = this.getPos(0);
		this.buttonList.add(new GuiSliderOptionColor(getClass(), -1, pos.x, pos.y-24, redOption));
		Position pos1 = this.getPos(2);
		this.buttonList.add(new GuiSliderOptionColor(getClass(), -1, pos1.x, pos1.y-24, greenOption));
		Position pos2 = this.getPos(4);
		this.buttonList.add(new GuiSliderOptionColor(getClass(), -1, pos2.x, pos2.y-24, blueOption));
		this.doneButton = new GuiButton(200, this.width / 2 - 100, this.height / 6 + 148, I18n.format("gui.done", new Object[0]));
		this.buttonList.add(doneButton);
		this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.cancel", new Object[0])));
		this.buttonList.add(new GuiButton(202, this.width / 2 - 100, this.height / 6 + 188, I18n.format("Reset Item Color", new Object[0])));
	}
	public Position getPos(int num){
		switch(num){
			case 0:
				return new Position(this.width / 2 - 155, this.height / 6 + 72 - 6);
			case 1:
				return new Position(this.width / 2 + 5, this.height / 6 + 72 - 6);
			case 2:
				return new Position(this.width / 2 - 155, this.height / 6 + 96 - 6);
			case 3:
				return new Position(this.width / 2 + 5, this.height / 6 + 96 - 6);
			case 4:
				return new Position(this.width / 2 - 155, this.height / 6 + 120 - 6);
			case 5:
				return new Position(this.width / 2 + 5, this.height / 6 + 120 - 6);
			case 6:
				return new Position(this.width / 2 - 155, this.height / 6 + 144 - 6);
			case 7:
				return new Position(this.width / 2 + 5, this.height / 6 + 144 - 6);
		}
		return null;
	}
	public void actionPerformed(GuiButton button){
		if(button.id == 200){
			String newColor = ColorHelper.convertRGBToInteger(red, green, blue)+"";
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(newColor, ITEM_INDEX, false);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if(button.id == 201){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if(button.id == 202) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(this.defaultColor+"", ITEM_INDEX, true);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	/**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
	  /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char character, int key){
        if(key == Keyboard.KEY_RETURN && this.doneButton.enabled){
			String newColor = ColorHelper.convertRGBToInteger(red, green, blue)+"";
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackColorPacket packet2 = new ChangeItemStackColorPacket(newColor, ITEM_INDEX, false);
			PacketDispatcher.sendToServer(packet2);
			Minecraft.getMinecraft().displayGuiScreen(null);
        }
        if(key == Keyboard.KEY_ESCAPE) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }
	
}
