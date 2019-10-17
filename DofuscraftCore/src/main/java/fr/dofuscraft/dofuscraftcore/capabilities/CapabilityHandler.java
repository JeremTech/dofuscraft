package fr.dofuscraft.dofuscraftcore.capabilities;

import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{

	public static final ResourceLocation LEVEL_CAP = new ResourceLocation(References.MODID, "level");
    public static final ResourceLocation SUBLEVEL_CAP = new ResourceLocation(References.MODID, "sublevel");
	public static final ResourceLocation CLASS_CAP = new ResourceLocation(References.MODID, "class");

	@SubscribeEvent
	public void attackCapability(AttachCapabilitiesEvent<Entity> e)
	{
		if(!(e.getObject() instanceof EntityPlayer)) return;

		e.addCapability(LEVEL_CAP, new DCLevelProvider());
		e.addCapability(SUBLEVEL_CAP, new DCSubLevelProvider());
		e.addCapability(CLASS_CAP, new DCClassProvider());

	}

}
