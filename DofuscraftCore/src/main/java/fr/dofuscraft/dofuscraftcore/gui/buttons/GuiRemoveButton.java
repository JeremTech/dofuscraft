package fr.dofuscraft.dofuscraftcore.gui.buttons;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiRemoveButton extends GuiButton {

    private final ResourceLocation ICON;

    public GuiRemoveButton(int buttonId, int x, int y)
    {
        super(buttonId, x, y, 11, 11, "");
        this.ICON = new ResourceLocation(References.MODID, "textures/gui/icons.png");
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

                mc.renderEngine.bindTexture(ICON);
                this.drawTexturedModalRect(this.x, this.y, 11, 51, 11, 11);

            }
            else
            {
                mc.renderEngine.bindTexture(ICON);
                this.drawTexturedModalRect(this.x, this.y, 0, 51, 11, 11);


            }

            GlStateManager.popMatrix();
        }
    }

}
