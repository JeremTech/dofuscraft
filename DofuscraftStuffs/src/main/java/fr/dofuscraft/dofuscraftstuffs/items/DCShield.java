package fr.dofuscraft.dofuscraftstuffs.items;

import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DCShield extends ItemShield
{
    public DCShield(String name)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(DofuscraftStuffs.armors);

        ModItems.INSTANCE.items.add(this);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return DofuscraftStuffs.armors;
    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.format(this.getUnlocalizedName() + ".name");
    }

}