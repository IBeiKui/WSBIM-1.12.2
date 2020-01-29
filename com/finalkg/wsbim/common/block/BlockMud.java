package com.finalkg.wsbim.common.block;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMud extends Block {

	protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.95D, 1.0D);
	
	public BlockMud() {
		super(Material.field_151578_c, MapColor.field_151664_l);
		this.func_149672_a(SoundType.field_185849_b);
	}
    
    /**
     * Called When an Entity Collided with the Block
     */
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.field_70159_w *= 0.25D;
        entityIn.field_70179_y *= 0.25D;
    }
    
    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return MUD_AABB;
    }


}
