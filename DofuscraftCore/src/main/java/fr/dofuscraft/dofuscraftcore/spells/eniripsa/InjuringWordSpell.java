package fr.dofuscraft.dofuscraftcore.spells.eniripsa;

import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class InjuringWordSpell extends GeneralSpell
{

    public static final InjuringWordSpell INSTANCE = new InjuringWordSpell();
    private double reachDistance = 4.0D;

    public InjuringWordSpell()
    {
        super("injuring_word", 10, 4, 4, new ResourceLocation(References.MODID, "textures/gui/icons/spells/injuring_word_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {

        Entity raycastedEntity = Utils.rayTraceEntity(reachDistance, 1, player);

        if (raycastedEntity instanceof EntityPlayer)
        {

            player.sendStatusMessage(new TextComponentTranslation("spell.jump.error_player", new Object[0]), true);


        } else
        {

            if (raycastedEntity instanceof EntityLiving)
            {


                spawnParticles(player, raycastedEntity, EnumParticleTypes.PORTAL, () -> {
                    raycastedEntity.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0f);
                    return null;
                });


                List<Entity> collidEntity = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX + 2, player.posY, player.posZ + 2, player.posX - 2, player.posY + 1, player.posZ - 2));

                if (!collidEntity.isEmpty())
                {

                    for (int i = 0; i < collidEntity.size(); i++)
                    {

                        if (collidEntity.get(i) instanceof EntityPlayer)
                        {

                            EntityPlayer target = (EntityPlayer) collidEntity.get(i);

                            spawnParticles(player, target, EnumParticleTypes.PORTAL, () -> {
                                target.heal(4);
                                return null;
                            });

                        }

                    }

                }

            }

        }

    }

    private void spawnParticles(EntityPlayer player, Entity target, EnumParticleTypes particles, Callable function)
    {

        Timer chrono = new Timer();
        chrono.schedule(new TimerTask()
        {

            int timer = 20;

            @Override
            public void run()
            {


                if (timer > 0)
                {

                    for (int i = 0; i < 5; i++)
                    {
                        /* a */
                        player.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX + 0.5D, target.posY + 1.5D, target.posZ + 0.5D, -0.1, -0.3D, -0.1);
                        /* b */
                        player.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX + 0.5D, target.posY + 1.5D, target.posZ - 0.5D, -0.1, -0.3D, 0.1);
                        /* c */
                        player.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX - 0.5D, target.posY + 1.5D, target.posZ + 0.5D, 0.1, -0.3D, -0.1);
                        /* d */
                        player.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX - 0.5D, target.posY + 1.5D, target.posZ - 0.5D, 0.1, -0.3D, 0.1);
                    }
                }

                if (timer == 0)
                {
                    spawnCircle(player, target, particles);
                    try
                    {
                        function.call();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        System.out.println("La fonction n'a pas pu être appelée !");
                    }
                    cancel();
                    chrono.purge();
                }

                timer--;
            }

        }, 0, 17);
    }

    private void spawnCircle(EntityPlayer player, Entity target, EnumParticleTypes particles)
    {
        int numbersOfCircles = 12;

        for (double i = 0; i <= Math.PI; i += Math.PI / numbersOfCircles)
        {

            double radius = Math.sin(i);
            double y = Math.cos(i);

            for (double a = 0; a < Math.PI * 2; a += Math.PI / numbersOfCircles)
            {

                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;

                player.world.spawnParticle(particles, target.posX + x, target.posY + y, target.posZ + z, 0, 0, 0, 0, 0);

            }
        }
    }
}
