package fr.dofuscraft.dofuscraftstuffs.items;

import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.item.ItemSword;

public class DCItemSword extends ItemSword
{
	public DCItemSword(String name, ToolMaterial material)
	{
		super(material);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(DofuscraftStuffs.armors);

		ModItems.INSTANCE.items.add(this);
	}
}
