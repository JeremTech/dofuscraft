package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class DCCapabilityEvents
{
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent e)
	{
		ILevel level = e.player.getCapability(DCLevelProvider.LEVEL_CAP, null);
        ISubLevel sublevel = e.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
        IClass classe = e.player.getCapability(DCClassProvider.CLASS_CAP, null);


        if(e.player instanceof EntityPlayerMP)
		{
            Utils.getLogger().info("Syncing player's capabilities");

            if(!e.player.hasCapability(DCLevelProvider.LEVEL_CAP, null))
            {
                level.setAlchemistLevel(1);
                level.setLumberjackLevel(1);
                level.setMinerLevel(1);
                level.setPeasantLevel(1);
            }
			if(!e.player.hasCapability(DCSubLevelProvider.SUBLEVEL_CAP, null))
            {
                sublevel.setAlchemistSubLevel(0);
                sublevel.setLumberjackSubLevel(0);
                sublevel.setMinerSubLevel(0);
                sublevel.setPeasantSubLevel(0);
            }
            if(!e.player.hasCapability(DCClassProvider.CLASS_CAP, null))
            {
                classe.setID("");
                classe.setName("");
                classe.setClassItem("");
            }

            classe.sync((EntityPlayerMP) e.player);
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
		newLevel.setJewelerLevel(oldLevel.getJewelerLevel());
		newLevel.setShoeMakerLevel(oldLevel.getShoeMakerLevel());
		newLevel.setTailorLevel(oldLevel.getTailorLevel());
		
		// SubLevels
        ISubLevel oldSubLevel = e.getOriginal().getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
        ISubLevel newSubLevel = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

        newSubLevel.setAlchemistSubLevel(oldSubLevel.getAlchemistSubLevel());
        newSubLevel.setLumberjackSubLevel(oldSubLevel.getLumberjackSubLevel());
        newSubLevel.setMinerSubLevel(oldSubLevel.getMinerSubLevel());
        newSubLevel.setPeasantSubLevel(oldSubLevel.getPeasantSubLevel());
        newSubLevel.setJewelerSubLevel(oldSubLevel.getJewelerSubLevel());
        newSubLevel.setShoeMakerSubLevel(oldSubLevel.getShoeMakerSubLevel());
        newSubLevel.setTailorSubLevel(oldSubLevel.getTailorSubLevel());

        // Class capability
        IClass oldClass = e.getOriginal().getCapability(DCClassProvider.CLASS_CAP, null);
        IClass newClass = player.getCapability(DCClassProvider.CLASS_CAP, null);

        newClass.setID(oldClass.getID());
        newClass.setName(oldClass.getName());
        newClass.setClassItem(oldClass.getClassItem());
	}

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event)
    {
        event.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCClassProvider.CLASS_CAP, null).sync((EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        event.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCClassProvider.CLASS_CAP, null).sync((EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        event.player.getCapability(DCLevelProvider.LEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null).sync((EntityPlayerMP) event.player);
        event.player.getCapability(DCClassProvider.CLASS_CAP, null).sync((EntityPlayerMP) event.player);
    }
}
