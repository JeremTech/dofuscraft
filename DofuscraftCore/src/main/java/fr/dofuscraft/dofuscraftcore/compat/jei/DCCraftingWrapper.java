package fr.dofuscraft.dofuscraftcore.compat.jei;

import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapedRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.BossInfo;

import java.awt.*;
import java.util.List;

public class DCCraftingWrapper implements IRecipeWrapper {

    protected final DCShapedRecipes recipe;

    public DCCraftingWrapper(DCShapedRecipes recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getInputsItems());
        System.out.print(recipe.getRecipeOutput().toString());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    protected List<List<ItemStack>> getRecipeInputs(DCShapedRecipes recipe)
    {
        return JEIPlugin.jeiHelpers.getStackHelper().expandRecipeItemStackInputs(recipe.getIngredients());
    }

    protected ItemStack getRecipeOutput(DCShapedRecipes recipe)
    {
        return recipe.getRecipeOutput();
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

        minecraft.fontRenderer.drawString(I18n.format("dc.container.craftingtable.job") + " " +I18n.format("job." + recipe.getJobRequire()), 95, 5, Color.WHITE.getRGB());
        minecraft.fontRenderer.drawString(I18n.format("dc.container.craftingtable.level") + " " +"" + recipe.getJobLevelRequire(), 95, 15, Color.WHITE.getRGB());
        minecraft.fontRenderer.drawString(recipe.getJobXpReward() + " " + I18n.format("dc.container.craftingtable.xp"), 128, 62, Color.WHITE.getRGB());


    }
}
