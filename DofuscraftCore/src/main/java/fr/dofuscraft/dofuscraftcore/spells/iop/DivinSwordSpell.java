package fr.dofuscraft.dofuscraftcore.spells.iop;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DivinSwordSpell extends GeneralSpell
{
    public static final DivinSwordSpell INSTANCE = new DivinSwordSpell();

    public DivinSwordSpell()
    {
        super("divin_sword", 15, 10, new ResourceLocation(References.MODID, "textures/gui/icons/spells/divine_sword_spell_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {

        List<Entity> EntityOne = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX + 3, player.posY + 1, player.posZ, player.posX - 3, player.posY + 1, player.posZ));

        List<Entity> EntityTwo = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY + 1, player.posZ + 3, player.posX, player.posY + 1, player.posZ - 3));

        BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);

        for (int i = 0; i < EntityOne.size(); i++)
        {
            if (EntityOne.get(i) instanceof EntityLiving)
            {
                EntityOne.get(i).attackEntityFrom(DamageSource.causeMobDamage(player), 4);
            }
        }

        for (int i = 0; i < EntityTwo.size(); i++)
        {
            if (EntityTwo.get(i) instanceof EntityLiving)
            {
                EntityTwo.get(i).attackEntityFrom(DamageSource.causeMobDamage(player), 4);
            }
        }

        spawnParticle(player, pos);

    }

    public void spawnParticle(EntityPlayer player, BlockPos pos)
    {

        Timer chrono = new Timer();
        chrono.schedule(new TimerTask()
        {
            int timer = 10;

            @Override
            public void run()
            {
                player.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.05, pos.getY() + 1, pos.getZ(), 0.1, 0, 0, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() - 0.05, pos.getY() + 1, pos.getZ(), -0.1, 0, 0, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX(), pos.getY() + 1, pos.getZ() + 0.05, 0, 0, 0.1, 1);
                player.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX(), pos.getY() + 1, pos.getZ() - 0.05, 0, 0, -0.1, 1);

                if (timer == 0)
                {

                    cancel();
                    chrono.purge();

                }

                timer--;
            }
        }, 100, 100);
    }
}
