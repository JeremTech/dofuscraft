package fr.dofuscraft.dofuscraftcore.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings
{
	public static KeyBinding spellKey;

	public static void init()
	{
		spellKey = new KeyBinding("key.dofuscraft.player_stats", Keyboard.KEY_R, "key.dofuscraft");
		ClientRegistry.registerKeyBinding(spellKey);
	}
}
