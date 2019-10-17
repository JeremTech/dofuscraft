package fr.dofuscraft.dofuscraftstuffs.init;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftstuffs.DofuscraftStuffs;
import fr.dofuscraft.dofuscraftstuffs.items.DCItemArmor;
import fr.dofuscraft.dofuscraftstuffs.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ModArmors
{
	public static final ModArmors INSTANCE = new ModArmors();

	// Example : public static ItemArmor[] testArmor = new ItemArmor[4];

	public List<ItemArmor> armors;

	// Cordonnier
	public static ItemArmor forcesque_belt, luck_belt, strength_boots, bottananas,inlays_boots, spamette_belt, kamatongues, kamature, fecaflip, tofuesques_sandals, buted_cat_boots, bouftou_boots, einekeineux,
	intelligence_sandals, agile_boots, kluh_boots, boots_of_luck;

 	// Bijoutier
	public static ItemArmor nail_necklace/*, smesme*/, puny_owl_pendant, bouftou_tear/*, sram_amulet*/, adventurer_amulet, animulette, owl_amulet, bear_amulet, wolf_amulet;

	// Tailleur
	public static ItemArmor louffeur, floude, lisseur, komintot_headband, vitality_headband, pandawushu_headband, antome_cap, wouaf_cap;

	public void init()
	{
		armors = Lists.newArrayList();

		// Cordonnier
		forcesque_belt = new DCItemArmor("forcesque_belt", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.LEGS, "forcesque_belt");
		luck_belt = new DCItemArmor("luck_belt", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.LEGS, "luck_belt");
		strength_boots = new DCItemArmor("strength_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "strength_boots");
		bottananas = new DCItemArmor("bottananas", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "bottananas");
		inlays_boots = new DCItemArmor("inlays_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "inlays_boots");
		spamette_belt = new DCItemArmor("spamette_belt", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.LEGS, "spamette_belt");
		kamatongues = new DCItemArmor("kamatongues", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "kamatongues");
		kamature = new DCItemArmor("kamature_belt", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.LEGS, "kamature");
		fecaflip = new DCItemArmor("fecaflip", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "fecaflip");
		tofuesques_sandals = new DCItemArmor("tofuesques_sandals", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "tofuesques_sandals");
		buted_cat_boots = new DCItemArmor("buted_cat_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "buted_cat_boots");
		bouftou_boots = new DCItemArmor("bouftou_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "bouftou_boots");
		einekeineux = new DCItemArmor("einekeineux", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "einekeineux");
		intelligence_sandals = new DCItemArmor("intelligence_sandals", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "intelligence_sandals");
		agile_boots = new DCItemArmor("agile_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "agile_boots");
		kluh_boots = new DCItemArmor("kluh_boots", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "kluh_boots");
		boots_of_luck = new DCItemArmor("boots_of_luck", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.FEET, "boots_of_luck");

		// Bijoutier
		nail_necklace = new DCItemArmor("nail_necklace", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "nail_necklace");
		//smesme = new DCItemArmor("smesme", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "smesme");
		puny_owl_pendant = new DCItemArmor("puny_owl_pendant", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "puny_owl_pendant");
		bouftou_tear = new DCItemArmor("bouftou_tear", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "bouftou_tear");
		//sram_amulet = new DCItemArmor("sram_amulet", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "sram_amulet");
		adventurer_amulet = new DCItemArmor("adventurer_amulet", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "adventurer_amulet");
		animulette = new DCItemArmor("animulette", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "animulette");
		owl_amulet = new DCItemArmor("owl_amulet", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "owl_amulet");
		bear_amulet = new DCItemArmor("bear_amulet", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "bear_amulet");
		wolf_amulet = new DCItemArmor("wolf_amulet", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST, "wolf_amulet");

		// Tailleur
		louffeur = new DCItemArmor("louffeur", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "headband/louffeur");
		floude = new DCItemArmor("floude", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "floude");
		lisseur = new DCItemArmor("lisseur", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "headband/lisseur");
		komintot_headband = new DCItemArmor("komintot_headband", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "headband/komintot_headband");
		vitality_headband = new DCItemArmor("vitality_headband", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "headband/vitality_headband");
		pandawushu_headband = new DCItemArmor("pandawushu_headband", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "headband/pandawushu_headband");
		antome_cap = new DCItemArmor("antome_cap", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "antome_cap");
		wouaf_cap = new DCItemArmor("wouaf_cap", ItemArmor.ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD, "wouaf_cap");
	}

	@SubscribeEvent
	public void registerArmorsRenders(ModelRegistryEvent e)
	{
		for ( int i = 0; i < 4; i++ )
		{
			//  Example : registerArmorRender(testArmor[i]);
		}

		// Cordonnier
		registerArmorRender(forcesque_belt);
		registerArmorRender(luck_belt);
		registerArmorRender(strength_boots);
		registerArmorRender(bottananas);
		registerArmorRender(inlays_boots);
		registerArmorRender(spamette_belt);
		registerArmorRender(kamatongues);
		registerArmorRender(kamature);
		registerArmorRender(fecaflip);
		registerArmorRender(tofuesques_sandals);
		registerArmorRender(buted_cat_boots);
		registerArmorRender(bouftou_boots);
		registerArmorRender(einekeineux);
		registerArmorRender(intelligence_sandals);
		registerArmorRender(agile_boots);
		registerArmorRender(kluh_boots);
		registerArmorRender(boots_of_luck);

		// Bijoutier
		registerArmorRender(nail_necklace);
		//registerArmorRender(smesme);
		registerArmorRender(puny_owl_pendant);
		registerArmorRender(bouftou_tear);
		//registerArmorRender(sram_amulet);
		registerArmorRender(adventurer_amulet);
		registerArmorRender(animulette);
		registerArmorRender(owl_amulet);
		registerArmorRender(bear_amulet);
		registerArmorRender(wolf_amulet);

		// Tailleur
		registerArmorRender(louffeur);
		registerArmorRender(floude);
		registerArmorRender(lisseur);
		registerArmorRender(komintot_headband);
		registerArmorRender(vitality_headband);
		registerArmorRender(pandawushu_headband);
		registerArmorRender(antome_cap);
		registerArmorRender(wouaf_cap);
	}

	private void registerArmorRender(Item itemArmor)
	{
		ModelLoader.setCustomModelResourceLocation(itemArmor, 0, new ModelResourceLocation(new ResourceLocation(References.MODID, itemArmor.getUnlocalizedName().substring(5)), "inventory"));
		DofuscraftStuffs.logger.info(itemArmor.getUnlocalizedName().substring(5) + " is rendered !");
	}

	private void createArmor(Item[] items, ItemArmor.ArmorMaterial armorMaterial, String armorName, int renderIndex)
	{
		if( items.length == 4 )
		{
			items[0] = new DCItemArmor(armorName + "_helmet", armorMaterial, renderIndex, EntityEquipmentSlot.HEAD, armorName);
			items[1] = new DCItemArmor(armorName + "_chestplate", armorMaterial, renderIndex, EntityEquipmentSlot.CHEST, armorName);
			items[2] = new DCItemArmor(armorName + "_leggings", armorMaterial, renderIndex, EntityEquipmentSlot.LEGS, armorName);
			items[3] = new DCItemArmor(armorName + "_boots", armorMaterial, renderIndex, EntityEquipmentSlot.FEET, armorName);
		}
		else
		{
			DofuscraftStuffs.logger.error(items.toString() + " [ Length is longer or shorter than 4 ]");
			return;
		}
	}
}
