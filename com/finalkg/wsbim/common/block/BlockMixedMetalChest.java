package com.finalkg.wsbim.common.block;

import java.util.Random;

import com.finalkg.wsbim.WSBIM;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMixedMetalChest extends BlockContainer {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	
	public BlockMixedMetalChest() {
		super(Material.IRON, MapColor.PINK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		this.setHardness(5F);
		this.setResistance(2000F);
		this.setSoundType(SoundType.METAL);
	}

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
    	return BOUNDING_BOX;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    public boolean isFullCube(IBlockState state){
        return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state){
        return true;
    }
    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    
    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
    	TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityMixedMetalChest){
        	InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityMixedMetalChest)tileentity);
        	worldIn.updateComparatorOutputLevel(pos, this);
        }
        
        super.breakBlock(worldIn, pos, state);
    }
    
    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        TileEntity te = worldIn.getTileEntity(pos);

        if (te == null || !(te instanceof TileEntityMixedMetalChest))
        {
            return true;
        }

        if (worldIn.getBlockState(pos.up()).doesSideBlockChestOpening(worldIn, pos.up(), EnumFacing.DOWN)){
            return true;
        }

        if (worldIn.isRemote)
        {
            return true;
        }
		FMLNetworkHandler.openGui(playerIn, WSBIM.instance,-1, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    return true;
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(BlockHorizontal.FACING, enumfacing);
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state){
        return ((EnumFacing)state.getValue(BlockHorizontal.FACING)).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(BlockHorizontal.FACING, rot.rotate((EnumFacing)state.getValue(BlockHorizontal.FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(BlockHorizontal.FACING)));
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {BlockHorizontal.FACING});
    }
    
    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMixedMetalChest();
	}

}
