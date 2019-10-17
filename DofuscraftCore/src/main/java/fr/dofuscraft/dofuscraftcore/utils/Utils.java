package fr.dofuscraft.dofuscraftcore.utils;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Utils
{
    private static Logger logger;

    public static Logger getLogger()
    {
        if( logger == null )
        {
            logger = LogManager.getFormatterLogger(References.NAME);
        }
        return logger;
    }

    public static boolean isStringNullOrWhiteSpace(String value) {
        if (value == null)
        {
            return true;
        }

        for (int i = 0; i < value.length(); i++)
        {
            if (!Character.isWhitespace(value.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public static RayTraceResult rayTraceBlocks(double blockReachDistance, float partialTicks, EntityPlayer player)
    {
        Vec3d vec3d = getPositionEyes(partialTicks, player);
        Vec3d vec3d1 = getLook(partialTicks, player);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return player.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
    }

    public static List<Entity> rayTraceEntities(double blockReachDistance, float partialTicks, EntityPlayer player)
    {
        Vec3d vec3d = getPositionEyes(partialTicks, player);
        Vec3d vec3d1 = getLook(partialTicks, player);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);

        return player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d2.x, vec3d2.y, vec3d2.z));
    }

    public static Entity rayTraceEntity(double blockReachDistance, float partialTicks, EntityPlayer player)
    {
        EntityLivingBase raycastedEntity = null;

        // On récupère toutes les entités 'traversées' par le raycast. ( on récupère une list car on récupère toutes les entités dans la zone de collision, cf: world.getEntitiesWithinAABBExcludingEntity)
        List<Entity> entities = rayTraceEntities(blockReachDistance, partialTicks, player);
        // On garde en mémoire l'id dans la liste de l'entité la plus proche du joueur
        int entityID = 0;

        if(!entities.isEmpty())
        {
            // On définit ici la plus petite distance entre le joueur et chaque entité de la liste pour pouvoir récupérée l'entité la plus proche
            double minDist = blockReachDistance;

            for(int i = 0; i < entities.size(); i++)
            {

                double dist = 0.d;

                Entity e = entities.get(i);

                // Formule de distance entre la position du joueur et l'entité à l'index 'i'
                dist = Math.sqrt(Math.pow(player.posX-e.posX, 2) + Math.pow(player.posY-e.posY, 2) + Math.pow(player.posZ-e.posZ, 2));

                //System.out.println("Distance ID " + i + ": " + dist);
                //System.out.println("Min Distance: " + minDist);

                // On affecte en mémoire l'id de l'entité si elle est plus proche que la précédente entité et on définit la nouvelle distance la plus basse
                if(dist < minDist && entities.get(entityID) instanceof EntityLiving) entityID = i; minDist = dist;
            }

            raycastedEntity = (EntityLivingBase) entities.get(entityID);
        }
        return raycastedEntity;
    }

    public static Vec3d getPositionEyes(float partialTicks, EntityPlayer player)
    {
        if (partialTicks == 1.0F)
        {
            return new Vec3d(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ);
        }
        else
        {
            double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks;
            double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks + (double)player.getEyeHeight();
            double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks;
            return new Vec3d(d0, d1, d2);
        }
    }

    public static Vec3d getLook(float partialTicks, EntityPlayer player)
    {
        if (partialTicks == 1.0F)
        {
            return getVectorForRotation(player.rotationPitch, player.rotationYaw);
        }
        else
        {
            float f = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks;
            float f1 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks;
            return getVectorForRotation(f, f1);
        }
    }

    public static final Vec3d getVectorForRotation(float pitch, float yaw)
    {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d((double)(f1 * f2), (double)f3, (double)(f * f2));
    }
}
