package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class DCSlab extends BlockSlab
{
	public DCSlab(String name, Material material)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(References.MODID, name));

		IBlockState state = this.blockState.getBaseState();
		if( !this.isDouble() )
		{
			state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		setDefaultState(state);
		this.useNeighborBrightness = true;
		this.setHardness(5.0f);
		this.setResistance(20.0f);
	}

	@Override
	public String getUnlocalizedName(int meta)
	{
		return this.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty()
	{
		return HALF;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return EnumBlockHalf.BOTTOM;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@SuppressWarnings("deprecated")
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if( !this.isDouble() )
		{
			return this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
		}
		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		if( this.isDouble() )
		{
			return 0;
		}
		return state.getValue(HALF).ordinal() + 1;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{HALF});
	}
}
