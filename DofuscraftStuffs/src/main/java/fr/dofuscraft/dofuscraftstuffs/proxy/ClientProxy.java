package fr.dofuscraft.dofuscraftstuffs.proxy;

import fr.dofuscraft.dofuscraftstuffs.init.ModArmors;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy  extends CommonProxy{

	@Override
	public void preInit()
	{
		super.preInit();

		MinecraftForge.EVENT_BUS.register(ModItems.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModArmors.INSTANCE);
	}

	@Override
	public void init()
	{
		super.init();
	}
}
