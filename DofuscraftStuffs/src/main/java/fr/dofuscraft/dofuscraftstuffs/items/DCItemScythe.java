package fr.dofuscraft.dofuscraftstuffs.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.GuiScreen;

import javax.annotation.Nullable;
import java.util.Set;

import static com.mojang.realmsclient.gui.ChatFormatting.*;

import java.util.List;

public class DCItemScythe extends ItemTool
{
	public DCItemScythe(String name, float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
	{
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(DofuscraftStuffs.tools);

		ModItems.INSTANCE.items.add(this);
	}


	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if( GuiScreen.isShiftKeyDown() )
		{

			if( stack.hasTagCompound() )
			{

				NBTTagCompound compound = stack.getTagCompound();

				if( compound.hasKey("CanDestroy2") )
				{

					tooltip.add(DARK_GREEN + "" + ITALIC + I18n.format("tooltip.candestroy").toString());

					NBTTagList canDestroy = (NBTTagList) compound.getTag("CanDestroy2");

					for ( NBTBase base : canDestroy )
					{
						if( base instanceof NBTTagString )
						{

							NBTTagString s = (NBTTagString) base;

							String block = s.getString();

							tooltip.add(GREEN + block);

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
	public void onUpdate(ItemStack stack, @Nullable World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
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

			if( compound.hasKey("CanDestroy2") )
			{
				NBTTagList list = compound.getTagList("CanDestroy2", 0);

				if( entityIn instanceof EntityPlayer )
				{
					EntityPlayer player = (EntityPlayer) entityIn;

					ILevel levels = player.getCapability(DCLevelProvider.LEVEL_CAP, null);

					int peasantLevel = levels.getPeasantLevel();

					list.appendTag(new NBTTagString(ModBlocks.wheat.getLocalizedName()));

					if(peasantLevel >= 20 )
					{
						list.appendTag(new NBTTagString(ModBlocks.barley.getLocalizedName()));
					}

					if(peasantLevel >= 40 )
					{
						list.appendTag(new NBTTagString(ModBlocks.oats.getLocalizedName()));
					}

					if(peasantLevel >= 60 )
					{

						list.appendTag(new NBTTagString(ModBlocks.hop.getLocalizedName()));
					}

					if(peasantLevel >= 80 )
					{
						list.appendTag(new NBTTagString(ModBlocks.flax.getLocalizedName()));
					}

					if(peasantLevel >= 100 )
					{
						list.appendTag(new NBTTagString(ModBlocks.rye.getLocalizedName()));
						//list.appendTag(new NBTTagString(ModBlocks.rice.getLocalizedName()));
					}

					/*if(peasantLevel >= 120 )
					{
						list.appendTag(new NBTTagString(ModBlocks.mal.getLocalizedName()));
					}*/

					if(peasantLevel >= 140 )
					{
						list.appendTag(new NBTTagString(ModBlocks.hemp.getLocalizedName()));
					}



					/*if(peasantLevel >= 160 )
					{
						list.appendTag(new NBTTagString(ModBlocks.ginseng.getLocalizedName()));
						list.appendTag(new NBTTagString(ModBlocks.belladonna.getLocalizedName()));
					}

					if(peasantLevel >= 180 )
					{
						list.appendTag(new NBTTagString(ModBlocks.mandrake.getLocalizedName()));
					}

					if(peasantLevel >= 200 )
					{
						list.appendTag(new NBTTagString(ModBlocks.snowdrop.getLocalizedName()));
						list.appendTag(new NBTTagString(ModBlocks.salikronia.getLocalizedName()));
					}*/

					NBTTagList canDestroy = new NBTTagList();
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_wheat"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_barley"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_oats"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_hop"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_flax"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_rye"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:dofuscraft_hemp"));

					compound.setTag("CanDestroy", canDestroy);
					compound.setTag("CanDestroy2", list);

				}
			}
		}
	}

}
