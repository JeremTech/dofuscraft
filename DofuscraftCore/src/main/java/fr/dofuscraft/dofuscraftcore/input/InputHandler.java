package fr.dofuscraft.dofuscraftcore.input;

import fr.dofuscraft.dofuscraftcore.gui.GuiDCClasseHelp;
import fr.dofuscraft.dofuscraftcore.gui.GuiDCPlayerInfo;
import fr.dofuscraft.dofuscraftcore.gui.toast.DCLevelUpToast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.io.File;

public class InputHandler
{
	Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if( KeyBindings.spellKey.isPressed() )
		{
			//this.mc.displayGuiScreen(new GuiDCClasseHelp("eniripsa.json"));
			this.mc.displayGuiScreen(new GuiDCPlayerInfo(Minecraft.getMinecraft().player));
			//Minecraft.getMinecraft().getToastGui().add(new DCLevelUpToast());

			//System.out.println(new File(DimensionManager.getCurrentSaveRootDirectory() + "/data").listFiles());

		}
	}
}








