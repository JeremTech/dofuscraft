package fr.dofuscraft.dofuscraftcore.spells.feca;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

import javax.vecmath.Vector3d;
import java.util.Timer;
import java.util.TimerTask;

public class NaturalAttackSpell extends GeneralSpell
{

    public static final NaturalAttackSpell INSTANCE = new NaturalAttackSpell();

    public NaturalAttackSpell()
    {
        super("natural_attack", 1, 3, 1, new ResourceLocation(References.MODID, "textures/gui/icons/spells/natural_attack_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {
        Entity ent = Utils.rayTraceEntity(4.0D, 1, player);

        if (ent instanceof EntityLivingBase)
        {

            EntityLivingBase target = (EntityLivingBase) ent;

            target.setFire(3);
            target.attackEntityFrom(DamageSource.causeMobDamage(player), damage);
            spawnParticles(player, target);

        }

    }

    private void spawnParticles(EntityPlayer player, Entity target)
    {

        Timer chrono = new Timer();
        chrono.schedule(new TimerTask()
        {

            int timer = 15;
            double counter = 2.0D;

            @Override
            public void run()
            {


                for (float f = 0.0F; f < 4.5F; f += 0.5F)
                {
                    /* [-90 ; -270] Z++ && ]-270 ; -90[ Z-- */
                  /*  int sign = 0;
                    if (player.getRotationYawHead() < -90.0F && player.getRotationYawHead() > -270.0F) sign = 1;
                    if (player.getRotationYawHead() > -90.0F && player.getRotationYawHead() < -270.0F) sign = -1;

                    -Z [
                  */

                    double highX = Math.max(target.posX, player.posX);
                    double highZ = Math.max(target.posZ, player.posZ);
                    double lowX = Math.min(target.posX, player.posX);
                    double lowZ = Math.min(target.posZ, player.posZ);

                    double xDiff = Math.abs(highX) - Math.abs(lowX);
                    double zDiff = Math.abs(highZ) - Math.abs(lowZ);

                    switch (player.getHorizontalFacing())
                    {

                        case NORTH:
                            player.world.spawnParticle(EnumParticleTypes.FLAME, target.posX, player.posY + 1.0D + (Math.sin(f)), target.posZ + zDiff - f, 0, 0, 0);
                            break;

                        case EAST:
                            player.world.spawnParticle(EnumParticleTypes.FLAME, target.posX - xDiff + f, target.posY + (Math.sin(f)), target.posZ, 0, 0, 0);
                            break;

                        case WEST:
                            player.world.spawnParticle(EnumParticleTypes.FLAME, target.posX + xDiff - f, target.posY + (Math.sin(f)), target.posZ, 0, 0, 0);
                            break;

                        case SOUTH:
                            player.world.spawnParticle(EnumParticleTypes.FLAME, target.posX, target.posY + (Math.sin(f)), target.posZ - zDiff + f, 0, 0, 0);
                            break;

                        default:
                            return;

                    }


                    counter -= counter / 7.5F;
                    System.out.println(player.getRotationYawHead());


                    // Vector3d orientation
                }

                timer--;

                if (timer == 0)
                {
                    chrono.purge();
                    chrono.cancel();
                }
            }

        }, 0, 17);


    }

}

