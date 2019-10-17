package fr.dofuscraft.dofuscraftcore.spells.feca;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;

import java.util.List;

public class StaminaSpell extends GeneralSpell
{

    public static final StaminaSpell INSTANCE = new StaminaSpell();

    public StaminaSpell()
    {

        super("stamina", 10, 4, 1, new ResourceLocation(References.MODID, "textures/gui/icons/spells/endurance_icon.png"));

    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {
        if(!player.world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 600, 2));
        }

        player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);

    }

}

