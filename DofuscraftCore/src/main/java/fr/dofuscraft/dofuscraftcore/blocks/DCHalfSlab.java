package fr.dofuscraft.dofuscraftcore.blocks;

import net.minecraft.block.material.Material;

public class DCHalfSlab extends DCSlab
{
	public DCHalfSlab(String name, Material mat)
	{
		super(name, mat);
	}

	@Override
	public boolean isDouble()
	{
		return false;
	}
}