package fr.dofuscraft.dofuscraftstuffs;

import fr.dofuscraft.dofuscraftstuffs.creativetabs.DCArmorsTab;
import fr.dofuscraft.dofuscraftstuffs.creativetabs.DCToolsTab;
import fr.dofuscraft.dofuscraftstuffs.events.CancelPlantsEvent;
import fr.dofuscraft.dofuscraftstuffs.events.RegisteringEvent;
import fr.dofuscraft.dofuscraftstuffs.proxy.CommonProxy;
import fr.dofuscraft.dofuscraftstuffs.utils.RecipeHandler;
import fr.dofuscraft.dofuscraftstuffs.utils.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION, dependencies = "required-after:dofuscraftcore@BETA 1.0 - Pre-Release 2")
public class DofuscraftStuffs
{
    public static final CreativeTabs armors = new DCArmorsTab();
    public static final CreativeTabs tools = new DCToolsTab();

    public static final Logger logger = LogManager.getLogger(References.MODID);

    @Mod.Instance(References.MODID)
    public static DofuscraftStuffs instance;

    @SidedProxy(serverSide = References.SERVER_PROXY_CLASS, clientSide = References.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    public DofuscraftStuffs()
    {
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
    }

    public static Object LOGGER;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();

        MinecraftForge.EVENT_BUS.register(new CancelPlantsEvent());

        RecipeHandler.registerRecipe();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }


}
