package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class DCRockBlock extends Block
{
	public DCRockBlock(String name)
	{
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(References.MODID, name));
		this.setCreativeTab(Dofuscraftcore.blocks);
		this.setSoundType(SoundType.STONE);
		this.setHardness(5.0f);
		this.setResistance(20.0f);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}
}
