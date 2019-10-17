package fr.dofuscraft.dofuscraftcore.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class Spells
{
	protected int cooldown;
	protected int damage;
	protected int level;
	protected String name;
	protected ResourceLocation icons;

	public Spells(String name, int cooldown, int level, ResourceLocation icon)
	{

		this.name = name;
		this.cooldown = cooldown;
		this.damage = 0;
		this.level = level;
		this.icons = icon;

	}

	public Spells(String name, int cooldown, int damage, int level, ResourceLocation icon)
	{
		this.name = name;
		this.cooldown = cooldown;
		this.damage = damage;
		this.level = level;
		this.icons = icon;
	}

	public abstract boolean checkIsValid(EntityPlayer player);

	public abstract void launchSpell(EntityPlayer player);

	public int GetDamage()
	{
		return damage;
	}

	public int GetCooldown()
	{
		return cooldown;
	}

	public int GetLevel()
	{
		return level;
	}

	public String GetName()
	{
		return name;
	}

	public ResourceLocation GetIcon()
	{
		return icons;
	}

}
