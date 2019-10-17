package fr.dofuscraft.dofuscraftcore.spells.iop;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Objects;

public class DeferlementSpell extends GeneralSpell
{
    public static final DeferlementSpell INSTANCE = new DeferlementSpell();

    public DeferlementSpell()
    {
        super("flood", 20, 4, 4, new ResourceLocation(References.MODID, "textures/gui/icons/spells/flood_spell_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        Utils utils = new Utils();
        Entity entity = null;
        entity = utils.rayTraceEntity(6.0D, 1, player);
        RayTraceResult raytrace = utils.rayTraceBlocks(6.0D, 1f, player);

        if (entity instanceof EntityPlayerMP)
        {
            return false;
        }
        else if (entity == null)
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
        World world = player.world;
        Utils utils = new Utils();
        Entity entity = null;

        Entity entityP = utils.rayTraceEntity(6.0D, 1, player);

        double spellReach = 6.0D;
        entity = utils.rayTraceEntity(spellReach, 1, player);

        if (entity instanceof EntityPlayerMP)
        {
            player.sendStatusMessage(new TextComponentTranslation("spell.jump.error_player", new Object[0]), true);
        }
        else
        {
            if (entity instanceof EntityLiving)
            {
                ImpulsePlayer(player, (EntityLiving) entity);
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(11)), 200, 0));
                entity.attackEntityFrom(DamageSource.causeMobDamage(player), 2.0f);
            }
        }

        spawnParticle(player, (EntityLivingBase) entityP);

    }

    private void ImpulsePlayer(EntityPlayer player, EntityLivingBase castEntity)
    {

        double diffX = castEntity.posX - player.posX;
        double diffZ = castEntity.posZ - player.posZ;

        player.motionX += diffX * 0.15f;
        player.motionZ += diffZ * 0.15f;
        player.motionY += 0.15f;

        player.velocityChanged = true;
    }


    public void spawnParticle(EntityPlayer player, Entity entityP)
    {
        if (entityP instanceof EntityLiving)
        {
            BlockPos loc = player.getPosition();

            double diffX = entityP.posX - player.posX;
            double diffZ = entityP.posZ - player.posZ;

            //Test Sinus et Cosinus

            double radius = 1.75D;
            for (double i = 0.0D; i <= 360.0D; i += 0.5D)
            {
                double x = (radius * Math.cos(Math.toRadians(i)));
                double y = (radius * Math.sin(Math.toRadians(i)));

                player.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, entityP.posX + x, entityP.posY, entityP.posZ + y, 0, 0.5D, 0, 0, 1);

            }
        }
    }
}
