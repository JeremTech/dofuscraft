package fr.dofuscraft.dofuscraftcore.init;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.items.*;
import fr.dofuscraft.dofuscraftcore.spells.eniripsa.AlternativeWordSpell;
import fr.dofuscraft.dofuscraftcore.spells.eniripsa.FearWordSpell;
import fr.dofuscraft.dofuscraftcore.spells.eniripsa.InjuringWordSpell;
import fr.dofuscraft.dofuscraftcore.spells.eniripsa.PreventingWordSpell;
import fr.dofuscraft.dofuscraftcore.spells.feca.BlindnessSpell;
import fr.dofuscraft.dofuscraftcore.spells.feca.NaturalAttackSpell;
import fr.dofuscraft.dofuscraftcore.spells.feca.RampartSpell;
import fr.dofuscraft.dofuscraftcore.spells.feca.StaminaSpell;
import fr.dofuscraft.dofuscraftcore.spells.iop.DeferlementSpell;
import fr.dofuscraft.dofuscraftcore.spells.iop.DivinSwordSpell;
import fr.dofuscraft.dofuscraftcore.spells.iop.JumpSpell;
import fr.dofuscraft.dofuscraftcore.spells.iop.PressionSpell;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import fr.dofuscraft.dofuscraftcore.utils.References;
import mezz.jei.api.IModRegistry;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ModItems
{

	public static final ModItems INSTANCE = new ModItems();

	// Books
	public static DCItemBook alchemist_book, lumberjack_book, miner_book, peasant_book, jeweler_book, shoemaker_book, tailor_book;

	// Kamas
	public static Item kamas, kamas10, kamas100, kamas1000, kamas10000, kamas100000;

	// Mobs Drops - Incraftam
	public static Item aminite_volve, mushroom, intangible_meat, celestial_wool, bouftou_spit, internal_fire, eternal_ash, incraftam_relic, chafer_bone, chapardam_tail, gloot_skin;
	public static Item potable_water, kardorim_skull, diaphane_petal, magical_cure, tofu_egg, chimeric_feather, milimulou_hair, milimulou_scalp, intangible_bidoche, frelated_meat;

	// Crops
	public static Item wheat, barley, oats, hop, flax, rye, hemp;

	// Plants
	public static Item five_leaf_clover_item, edelweiss_item, freyesque_orchid_item, nettles_item, sage_item, wild_mint_item;

	// Ores
	public static Item iron, copper, bronze, cobalt, tin;

	//region Alchemist

	public static Item baker_yeast;
	public static Item small_care_potion, grobid_potion;
	public static Item agglomerated_planks;

	//endregion


	public static Item ferrite;

	// Class items
	public static DCItemSpell MasdaigatSword, Eniripsa, fecaShield;

	public static DCItemFood incraftam_bread, michette;

	private List<Item> items;

	public void init()
	{
		items = Lists.newArrayList();

		baker_yeast = new DCItem("baker_yeast");
		small_care_potion = new DCItemMagicFood("small_care_potion", 0, false, true, EnumAction.DRINK, new PotionEffect(Potion.getPotionById(6),1, 0));
		agglomerated_planks = new DCItem("agglomerated_planks");
		ferrite = new DCItem("ferrite");
		grobid_potion = new DCItem("grobid_potion");

		//region Books
		alchemist_book = new DCItemBook("alchemist_book", "alchemist.json");
		//test.addDescription(new ItemStack(alchemist_book), "test");
		lumberjack_book = new DCItemBook("lumberjack_book", "lumberjack.json");
		miner_book = new DCItemBook("miner_book", "miner.json");
		peasant_book = new DCItemBook("peasant_book", "farmer.json");
		jeweler_book = new DCItemBook("jeweler_book", "null");
		shoemaker_book = new DCItemBook("shoemaker_book", "null");
		tailor_book = new DCItemBook("tailor_book","null");
		//endregion

		//region Kamas
		kamas = new DCItem("kamas");
		kamas10 = new DCItem("kamas10");
		kamas100 = new DCItem("kamas100");
		kamas1000 = new DCItem("kamas1k");
		kamas10000 = new DCItem("kamas10k");
		kamas100000 = new DCItem("kamas100k");
		//endregion

		//region Mobs Drops - Incraftam
		aminite_volve = new DCItem("aminite_volve");
		mushroom = new DCItem("mushroom");
		intangible_meat = new DCItem("intangible_meat");
		celestial_wool = new DCItem("celestial_wool");
		bouftou_spit = new DCItem("bouftou_spit");
		internal_fire = new DCItem("internal_fire");
		eternal_ash = new DCItem("eternal_ash");
		incraftam_relic = new DCItem("incraftam_relic");
		chafer_bone = new DCItem("chafer_bone");
		chapardam_tail = new DCItem("chapardam_tail");
		gloot_skin = new DCItem("gloot_skin");
		potable_water = new DCItem("potable_water");
		kardorim_skull = new DCItem("kardorim_skull");
		diaphane_petal = new DCItem("diaphane_petal");
		magical_cure = new DCItem("magical_cure");
		tofu_egg = new DCItem("tofu_egg");
		chimeric_feather = new DCItem("chimeric_feather");
		milimulou_hair = new DCItem("milimulou_hair");
		milimulou_scalp = new DCItem("milimulou_scalp");
		intangible_bidoche = new DCItem("intangible_bidoche");
		frelated_meat = new DCItem("frelated_meat");
		//endregion

		//region Crops
		wheat = new DCItem("wheat");
		barley = new DCItem("barley");
		oats = new DCItem("oats");
		hop = new DCItem("hop");
		flax = new DCItem("flax");
		rye = new DCItem("rye");
		hemp = new DCItem("hemp");
		//endregion

		//region Plants
		five_leaf_clover_item = new DCItem("five_leaf_clover_item");
		edelweiss_item = new DCItem("edelweiss_item");
		freyesque_orchid_item = new DCItem("freyesque_orchid_item");
		nettles_item = new DCItem("nettles_item");
		sage_item = new DCItem("sage_item");
		wild_mint_item = new DCItem("wild_mint_item");
		//endregion

		//region Ores
		iron = new DCItem("iron");
		copper = new DCItem("copper");
		bronze = new DCItem("bronze");
		cobalt = new DCItem("cobalt");
		tin = new DCItem("tin");
		//endregion

		//region Food
		incraftam_bread = new DCItemFood("incraftam_bread", 5, false);
		michette = new DCItemFood("michette", 5, false);
		//endregion

		//region
		MasdaigatSword = new DCItemSpell("masdaigat_sword", PressionSpell.INSTANCE, JumpSpell.INSTANCE, DeferlementSpell.INSTANCE, DivinSwordSpell.INSTANCE);
		Eniripsa = new DCItemSpell("eniripsa_potion", InjuringWordSpell.INSTANCE, AlternativeWordSpell.INSTANCE, FearWordSpell.INSTANCE, PreventingWordSpell.INSTANCE);
		fecaShield = new DCItemSpell("feca_shield", BlindnessSpell.INSTANCE, NaturalAttackSpell.INSTANCE, RampartSpell.INSTANCE, StaminaSpell.INSTANCE);

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
        Utils.getLogger().info("Register item model : " + item.getUnlocalizedName());
	}

	public List<Item> getItems()
	{
		return items;
	}
}
