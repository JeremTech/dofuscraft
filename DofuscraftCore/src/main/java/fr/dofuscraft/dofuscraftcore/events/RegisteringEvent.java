package fr.dofuscraft.dofuscraftcore.events;


import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringEvent
{

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> e)
	{
		ModItems.INSTANCE.init();
		e.getRegistry().registerAll(ModItems.INSTANCE.getItems().toArray(new Item[0]));
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> e)
	{
		ModBlocks.INSTANCE.init();
		e.getRegistry().registerAll(ModBlocks.INSTANCE.getBlocks().toArray(new Block[0]));

	}
}
