package com.finalkg.wsbim.common.block;

import java.util.Random;
import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIceMaker extends BlockContainer
{
    public static final PropertyDirection FACING = BlockHorizontal.field_185512_D;
    private final boolean isRunning;
    private static boolean keepInventory;

    public BlockIceMaker(boolean isRunning)
    {
        super(Material.field_151573_f);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
        this.isRunning = isRunning;
        this.func_149672_a(SoundType.field_185852_e);
        this.func_149711_c(1.5F);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item func_180660_a(IBlockState state, Random rand, int fortune)
    {
        return Item.func_150898_a(WSBIM.blockIceMaker);
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.field_72995_K)
        {
            IBlockState iblockstate = worldIn.func_180495_p(pos.func_177978_c());
            IBlockState iblockstate1 = worldIn.func_180495_p(pos.func_177968_d());
            IBlockState iblockstate2 = worldIn.func_180495_p(pos.func_177976_e());
            IBlockState iblockstate3 = worldIn.func_180495_p(pos.func_177974_f());
            EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.func_185913_b() && !iblockstate1.func_185913_b())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.func_185913_b() && !iblockstate.func_185913_b())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.func_185913_b() && !iblockstate3.func_185913_b())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.func_185913_b() && !iblockstate2.func_185913_b())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.func_180501_a(pos, state.func_177226_a(FACING, enumfacing), 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (this.isRunning)
        {
            EnumFacing enumfacing = (EnumFacing)stateIn.func_177229_b(FACING);
            double d0 = (double)pos.func_177958_n();
            double d1 = (double)pos.func_177956_o();
            double d2 = (double)pos.func_177952_p();
           switch (enumfacing)
            {
                case WEST:
                   worldIn.func_175688_a(EnumParticleTypes.SNOWBALL, d0+(rand.nextDouble() * 0.5D), d1+1.02D, d2+0.3D+ (rand.nextDouble() * 0.3D), 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                	worldIn.func_175688_a(EnumParticleTypes.SNOWBALL, d0+0.5D+(rand.nextDouble() * 0.5D), d1+1.02D, d2+0.4D+ (rand.nextDouble() * 0.3D), 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                	worldIn.func_175688_a(EnumParticleTypes.SNOWBALL, d0+0.3D+ (rand.nextDouble() * 0.3D), d1+1.02D, d2+(rand.nextDouble() * 0.5D), 0.0D, 0.0D, 0.0D);
                	break;
                case SOUTH:
                	worldIn.func_175688_a(EnumParticleTypes.SNOWBALL, d0+0.4D+ (rand.nextDouble() * 0.3D), d1+1.02D, d2+0.5D+(rand.nextDouble() * 0.5D), 0.0D, 0.0D, 0.0D);
                    break;
			default:
				break;
            }
        }
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.field_72995_K)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.func_175625_s(pos);

            if (tileentity instanceof TileEntityIceMaker)
            {
            	FMLNetworkHandler.openGui(playerIn,WSBIM.instance,-1, worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            }

            return true;
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        TileEntity tileentity = worldIn.func_175625_s(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.func_180501_a(pos, WSBIM.blockIceMakerActive.func_176223_P().func_177226_a(FACING, iblockstate.func_177229_b(FACING)), 3);
            worldIn.func_180501_a(pos, WSBIM.blockIceMakerActive.func_176223_P().func_177226_a(FACING, iblockstate.func_177229_b(FACING)), 3);
        }
        else
        {
            worldIn.func_180501_a(pos, WSBIM.blockIceMaker.func_176223_P().func_177226_a(FACING, iblockstate.func_177229_b(FACING)), 3);
            worldIn.func_180501_a(pos, WSBIM.blockIceMaker.func_176223_P().func_177226_a(FACING, iblockstate.func_177229_b(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.func_145829_t();
            worldIn.func_175690_a(pos, tileentity);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity func_149915_a(World worldIn, int meta)
    {
        return new TileEntityIceMaker();
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.func_176223_P().func_177226_a(FACING, placer.func_174811_aO().func_176734_d());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.func_180501_a(pos, state.func_177226_a(FACING, placer.func_174811_aO().func_176734_d()), 2);
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntity tileentity = worldIn.func_175625_s(pos);

            if (tileentity instanceof TileEntityIceMaker)
            {
                InventoryHelper.func_180175_a(worldIn, pos, (TileEntityIceMaker)tileentity);
                worldIn.func_175666_e(pos, this);
            }
        }

        super.func_180663_b(worldIn, pos, state);
    }

    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(WSBIM.blockIceMaker);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType func_149645_b(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState func_176203_a(int meta)
    {
        EnumFacing enumfacing = EnumFacing.func_82600_a(meta);

        if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.func_176223_P().func_177226_a(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int func_176201_c(IBlockState state)
    {
        return ((EnumFacing)state.func_177229_b(FACING)).func_176745_a();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState func_185499_a(IBlockState state, Rotation rot)
    {
        return state.func_177226_a(FACING, rot.func_185831_a((EnumFacing)state.func_177229_b(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn)
    {
        return state.func_185907_a(mirrorIn.func_185800_a((EnumFacing)state.func_177229_b(FACING)));
    }

    protected BlockStateContainer func_180661_e()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}
