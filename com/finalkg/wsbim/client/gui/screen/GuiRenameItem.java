package com.finalkg.wsbim.client.gui.screen;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.finalkg.wsbim.client.lib.GuiHelper;
import com.finalkg.wsbim.common.net.PacketDispatcher;
import com.finalkg.wsbim.common.net.server.ChangeItemStackNamePacket;
import com.finalkg.wsbim.common.net.server.CloseAllIInventoriesOnPlayerPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class GuiRenameItem extends GuiScreen {
	
	private GuiButton doneButton;
	private GuiTextField renameTextBox;
	private GuiButton cancelButton;
	private ItemStack backpackStack;
	private String type;
	private final int itemIndex;
	private final String initialTextBoxText;
	/**
	 * Allows for you to rename an item with this GUI. Type is the base name of the item.
	 * @param backpackStack - itemstack to rename
	 * @param type - base name of the item
	 */
	public GuiRenameItem(ItemStack backpackStack, String type, int itemIndex){
		this.backpackStack = backpackStack;
		this.type = type;
		this.itemIndex = itemIndex;
		this.initialTextBoxText = this.backpackStack.getDisplayName().equals(this.type) ? "" : this.backpackStack.getDisplayName().split(" "+this.type)[0];
	}
	
	public void mouseClicked(int i, int j, int k) throws IOException{
		super.mouseClicked(i, j, k);
		this.renameTextBox.mouseClicked(i, j, k);
	}
	
	
	public void drawScreen(int mx, int my, float part){
		this.drawDefaultBackground();
		super.drawScreen(mx, my, part);
		if(this.renameTextBox !=null){
			this.renameTextBox.drawTextBox();
		}
		this.drawString(Minecraft.getMinecraft().fontRenderer, "Rename "+this.type, this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth("Rename "+this.type) / 2), this.height / 2 - 100  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		if(doneButton.enabled){
			this.drawString(Minecraft.getMinecraft().fontRenderer, TextFormatting.GREEN+"Hit Done or Enter to save changes", this.width / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth("Hit Done or Enter to save changes") / 2), this.height / 2 - 100 + 48 + 12  - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT), 16777215);
		}
		this.drawString(Minecraft.getMinecraft().fontRenderer, "+ "+this.type, this.width / 2 + 105, this.height / 2 - 76 + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT / 2), 16777215);

	}
	

	
	public void keyTyped(char c, int key) throws IOException{
		super.keyTyped(c, key);
		if(this.renameTextBox.isFocused()) this.renameTextBox.textboxKeyTyped(c, key);
		
		if(key == Keyboard.KEY_ESCAPE) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
		}
		this.doneButton.enabled = this.renameTextBox.getText() !=null  && !(this.renameTextBox.getText().equals(""))&& this.renameTextBox.getText().length() < 25 && !(this.type.equals(this.renameTextBox.getText())) && !(this.initialTextBoxText.equals(this.renameTextBox.getText()));
		
		if(this.renameTextBox.isFocused() && this.doneButton.enabled){
			if(key == Keyboard.KEY_RETURN){
				this.actionPerformed(doneButton);
			}
		}
	}
	
	public void initGui(){
		super.initGui();
		this.doneButton = new GuiButton(0,this.width / 2 - 100, this.height / 2 - 76 + 48,I18n.format("gui.done", new Object[0]));
		this.cancelButton = new GuiButton(1,this.width / 2 - 100, this.height / 2,I18n.format("gui.cancel", new Object[0]));
		GuiButton resetNameButton = new GuiButton(2, this.width / 2 - 100, this.height / 2 + 28, "Reset Name");
		this.renameTextBox = new GuiTextField(2, Minecraft.getMinecraft().fontRenderer, this.width / 2 - 100, this.height / 2 - 76, 200, 20);
		this.buttonList.add(doneButton);
		this.buttonList.add(cancelButton);
		this.buttonList.add(resetNameButton);
		this.doneButton.enabled = false;
		this.renameTextBox.setText(this.backpackStack.getDisplayName().equals(this.type) ? "" : this.backpackStack.getDisplayName().split(" "+this.type)[0]);
	}
	
	public void actionPerformed(GuiButton button){
		if(button.id == 0){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackNamePacket packet2 = new ChangeItemStackNamePacket(this.renameTextBox.getText() + " " + this.type, itemIndex, false);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if(button.id == 1){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		if(button.id == 2) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackNamePacket packet2 = new ChangeItemStackNamePacket(this.type, itemIndex, true);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	public boolean doesGuiPauseGame(){
		return false;
	}
}
