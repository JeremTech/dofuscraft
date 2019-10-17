package fr.dofuscraft.dofuscraftcore.gui.buttons;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDCRefresh extends GuiButton
{
	private static final ResourceLocation REFRESH_ICON = new ResourceLocation(References.MODID, "textures/gui/buttons/refresh.png");
	private static final ResourceLocation REFRESH_HOVER_ICON = new ResourceLocation(References.MODID, "textures/gui/buttons/refresh_hover.png");

	public GuiButtonDCRefresh(int buttonId, int x, int y)
	{
		super(buttonId, x, y, 20, 20, ""); // taille de 20x20, pas de nom
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if( this.visible )
		{
			boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			if( mouseHover ) // si la souris est sur le bouton
			{
				mc.getTextureManager().bindTexture(REFRESH_HOVER_ICON);
			}
			else
			{
				mc.getTextureManager().bindTexture(REFRESH_ICON);
			}
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Gui.drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, 128, 128, 20, 20, 128, 128);
		}
	}

}