package fr.dofuscraft.dofuscraftcore.blocks;

import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DCOre extends DCBlock
{
	private Item toDrop;
	private int jobXPdropped;

	public DCOre(String name, Material materialIn, float hardness, float resistance, String harvestType, int harvestLevel, SoundType soundType, Item toDrop, int minerXPdropped)
	{
		super(name, materialIn, hardness, resistance, harvestType, harvestLevel, soundType);
		this.toDrop = toDrop;
		this.jobXPdropped = minerXPdropped;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		if( state.getBlock() == ModBlocks.iron_ore )
		{
			worldIn.setBlockState(pos, ModBlocks.iron_regen.getDefaultState(), 3);
			Entity drop = new EntityItem(worldIn);
			drop.setPosition(pos.getX(), pos.getY(), pos.getZ());
			((EntityItem) drop).setItem(new ItemStack(ModItems.iron));
			worldIn.spawnEntity(drop);
		}
		else if( state.getBlock() == ModBlocks.copper_ore )
		{
			worldIn.setBlockState(pos, ModBlocks.copper_regen.getDefaultState(), 3);
			Entity drop = new EntityItem(worldIn);
			drop.setPosition(pos.getX(), pos.getY(), pos.getZ());
			((EntityItem) drop).setItem(new ItemStack(ModItems.copper));
			worldIn.spawnEntity(drop);
		}
		else if( state.getBlock() == ModBlocks.kobalt_ore )
		{
			worldIn.setBlockState(pos, ModBlocks.kobalt_regen.getDefaultState(), 3);
			Entity drop = new EntityItem(worldIn);
			drop.setPosition(pos.getX(), pos.getY(), pos.getZ());
			((EntityItem) drop).setItem(new ItemStack(ModItems.cobalt));
			worldIn.spawnEntity(drop);
		}
		else if( state.getBlock() == ModBlocks.bronze_ore )
		{
			worldIn.setBlockState(pos, ModBlocks.bronze_regen.getDefaultState(), 3);
			Entity drop = new EntityItem(worldIn);
			drop.setPosition(pos.getX(), pos.getY(), pos.getZ());
			((EntityItem) drop).setItem(new ItemStack(ModItems.bronze));
			worldIn.spawnEntity(drop);
		}
		else if( state.getBlock() == ModBlocks.tin_ore )
		{
			worldIn.setBlockState(pos, ModBlocks.tin_regen.getDefaultState(), 3);
			Entity drop = new EntityItem(worldIn);
			drop.setPosition(pos.getX(), pos.getY(), pos.getZ());
			((EntityItem) drop).setItem(new ItemStack(ModItems.tin));
			worldIn.spawnEntity(drop);
		}

		ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
		ILevel level = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
		sublevels.setMinerSubLevel(sublevels.getMinerSubLevel() + this.jobXPdropped);
		sublevels.checkLevel((EntityPlayerMP) player, "miner");
		sublevels.sync((EntityPlayerMP)player);
		player.sendStatusMessage(new TextComponentTranslation("info.bar.xp.job", jobXPdropped, I18n.format("job.miner"), sublevels.getMinerSubLevel(), sublevels.getSubLevelRequire(level.getMinerLevel())), true);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return toDrop;
	}
}
