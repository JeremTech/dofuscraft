package fr.dofuscraft.dofuscraftcore.tabs;

import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DofuscraftItemsTab extends CreativeTabs
{
	public DofuscraftItemsTab(String label)
	{
		super(label);
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.incraftam_relic);
	}
}
