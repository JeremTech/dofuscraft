package fr.dofuscraft.dofuscraftcore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Random;

public class DCDoubleSlab extends DCSlab
{
	private final Block loot;

	public DCDoubleSlab(String name, Material mat, Block drop)
	{
		super(name, mat);
		this.loot = drop;
	}

	@Override
	public boolean isDouble()
	{
		return true;
	}

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this.loot);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 2;
	}

}
