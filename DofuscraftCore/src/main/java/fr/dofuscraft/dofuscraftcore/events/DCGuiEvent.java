package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.gui.GuiDCInventory;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCLanguage;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCMainMenu;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCPauseGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class DCGuiEvent
{

	@SubscribeEvent
	public void onCancel(GuiOpenEvent e)
	{
		if(e.getGui() != null)
		{
			if( e.getGui().getClass() == GuiMainMenu.class)
			{
				e.setGui(new GuiDCMainMenu());
			}

			if(e.getGui().getClass() == GuiIngameMenu.class){

				e.setGui(new GuiDCPauseGame());

			}

			if(e.getGui().getClass() == GuiInventory.class){

				e.setGui(new GuiDCInventory(Minecraft.getMinecraft().player));

			}

			/*if(e.getGui().getClass() == GuiLanguage.class){

				e.setGui(new GuiDCLanguage(new GuiOptions(new GuiDCMainMenu(),Minecraft.getMinecraft().gameSettings), Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().getLanguageManager()));

			}*/

			

			/*if(e.getGui().getClass() == GuiFurnace.class){


				e.getGui().mc.player.closeScreen();
				return;


			}

			if(e.getGui().getClass() == GuiDispenser.class){


				e.getGui().mc.player.closeScreen();


			}*/

		}
	}

	/*@SubscribeEvent
	public void onCancel(PlayerContainerEvent e){

		if( e.getContainer().getClass() == ContainerPlayer.class)
		{
			System.out.println("Container Inventory Open");
		}

	}*/

}




