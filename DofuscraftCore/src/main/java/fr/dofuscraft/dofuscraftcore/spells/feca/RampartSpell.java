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

import java.util.List;

public class RampartSpell extends GeneralSpell
{

    public static final RampartSpell INSTANCE = new RampartSpell();

    public RampartSpell(){

        super("rampart", 10, 4, 1, new ResourceLocation(References.MODID, "textures/gui/icons/spells/rampart_icon.png"));

    }


    @Override
    public boolean checkIsValid(EntityPlayer player) {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player) {

        EntityLivingBase ent;

        if(!player.world.isRemote)
        {

            player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 300, 1));

            List<Entity> collidEntity = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX + 2, player.posY, player.posZ + 2, player.posX -2, player.posY + 1, player.posZ -2));

            for(int i = 0; i < collidEntity.size(); i++) {

                //System.out.println(collidEntity.get(i).toString());

                if(collidEntity.get(i) instanceof EntityPlayer)
                {

                    ent = (EntityLivingBase) collidEntity.get(i);

                    EntityPlayer target = (EntityPlayer) collidEntity.get(i);

                    target.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 200, 0));



                }

            }



        }

        player.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);

    }




}
