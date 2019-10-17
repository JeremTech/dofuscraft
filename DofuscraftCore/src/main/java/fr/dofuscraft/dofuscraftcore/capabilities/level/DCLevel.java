package fr.dofuscraft.dofuscraftcore.capabilities.level;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.network.PacketSyncLevel;
import net.minecraft.entity.player.EntityPlayerMP;

public class DCLevel implements ILevel
{

	private int al_level = 1, lj_level = 1, mi_level = 1, ps_level = 1, jl_level = 1, t_level = 1, sm_level = 1;

	@Override
	public void setAlchemistLevel(int level)
	{
		this.al_level = level;
	}

	@Override
	public int getAlchemistLevel()
	{
		return al_level;
	}

	@Override
	public void setLumberjackLevel(int level)
	{
		this.lj_level = level;
	}

	@Override
	public int getLumberjackLevel()
	{
		return lj_level;
	}

	@Override
	public void setMinerLevel(int level)
	{
		this.mi_level = level;
	}

	@Override
	public int getMinerLevel()
	{
		return mi_level;
	}

	@Override
	public void setPeasantLevel(int level)
	{
		this.ps_level = level;
	}

	@Override
	public int getPeasantLevel()
	{
		return ps_level;
	}

	@Override
	public void setJewelerLevel(int sublevel)
	{
		this.jl_level = sublevel;
	}

	@Override
	public int getJewelerLevel()
	{
		return jl_level;
	}

	@Override
	public void setTailorLevel(int sublevel)
	{
		this.t_level = sublevel;
	}

	@Override
	public int getTailorLevel()
	{
		return t_level;
	}

	@Override
	public void setShoeMakerLevel(int sublevel)
	{
		this.sm_level = sublevel;
	}

	@Override
	public int getShoeMakerLevel()
	{
		return sm_level;
	}


    @Override
    public void sync(EntityPlayerMP player)
    {
	    PacketSyncLevel packet = new PacketSyncLevel(player.getCapability(DCLevelProvider.LEVEL_CAP, null));

        if(!player.getEntityWorld().isRemote)
        {
            Dofuscraftcore.network.sendTo(packet, player);
        }
        else
        {
            Dofuscraftcore.network.sendToServer(packet);
        }
    }
}
