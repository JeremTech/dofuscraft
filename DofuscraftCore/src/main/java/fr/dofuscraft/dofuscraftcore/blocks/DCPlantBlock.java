package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DCPlantBlock extends Block
{
	private final boolean isDouble;
	private Block lowerVersion1;
	private int jobXPdropped;

	public DCPlantBlock(String name, Material materialIn, boolean isGrowVersion, Block lowerVersion, int alchemistXPdropped)
	{
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(SoundType.PLANT);
		this.setResistance(3f);
		this.setHardness(0.3f);
		this.isDouble = isGrowVersion;
		this.requiresUpdates();
		this.needsRandomTick = true;
		this.jobXPdropped = alchemistXPdropped;

		if( this.isDouble )
		{
			this.setCreativeTab(Dofuscraftcore.blocks);
		}
		if( !this.isDouble )
		{
			this.setBlockUnbreakable();
		}
		lowerVersion1 = lowerVersion;

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		if( !this.isDouble )
		{
			if( state.getBlock() == ModBlocks.small_nettle )
			{
				worldIn.setBlockState(pos, ModBlocks.nettle.getDefaultState(), 3);
			}
			if( state.getBlock() == ModBlocks.small_sage )
			{
				worldIn.setBlockState(pos, ModBlocks.sage.getDefaultState(), 3);
			}
			if( state.getBlock() == ModBlocks.small_five_leaf_clover )
			{
				worldIn.setBlockState(pos, ModBlocks.five_leaf_clover.getDefaultState(), 3);
			}
			if( state.getBlock() == ModBlocks.small_wild_mint )
			{
				worldIn.setBlockState(pos, ModBlocks.wild_mint.getDefaultState(), 3);
			}
			if( state.getBlock() == ModBlocks.small_freyesque_orchid )
			{
				worldIn.setBlockState(pos, ModBlocks.freyesque_orchid.getDefaultState(), 3);
			}
			if( state.getBlock() == ModBlocks.small_edelweiss )
			{
				worldIn.setBlockState(pos, ModBlocks.edelweiss.getDefaultState(), 3);
			}
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if( this.isDouble )
		{
			if( state.getBlock() == ModBlocks.wild_mint || state.getBlock() == ModBlocks.edelweiss )
			{
				return new AxisAlignedBB(0.398, 0, 0.43, 0.6, 0.75f, 0.632);
			}
			else if( state.getBlock() == ModBlocks.sage )
			{
				return new AxisAlignedBB(0.398, 0, 0.43, 0.6, 0.45f, 0.632);
			}
			else if( state.getBlock() == ModBlocks.five_leaf_clover )
			{
				return new AxisAlignedBB(0.398, 0, 0.43, 0.6, 0.3, 0.632);
			}
			else
			{
				return new AxisAlignedBB(0.398, 0, 0.43, 0.6, 1, 0.632);
			}
		}
		else
		{
			return new AxisAlignedBB(0.41, 0, 0.41, 0.6123, 0.3, 0.6123);
		}
	}

	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isTranslucent(IBlockState state)
	{
		return true;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
	{

		ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
		ILevel level = player.getCapability(DCLevelProvider.LEVEL_CAP, null);

		if( !worldIn.isRemote )
		{
			if( this.isDouble )
			{
				worldIn.setBlockState(pos, lowerVersion1.getDefaultState());
				if( state.getBlock() == ModBlocks.five_leaf_clover )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.five_leaf_clover_item)));
				}
				else if( state.getBlock() == ModBlocks.edelweiss )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.edelweiss_item)));
				}
				else if( state.getBlock() == ModBlocks.freyesque_orchid )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.freyesque_orchid_item)));
				}
				else if( state.getBlock() == ModBlocks.nettle )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.nettles_item)));
				}
				else if( state.getBlock() == ModBlocks.sage )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.sage_item)));
				}
				else if( state.getBlock() == ModBlocks.wild_mint )
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.wild_mint_item)));
				}
				else
				{
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this)));
				}

				givePeasantXP(player);
				player.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.alchemist"), sublevels.getAlchemistSubLevel(), sublevels.getSubLevelRequire(level.getAlchemistLevel())), true);
				return;
			}
		}
	}

	public void givePeasantXP(EntityPlayer player)
	{
		ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

		sublevels.setAlchemistSubLevel(sublevels.getAlchemistSubLevel() + this.jobXPdropped);
		sublevels.checkLevel((EntityPlayerMP) player, "alchemist");
		sublevels.sync((EntityPlayerMP)player);
	}

	@Override
	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity)
	{
		return SoundType.PLANT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return null;
	}
}

