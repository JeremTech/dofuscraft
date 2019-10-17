package fr.dofuscraft.dofuscraftcore.spells.eniripsa;

import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class FearWordSpell extends GeneralSpell
{

	public static final FearWordSpell INSTANCE = new FearWordSpell();

	public FearWordSpell()
	{
		super("fear_word", 20, 4, new ResourceLocation(References.MODID, "textures/gui/icons/spells/fear_word_icon.png"));
	}

	@Override
	public void launchSpell(EntityPlayer player)
	{
		/* TODO: Le joueur se prend des dégats et il ne devrait pas */

		Entity ent = Utils.rayTraceEntity(4.0D, 1, player);

		if(ent instanceof EntityLivingBase )
		{

			if(ent instanceof EntityPlayer) return;

			EntityLivingBase target = (EntityLivingBase) ent;

			double x = player.posX - target.posX; // ???
			double z = player.posZ - target.posZ;

			float knockbackForce = 1.0F;
			target.knockBack(target, knockbackForce, x, z);
			spawnParticles(target);
		}
	}

	@Override
	public boolean checkIsValid(EntityPlayer player)
	{
		return true;
	}

	private void spawnParticles(EntityLivingBase target)
	{

		for (int i = 0; i < 25; i++)
		{
			target.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX, target.posY + ((i/100.0F)+1.0D), target.posZ, 0, 0, 0);
		}
		target.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1.0F, 1.0F);

	}

}

