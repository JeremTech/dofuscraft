package fr.dofuscraft.dofuscraftcore.crafting_manager;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DCShapelessRecipes implements IRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;
    /** Is a List of ItemStack that composes the recipe. */
    public final List recipeItems;

    public DCShapelessRecipes(ItemStack output, List inputList)
    {
        this.recipeOutput = output;
        this.recipeItems = inputList;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }


    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> aitemstack = NonNullList.create();
        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            aitemstack.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }
        return aitemstack;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     * Retourne true si la recette correspond à la matrice donnée (le craft que le joueur a fait)
     *
     * La fonction prend une liste des items requis pour le craft, puis pour chaque item rencontré dans la matrice, le retire de la liste des items,
     * si la liste ne contient pas l'item, c'est qu'il y a un item en trop dans le craft et la fonction retourne false, à la fin, si la liste est vide, la
     * fonction retourne true.
     */
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        ArrayList arraylist = Lists.newArrayList(this.recipeItems); //Copie les items du craft dans une nouvelle liste
        for (int i = 0; i < inv.getHeight(); ++i)
        {
            for (int j = 0; j < inv.getWidth(); ++j)
            {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);
                if (itemstack != null)
                {
                    boolean flag = false;
                    for(Object component : arraylist)
                    {
                        if(component instanceof String) //Search in ore dictionary
                        {
                            List<ItemStack> stacks = OreDictionary.getOres((String) component);
                            for(ItemStack itemstack1 : stacks)
                            {
                                if (DCShapedRecipes.areItemStacksEquals(itemstack1, itemstack))
                                {
                                    flag = true;
                                    arraylist.remove(itemstack1);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            ItemStack itemstack1 = (ItemStack)component;
                            if (DCShapedRecipes.areItemStacksEquals(itemstack1, itemstack))
                            {
                                flag = true;
                                arraylist.remove(itemstack1);
                                break;
                            }
                        }
                    }
                    if (!flag)
                        return false;
                }
            }
        }
        return arraylist.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name)
    {
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(References.MODID, "dcshapelessrecipes");
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return IRecipe.class;
    }
}
