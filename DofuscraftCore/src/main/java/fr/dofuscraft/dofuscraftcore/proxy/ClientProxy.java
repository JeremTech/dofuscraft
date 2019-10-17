package fr.dofuscraft.dofuscraftcore.proxy;

import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModFluids;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import fr.dofuscraft.dofuscraftcore.input.InputHandler;
import fr.dofuscraft.dofuscraftcore.input.KeyBindings;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit()
	{
		super.preInit();
		MinecraftForge.EVENT_BUS.register(ModItems.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModBlocks.INSTANCE);

		// Enregistrement des rendus des liquides
		Utils.getLogger().info("Register fluids renders");
		ModFluids.renderFluids();
	}

	@Override
	public void init()
	{
		super.init();

		Utils.getLogger().info("Register inputs");
		MinecraftForge.EVENT_BUS.register(new InputHandler());
		KeyBindings.init();

	}

	@Override
	public void postInit()
	{
		super.postInit();
	}
}
