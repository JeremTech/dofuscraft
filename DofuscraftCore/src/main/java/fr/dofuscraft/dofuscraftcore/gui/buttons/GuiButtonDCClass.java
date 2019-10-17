package fr.dofuscraft.dofuscraftcore.gui.buttons;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDCClass extends GuiButton
{
    private final ResourceLocation ICON;
    private final ResourceLocation HOVER_ICON;

    public GuiButtonDCClass(int buttonId, int x, int y, ResourceLocation icon, ResourceLocation hovered_icon)
    {
        super(buttonId, x, y, 20, 20, "");
        this.ICON = icon;
        this.HOVER_ICON = hovered_icon;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if( this.visible )
        {
            boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);


            if( mouseHover ) // si la souris est sur le bouton
            {
                mc.renderEngine.bindTexture(HOVER_ICON);
            }
            else
            {
                mc.renderEngine.bindTexture(ICON);
            }
            Gui.drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, 64, 64, 20, 20, 64, 64);
            GlStateManager.popMatrix();
        }
    }

}