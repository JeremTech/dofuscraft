package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CapabilityEvents
{
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent e)
	{
		ILevel level = e.player.getCapability(DCLevelProvider.LEVEL_CAP, null);
        ISubLevel sublevel = e.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

		if(e.player instanceof EntityPlayerMP)
		{
			if(!e.player.hasCapability(DCLevelProvider.LEVEL_CAP, null))
            {
                level.setAlchemistLevel(0);
                level.setLumberjackLevel(0);
                level.setMinerLevel(0);
                level.setPeasantLevel(0);
            }
			if(!e.player.hasCapability(DCSubLevelProvider.SUBLEVEL_CAP, null))
            {
                sublevel.setAlchemistSubLevel(0);
                sublevel.setLumberjackSubLevel(0);
                sublevel.setMinerSubLevel(0);
                sublevel.setPeasantSubLevel(0);
            }

            Utils.getLogger().info("Syncing player's capabilities");
		    level.sync((EntityPlayerMP) e.player);
			sublevel.sync((EntityPlayerMP) e.player);
		}
	}

	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone e)
	{
        Utils.getLogger().info("Updating player's capabilities");

        // Levels
		EntityPlayer player = e.getEntityPlayer();
		ILevel oldLevel = e.getOriginal().getCapability(DCLevelProvider.LEVEL_CAP, null);
		ILevel newLevel = player.getCapability(DCLevelProvider.LEVEL_CAP, null);

		newLevel.setAlchemistLevel(oldLevel.getAlchemistLevel());
		newLevel.setLumberjackLevel(oldLevel.getLumberjackLevel());
		newLevel.setMinerLevel(oldLevel.getMinerLevel());
		newLevel.setPeasantLevel(oldLevel.getPeasantLevel());
		
		// SubLevels
        ISubLevel oldSubLevel = e.getOriginal().getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
        ISubLevel newSubLevel = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

        newSubLevel.setAlchemistSubLevel(oldSubLevel.getAlchemistSubLevel());
        newSubLevel.setLumberjackSubLevel(oldSubLevel.getLumberjackSubLevel());
        newSubLevel.setMinerSubLevel(oldSubLevel.getMinerSubLevel());
        newSubLevel.setPeasantSubLevel(oldSubLevel.getPeasantSubLevel());
	}

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event)
    {
        event.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        event.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent e)
    {

        e.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) e.player);
        e.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) e.player);

    }
}
