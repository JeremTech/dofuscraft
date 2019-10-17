package fr.dofuscraft.dofuscraftcore.capabilities.level;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.gui.toast.DCLevelUpToast;
import fr.dofuscraft.dofuscraftcore.network.PacketSyncSubLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.concurrent.Callable;

public class DCSubLevel implements ISubLevel
{
    private int al_sublevel = 0, lj_sublevel = 0, mi_sublevel = 0, ps_sublevel = 0, jl_sublevel = 0, t_sublevel = 0, sm_sublevel = 0;

    @Override
    public void setAlchemistSubLevel(int sublevel)
    {
        this.al_sublevel = sublevel;
    }

    @Override
    public int getAlchemistSubLevel()
    {
        return al_sublevel;
    }

    @Override
    public void setLumberjackSubLevel(int sublevel)
    {
        this.lj_sublevel = sublevel;
    }

    @Override
    public int getLumberjackSubLevel()
    {
        return lj_sublevel;
    }

    @Override
    public void setMinerSubLevel(int sublevel)
    {
        this.mi_sublevel = sublevel;
    }

    @Override
    public int getMinerSubLevel()
    {
        return mi_sublevel;
    }

    @Override
    public void setPeasantSubLevel(int sublevel)
    {
        this.ps_sublevel = sublevel;
    }

    @Override
    public int getPeasantSubLevel()
    {
        return ps_sublevel;
    }

    @Override
    public void setJewelerSubLevel(int sublevel)
    {
        this.jl_sublevel = sublevel;
    }

    @Override
    public int getJewelerSubLevel()
    {
        return jl_sublevel;
    }

    @Override
    public void setTailorSubLevel(int sublevel)
    {
        this.t_sublevel = sublevel;
    }

    @Override
    public int getTailorSubLevel()
    {
        return t_sublevel;
    }

    @Override
    public void setShoeMakerSubLevel(int sublevel)
    {
        this.sm_sublevel = sublevel;
    }

    @Override
    public int getShoeMakerSubLevel()
    {
        return sm_sublevel;
    }


    @Override
    public void sync(EntityPlayerMP player)
    {

        PacketSyncSubLevel packet = new PacketSyncSubLevel(player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null));
        if(!player.getEntityWorld().isRemote)
        {
            Dofuscraftcore.network.sendTo(packet, player);
        }
        else
        {
            Dofuscraftcore.network.sendToServer(packet);
        }
    }

    @Override
    public void checkLevel(EntityPlayerMP player, String job)
    {
        ILevel levels = player.getCapability(DCLevelProvider.LEVEL_CAP, null);

        switch (job)
        {
            case "alchemist" :
            {
                if(this.getAlchemistSubLevel() >= this.getSubLevelRequire(levels.getAlchemistLevel()))
                {
                    levels.setAlchemistLevel(levels.getAlchemistLevel() + 1);
                    this.setAlchemistSubLevel(this.getAlchemistSubLevel() -  this.getSubLevelRequire(levels.getAlchemistLevel() - 1));
                    this.checkLevel(player, "alchemist");
                }
            }
            case "lumberjack" :
            {
                if(this.getLumberjackSubLevel() >= this.getSubLevelRequire(levels.getLumberjackLevel()))
                {
                    levels.setLumberjackLevel(levels.getLumberjackLevel() + 1);
                    this.setLumberjackSubLevel(this.getLumberjackSubLevel() -  this.getSubLevelRequire(levels.getLumberjackLevel() - 1));
                    this.checkLevel(player, "lumberjack");
                }
            }
            case "miner" :
            {
                if(this.getMinerSubLevel() >= this.getSubLevelRequire(levels.getMinerLevel()))
                {
                    levels.setMinerLevel(levels.getMinerLevel() + 1);
                    this.setMinerSubLevel(this.getMinerSubLevel() -  this.getSubLevelRequire(levels.getMinerLevel() - 1));
                    this.checkLevel(player, "miner");
                }
            }
            case "peasant" :
            {
                if(this.getPeasantSubLevel() >= this.getSubLevelRequire(levels.getPeasantLevel()))
                {
                    levels.setPeasantLevel(levels.getPeasantLevel() + 1);
                    this.setPeasantSubLevel(this.getPeasantSubLevel() -  this.getSubLevelRequire(levels.getPeasantLevel() - 1));
                    this.checkLevel(player, "peasant");
                }
            }
            case "jeweler" :
            {
                if(this.getJewelerSubLevel() >= this.getSubLevelRequire(levels.getJewelerLevel()))
                {
                    levels.setJewelerLevel(levels.getJewelerLevel() + 1);
                    this.setJewelerSubLevel(this.getJewelerSubLevel() -  this.getSubLevelRequire(levels.getJewelerLevel() - 1));
                    this.checkLevel(player, "jeweler");
                }
            }
            case "tailor" :
            {
                if(this.getTailorSubLevel() >= this.getSubLevelRequire(levels.getTailorLevel()))
                {
                    levels.setTailorLevel(levels.getTailorLevel() + 1);
                    this.setTailorSubLevel(this.getTailorSubLevel() -  this.getSubLevelRequire(levels.getTailorLevel() - 1));
                    this.checkLevel(player, "tailor");
                }
            }
            case "shoemaker" :
            {
                if(this.getShoeMakerSubLevel() >= this.getSubLevelRequire(levels.getShoeMakerLevel()))
                {
                    levels.setShoeMakerLevel(levels.getShoeMakerLevel() + 1);
                    this.setShoeMakerSubLevel(this.getShoeMakerSubLevel() -  this.getSubLevelRequire(levels.getShoeMakerLevel() - 1));
                    this.checkLevel(player, "shoemaker");
                }
            }
        }

        this.sync(player);
        levels.sync(player);
    }

    @Override
    public int getSubLevelRequire(int current_level)
    {
        return Math.round((current_level + 1) * current_level * 10);
    }




}
