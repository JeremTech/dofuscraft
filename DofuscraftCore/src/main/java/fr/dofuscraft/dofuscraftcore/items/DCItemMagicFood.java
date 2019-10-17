package fr.dofuscraft.dofuscraftcore.items;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DCItemMagicFood extends ItemFood {

    private PotionEffect[] effects;
    private EnumAction action;

    public DCItemMagicFood(String name, int amount, boolean isWolfFood,boolean alwaysEdible, EnumAction action, PotionEffect... potionEffects)
    {
        super(amount, isWolfFood);
        setRegistryName(name).setUnlocalizedName(name);
        this.effects = potionEffects;
        if(alwaysEdible)
            setAlwaysEdible();
        this.setCreativeTab(Dofuscraftcore.items);
        this.action = action;
        ModItems.INSTANCE.getItems().add(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {

            player.setHealth(player.getHealth() + 2.0f);

    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return action;
    }
}
