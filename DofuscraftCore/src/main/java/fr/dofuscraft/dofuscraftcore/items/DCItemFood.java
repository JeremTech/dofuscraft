package fr.dofuscraft.dofuscraftcore.items;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.item.ItemFood;

public class DCItemFood extends ItemFood
{
	public DCItemFood(String name, int amount, boolean isWolfFood)
	{
		super(amount, isWolfFood);
		setRegistryName(name).setUnlocalizedName(name);
		this.setCreativeTab(Dofuscraftcore.items);
		ModItems.INSTANCE.getItems().add(this);
	}
}
