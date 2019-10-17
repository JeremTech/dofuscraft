package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DCLog extends BlockLog
{
	public static final PropertyEnum<EnumAxis> LOG_AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	private int jobXPdropped;

	public DCLog(String name, int lumberjackXPdropped)
	{
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(References.MODID, name));
		this.setHardness(2f);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(Dofuscraftcore.blocks);
		this.jobXPdropped = lumberjackXPdropped;

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{LOG_AXIS});
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.harvestBlock(worldIn, player, pos, state, te, stack);

		ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
		ILevel level = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
		sublevels.setLumberjackSubLevel(sublevels.getLumberjackSubLevel() + this.jobXPdropped);
		sublevels.checkLevel((EntityPlayerMP) player, "lumberjack");
		sublevels.sync((EntityPlayerMP)player);

		player.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.lumberjack"), sublevels.getLumberjackSubLevel(), sublevels.getSubLevelRequire(level.getLumberjackLevel())), true);

	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.values()[meta >> 2]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4;
	}
}
