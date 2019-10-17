package fr.dofuscraft.dofuscraftcore.gui.buttons;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDCDiscord extends GuiButton
{
	private static final ResourceLocation DISCORD_ICON = new ResourceLocation(References.MODID, "textures/gui/buttons/discord.png");
	private static final ResourceLocation DISCORD_HOVER_ICON = new ResourceLocation(References.MODID, "textures/gui/buttons/discord_hover.png");

	public GuiButtonDCDiscord(int buttonId, int x, int y)
	{
		super(buttonId, x, y, 30, 30, ""); // taille de 20x20, pas de nom
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if( this.visible )
		{
			boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

			GlStateManager.pushMatrix();
			if( mouseHover ) // si la souris est sur le bouton
			{
				mc.renderEngine.bindTexture(DISCORD_HOVER_ICON);
			}
			else
			{
				mc.renderEngine.bindTexture(DISCORD_ICON);
			}
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Gui.drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, 128, 128, 30, 30, 128, 128);
			GlStateManager.popMatrix();
		}
	}

}
