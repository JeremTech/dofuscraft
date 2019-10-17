package fr.dofuscraft.dofuscraftcore.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCJobBook;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class DCItemBook extends Item
{

	private String jobJSON;

	public DCItemBook(String name, String jobJSON)
	{
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(Dofuscraftcore.items);
		setMaxStackSize(1);
		this.jobJSON = jobJSON;
		ModItems.INSTANCE.getItems().add(this);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if( entityIn instanceof EntityPlayer )
		{
			EntityPlayer player = (EntityPlayer) entityIn;

			ILevel level = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
			ISubLevel subLevel = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

			NBTTagCompound compound = new NBTTagCompound();
			if( stack.getItem() == ModItems.alchemist_book )
			{
				compound.setInteger("alchemistLevel", level.getAlchemistLevel());
				compound.setInteger("alchemistSubLevel", subLevel.getAlchemistSubLevel());
			}
			if( stack.getItem() == ModItems.lumberjack_book )
			{
				compound.setInteger("lumberjackLevel", level.getLumberjackLevel());
				compound.setInteger("lumberjackSubLevel", subLevel.getLumberjackSubLevel());
			}

			if( stack.getItem() == ModItems.miner_book )
			{
				compound.setInteger("minerLevel", level.getMinerLevel());
				compound.setInteger("minerSubLevel", subLevel.getMinerSubLevel());
			}

			if( stack.getItem() == ModItems.peasant_book )
			{
				compound.setInteger("peasantLevel", level.getPeasantLevel());
				compound.setInteger("peasantSubLevel", subLevel.getPeasantSubLevel());
			}

			if( stack.getItem() == ModItems.jeweler_book )
			{
				compound.setInteger("jewelerLevel", level.getJewelerLevel());
				compound.setInteger("jewelerSubLevel", subLevel.getJewelerSubLevel());
			}

			if( stack.getItem() == ModItems.tailor_book )
			{
				compound.setInteger("tailorLevel", level.getTailorLevel());
				compound.setInteger("tailorSubLevel", subLevel.getTailorSubLevel());
			}

			if( stack.getItem() == ModItems.shoemaker_book )
			{
				compound.setInteger("shoemakerLevel", level.getShoeMakerLevel());
				compound.setInteger("shoemakerSubLevel", subLevel.getShoeMakerSubLevel());
			}

			stack.setTagCompound(compound);

		}
	}

	//TODO Fixer le probleme des manuscrits qui n'affichent pas le lore

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(stack.hasTagCompound())
		{
			if( stack.getItem() == ModItems.alchemist_book )
			{
				if( stack.getTagCompound().hasKey("alchemistLevel") && stack.getTagCompound().getInteger("alchemistLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("alchemistLevel");
					int sublevel = stack.getTagCompound().getInteger("alchemistSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.lumberjack_book )
			{
				if( stack.getTagCompound().hasKey("lumberjackLevel") && stack.getTagCompound().getInteger("lumberjackLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("lumberjackLevel");
					int sublevel = stack.getTagCompound().getInteger("lumberjackSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.miner_book )
			{
				if( stack.getTagCompound().hasKey("minerLevel") && stack.getTagCompound().getInteger("minerLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("minerLevel");
					int sublevel = stack.getTagCompound().getInteger("minerSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.peasant_book )
			{
				if( stack.getTagCompound().hasKey("peasantLevel") && stack.getTagCompound().getInteger("peasantLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("peasantLevel");
					int sublevel = stack.getTagCompound().getInteger("peasantSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.jeweler_book )
			{
				if( stack.getTagCompound().hasKey("jewelerLevel") && stack.getTagCompound().getInteger("jewelerLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("jewelerLevel");
					int sublevel = stack.getTagCompound().getInteger("jewelerSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.tailor_book )
			{
				if( stack.getTagCompound().hasKey("tailorLevel") && stack.getTagCompound().getInteger("tailorLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("tailorLevel");
					int sublevel = stack.getTagCompound().getInteger("tailorSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}
			else if( stack.getItem() == ModItems.shoemaker_book )
			{
				if( stack.getTagCompound().hasKey("shoemakerLevel") && stack.getTagCompound().getInteger("shoemakerLevel") > 0 )
				{
					int level = stack.getTagCompound().getInteger("shoemakerLevel");
					int sublevel = stack.getTagCompound().getInteger("shoemakerSubLevel");
					int max_sublevel =  Math.round((level + 1) * level * 10);
					tooltip.add(ChatFormatting.PREFIX_CODE + "6" + I18n.format("tooltip.book.level") + " " + level + ChatFormatting.PREFIX_CODE + "f" + " - " + ChatFormatting.PREFIX_CODE + "e" + sublevel + "/" + max_sublevel + " XP");
				}
			}

		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if(jobJSON != "null"){

			Minecraft.getMinecraft().displayGuiScreen(new GuiDCJobBook(jobJSON));

		}else{

			playerIn.sendMessage(new TextComponentString("This book is not written yet"));

		}

		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}
}