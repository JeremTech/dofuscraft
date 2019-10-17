package fr.dofuscraft.dofuscraftcore.spells.feca;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;

import java.util.List;

public class BlindnessSpell extends GeneralSpell
{

    public static final BlindnessSpell INSTANCE = new BlindnessSpell();

    public BlindnessSpell()
    {

        super("blindness", 10, 4, 1, new ResourceLocation(References.MODID, "textures/gui/icons/spells/blindness_icon.png"));

    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {

        RayTraceResult raytrace = Utils.rayTraceBlocks(7d, 1f, player);
        if (raytrace != null)
        {


            List<Entity> collidEntity = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(raytrace.getBlockPos().getX() - 2, raytrace.getBlockPos().getY()+1, raytrace.getBlockPos().getZ() - 2, raytrace.getBlockPos().getX() + 2, raytrace.getBlockPos().getY() + 2, raytrace.getBlockPos().getZ() + 2));

            System.out.println(collidEntity.toString());

            for (Entity ent : collidEntity)
            {

                if (ent instanceof EntityLiving)
                {
                    //System.out.println(collidEntity.get(i).toString());
                    ent.attackEntityFrom(DamageSource.causeMobDamage(player), 4);
                    spawnParticles(player, (EntityLivingBase) ent);

                }

            }

        }

    }


    private void spawnParticles(EntityPlayer player, EntityLivingBase target)
    {

        for (int i = 0; i < 10; i++)
        {

            double highX = Math.max(target.posX, player.posX);
            double highZ = Math.max(target.posZ, player.posZ);
            double lowX = Math.min(target.posX, player.posX);
            double lowZ = Math.min(target.posZ, player.posZ);

            double xDiff = Math.abs(highX) - Math.abs(lowX);
            double zDiff = Math.abs(highZ) - Math.abs(lowZ);

            player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX - Math.signum(target.posX) * (xDiff) / (10 - i + 1), target.posY, target.posZ - Math.signum(target.posZ) * (zDiff) / (10 - i + 1), 0, 0, 0);
        }
    }



}
