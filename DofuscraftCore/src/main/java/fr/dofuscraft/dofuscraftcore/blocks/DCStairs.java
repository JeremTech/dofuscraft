package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class DCStairs extends BlockStairs
{
	public DCStairs(String name, IBlockState modelState)
	{
		super(modelState);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Dofuscraftcore.blocks);
		this.setHardness(5.0f);
		this.setResistance(10.0f);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}
}