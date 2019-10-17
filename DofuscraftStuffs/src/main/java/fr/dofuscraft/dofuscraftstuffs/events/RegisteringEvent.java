package fr.dofuscraft.dofuscraftstuffs.events;

import fr.dofuscraft.dofuscraftstuffs.init.ModArmors;
import fr.dofuscraft.dofuscraftstuffs.init.ModItems;
import fr.dofuscraft.dofuscraftstuffs.utils.References;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = References.MODID)
public class RegisteringEvent
{

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> e)
	{
		ModItems.INSTANCE.init();
		ModArmors.INSTANCE.init();
		e.getRegistry().registerAll(ModItems.INSTANCE.items.toArray(new Item[0]));
		e.getRegistry().registerAll(ModArmors.INSTANCE.armors.toArray(new ItemArmor[0]));
	}


}
