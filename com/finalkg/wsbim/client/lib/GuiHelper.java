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
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c(red, green, blue, alpha);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b((double)left, (double)bottom, 0.0D).func_181675_d();
        bufferbuilder.func_181662_b((double)right, (double)bottom, 0.0D).func_181675_d();
        bufferbuilder.func_181662_b((double)right, (double)top, 0.0D).func_181675_d();
        bufferbuilder.func_181662_b((double)left, (double)top, 0.0D).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void closePlayerInventoriesOnClientSide() {
    	EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
		Object[] slots = player.field_71070_bA.field_75151_b.toArray();
		List<IInventory> inventories = new ArrayList<IInventory>();
		for(int i = 0; i < slots.length; i++){
			Slot slot = (Slot) slots[i];
			if(slot !=null){
				if(!inventories.contains(slot.field_75224_c)){
					inventories.add(slot.field_75224_c);
				}
			}
		}
		for(int k = 0; k < inventories.size(); k++){
			IInventory inv = inventories.get(k);
			if(inv !=null){
				inv.func_174886_c(player);
			}
		}
		player.field_71070_bA.func_75142_b();
		player.field_71070_bA.func_75134_a(player);
		player.func_71053_j();
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
            float f1 = (float)itemStack.func_190921_D() - partialTicks;
            if (f1 > 0.0F){
                GlStateManager.func_179094_E();
                float f2 = 1.0F + f1 / 5.0F;
                GlStateManager.func_179109_b((float)(x + 8), (float)(y + 12), 0.0F);
                GlStateManager.func_179152_a(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GlStateManager.func_179109_b((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            Minecraft.func_71410_x().field_71462_r.field_146296_j.func_175042_a(itemStack, x, y);
            if (f1 > 0.0F){
                GlStateManager.func_179121_F();
            }
        }
	}
	
	/**
     * Renders the specified item of the inventory slot at the specified location. 
     * Works anytime with mc reference.
     */
    public static void renderItemStack(Minecraft mc, EntityPlayerSP player, ItemStack stack, int x, int y, float renderTick){
    	net.minecraft.client.renderer.RenderItem renderItem = mc.func_175599_af();
        if (stack != null && !stack.func_190926_b()){
            float f1 = (float)stack.func_190921_D() - renderTick;
            if (f1 > 0.0F){
                GlStateManager.func_179094_E();
                float f2 = 1.0F + f1 / 5.0F;
                GlStateManager.func_179109_b((float)(x + 8), (float)(y + 12), 0.0F);
                GlStateManager.func_179152_a(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GlStateManager.func_179109_b((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }
            renderItem.func_184391_a(player, stack, x, y);
            if (f1 > 0.0F){
                GlStateManager.func_179121_F();
            }
            renderItem.func_175030_a(mc.field_71466_p, stack, x, y);
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
