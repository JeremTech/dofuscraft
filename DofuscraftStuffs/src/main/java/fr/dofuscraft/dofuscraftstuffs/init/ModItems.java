package fr.dofuscraft.dofuscraftstuffs.init;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftstuffs.items.*;
import fr.dofuscraft.dofuscraftstuffs.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ModItems
{
	public static final ModItems INSTANCE = new ModItems();
	public List<Item> items;

	// Axes
	public static DCItemAxe ash_axe, iron_axe, copper_axe, bronze_axe, cobalt_axe;
	// Pickaxes
	public static DCItemPickaxe ash_pickaxe, iron_pickaxe, copper_pickaxe, bronze_pickaxe, cobalt_pickaxe;
	// Scythes
	public static DCItemScythe ash_scythe, iron_scythe, copper_scythe, bronze_scythe, cobalt_scythe;
	// Sickles
	public static DCItemSickle ash_sickle, iron_sickle, copper_sickle, bronze_sickle, cobalt_sickle;

	// Other
	public static ItemShield beginner_shield;
	public static DCItemSword beginner_sword;


	public void init()
	{
		items = Lists.newArrayList();

		// Haches
		ash_axe = new DCItemAxe("ash_axe", DCToolMaterials.ash);
		iron_axe = new DCItemAxe("iron_axe", DCToolMaterials.iron);
		copper_axe = new DCItemAxe("copper_axe", DCToolMaterials.copper);
		bronze_axe = new DCItemAxe("bronze_axe", DCToolMaterials.bronze);
		cobalt_axe = new DCItemAxe("cobalt_axe", DCToolMaterials.cobalt);

		// Pioches
		ash_pickaxe = new DCItemPickaxe("ash_pickaxe", DCToolMaterials.ash);
		iron_pickaxe = new DCItemPickaxe("iron_pickaxe", DCToolMaterials.iron);
		copper_pickaxe = new DCItemPickaxe("copper_pickaxe", DCToolMaterials.copper);
		bronze_pickaxe = new DCItemPickaxe("bronze_pickaxe", DCToolMaterials.bronze);
		cobalt_pickaxe = new DCItemPickaxe("cobalt_pickaxe", DCToolMaterials.cobalt);

		// Faux
		ash_scythe = new DCItemScythe("ash_scythe", 1.0f, -0.3f, DCToolMaterials.ash, DCToolMaterials.scythe_set);
		iron_scythe = new DCItemScythe("iron_scythe", 1.0f, -0.3f, DCToolMaterials.iron, DCToolMaterials.scythe_set);
		copper_scythe = new DCItemScythe("copper_scythe", 1.0f, -0.3f, DCToolMaterials.copper, DCToolMaterials.scythe_set);
		bronze_scythe = new DCItemScythe("bronze_scythe", 1.0f, -0.3f, DCToolMaterials.bronze, DCToolMaterials.scythe_set);
		cobalt_scythe = new DCItemScythe("cobalt_scythe", 1.0f, -0.3f, DCToolMaterials.cobalt, DCToolMaterials.scythe_set);

		// Faucilles
		ash_sickle = new DCItemSickle("ash_sickle", 1.0f, -0.3f, DCToolMaterials.sickle_ash, DCToolMaterials.sickle_set);
		iron_sickle = new DCItemSickle("iron_sickle", 1.0f, -0.3f, DCToolMaterials.sickle_iron, DCToolMaterials.sickle_set);
		copper_sickle = new DCItemSickle("copper_sickle", 1.0f, -0.3f, DCToolMaterials.sickle_copper, DCToolMaterials.sickle_set);
		bronze_sickle = new DCItemSickle("bronze_sickle", 1.0f, -0.3f, DCToolMaterials.sickle_bronze, DCToolMaterials.sickle_set);
		cobalt_sickle = new DCItemSickle("cobalt_sickle", 1.0f, -0.3f, DCToolMaterials.sickle_cobalt, DCToolMaterials.sickle_set);

		// Epee
        beginner_sword = new DCItemSword("beginner_sword", DCToolMaterials.beginner_sword);
		// chafer_blade = new DCItemSword("chafer_blade", DCToolMaterials.beginner_sword);

		// Bouclier
		beginner_shield = new DCShield("beginner_shield");
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent e)
	{
		for ( Item item : items )
		{
			registerModel(item);
		}
	}

	private void registerModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(References.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
}
