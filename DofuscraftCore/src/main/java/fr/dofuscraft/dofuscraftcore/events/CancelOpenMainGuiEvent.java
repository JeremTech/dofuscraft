package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.gui.GuiDCMainMenu;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCPauseGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class CancelOpenMainGuiEvent
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

		}
	}



	@SubscribeEvent
	public static void onOverlay(RenderGameOverlayEvent.Text event) {
		event.getLeft().add("Test d'overlay");
	}

	@SubscribeEvent
	public void pojger(GuiScreenEvent.KeyboardInputEvent e)
	{

		if( e.getGui().getClass() == GuiDCMainMenu.class )
		{

			if( Keyboard.isKeyDown(Keyboard.KEY_D) )
			{

				if( Keyboard.isKeyDown(Keyboard.KEY_C) )
				{

					e.getGui().onGuiClosed();
					FMLClientHandler.instance().showGuiScreen(new GuiWorldSelection(e.getGui()));

				}

			}
			else if( Keyboard.isKeyDown(Keyboard.KEY_E) )
			{

				e.getGui().onGuiClosed();
				Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(e.getGui(), Minecraft.getMinecraft(), new ServerData("Local", "localhost", false)));

			}

			if( Keyboard.isKeyDown(Keyboard.KEY_M) )
			 {
			 e.getGui().onGuiClosed();
			 FMLClientHandler.instance().showGuiScreen(new GuiMultiplayer(e.getGui()));
			 }

		}
	}
}




