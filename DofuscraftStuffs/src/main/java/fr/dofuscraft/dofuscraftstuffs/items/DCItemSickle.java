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
import java.util.List;
import java.util.Set;

import static com.mojang.realmsclient.gui.ChatFormatting.*;

public class DCItemSickle extends ItemTool {


	public DCItemSickle(String name, float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
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

					int alchemistLevel = levels.getAlchemistLevel();

					list.appendTag(new NBTTagString(ModBlocks.nettle.getLocalizedName()));

					if(alchemistLevel >= 20 )
					{
						list.appendTag(new NBTTagString(ModBlocks.sage.getLocalizedName()));
					}

					if(alchemistLevel >= 40 )
					{
						list.appendTag(new NBTTagString(ModBlocks.five_leaf_clover.getLocalizedName()));
					}

					if(alchemistLevel >= 60 )
					{

						list.appendTag(new NBTTagString(ModBlocks.wild_mint.getLocalizedName()));
					}

					if(alchemistLevel >= 80 )
					{
						list.appendTag(new NBTTagString(ModBlocks.freyesque_orchid.getLocalizedName()));
					}

					if(alchemistLevel >= 100 )
					{
						list.appendTag(new NBTTagString(ModBlocks.edelweiss.getLocalizedName()));
					}
					/*
					if(alchemistLevel >= 120 )
					{
						list.appendTag(new NBTTagString(ModBlocks.pandkin.getLocalizedName()));
					}

					if(alchemistLevel >= 140 )
					{
					}

					if(alchemistLevel >= 160 )
					{
						list.appendTag(new NBTTagString(ModBlocks.ginseng.getLocalizedName()));
						list.appendTag(new NBTTagString(ModBlocks.belladonna.getLocalizedName()));
					}

					if(alchemistLevel >= 180 )
					{
						list.appendTag(new NBTTagString(ModBlocks.mandrake.getLocalizedName()));
					}

					if(alchemistLevel >= 200 )
					{
						list.appendTag(new NBTTagString(ModBlocks.snowdrop.getLocalizedName()));
						list.appendTag(new NBTTagString(ModBlocks.salikronia.getLocalizedName()));
					}*/
					NBTTagList canDestroy = new NBTTagList();
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:nettle"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:sage"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:five_leaf_clover"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:wild_mint"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:freyesque_orchid"));
					canDestroy.appendTag(new NBTTagString("dofuscraftcore:edelweiss"));

					compound.setTag("CanDestroy", canDestroy);

					compound.setTag("CanDestroy2", list);

				}
			}
		}

    	/*
        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("HideFlags", 8);

            NBTTagList list = new NBTTagList();
            // On ajoute les blocs que peut casser la pioche en mode aventure
            list.appendTag(new NBTTagString("tile.bauxite_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:bronze_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:cobalt_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:copper_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:dolomite_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:gold_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:iron_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:manganese_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:obsidian_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:sepiolite_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:silicate_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:silver_ore"));
            list.appendTag(new NBTTagString("dofuscraftcore:tin_ore"));

            stack.getTagCompound().setTag("CanDestroy2", list);
        }*/
	}



}
