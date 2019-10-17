package fr.dofuscraft.dofuscraftstuffs.items;

import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.init.ModArmors;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import fr.dofuscraft.dofuscraftstuffs.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class DCItemArmor extends ItemArmor
{
    private final String armorName;

	public DCItemArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String armorName)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);

		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(DofuscraftStuffs.armors);

        this.armorName = armorName;
		ModArmors.INSTANCE.armors.add(this);
	}

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {

        if( slot == EntityEquipmentSlot.LEGS )
        {
            return References.MODID + ":textures/models/armor/" + this.armorName + "_layer_2.png";
        }
        return References.MODID + ":textures/models/armor/" + this.armorName + "_layer_1.png";
    }
}
