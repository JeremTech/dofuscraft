package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DCCropBlock extends BlockCrops
{
	public Item blockdrop;
    private int jobXPdropped;
    private AxisAlignedBB[] CROPS_AABB;

	public DCCropBlock(String name, Item drop, int peasantXPdropped, AxisAlignedBB[] box)
	{
		super();
		setRegistryName(name).setUnlocalizedName(name);
		setResistance(3f);
		setHardness(0.3f);
		this.blockdrop = drop;
		this.setCreativeTab(Dofuscraftcore.blocks);
		this.jobXPdropped = peasantXPdropped;
		this.CROPS_AABB = box;

		ModBlocks.INSTANCE.getBlocks().add(this);
	}


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess p_getBoundingBox_2_, BlockPos p_getBoundingBox_3_)
    {

        return CROPS_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];


    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, @Nullable TileEntity tileentity, @Nullable ItemStack stack)
    {
        ISubLevel sublevels = playerIn.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
        ILevel level = playerIn.getCapability(DCLevelProvider.LEVEL_CAP, null);

        if(!worldIn.isRemote)
        {
            if(state.getBlock() == ModBlocks.wheat)
            {
                worldIn.setBlockState(pos, ModBlocks.wheat.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.wheat)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.rye)
            {
                worldIn.setBlockState(pos, ModBlocks.rye.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.rye)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.barley)
            {
                worldIn.setBlockState(pos, ModBlocks.barley.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.barley)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.hop)
            {
                worldIn.setBlockState(pos, ModBlocks.hop.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.hop)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.oats)
            {
                worldIn.setBlockState(pos, ModBlocks.oats.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.oats)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.flax)
            {
                worldIn.setBlockState(pos, ModBlocks.flax.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.flax)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }
            else if(state.getBlock() == ModBlocks.hemp)
            {
                worldIn.setBlockState(pos, ModBlocks.hemp.getDefaultState());
                if(getAge(state) == 3)
                {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.hemp)));
                    this.givePeasantXP(playerIn);
                    playerIn.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.peasant"), sublevels.getPeasantSubLevel(), sublevels.getSubLevelRequire(level.getPeasantLevel())), true);
                }
            }


        }
    }

    public void givePeasantXP(EntityPlayer player)
    {
        ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

        sublevels.setPeasantSubLevel(sublevels.getPeasantSubLevel() + this.jobXPdropped);
        sublevels.checkLevel((EntityPlayerMP) player, "peasant");
        sublevels.sync((EntityPlayerMP)player);
    }

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
        return null;
	}

    @Override
    protected Item getSeed()
    {
        return null;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        if(state.getBlock() == ModBlocks.wheat)
        {
            return new ItemStack(ModBlocks.wheat);
        }
        else if(state.getBlock() == ModBlocks.rye)
        {
            return new ItemStack(ModBlocks.rye);
        }
        else if(state.getBlock() == ModBlocks.barley)
        {
            return new ItemStack(ModBlocks.barley);
        }
        else if(state.getBlock() == ModBlocks.hop)
        {
            return new ItemStack(ModBlocks.hop);
        }
        else if(state.getBlock() == ModBlocks.oats)
        {
            return new ItemStack(ModBlocks.oats);
        }
        else if(state.getBlock() == ModBlocks.flax)
        {
            return new ItemStack(ModBlocks.flax);
        }
        else if(state.getBlock() == ModBlocks.hemp)
        {
            return new ItemStack(ModBlocks.hemp);
        }

        return null;
    }

    @Override
	protected int getAge(IBlockState state)
	{
		return super.getAge(state);
	}

	@Override
	public int getMaxAge()
	{
		return 3;
	}

	@Override
	protected Item getCrop() {
		return null;
	}


}
