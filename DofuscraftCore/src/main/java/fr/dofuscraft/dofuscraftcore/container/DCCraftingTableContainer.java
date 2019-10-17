package fr.dofuscraft.dofuscraftcore.container;

import fr.dofuscraft.dofuscraftcore.crafting_manager.DCCraftingManager;
import fr.dofuscraft.dofuscraftcore.gui.slots.DCCraftingResultSlot;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DCCraftingTableContainer extends Container
{
    /** Largeur du craft */
    public static final int craftWidth = 5;
    /** Hauteur du craft */
    public static final int craftHeigth = 5;

    /** Inventaire contenant le craft */
    private InventoryCrafting craftMatrix = new InventoryCrafting(this, craftWidth, craftHeigth);
    /** Inventaire contenant le résultat du craft */
    private InventoryCraftResult craftResult = new InventoryCraftResult();

    private final World worldObj;
    private final BlockPos pos;
    private final EntityPlayer player;

    public DCCraftingTableContainer(InventoryPlayer invPlayer, World world, BlockPos pos)
    {
        this.worldObj = world;
        this.pos = pos;
        this.player = invPlayer.player;

        //Ajout du slot pour le résultat
        this.addSlotToContainer(new DCCraftingResultSlot(invPlayer.player, craftMatrix, craftResult, 0, 172, 76));

        int startX = 16; //Position x ou les slots de craft commencent à être dessinés
        int startY = 9; //Position y ou les slots de craft commencent à être dessinés
        //Ajout des slots de craft
        for (int y = 0; y < craftHeigth; ++y)
        {
            for(int x = 0; x < craftWidth; ++x)
            {
                this.addSlotToContainer(new Slot(craftMatrix, x + y * craftWidth, startX + x * 18, startY + y * 18));
            }
        }

        startX = 25; //Position x ou les slots de l'inventaire commencent à être dessinés
        startY = 108; //Position y ou les slots de l'inventaire commencent à être dessinés
        //Ajout des slots de l'inventaire du joueur
        for (int y = 0; y < 3; ++y)
        {
            for(int x = 0; x < 9; ++x)
            {
                this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, startX + x * 18, startY + y * 18));
            }
        }
        startY = 166; //Position y ou les slots de la hotbar commencent à être dessinés
        //Ajout des slots de la hotbar
        for (int x = 0; x < 9; ++x)
        {
            this.addSlotToContainer(new Slot(invPlayer, x, startX + x * 18, startY));
        }
    }

    /**
     * Appelé quand la matrice (les slots de craft) change
     */
    @Override
    public void onCraftMatrixChanged(IInventory iiventory)
    {

        //On met le résultat du craft dans le slot de résultat
        if(DCCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj) != null)
        {
            craftResult.setInventorySlotContents(0, DCCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
        }
    }

    /**
     * Retourne true si le joueur peut interagir avec ce gui, en général on teste la distance par rapport au joueur dans cette fonction
     */
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.worldObj.getBlockState(this.pos).getBlock() != ModBlocks.dofuscraft_crafting_table ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    /**
     * Appelé quand le container est fermé
     */
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            this.clearContainer(player, this.worldObj, this.craftMatrix);
        }

    }

    /**
     * Cette fonction est appelée lors du shift+clic (je vous conseille de la laisser comme tel, elle s'adaptera en fonction de la taille de votre craft)
     * EDIT 11/01/16 Le contenu de cette fonction a été modifié via notepad++ et n'a pas été testé, si vous avez un problème rapportez le moi
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotId == 0)
            {
                itemstack1.getItem().onCreated(itemstack1, this.worldObj, player);

                if (!this.mergeItemStack(itemstack1, 26, 62, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotId >= 26 && slotId < 53)
            {
                if (!this.mergeItemStack(itemstack1, 53, 62, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slotId >= 53 && slotId < 62)
            {
                if (!this.mergeItemStack(itemstack1, 26, 53, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 26, 62, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(player, itemstack1);

           /* if (slotId == 0)
            {
                player.dropItem(itemstack2, false);
            }*/
        }

        return itemstack;
    }

    /**
     * Appelé quand on double clic sur un slot :
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }

    public EntityPlayer getPlayer()
    {
        return this.player;
    }
}
