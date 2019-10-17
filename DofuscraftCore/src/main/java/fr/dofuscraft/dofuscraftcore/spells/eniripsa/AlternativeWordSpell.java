package fr.dofuscraft.dofuscraftcore.spells.eniripsa;

import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class AlternativeWordSpell extends GeneralSpell {

	public static final AlternativeWordSpell INSTANCE = new AlternativeWordSpell();

	public AlternativeWordSpell() {
		super("alternative_word", 20, 3, 4, new ResourceLocation(References.MODID, "textures/gui/icons/spells/alternative_word_icon.png"));
	}

	@Override
	public boolean checkIsValid(EntityPlayer player) {
		return true;
	}

	@Override
	public void launchSpell(EntityPlayer player) {

		Entity target = Utils.rayTraceEntity(4.0D, 1, player);


		if (target instanceof EntityLivingBase) {

			if (target instanceof EntityPlayer) {
				/* Dans ce cas, on heal le joueur */

				EntityPlayer targetP = (EntityPlayer) target;

				targetP.heal(damage);

				spawnParticle(player, target, EnumParticleTypes.HEART);

			} else {

				EntityLivingBase ent = (EntityLivingBase) target;

				ent.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0f);

				spawnParticle(player, target, EnumParticleTypes.FLAME);
			}
		}
	}

	public void spawnParticle(EntityPlayer player, Entity target, EnumParticleTypes particles) {

		//player.world.spawnParticle();

		// cosinus -> axe X
		// sinus -> axe Z

		if (target != null) {

			double radius = 0.25D;
			double factor = 0.0095D;
			for (double i = 0.0D; i <= 480.0D; i += 7.0D) {
				double x = (radius * Math.cos(Math.toRadians(i)));
				double y = (radius * Math.sin(Math.toRadians(i)));

				player.world.spawnParticle(particles, target.posX + x, target.posY + 0.5D, target.posZ + y, 0, 0D, 0, 0, 0);
				radius += factor;
			}
		} else {
			System.out.println("The target particles");
		}


	}

}