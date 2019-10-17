package fr.dofuscraft.dofuscraftcore.spells.iop;

import fr.dofuscraft.dofuscraftcore.spells.GeneralSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.Random;

public class JumpSpell extends GeneralSpell
{
    public static final JumpSpell INSTANCE = new JumpSpell();

    public JumpSpell()
    {
        super("jump", 20, 10, 0, new ResourceLocation(References.MODID, "textures/gui/icons/spells/jump_spell_icon.png"));
    }

    @Override
    public boolean checkIsValid(EntityPlayer player)
    {
        Utils utils = new Utils();
        RayTraceResult raytrace = utils.rayTraceBlocks(7d, 1f, player);

        if (player.world.getBlockState(raytrace.getBlockPos()).getBlock() != Blocks.AIR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void launchSpell(EntityPlayer player)
    {
        Utils utils = new Utils();
        RayTraceResult raytrace = utils.rayTraceBlocks(7d, 1f, player);
        if (raytrace != null)
        {

            if (player.world.getBlockState(raytrace.getBlockPos()).getBlock() != Blocks.AIR)
            {
                player.setPosition(raytrace.getBlockPos().getX(), raytrace.getBlockPos().getY() + 1, raytrace.getBlockPos().getZ());
                List<Entity> collidEntity = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX + 2, player.posY, player.posZ + 2, player.posX - 2, player.posY + 1, player.posZ - 2));


                for (int i = 0; i < collidEntity.size(); i++)
                {
                    if (collidEntity.get(i) instanceof EntityLiving)
                    {
                        collidEntity.get(i).attackEntityFrom(DamageSource.causeMobDamage(player), 4);
                    }
                }

                spawnParticle(player, raytrace.getBlockPos());
            }
            else
            {
                player.sendStatusMessage(new TextComponentTranslation("spell.jump.error", new Object[0]), true);
            }
        }
    }

    //TODO Récupèrer le block avec lequel on a intéragit pour avoir ses particules

    public void spawnParticle(EntityPlayer player, BlockPos pos)
    {

        Random rand = player.world.rand;

        for (int i = 0; i < 30; i++)
        {
            // On génère un nombre double aléatoirement ( en fait on récupère le premier élément d'une liste de nombres générés )
            // dans un intervalle spécifié le deuxième argument ( début de l'intervalle ) et le troisième argument ( fin de l'intervalle )
            double rX = rand.doubles(1, -1.0D, 1.0D).findFirst().getAsDouble();
            //double rY = rand.doubles(1, 0.5D, 1.2D).findFirst().getAsDouble();
            double rZ = rand.doubles(1, -1.0D, 1.0D).findFirst().getAsDouble();

            double x = player.posX + (rand.nextDouble() - 0.5D) * (double) player.width + rX;
            double y = player.posY + rand.nextDouble() * (double) player.height;
            double z = player.posZ + (rand.nextDouble() - 0.5D) * (double) player.width + rZ;

            double xSpeed = rand.doubles(1, -1.0D, 1.0D).findFirst().getAsDouble();
            double ySpeed = rand.doubles(1, 0.2D, 0.5D).findFirst().getAsDouble();
            double zSpeed = rand.doubles(1, -1.0D, 1.0D).findFirst().getAsDouble();


            player.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, x, y, z, xSpeed, 0.0D, zSpeed, Block.getStateId(player.world.getBlockState(pos)));
        }

    }
}
