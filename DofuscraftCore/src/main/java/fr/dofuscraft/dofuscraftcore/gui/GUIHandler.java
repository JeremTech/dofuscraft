package fr.dofuscraft.dofuscraftcore.gui;

import fr.dofuscraft.dofuscraftcore.container.DCCraftingTableContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler  implements IGuiHandler
{
    public static final int guiCraftingTableID = 0;
    public static final int classSelectionGUI_ID = 2;
    public static final int playerInfoGUI_ID = 3;
    public static final int messageConfirmationGUI_ID = 4;


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        switch (ID)
        {
            case guiCraftingTableID:
                return new DCCraftingTableContainer(player.inventory, world, pos); //On retourne le container du gui
        }

        return pos;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);

        switch (ID)
        {   case guiCraftingTableID:
                return new GuiDCCraftingTable(player.inventory, world, pos);
            case classSelectionGUI_ID:
                return new GuiDCClasseSelection(player); //On retourne le gui
            case playerInfoGUI_ID:
                return new GuiDCPlayerInfo(player); //On retourne le gui
            case messageConfirmationGUI_ID:
                return new GuiDCClassConfirmation(player); //On retourne le gui
        }

        return pos;
    }


}