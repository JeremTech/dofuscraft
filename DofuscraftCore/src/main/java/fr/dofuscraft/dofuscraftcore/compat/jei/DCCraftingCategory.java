package fr.dofuscraft.dofuscraftcore.compat.jei;

import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapedRecipes;
import fr.dofuscraft.dofuscraftcore.utils.References;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static fr.dofuscraft.dofuscraftcore.compat.jei.JEIPlugin.DC_CRAFTING;

public class DCCraftingCategory implements IRecipeCategory<DCCraftingWrapper> {

    private static final int craftOutputSlot = 25;
    private static final int craftInputSlot1 = 0;

    private final String localizedName;
    private final ICraftingGridHelper gridHelper;

    public static IDrawableStatic dofuscraft_crafting;

    public DCCraftingCategory(IGuiHelper guiHelper)
    {
        localizedName = I18n.format("crafting.dofuscraft");
        gridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);

        ResourceLocation location = new ResourceLocation("dofuscraftcore:textures/gui/dofuscraft_craftingtable_jei.png");
        dofuscraft_crafting = guiHelper.createDrawable(location, 7, 5, 158, 90);
    }

    @Override
    public String getUid() {
        return DC_CRAFTING;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return References.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return dofuscraft_crafting;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DCCraftingWrapper recipeWrapper, IIngredients ingredients)
    {

        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();



        guiItemStacks.init(craftOutputSlot, false, 133, 37);

        for (int y = 0; y < 5; ++y)
        {
            for (int x = 0; x < 5; ++x)
            {
                int index = craftInputSlot1 + x + (y * 5);
                guiItemStacks.init(index, true, (x * 18), (y * 18) );
            }
        }
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        if (recipeWrapper.recipe instanceof DCShapedRecipes)
        {
            try
            {
                int width = ((DCShapedRecipes) recipeWrapper.recipe).recipeWidth;
                int height = ((DCShapedRecipes) recipeWrapper.recipe).recipeHeight;

                    List<List<ItemStack>> newInputs = NonNullList.withSize(5 * 5, Collections.emptyList());
                    for (int i = 0; i < 5; i++)
                    {
                        for (int j = 0; j < 5; j++)
                        {
                            int index = i + j * 5;
                            int oldidx = i + j * width;
                            if (j < width)
                            {
                                newInputs.set(index, inputs.get(oldidx));
                            }
                        }
                    }
                    ingredients.setInputLists(VanillaTypes.ITEM, newInputs);
                    ingredients.setOutputLists(VanillaTypes.ITEM, ingredients.getOutputs(VanillaTypes.ITEM));


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            recipeLayout.setShapeless();
        }
        guiItemStacks.set(ingredients);

    }
}
