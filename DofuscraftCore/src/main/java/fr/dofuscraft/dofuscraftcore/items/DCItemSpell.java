package fr.dofuscraft.dofuscraftcore.items;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.events.OverlayEvent;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import fr.dofuscraft.dofuscraftcore.spells.Spells;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class DCItemSpell extends Item
{

	public Spells[] spellList;

	Profiler profiler = new Profiler();

	public DCItemSpell(String name, Spells... spells)
	{
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(Dofuscraftcore.items);
		setMaxStackSize(1);

		this.spellList = spells;

		ModItems.INSTANCE.getItems().add(this);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{

		EntityPlayer player = (EntityPlayer) entityIn;



		if( !stack.hasTagCompound() )
		{
			stack.setTagCompound(new NBTTagCompound());
		}

		NBTTagCompound compound = stack.getTagCompound();


		if (compound != null && !compound.hasKey("currentSpell"))
		{
			compound.setInteger("currentSpell", 0);
		}

		if (compound != null && !compound.hasKey("cooldownList"))
		{

			int[] coolList = new int[spellList.length];
			for (int i = 0; i < 4; i++)
			{

				coolList[i] = 0;

			}

			compound.setIntArray("cooldownList", coolList);
		}


		//TODO Empecher de bouger l'item en dehors de la hotbar


		int currentSpell = compound.getInteger("currentSpell");

		if(isSelected )
		{

			OverlayEvent.spellText = spellList[currentSpell].GetName();
			OverlayEvent.spellIcon = spellList[currentSpell].GetIcon();

		}




	}


	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {


		NBTTagCompound compound = null;

		EntityPlayer p = (EntityPlayer) entityLiving;

		if( p.hasItemInSlot(EntityEquipmentSlot.MAINHAND) && p.getHeldItem(EnumHand.MAIN_HAND).hasTagCompound() )
		{
			compound = p.getHeldItem(EnumHand.MAIN_HAND).getTagCompound();
		}


		// Si on est à la fin de la liste des sorts, on revient au début
		if( compound.getInteger("currentSpell") + 1 < spellList.length && p.experienceLevel >= spellList[compound.getInteger("currentSpell") + 1].GetLevel() )
		{
			compound.setInteger("currentSpell", compound.getInteger("currentSpell") + 1);
			//currentSpell++;
		}
		// Autrement on passe au sort suivant
		else //if(currentSpell + 1 > spellList.length || (currentSpell + 1 < spellList.length && p.experienceLevel < spellList[currentSpell+1].GetLevel()))
		{
			compound.setInteger("currentSpell", 0);

		}




		return super.onEntitySwing(entityLiving, stack);


	}



	//region Clic Droit

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{

		//TODO Régler les cooldowns
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		NBTTagCompound compound = null;

		if( playerIn.hasItemInSlot(EntityEquipmentSlot.MAINHAND) && playerIn.getHeldItem(EnumHand.MAIN_HAND).hasTagCompound() && itemstack.getItem() == this)
		{
			compound = playerIn.getHeldItem(EnumHand.MAIN_HAND).getTagCompound();
		}

		//compound.getIntArray("cooldownList");
		if (compound.getIntArray("cooldownList")[compound.getInteger("currentSpell")] + spellList[compound.getInteger("currentSpell")].GetCooldown() <= (System.currentTimeMillis()/1000))
		{

			System.out.println("Cooldown éxpiré");


			if (spellList[compound.getInteger("currentSpell")].checkIsValid(playerIn))
			{

				spellList[compound.getInteger("currentSpell")].launchSpell(playerIn);
				int[] cList = compound.getIntArray("cooldownList");
				cList[compound.getInteger("currentSpell")] = (int) (System.currentTimeMillis() / 1000);
				compound.setIntArray("cooldownList", cList);


			}

		}





		//playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}

	//endregion





	//TODO Faire le système de changement de sort dans le sens contraire avec un shift click
	//TODO Faire un système de coups critiques



}
