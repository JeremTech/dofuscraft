package fr.dofuscraft.dofuscraftcore;

import fr.dofuscraft.dofuscraftcore.commands.CommandClass;
import fr.dofuscraft.dofuscraftcore.commands.CommandDisplay;
import fr.dofuscraft.dofuscraftcore.commands.CommandLevel;
import fr.dofuscraft.dofuscraftcore.events.*;
import fr.dofuscraft.dofuscraftcore.gui.GUIHandler;
import fr.dofuscraft.dofuscraftcore.network.*;
import fr.dofuscraft.dofuscraftcore.proxy.CommonProxy;
import fr.dofuscraft.dofuscraftcore.tabs.DofuscraftBlocksTab;
import fr.dofuscraft.dofuscraftcore.tabs.DofuscraftItemsTab;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.Display;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MC_VERSION)
public class Dofuscraftcore
{
	public static final CreativeTabs items = new DofuscraftItemsTab("DCTabItems");
	public static final CreativeTabs blocks = new DofuscraftBlocksTab("DCTabBlocks");
	@Mod.Instance(References.MODID)
	public static Dofuscraftcore instance;

	@SidedProxy(serverSide = References.SERVER_PROXY, clientSide = References.CLIENT_PROXY)
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper network;

	public Dofuscraftcore()
	{
		MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
	}

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		if(event.getSide().isClient())
		{
			Display.setTitle("Dofuscraft " + References.VERSION);
		}

		proxy.preInit();

		// Enregistrement des paquets
        Utils.getLogger().info("Register packets");
		network = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);
		network.registerMessage(PacketSyncLevel.Handler.class, PacketSyncLevel.class, 0, Side.CLIENT);
		network.registerMessage(PacketSyncSubLevel.Handler.class, PacketSyncSubLevel.class, 1, Side.CLIENT);
        network.registerMessage(PacketSyncLevel.HandlerServer.class, PacketSyncLevel.class, 2, Side.SERVER);
        network.registerMessage(PacketSyncSubLevel.HandlerServer.class, PacketSyncSubLevel.class, 3, Side.SERVER);
		network.registerMessage(PacketDCClass.Handler.class, PacketDCClass.class, 4, Side.CLIENT);
		network.registerMessage(PacketDCClass.HandlerServer.class, PacketDCClass.class, 5, Side.SERVER);
		network.registerMessage(PacketCommand.HandlerServer.class, PacketCommand.class, 6, Side.SERVER);
		network.registerMessage(PacketCmdDisplay.Handler.class, PacketCmdDisplay.class, 7, Side.CLIENT);

		// Detection du mod CustomMainMenu. Si il est present : fermeture du jeu
        Utils.getLogger().info("Checking loaded mods");
		if(Loader.isModLoaded("custommainmenu"))
		{
            Utils.getLogger().error("custommainmenu is not compatible with Dofuscraft ! Minecraft shutting down...");
			Minecraft.getMinecraft().shutdown();
		}
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();

        Utils.getLogger().info("Register 'DCCapabilityEvents' event");
		MinecraftForge.EVENT_BUS.register(new DCCapabilityEvents());
        Utils.getLogger().info("Register 'DCBlockBreakListener' event");
		MinecraftForge.EVENT_BUS.register(new DCBlockBreakListener());
		Utils.getLogger().info("Register 'DCPlayerEvents' event");
		MinecraftForge.EVENT_BUS.register(new DCPlayerEvent());

		if( event.getSide().isClient() )
		{
            Utils.getLogger().info("Register 'DCGuiEvent' event");
			MinecraftForge.EVENT_BUS.register(new DCGuiEvent());
            Utils.getLogger().info("Register 'OverlayEvent' event");
            MinecraftForge.EVENT_BUS.register(new OverlayEvent());

		}

        Utils.getLogger().info("Register GUIHandler");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());



	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        Utils.getLogger().info("Register commands");
        CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
        commandManager.registerCommand(new CommandLevel());
		commandManager.registerCommand(new CommandClass());
		commandManager.registerCommand(new CommandDisplay());
    }
}
