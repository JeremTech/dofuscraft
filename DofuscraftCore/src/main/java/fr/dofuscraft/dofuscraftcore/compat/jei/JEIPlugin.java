package fr.dofuscraft.dofuscraftcore.compat.jei;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.container.DCCraftingTableContainer;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCCraftingManager;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapedRecipes;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapelessRecipes;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCCraftingTable;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import mezz.jei.api.*;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;
import java.util.List;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin
{
    public static final String DC_CRAFTING = "Dofuscraft.Crafting";

    public static IJeiHelpers jeiHelpers;
    public static IDrawableStatic dc_crafting;
    public static IDrawableStatic static_singularity;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(new DCCraftingCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry)
    {
        jeiHelpers = registry.getJeiHelpers();

        //region GetInfo

        //endregion

        registry.addRecipes(DCCraftingManager.getInstance().getRecipeList(), DC_CRAFTING);
        registry.handleRecipes(DCShapedRecipes.class, DCCraftingWrapper::new, DC_CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.dofuscraft_crafting_table), DC_CRAFTING);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(DCCraftingTableContainer.class, DC_CRAFTING, 1, 25, 25, 36);
        registry.addRecipeClickArea(GuiDCCraftingTable.class, 128, 77, 21, 14, DC_CRAFTING);

    }

}
