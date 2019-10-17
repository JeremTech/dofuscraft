package fr.dofuscraft.dofuscraftstuffs.creativetabs;

import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DCArmorsTab extends CreativeTabs
{
    public DCArmorsTab()
    {
        super("DofuscraftArmors");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.beginner_shield);
    }
}
