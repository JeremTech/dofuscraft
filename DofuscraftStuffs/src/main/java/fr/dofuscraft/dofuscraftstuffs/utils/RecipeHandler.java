package fr.dofuscraft.dofuscraftstuffs.utils;

import fr.dofuscraft.dofuscraftcore.crafting_manager.DCCraftingManager;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import fr.dofuscraft.dofuscraftstuffs.init.ModArmors;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeHandler {



    public static void registerRecipe(){

        DCCraftingManager DCCmanager = DCCraftingManager.getInstance();


        // Level XP Range [ lvl+2 ; (lvl+2)*2 ]

        DCCmanager.addRecipe("tailor", 8, 13, new ItemStack(ModArmors.antome_cap), "YXY  ", "YXY  ", "     ", "     ", "     ", 'X', ModItems.internal_fire, 'Y', ModItems.diaphane_petal);
        DCCmanager.addRecipe("jeweler", 7, 18, new ItemStack(ModArmors.animulette), "YXY  ", "YXY  ", "     ", "     ", "     ", 'X', ModItems.chafer_bone, 'Y', ModItems.incraftam_relic);

        DCCmanager.addRecipe("shoemaker", 8, 10, new ItemStack(ModArmors.forcesque_belt), "YXY  ", "XYX  ", "     ", "     ", "     ", 'X', ModItems.eternal_ash, 'Y', ModBlocks.log_ash);

        DCCmanager.addRecipe("shoemaker", 1, 3, new ItemStack(ModArmors.luck_belt), "XYX  ", "YXY  ", "     ", "     ", "     ", 'X', ModItems.nettles_item, 'Y', ModItems.internal_fire);

        DCCmanager.addRecipe("shoemaker", 1, 3, new ItemStack(ModArmors.strength_boots), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.diaphane_petal, 'Y', ModItems.chapardam_tail);

        DCCmanager.addRecipe("shoemaker", 1, 4, new ItemStack(ModArmors.bottananas), "YYYY ", "XXXX ", "     ", "     ", "     ", 'X', ModItems.nettles_item, 'Y', ModItems.eternal_ash);

        DCCmanager.addRecipe("shoemaker", 8, 13, new ItemStack(ModArmors.inlays_boots), "XX   ", "YY   ", "     ", "     ", "     ", 'X', ModItems.diaphane_petal, 'Y', ModBlocks.log_ash);

        DCCmanager.addRecipe("shoemaker", 1, 3, new ItemStack(ModArmors.spamette_belt), "XX   ", "YY   ", "     ", "     ", "     ", 'X', ModItems.gloot_skin, 'Y', ModItems.nettles_item);

        DCCmanager.addRecipe("shoemaker", 1, 3, new ItemStack(ModArmors.kamatongues), "XXX  ", "YYY  ", "     ", "     ", "     ", 'X', ModItems.diaphane_petal, 'Y', ModItems.chafer_bone);

        DCCmanager.addRecipe("shoemaker", 1, 3, new ItemStack(ModArmors.kamature), "XYX  ", "YXY  ", "     ", "     ", "     ", 'X', ModItems.internal_fire, 'Y', ModItems.incraftam_relic);

        DCCmanager.addRecipe("shoemaker", 2, 6, new ItemStack(ModArmors.fecaflip), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.chimeric_feather, 'Y', ModItems.diaphane_petal);

        //sandale tofuesque
        DCCmanager.addRecipe("shoemaker", 3, 8, new ItemStack(ModArmors.tofuesques_sandals), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.nettles_item, 'Y', ModItems.chapardam_tail);
        //bottes du chat buté
        DCCmanager.addRecipe("shoemaker", 4, 9, new ItemStack(ModArmors.buted_cat_boots), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModBlocks.log_ash, 'Y', ModItems.bouftou_spit);
        //botte du bouftou
        DCCmanager.addRecipe("shoemaker", 4, 9, new ItemStack(ModArmors.bouftou_boots), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.chimeric_feather, 'Y', ModItems.chapardam_tail);
        //Einekeineux
        DCCmanager.addRecipe("shoemaker", 5, 10, new ItemStack(ModArmors.einekeineux), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.celestial_wool, 'Y', ModItems.chapardam_tail);
        //Sandales d'intelligence
        DCCmanager.addRecipe("shoemaker", 6, 10, new ItemStack(ModArmors.intelligence_sandals), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.eternal_ash, 'Y', ModItems.chimeric_feather);
        //Bottes Agilesques
        DCCmanager.addRecipe("shoemaker", 6, 10, new ItemStack(ModArmors.agile_boots), "XY   ", "XY   ", "XY   ", "     ", "     ", 'X', ModItems.eternal_ash, 'Y', ModItems.celestial_wool);
        //Bottes de Kluh
        DCCmanager.addRecipe("shoemaker", 7, 12, new ItemStack(ModArmors.kluh_boots), "XYX  ", " X   ", "     ", "     ", "     ", 'X', ModItems.gloot_skin, 'Y', ModItems.iron);
        //Bottes de chance
        DCCmanager.addRecipe("shoemaker", 7, 13, new ItemStack(ModArmors.boots_of_luck), "XYX  ", " X   ", "     ", "     ", "     ", 'X', ModItems.chimeric_feather, 'Y', ModItems.iron);

        DCCmanager.addRecipe("jeweler", 1, 3, new ItemStack(ModArmors.nail_necklace), "  Y  ", " YXY ", "     ", "     ", "     ", 'X', ModItems.iron, 'Y', ModItems.magical_cure);
        //DCCmanager.addRecipe("jeweler", 8, 10, new ItemStack(ModArmors.smesme), "XX   ", "YY   ", "     ", "     ", "     ", 'X', ModItems.chimeric_feather, 'Y', ModBlocks.log_ash);
        DCCmanager.addRecipe("jeweler", 3, 7, new ItemStack(ModArmors.puny_owl_pendant), " XYX ", " YXY ", "     ", "     ", "     ", 'X', ModItems.magical_cure, 'Y', ModItems.diaphane_petal);
        DCCmanager.addRecipe("jeweler", 5, 8, new ItemStack(ModArmors.bouftou_tear), " X X ", "  X  ", " Y Y ", "  Y  ", "     ", 'X', ModItems.bouftou_spit, 'Y', ModItems.internal_fire);
        //DCCmanager.addRecipe("jeweler", 8, 10, new ItemStack(ModArmors.sram_amulet), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.magical_cure, 'Y', ModItems.aminite_volve);
        DCCmanager.addRecipe("jeweler", 1, 2, new ItemStack(ModArmors.adventurer_amulet), "  Y  ", " Y Y ", "  X  ", "     ", "     ", 'X', ModItems.magical_cure, 'Y', ModItems.agglomerated_planks);
        DCCmanager.addRecipe("jeweler", 7, 16, new ItemStack(ModArmors.animulette), "  X  ", " Y Y ", " Y Y ", "  X  ", "     ", 'X', ModItems.chafer_bone, 'Y', ModItems.incraftam_relic);
        DCCmanager.addRecipe("jeweler", 2, 6, new ItemStack(ModArmors.owl_amulet), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.incraftam_relic, 'Y', ModItems.bouftou_spit);
        DCCmanager.addRecipe("jeweler", 2, 6, new ItemStack(ModArmors.bear_amulet), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.eternal_ash, 'Y', ModItems.incraftam_relic);
        DCCmanager.addRecipe("jeweler", 2, 6, new ItemStack(ModArmors.wolf_amulet), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModBlocks.log_ash, 'Y', ModItems.incraftam_relic);

        DCCmanager.addRecipe("tailor", 1, 3, new ItemStack(ModArmors.louffeur), "     ", " X  Y ", "  X Y", " X Y ", "     ", 'X', ModItems.chapardam_tail, 'Y', ModItems.internal_fire);
        DCCmanager.addRecipe("tailor", 1, 4, new ItemStack(ModArmors.lisseur), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.magical_cure, 'Y', ModItems.eternal_ash);
        DCCmanager.addRecipe("tailor", 1, 3, new ItemStack(ModArmors.komintot_headband), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModBlocks.log_ash, 'Y', ModItems.celestial_wool);
        DCCmanager.addRecipe("tailor", 1, 4, new ItemStack(ModArmors.vitality_headband), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.potable_water, 'Y', ModItems.internal_fire);
        DCCmanager.addRecipe("tailor", 1, 4, new ItemStack(ModArmors.pandawushu_headband), " X X ", "  Y  ", " Y Y ", "  X  ", "     ", 'X', ModItems.diaphane_petal, 'Y', ModItems.gloot_skin);
        DCCmanager.addRecipe("tailor", 1, 3, new ItemStack(ModArmors.floude), "XX   ", "YY   ", "     ", "     ", "     ", 'X', ModBlocks.nettle, 'Y', ModItems.celestial_wool);
        DCCmanager.addRecipe("tailor", 11, 20, new ItemStack(ModArmors.wouaf_cap), "XYX  ", "YXY  ", "     ", "     ", "     ", 'X', ModItems.gloot_skin, 'Y', ModItems.aminite_volve);

        //DCCmanager.addRecipe("miner", 7, new ItemStack(ModArmors.animulette), new Object[]{"XX", "YY", "XX", 'X', Items.LEATHER, 'Y', Items.IRON_INGOT});




    }

}
