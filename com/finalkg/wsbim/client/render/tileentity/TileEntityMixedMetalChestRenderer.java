package com.finalkg.wsbim.client.render.tileentity;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityMixedMetalChestRenderer extends TileEntitySpecialRenderer<TileEntityMixedMetalChest>
{
    private static final ResourceLocation MIXED_METAL_CHEST_TEXTURE = new ResourceLocation(WSBIM.MODID, "textures/models/chest/mixed_metal_chest.png");
    private final ModelChest modelChest = new ModelChest();

    public void func_192841_a(TileEntityMixedMetalChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	//int i is set to 2 for default inventory rendering
        int i = 2;
        if (te.func_145830_o())
        {
            i = te.func_145832_p();
        }

        if (destroyStage >= 0)
        {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(field_178460_a[destroyStage]);
            GlStateManager.func_179128_n(5890);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
            GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.func_179128_n(5888);
        }
        else
        {
        	Minecraft.func_71410_x().field_71446_o.func_110577_a(MIXED_METAL_CHEST_TEXTURE);
        }

        GlStateManager.func_179094_E();
        GlStateManager.func_179091_B();
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, alpha);
        GlStateManager.func_179109_b((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GlStateManager.func_179152_a(1.0F, -1.0F, -1.0F);
        GlStateManager.func_179109_b(0.5F, 0.5F, 0.5F);
        int j = 0;

        if (i == 2)
        {
            j = 180;
        }

        if (i == 3)
        {
            j = 0;
        }

        if (i == 4)
        {
            j = 90;
        }

        if (i == 5)
        {
            j = -90;
        }

        GlStateManager.func_179114_b((float)j, 0.0F, 1.0F, 0.0F);
        GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
        float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        this.modelChest.field_78234_a.field_78795_f = -(f * ((float)Math.PI / 2F));
        this.modelChest.func_78231_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179121_F();
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0)
        {
            GlStateManager.func_179128_n(5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
        }
    }
}
