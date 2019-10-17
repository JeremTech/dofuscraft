package fr.dofuscraft.dofuscraftcore.crafting_manager;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IDCRecipe extends IForgeRegistryEntry<IDCRecipe>
{
    /**
     * Used to check if a recipe matches current crafting inventory and player's "job" "level"
     */
    boolean matches(InventoryCrafting inventory, World world, String job, int level);

    boolean CraftMatches(InventoryCrafting inventory, World world);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCrafting inv);

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    boolean canFit(int width, int height);

    ItemStack getRecipeOutput();

    String getJobRequire();

    int getJobLevelRequire();

    int getJobXpReward();

    default NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        return net.minecraftforge.common.ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    default NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.<Ingredient>create();
    }

    default boolean isDynamic()
    {
        return false;
    }

    default String getGroup()
    {
        return "";
    }
}
