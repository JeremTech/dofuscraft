package fr.dofuscraft.dofuscraftcore.spells.eniripsa;

import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class PreventingWordSpell extends GeneralSpell
{

	public static final PreventingWordSpell INSTANCE = new PreventingWordSpell();

	public PreventingWordSpell()
	{
		super("preventing_word", 20, 3, 4, new ResourceLocation(References.MODID, "textures/gui/icons/spells/preventing_word_icon.png"));
	}

	@Override
	public void launchSpell(EntityPlayer player)
	{

		if(!player.world.isRemote)
		{

			Entity ent = Utils.rayTraceEntity(4.0D, 1, player);

			if(ent instanceof EntityPlayer)
			{

				EntityPlayer target = (EntityPlayer) ent;

				target.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 350, damage-1));

				spawnParticles(target);
			}

		}

	}

	@Override
	public boolean checkIsValid(EntityPlayer player)
	{
		return true;
	}

	private void spawnParticles(EntityLivingBase target)
	{
		target.world.spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY, target.posZ, 0, 0, 0);
		target.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1.0F, 1.0F);
	}
}
