package com.finalkg.wsbim.client.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Contains various methods to help with rendering or managing GUIs
 * @author finalkg
 *
 */
@SideOnly(Side.CLIENT)
public class GuiHelper {

    public static void drawSpecificRect(int left, int top, int right, int bottom, float red, float green, float blue, float alpha){
    	if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(red, green, blue, alpha);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)top, 0.0D).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void closePlayerInventoriesOnClientSide() {
    	EntityPlayerSP player = Minecraft.getMinecraft().player;
		Object[] slots = player.openContainer.inventorySlots.toArray();
		List<IInventory> inventories = new ArrayList<IInventory>();
		for(int i = 0; i < slots.length; i++){
			Slot slot = (Slot) slots[i];
			if(slot !=null){
				if(!inventories.contains(slot.inventory)){
					inventories.add(slot.inventory);
				}
			}
		}
		for(int k = 0; k < inventories.size(); k++){
			IInventory inv = inventories.get(k);
			if(inv !=null){
				inv.closeInventory(player);
			}
		}
		player.openContainer.detectAndSendChanges();
		player.openContainer.onContainerClosed(player);
		player.closeScreen();
    }

    /**
     * Basic because mc.currentScreen !=null 
     * and it does not render item overlays.
     * Used for tabs.
     * @param itemStack
     * @param x
     * @param y
     * @param partialTicks
     */
	public static void renderItemStackBasic(ItemStack itemStack, int x, int y, float partialTicks) {
        if (itemStack != null){
            float f1 = (float)itemStack.getAnimationsToGo() - partialTicks;
            if (f1 > 0.0F){
                GlStateManager.pushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GlStateManager.translate((float)(x + 8), (float)(y + 12), 0.0F);
                GlStateManager.scale(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GlStateManager.translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            Minecraft.getMinecraft().currentScreen.itemRender.renderItemIntoGUI(itemStack, x, y);
            if (f1 > 0.0F){
                GlStateManager.popMatrix();
            }
        }
	}
	
	/**
     * Renders the specified item of the inventory slot at the specified location. 
     * Works anytime with mc reference.
     */
    public static void renderItemStack(Minecraft mc, EntityPlayerSP player, ItemStack stack, int x, int y, float renderTick){
    	net.minecraft.client.renderer.RenderItem renderItem = mc.getRenderItem();
        if (stack != null && !stack.isEmpty()){
            float f1 = (float)stack.getAnimationsToGo() - renderTick;
            if (f1 > 0.0F){
                GlStateManager.pushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GlStateManager.translate((float)(x + 8), (float)(y + 12), 0.0F);
                GlStateManager.scale(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GlStateManager.translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }
            renderItem.renderItemAndEffectIntoGUI(player, stack, x, y);
            if (f1 > 0.0F){
                GlStateManager.popMatrix();
            }
            renderItem.renderItemOverlays(mc.fontRenderer, stack, x, y);
        }
    }
    
	public static Object getObjectFromClass(String varName, Class<?> clazz, Object instance){
		Field f = null;
		try {
			f = clazz.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		f.setAccessible(true);
		
		try {
			return f.get(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
