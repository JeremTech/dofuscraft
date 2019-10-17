package fr.dofuscraft.dofuscraftstuffs.creativetabs;

import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DCToolsTab extends CreativeTabs
{
    public DCToolsTab()
    {
        super("DofuscraftTools");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.cobalt_pickaxe);
    }
}
