package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DCLogRegenerator extends DCBlock
{

	private Block toRegen;

	public DCLogRegenerator(String name, Material materialIn, float hardness, float resistance, String harvestType, int harvestLevel, SoundType soundType, Block toRegen)
	{
		super(name, materialIn, hardness, resistance, harvestType, harvestLevel, soundType);
		this.setTickRandomly(true);
		this.setBlockUnbreakable();
		this.toRegen = toRegen;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		int z = 1;
		boolean isFinish = false;

		while (!isFinish && z < 7)
		{
			BlockPos posreplace = pos.up(z);

			if(worldIn.getBlockState(posreplace).getBlock() == toRegen || worldIn.getBlockState(posreplace).getBlock() == Blocks.AIR)
			{
				worldIn.setBlockState(posreplace, toRegen.getDefaultState().withProperty(DCLog.LOG_AXIS, BlockLog.EnumAxis.Y), 3);
			}
			else
			{
				isFinish = true;
			}

			z++;
		}
	}
}
