package fr.dofuscraft.dofuscraftstuffs.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.mojang.realmsclient.gui.ChatFormatting.DARK_GREEN;
import static com.mojang.realmsclient.gui.ChatFormatting.ITALIC;

public class DCItemPickaxe extends ItemPickaxe
{
	public DCItemPickaxe(String name, ToolMaterial material)
	{
		super(material);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(DofuscraftStuffs.tools);

		ModItems.INSTANCE.items.add(this);
	}

	@Override
	public boolean isDamageable()
	{
		return false;
	}

	@Override
	public boolean isDamaged(ItemStack stack)
	{
		return false;
	}


	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if( GuiScreen.isShiftKeyDown() )
		{
			// On affiche l'info seulement si l'item a un nbt
			if( stack.hasTagCompound() )
			{

				NBTTagCompound compound = stack.getTagCompound();

				// On cherche la clé qu'il faut dans le nbt
				if( compound.hasKey("CanDestroy2") )
				{

					// Enfin on affiche touts les string présents dans la clé CanDestroy
					tooltip.add(DARK_GREEN + "" + ITALIC + I18n.format("tooltip.candestroy").toString());

					NBTTagList canDestroy = (NBTTagList) compound.getTag("CanDestroy2");

					for ( NBTBase base : canDestroy )
					{
						if( base instanceof NBTTagString )
						{

							NBTTagString s = (NBTTagString) base;

							String block = s.getString();

							tooltip.add(ChatFormatting.GREEN + block);

						}
					}
				}

			}
		}
		else
		{
			tooltip.add(ChatFormatting.YELLOW + I18n.format("tooltip.pushto").toString() + " " + ChatFormatting.BOLD + "" + ChatFormatting.GOLD + "" + ChatFormatting.ITALIC + "SHIFT" + ChatFormatting.RESET + "" + ChatFormatting.YELLOW + " " + I18n.format("tooltip.toshow").toString());
		}

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		stack.setTagCompound(new NBTTagCompound());
		if( stack.hasTagCompound() )
		{
			NBTTagCompound compound = stack.getTagCompound();

			compound.setInteger("HideFlags", 8);

			if( !compound.hasKey("CanDestroy2") )
			{
				compound.setTag("CanDestroy2", new NBTTagList());
			}

			if( compound.hasKey("CanDestroy2")){
				NBTTagList list = compound.getTagList("CanDestroy2", 0);

				if( entityIn instanceof EntityPlayer )
				{
					EntityPlayer player = (EntityPlayer) entityIn;

					ILevel levels = player.getCapability(DCLevelProvider.LEVEL_CAP, null);

					int minerLevel = levels.getMinerLevel();

					list.appendTag(new NBTTagString(ModBlocks.iron_ore.getLocalizedName()));

					if( minerLevel >= 20 )
					{
						list.appendTag(new NBTTagString(ModBlocks.copper_ore.getLocalizedName()));
					}

					if( minerLevel >= 40 )
					{
						list.appendTag(new NBTTagString(ModBlocks.bronze_ore.getLocalizedName()));
					}

					if( minerLevel >= 60 )
					{

						list.appendTag(new NBTTagString(ModBlocks.kobalt_ore.getLocalizedName()));
					}

					if( minerLevel >= 100 )
					{
						list.appendTag(new NBTTagString(ModBlocks.tin_ore.getLocalizedName()));
					}

					NBTTagList canDestroy = new NBTTagList();
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:bronze_ore"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:kobalt_ore"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:copper_ore"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:iron_ore"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:tin_ore"));

					compound.setTag("CanDestroy", canDestroy);

					compound.setTag("CanDestroy2", list);


				}
			}
		}
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		for( Block b : DCToolMaterials.pickaxe_set)
		{
			if(b == blockIn.getBlock())
			{
				return true;
			}
		}
		return false;
	}
}
