package fr.dofuscraft.dofuscraftcore.spells.iop;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Timer;
import java.util.TimerTask;

public class PressionSpell extends GeneralSpell
{

    public static final PressionSpell INSTANCE = new PressionSpell();
    private double reachDistance = 4.0D;

    public PressionSpell()
    {
        super("pression", 10, 10, 0, new ResourceLocation(References.MODID, "textures/gui/icons/spells/pression_spell_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        Utils utils = new Utils();

        Entity posEntity = utils.rayTraceEntity(reachDistance, 1, player);
        RayTraceResult raytrace = utils.rayTraceBlocks(7d, 1f, player);

        if (posEntity instanceof EntityPlayerMP)
        {
            return false;

        }
        else if (posEntity == null)
        {
            return false;

        }
        else
        {
            return true;
        }

    }

    @Override
    public void launchSpell(EntityPlayer player)
    {
        Utils utils = new Utils();

        Entity entityP = null;
        Entity posEntity = utils.rayTraceEntity(reachDistance, 1, player);

        if (!player.world.isRemote)
        {
            Entity raycastedEntity = utils.rayTraceEntity(reachDistance, 1, player);

            if (posEntity instanceof EntityPlayerMP)
            {
                player.sendStatusMessage(new TextComponentTranslation("spell.jump.error_player", new Object[0]), true);
            }
            else
            {
                if (raycastedEntity != null && raycastedEntity instanceof EntityLiving)
                {
                    raycastedEntity.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0f);
                }
            }
        }

        spawnParticle(player, (EntityLivingBase) posEntity);
    }


    public void spawnParticle(EntityPlayer player, Entity posEntity)
    {
        Timer chronoP = new Timer();
        chronoP.schedule(new TimerTask()
        {
            int timer = 5;

            @Override
            public void run()
            {
                player.world.spawnParticle(EnumParticleTypes.FLAME, posEntity.posX + 0.4, posEntity.posY, posEntity.posZ + 0.4, 0, 0.15, 0, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, posEntity.posX + 0.4, posEntity.posY, posEntity.posZ - 0.4, 0, 0.15, 0, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, posEntity.posX - 0.4, posEntity.posY, posEntity.posZ + 0.4, 0, 0.15, 0, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, posEntity.posX - 0.4, posEntity.posY, posEntity.posZ - 0.4, 0, 0.15, 0, 1);

                if (timer == 0)
                {
                    cancel();
                    chronoP.purge();
                }

                timer--;

            }
        }, 80, 100);
    }

}
