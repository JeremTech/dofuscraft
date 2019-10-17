package fr.dofuscraft.dofuscraftcore.items;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DCItem extends Item
{
	public DCItem(String name)
	{
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(Dofuscraftcore.items);

		ModItems.INSTANCE.getItems().add(this);
	}




}
