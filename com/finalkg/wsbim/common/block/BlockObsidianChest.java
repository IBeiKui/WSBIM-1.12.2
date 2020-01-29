package com.finalkg.wsbim.common.block;

import java.util.Random;
import com.finalkg.wsbim.WSBIM;
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


import com.finalkg.wsbim.WSBIM;
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

public class BlockObsidianChest extends BlockContainer {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	
	public BlockObsidianChest() {
		super(Material.field_151576_e, MapColor.field_151678_z);
		this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(BlockHorizontal.field_185512_D, EnumFacing.NORTH));
		this.func_149711_c(30.0F);
		this.func_149752_b(10000F);
		this.func_149672_a(SoundType.field_185851_d);
	}

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos){
    	return BOUNDING_BOX;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean func_149662_c(IBlockState state){
        return false;
    }

    public boolean func_149686_d(IBlockState state){
        return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean func_190946_v(IBlockState state){
        return true;
    }
    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType func_149645_b(IBlockState state){
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.func_176223_P().func_177226_a(BlockHorizontal.field_185512_D, placer.func_174811_aO().func_176734_d());
    }
    
    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.func_180501_a(pos, state.func_177226_a(BlockHorizontal.field_185512_D, placer.func_174811_aO().func_176734_d()), 2);
    }
    
    public float func_180647_a(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos){
    	int x = pos.func_177958_n();
    	int y = pos.func_177956_o();
    	int z = pos.func_177952_p();
    	TileEntity te = worldIn.func_175625_s(pos);
    	if(te instanceof TileEntityObsidianChest){
    		TileEntityObsidianChest ob = (TileEntityObsidianChest)te;
    		if(ob.owner !=null && !ob.owner.equals(player.getDisplayNameString())){
    			return 0.0F;
    		}
    		else{
    			return super.func_180647_a(state, player, worldIn, pos);
    		}
    	}
    	else{
    		return super.func_180647_a(state, player, worldIn, pos);
    	}
    }
    
    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state){
    	TileEntity tileentity = worldIn.func_175625_s(pos);

        if (tileentity instanceof TileEntityObsidianChest){
        	InventoryHelper.func_180175_a(worldIn, pos, (TileEntityObsidianChest)tileentity);
        	worldIn.func_175666_e(pos, this);
        }
        
        super.func_180663_b(worldIn, pos, state);
    }
    
    /**
     * Called when the block is right clicked by a player.
     */
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        InventoryEnderChest inventoryenderchest = playerIn.func_71005_bN();
        TileEntity te = worldIn.func_175625_s(pos);

        if (te == null || !(te instanceof TileEntityObsidianChest))
        {
            return true;
        }

        if (worldIn.func_180495_p(pos.func_177984_a()).doesSideBlockChestOpening(worldIn, pos.func_177984_a(), EnumFacing.DOWN)){
            return true;
        }

        if (worldIn.field_72995_K)
        {
            return true;
        }
        
		String owner = ((TileEntityObsidianChest)te).owner;
		//TODO !
		
		if(owner !=null && !owner.equals(playerIn.getDisplayNameString())){
			TextComponentString message1 = new TextComponentString("Sorry, "+playerIn.getDisplayNameString()+" you do "+"not own "+"this "+"chest, "+"it is owned by"+" "+owner);
			message1.func_150256_b().func_150238_a(TextFormatting.RED);
			playerIn.func_145747_a(message1);
				if(worldIn.func_72924_a(owner) !=null){
					TextComponentString message2 = new TextComponentString(playerIn.getDisplayNameString()+" tried "+"to get"+" into your "+"obsidian "+"che"+"st! "+"@ "+"x="+""+pos.func_177958_n()+" y="+""+pos.func_177956_o()+" z="+""+pos.func_177952_p());
					message2.func_150256_b().func_150238_a(TextFormatting.DARK_RED);
					worldIn.func_72924_a(owner).func_145747_a(message2);
				}
				
			return true;
		}
		else if(owner == null){
			TextComponentString message3 = new TextComponentString("This obsidian "+"chest "+"is now yours,"+" "+"you "+"will "+"be "+"notified "+"if"+" "+"someone "+"tries "+"to steal "+"things from "+"this chest. "+"No "+"one "+"else "+"besides"+" you "+"can "+"go "+"into "+"this "+"chest. ");
			message3.func_150256_b().func_150238_a(TextFormatting.LIGHT_PURPLE);
			playerIn.func_145747_a(message3);
			((TileEntityObsidianChest)te).owner = playerIn.getDisplayNameString();
		}
		  
	        
	        
	        FMLNetworkHandler.openGui(playerIn, WSBIM.instance,-1, worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
	        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        for (int i = 0; i < 3; ++i)
        {
            int j = rand.nextInt(2) * 2 - 1;
            int k = rand.nextInt(2) * 2 - 1;
            double d0 = (double)pos.func_177958_n() + 0.5D + 0.25D * (double)j;
            double d1 = (double)((float)pos.func_177956_o() + rand.nextFloat());
            double d2 = (double)pos.func_177952_p() + 0.5D + 0.25D * (double)k;
            double d3 = (double)(rand.nextFloat() * (float)j);
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            double d5 = (double)(rand.nextFloat() * (float)k);
            if(rand.nextInt(10) == 0)worldIn.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3 * 0.25F, d4, d5 * 0.25F);
        }
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState func_176203_a(int meta) {
        EnumFacing enumfacing = EnumFacing.func_82600_a(meta);

        if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.func_176223_P().func_177226_a(BlockHorizontal.field_185512_D, enumfacing);
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int func_176201_c(IBlockState state){
        return ((EnumFacing)state.func_177229_b(BlockHorizontal.field_185512_D)).func_176745_a();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState func_185499_a(IBlockState state, Rotation rot)
    {
        return state.func_177226_a(BlockHorizontal.field_185512_D, rot.func_185831_a((EnumFacing)state.func_177229_b(BlockHorizontal.field_185512_D)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn){
        return state.func_185907_a(mirrorIn.func_185800_a((EnumFacing)state.func_177229_b(BlockHorizontal.field_185512_D)));
    }

    protected BlockStateContainer func_180661_e(){
        return new BlockStateContainer(this, new IProperty[] {BlockHorizontal.field_185512_D});
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
    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
	@Override
	public TileEntity func_149915_a(World worldIn, int meta) {
		return new TileEntityObsidianChest();
	}

}
