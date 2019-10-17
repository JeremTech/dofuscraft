package fr.dofuscraft.dofuscraftcore.proxy;

import fr.dofuscraft.dofuscraftcore.capabilities.*;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClass;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassStorage;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.capabilities.level.*;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelStorage;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelStorage;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.init.ModFluids;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{
	public void preInit()
	{
		// Enregistrement des liquides
		Utils.getLogger().info("Register fluids");
		ModFluids.registerFluids();
	}

	public void init()
	{
		Utils.getLogger().info("Register capabilities");
		CapabilityManager.INSTANCE.register(ILevel.class, new DCLevelStorage(), DCLevel.class);
		CapabilityManager.INSTANCE.register(ISubLevel.class, new DCSubLevelStorage(), DCSubLevel.class);
		CapabilityManager.INSTANCE.register(IClass.class, new DCClassStorage(), DCClass.class);

		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	public void postInit()
	{

	}

	public IThreadListener getListener(MessageContext ctx)
	{
		return (WorldServer) ctx.getServerHandler().player.world;
	}

	public EntityPlayer getPlayer(MessageContext ctx)
	{
		return ctx.getServerHandler().player;
	}
}
