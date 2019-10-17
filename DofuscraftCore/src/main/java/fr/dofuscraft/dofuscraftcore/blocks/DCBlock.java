package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DCBlock extends Block
{
	public DCBlock(String name, Material materialIn, float hardness, float resistance, String harvestType, int harvestLevel, SoundType soundType)
	{
		super(materialIn);
		setRegistryName(name).setUnlocalizedName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(harvestType, harvestLevel);
		setSoundType(soundType);
		setCreativeTab(Dofuscraftcore.blocks);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}
}
