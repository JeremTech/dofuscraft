package fr.dofuscraft.dofuscraftcore.gui.slots;

import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCCraftingManager;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapedRecipes;
import fr.dofuscraft.dofuscraftcore.crafting_manager.IDCRecipe;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;

public class DCCraftingResultSlot extends Slot
{
    /** The craft matrix inventory linked to this result slot. */
    private final InventoryCrafting craftMatrix;
    /** The player that is using the GUI where this slot resides. */
    private final EntityPlayer thePlayer;
    /** The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset. */
    private int amountCrafted;

    public DCCraftingResultSlot(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
        this.thePlayer = player;
        this.craftMatrix = craftingInventory;
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(amount, this.getStack().getCount());
        }
        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    @Override
    protected void onCrafting(ItemStack stack, int amount)
    {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    /**
     * Appelé quand on craft, permet de gérer les achievements
     */
    @Override
    protected void onCrafting(ItemStack stack)
    {
        if (this.amountCrafted > 0)
        {
            stack.onCrafting(this.thePlayer.world, this.thePlayer, this.amountCrafted);
        }
    }

    /**
     * Appelée quand le joueur retire l'item du slot, permet de retirer le composants utilisés pour le craft
     */
    @Override
    public ItemStack onTake(EntityPlayer playerIn, ItemStack stack)
    {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix); //Distribue l'event de craft
        this.onCrafting(stack); //Gestion des achievements
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn); //Utilisé afin de retirer les items utiliser (pas sur de cela)
        NonNullList<ItemStack> aitemstack = DCCraftingManager.getInstance().getRemainingItems(this.craftMatrix, playerIn.world); //On récupère les items restants
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        IDCRecipe recipe = DCCraftingManager.getInstance().getMatchingRecipe(this.craftMatrix, thePlayer.world);


        if(recipe != null && !playerIn.getEntityWorld().isRemote)
        {
            ISubLevel sub_levels_cap = playerIn.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
            switch (recipe.getJobRequire())
            {
                case "alchemist" :
                {
                    sub_levels_cap.setAlchemistSubLevel(sub_levels_cap.getAlchemistSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "alchemist");
                }
                case "lumberjack" :
                {
                    sub_levels_cap.setLumberjackSubLevel(sub_levels_cap.getLumberjackSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "lumberjack");
                }
                case "miner" :
                {
                    sub_levels_cap.setMinerSubLevel(sub_levels_cap.getMinerSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "miner");
                }
                case "peasant" :
                {
                    sub_levels_cap.setPeasantSubLevel(sub_levels_cap.getPeasantSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "peasant");
                }
                case "jeweler" :
                {
                    sub_levels_cap.setJewelerSubLevel(sub_levels_cap.getJewelerSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "jeweler");
                }
                case "tailor" :
                {
                    sub_levels_cap.setTailorSubLevel(sub_levels_cap.getTailorSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "tailor");
                }
                case "shoemaker" :
                {
                    sub_levels_cap.setShoeMakerSubLevel(sub_levels_cap.getShoeMakerSubLevel() + recipe.getJobXpReward());
                    sub_levels_cap.checkLevel((EntityPlayerMP) playerIn, "shoemaker");
                }
            }
        }
        
        for (int i = 0; i < aitemstack.size(); ++i) //On actualise les slots de craft
        {
            ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = aitemstack.get(i);
            if (itemstack != null)
            {
                this.craftMatrix.decrStackSize(i, 1);
                itemstack = this.craftMatrix.getStackInSlot(i);
            }
            if (itemstack1 != null)
            {
                if (itemstack == null)
                {
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1))
                {
                    int count = itemstack1.getCount();
                    count += itemstack.getCount();
                    this.craftMatrix.setInventorySlotContents(i, itemstack1);
                }
                else if (!this.thePlayer.inventory.addItemStackToInventory(itemstack1))
                {
                    this.thePlayer.dropItem(itemstack1, false);
                }
            }
        }

        return super.onTake(thePlayer, stack);
    }
}
