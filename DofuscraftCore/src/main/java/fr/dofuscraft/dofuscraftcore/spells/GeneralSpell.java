package fr.dofuscraft.dofuscraftcore.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GeneralSpell extends Spells
{

	public GeneralSpell(String name, int cooldown, int level, ResourceLocation icon)
	{
		super(name, cooldown, level, icon);
	}

	public GeneralSpell(String name, int cooldown, int damage, int level, ResourceLocation icon)
	{
		super(name, cooldown, damage, level, icon);
	}

	@Override
	public void launchSpell(EntityPlayer player)
	{

	}

	@Override
	public boolean checkIsValid(EntityPlayer player) {
		return false;
	}
}
