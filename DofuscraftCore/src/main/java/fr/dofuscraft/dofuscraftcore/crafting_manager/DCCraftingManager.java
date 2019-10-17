package fr.dofuscraft.dofuscraftcore.crafting_manager;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DCCraftingManager
{
    private static final DCCraftingManager INSTANCE = new DCCraftingManager();

    public static DCCraftingManager getInstance()
    {
        return INSTANCE;
    }

    private String job_require_p;
    private int level_require_p;
    private int job_xp_reward;

    /** La liste des recettes */
    private final List<IDCRecipe> recipes = Lists.<IDCRecipe>newArrayList();

    private DCCraftingManager()
    {
        // Possible job : alchemist, miner, peasant, lumberjack, jeweler, tailor, shoemaker

        Utils.getLogger().info("Register 5x5 recipes");

        this.addRecipe("alchemist", 1, 10, new ItemStack(ModItems.baker_yeast), "XY   ", "     ", "     ", "     ", "     ", 'X', ModItems.potable_water, 'Y', ModItems.magical_cure);
        this.addRecipe("alchemist", 1, 5, new ItemStack(ModItems.small_care_potion), "XX   ", "XX   ", "     ", "     ", "     ", 'X', ModItems.nettles_item);

        //this.addRecipe("lumberjack", 1, 7, new ItemStack(ModItems.agglomerated_planks), "YXXXY", "YXXXY", "     ", "     ", "     ", 'X', ModBlocks.log_ash, 'Y', ModItems.iron);
        //this.addRecipe("miner", 1, 12, new ItemStack(ModItems.ferrite), " XXX ", "XYYYX", "XYYYX", " XXX ", "     ", 'X', ModBlocks.log_ash, 'Y', ModItems.iron);

        this.addRecipe("peasant", 1, 5, new ItemStack(ModItems.incraftam_bread), "XX   ", "XX   ", "     ", "     ", "     ", 'X', ModItems.wheat);
        this.addRecipe("peasant", 10, 5, new ItemStack(ModItems.michette), "XXXXX", "     ", "     ", "     ", "     ", 'X', ModItems.wheat);

        this.addRecipe("alchemist", 1, 5, new ItemStack(ModItems.grobid_potion), "XY   ", "     ", "     ", "     ", "     ", 'X', ModItems.magical_cure, 'Y', ModItems.diaphane_petal);

    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public DCShapedRecipes addRecipe(String job_require, int job_level_require, int job_xp_reward, ItemStack result, Object... recipeComponents)
    {
        this.job_require_p = job_require;
        this.level_require_p = job_level_require;
        this.job_xp_reward = job_xp_reward;

        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
        if( recipeComponents[i] instanceof String[] )
        {
            String[] astring = (String[])((String[])recipeComponents[i++]);
            for ( String s2 : astring )
            {
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }
        else
        {
            while ( recipeComponents[i] instanceof String )
            {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        Character character;
        Map<Character, Object> components = Maps.<Character, Object>newHashMap();
        Object in;
        for ( ; i < recipeComponents.length; i += 2 )
        {
            in = recipeComponents[i + 1];
            Object component = null;
            character = (Character) recipeComponents[i];
            if( in instanceof Item )
            {
                component = new ItemStack((Item) recipeComponents[i + 1]);
            }
            else if( in instanceof Block )
            {
                component = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            }
            else if( in instanceof ItemStack )
            {
                component = (ItemStack) recipeComponents[i + 1];
            }
            else if( in instanceof String )
            {
                component = (String) in;
            }
            else
            {
                throw new IllegalArgumentException("Invalid shaped recipe: unknown type " + in.getClass().getName() + "!");
            }
            components.put(character, component);
        }
        ItemStack[] aitemstack = new ItemStack[j * k];
        char key;
        Object component;
        for ( int i1 = 0; i1 < j * k; ++i1 )
        {
            key = s.charAt(i1);
            if( components.containsKey(Character.valueOf(key)) )
            {
                component = components.get(Character.valueOf(key));
                if( component instanceof ItemStack )
                {
                    aitemstack[i1] = ((ItemStack) component).copy();
                }
                else
                {
                    aitemstack[i1] = ItemStack.EMPTY;
                }
            }
            else
            {
                aitemstack[i1] = ItemStack.EMPTY;
            }
        }
        DCShapedRecipes shapedrecipes = new DCShapedRecipes(j, k, aitemstack, result, job_require, job_level_require, job_xp_reward);
        Utils.getLogger().info("Add 5x5 recipe for : " + result.getUnlocalizedName());
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.

     public void addShapelessRecipe(ItemStack result, Object... recipeComponents)
     {
     List list = Lists.newArrayList();
     for (Object component : recipeComponents) //Pour chaque composant de la recette
     {
     if (component instanceof ItemStack)
     {
     list.add(((ItemStack)component).copy());
     }
     else if (component instanceof Item)
     {
     list.add(new ItemStack((Item)component));
     }
     else if(component instanceof Block)
     {
     list.add(new ItemStack((Block)component));
     }
     else if(component instanceof String) //Pour l'ore dictionnary
     {
     list.add(component);
     }
     else throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + component.getClass().getName() + "!");
     }
     this.recipes.add(new DCShapelessRecipes(result, list));
     }**/

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IDCRecipe recipe)
    {
        this.recipes.add(recipe);
    }

    /**
     * Retourne le résultat de la recette ou null si il n'y en a aucun
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {

        for (IDCRecipe irecipe : this.recipes) //Pour chaque recette
        {
            if (irecipe.CraftMatches(craftMatrix, worldIn)) //Si elle correspond à la matrice actuelle
            {
                this.job_require_p = irecipe.getJobRequire();
                this.level_require_p = irecipe.getJobLevelRequire();

                if(irecipe.matches(craftMatrix, worldIn, irecipe.getJobRequire(), irecipe.getJobLevelRequire()))
                {
                    return irecipe.getCraftingResult(craftMatrix); //On donne son résultat
                }

                return ItemStack.EMPTY;
            }

        }

        this.job_require_p =  "";
        this.level_require_p = 0;

        return ItemStack.EMPTY;

    }

    @Nullable
    public IDCRecipe getMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {

        for (IDCRecipe irecipe : this.recipes) //Pour chaque recette
        {
            if (irecipe.CraftMatches(craftMatrix, worldIn)) //Si elle correspond à la matrice actuelle
            {
                this.job_require_p = irecipe.getJobRequire();
                this.level_require_p = irecipe.getJobLevelRequire();

                if(irecipe.matches(craftMatrix, worldIn, irecipe.getJobRequire(), irecipe.getJobLevelRequire()))
                {
                    return irecipe; //On donne son résultat
                }

                return null;
            }

        }

        this.job_require_p =  "";
        this.level_require_p = 0;

        return null;

    }



    /**
     * Retourne les items retants après un craft
     */
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IDCRecipe irecipe : this.recipes) //Pour chaque recette
        {
            if (irecipe.matches(craftMatrix, worldIn, irecipe.getJobRequire(), irecipe.getJobLevelRequire())) //Si elle correspond à la matrice actuelle
            {
                return irecipe.getRemainingItems(craftMatrix); //On retourne les items restants
            }
        }
        NonNullList<ItemStack> aitemstack = NonNullList.withSize(craftMatrix.getSizeInventory(), new ItemStack(Blocks.AIR));
        for (int i = 0; i < craftMatrix.getSizeInventory(); ++i)
        {
            aitemstack.set(i, craftMatrix.getStackInSlot(i));
        }
        return aitemstack; //Si ça ne correspond à aucune recette, on retourne tous les items qui sont présents dans la matrice
    }

    public List<IDCRecipe> getRecipeList()
    {
        return this.recipes;
    }

    public String getJobRequireP()
    {
        return this.job_require_p;
    }

    public int getLvelRequireP()
    {
        return this.level_require_p;
    }
}
