package fr.dofuscraft.dofuscraftcore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DCBlockRegenerator extends DCBlock
{

	private Block regenBlock;

	public DCBlockRegenerator(String name, Material materialIn, float hardness, float resistance, String harvestType, int harvestLevel, SoundType soundType, Block toRegen)
	{
		super(name, materialIn, hardness, resistance, harvestType, harvestLevel, soundType);
		setBlockUnbreakable();
		setTickRandomly(true);
		regenBlock = toRegen;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		if( !worldIn.isRemote )
		{
			worldIn.setBlockState(pos, regenBlock.getDefaultState());
		}
	}
}
