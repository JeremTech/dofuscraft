package fr.dofuscraft.dofuscraftcore.crafting_manager;

import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.container.DCCraftingTableContainer;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class DCShapedRecipes implements IDCRecipe
{
    /** How many horizontal slots this recipe is wide. */
    public final int recipeWidth;
    /** How many vertical slots this recipe uses. */
    public final int recipeHeight;
    /** Is a array of ItemStack that composes the recipe. */
    public final ItemStack[] recipeItems;
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;
    private boolean copyIngredientNBT;
    /** Job and level **/
    protected int level_require;
    protected String job_require;
    protected int job_xp_reward;
    public EntityPlayer player;
    public static boolean isCrafted;

    public static String matchesS;

    public DCShapedRecipes(int width, int height, ItemStack[] items, ItemStack output, String job, int level, int job_xp_reward)
    {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = items;
        this.recipeOutput = output;
        this.level_require = level;
        this.job_require = job;
        this.job_xp_reward = job_xp_reward;
        Utils.getLogger().info("Register recipe for " + output.getUnlocalizedName() + " for job " + job + " level " + level);
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    @Override
    public int getJobXpReward()
    {
        return job_xp_reward;
    }

    @Override
    public String getJobRequire()
    {
        return this.job_require;
    }

    @Override
    public int getJobLevelRequire()
    {
        return this.level_require;
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);

            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     * Retourne true si la recette correspond à la matrice donnée (le craft que le joueur a fait)
     */
    public boolean matches(InventoryCrafting inv, World worldIn, String job, int level)
    {
        System.out.println("" + job + " level " + level);

        Field f = ReflectionHelper.findField(InventoryCrafting.class, "eventHandler", "field_70465_c");

        try
        {
            Container container = (Container) f.get(inv);

            if (container instanceof DCCraftingTableContainer)
            {
                this.player = ((DCCraftingTableContainer) container).getPlayer();
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inv, i, j, true))
                {
                    return checkJobLevel(job, level);
                }
                if (this.checkMatch(inv, i, j, false))
                {
                    return checkJobLevel(job, level);
                }
            }
        }

        this.matchesS = "false";
        return false;
    }

    public boolean CraftMatches(InventoryCrafting inv, World worldIn)
    {
        Field f = ReflectionHelper.findField(InventoryCrafting.class, "eventHandler", "field_70465_c");

        try
        {
            Container container = (Container) f.get(inv);

            if (container instanceof DCCraftingTableContainer)
            {
                this.player = ((DCCraftingTableContainer) container).getPlayer();
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j)
            {
                if (this.checkMatch(inv, i, j, true))
                {
                    return true;
                }
                if (this.checkMatch(inv, i, j, false))
                {
                    return true;
                }
            }
        }

        this.matchesS = "false";
        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     * Compare deux parties du craft
     */
    private boolean checkMatch(InventoryCrafting inv, int regionX, int regionY, boolean mirror)
    {
        for (int x = 0 ; x < inv.getWidth() ; ++x)
        {
            for (int y = 0 ; y < inv.getHeight() ; ++y)
            {
                int x1 = x - regionX;
                int y1 = y - regionY;
                Object patternStack = null;
                if (x1 >= 0 && y1 >= 0 && x1 < this.recipeWidth && y1 < this.recipeHeight)
                {
                    if (mirror)
                        patternStack = this.recipeItems[this.recipeWidth - x1 - 1 + y1 * this.recipeWidth];
                    else
                        patternStack = this.recipeItems[x1 + y1 * this.recipeWidth];
                    if(patternStack instanceof String)
                    {
                        List<ItemStack> stacks = OreDictionary.getOres((String) patternStack);
                        boolean matches = false;
                        for(ItemStack stack : stacks)
                        {
                            if(areItemStacksEquals(stack, inv.getStackInRowAndColumn(x, y))) //If the pattern's stack doesn't match with the stack in the inv crafting
                            {
                                matches = true;
                            }
                        }
                        if(!matches)
                            return false;
                    }
                    else if(!areItemStacksEquals((ItemStack) patternStack, inv.getStackInRowAndColumn(x, y)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Compare les deux stacks
     */
    public static boolean areItemStacksEquals(ItemStack stack1, ItemStack stack2)
    {
        if(stack1 == null || stack2 == null) return stack1 == stack2;
        return stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == OreDictionary.WILDCARD_VALUE || stack1.getMetadata() == stack2.getMetadata());
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack itemstack = this.getRecipeOutput().copy();
        if (this.copyIngredientNBT)
        {
            for (int i = 0; i < inv.getSizeInventory(); ++i)
            {
                ItemStack itemstack1 = inv.getStackInSlot(i);
                if (itemstack1 != null && itemstack1.hasTagCompound())
                {
                    itemstack.setTagCompound((NBTTagCompound)itemstack1.getTagCompound().copy());
                }
            }
        }
        return itemstack;
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
        return this.recipeWidth * this.recipeHeight;
    }



    /**
     * Set this crafting recipe to copy the NBT tag compound of the last ItemStack that has one in the crafting table.
     */
    public DCShapedRecipes setCopyIngredientNBT()
    {
        this.copyIngredientNBT = true;
        return this;
    }

    @Override
    public IDCRecipe setRegistryName(ResourceLocation name) {
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(References.MODID, getRecipeOutput().getUnlocalizedName());
    }

    @Override
    public Class<IDCRecipe> getRegistryType() {
        return IDCRecipe.class;
    }

    private boolean checkJobLevel(String job, int level)
    {
        ILevel level_capabilities = this.player.getCapability(DCLevelProvider.LEVEL_CAP, null);

        if (job.equalsIgnoreCase("alchemist"))
        {
            if (level_capabilities.getAlchemistLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        else if (job.equalsIgnoreCase("miner"))
        {
            if (level_capabilities.getMinerLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        else if (job.equalsIgnoreCase("peasant"))
        {
            if (level_capabilities.getPeasantLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        else if (job.equalsIgnoreCase("lumberjack"))
        {
            if (level_capabilities.getLumberjackLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        if (job.equalsIgnoreCase("jeweler"))
        {
            if (level_capabilities.getJewelerLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        if (job.equalsIgnoreCase("tailor"))
        {
            if (level_capabilities.getTailorLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        if (job.equalsIgnoreCase("shoemaker"))
        {
            if (level_capabilities.getShoeMakerLevel() >= level)
            {
                this.matchesS = "true";
                return true;
            } else {
                this.matchesS = "nolvl";
                return false;
            }
        }
        this.matchesS = "false";
        return false;
    }

    public List<List<ItemStack>> getInputsItems()
    {
        List<List<ItemStack>> out = NonNullList.withSize(25, Collections.emptyList());
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                int id = i + j * 5;
                List<ItemStack> temp = NonNullList.withSize(1, recipeItems[id]);
                out.set(id, temp);
            }
        }


        return out;
    }

    public static String getMatchesS() {
        return matchesS;
    }

    public static boolean getIsCrafted() {
        return isCrafted;
    }
}


