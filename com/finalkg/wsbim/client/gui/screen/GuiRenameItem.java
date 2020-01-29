package com.finalkg.wsbim.client.gui.screen;

import java.io.IOException;
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
import org.lwjgl.input.Keyboard;


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
		this.initialTextBoxText = this.backpackStack.func_82833_r().equals(this.type) ? "" : this.backpackStack.func_82833_r().split(" "+this.type)[0];
	}
	
	public void func_73864_a(int i, int j, int k) throws IOException{
		super.func_73864_a(i, j, k);
		this.renameTextBox.func_146192_a(i, j, k);
	}
	
	
	public void func_73863_a(int mx, int my, float part){
		this.func_146276_q_();
		super.func_73863_a(mx, my, part);
		if(this.renameTextBox !=null){
			this.renameTextBox.func_146194_f();
		}
		this.func_73731_b(Minecraft.func_71410_x().field_71466_p, "Rename "+this.type, this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a("Rename "+this.type) / 2), this.field_146295_m / 2 - 100  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), 16777215);
		if(doneButton.field_146124_l){
			this.func_73731_b(Minecraft.func_71410_x().field_71466_p, TextFormatting.GREEN+"Hit Done or Enter to save changes", this.field_146294_l / 2 - (Minecraft.func_71410_x().field_71466_p.func_78256_a("Hit Done or Enter to save changes") / 2), this.field_146295_m / 2 - 100 + 48 + 12  - (Minecraft.func_71410_x().field_71466_p.field_78288_b), 16777215);
		}
		this.func_73731_b(Minecraft.func_71410_x().field_71466_p, "+ "+this.type, this.field_146294_l / 2 + 105, this.field_146295_m / 2 - 76 + (Minecraft.func_71410_x().field_71466_p.field_78288_b / 2), 16777215);

	}
	

	
	public void func_73869_a(char c, int key) throws IOException{
		super.func_73869_a(c, key);
		if(this.renameTextBox.func_146206_l()) this.renameTextBox.func_146201_a(c, key);
		
		if(key == Keyboard.KEY_ESCAPE) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
		}
		this.doneButton.field_146124_l = this.renameTextBox.func_146179_b() !=null  && !(this.renameTextBox.func_146179_b().equals(""))&& this.renameTextBox.func_146179_b().length() < 25 && !(this.type.equals(this.renameTextBox.func_146179_b())) && !(this.initialTextBoxText.equals(this.renameTextBox.func_146179_b()));
		
		if(this.renameTextBox.func_146206_l() && this.doneButton.field_146124_l){
			if(key == Keyboard.KEY_RETURN){
				this.func_146284_a(doneButton);
			}
		}
	}
	
	public void func_73866_w_(){
		super.func_73866_w_();
		this.doneButton = new GuiButton(0,this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 76 + 48,I18n.func_135052_a("gui.done", new Object[0]));
		this.cancelButton = new GuiButton(1,this.field_146294_l / 2 - 100, this.field_146295_m / 2,I18n.func_135052_a("gui.cancel", new Object[0]));
		GuiButton resetNameButton = new GuiButton(2, this.field_146294_l / 2 - 100, this.field_146295_m / 2 + 28, "Reset Name");
		this.renameTextBox = new GuiTextField(2, Minecraft.func_71410_x().field_71466_p, this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 76, 200, 20);
		this.field_146292_n.add(doneButton);
		this.field_146292_n.add(cancelButton);
		this.field_146292_n.add(resetNameButton);
		this.doneButton.field_146124_l = false;
		this.renameTextBox.func_146180_a(this.backpackStack.func_82833_r().equals(this.type) ? "" : this.backpackStack.func_82833_r().split(" "+this.type)[0]);
	}
	
	public void func_146284_a(GuiButton button){
		if(button.field_146127_k == 0){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackNamePacket packet2 = new ChangeItemStackNamePacket(this.renameTextBox.func_146179_b() + " " + this.type, itemIndex, false);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
		if(button.field_146127_k == 1){
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
		if(button.field_146127_k == 2) {
			CloseAllIInventoriesOnPlayerPacket packet = new CloseAllIInventoriesOnPlayerPacket();
			PacketDispatcher.sendToServer(packet);
			ChangeItemStackNamePacket packet2 = new ChangeItemStackNamePacket(this.type, itemIndex, true);
			PacketDispatcher.sendToServer(packet2);
			GuiHelper.closePlayerInventoriesOnClientSide();
			Minecraft.func_71410_x().func_147108_a(null);
		}
	}
	public boolean func_73868_f(){
		return false;
	}
}
