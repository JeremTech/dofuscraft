package fr.dofuscraft.dofuscraftcore.gui.buttons;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDCArrow extends GuiButton {

    private final ResourceLocation ICON;
    private final boolean isLeft;

    public GuiButtonDCArrow(int buttonId, int x, int y, boolean isLeft)
    {
        super(buttonId, x, y, 20, 14, "");
        this.ICON = new ResourceLocation(References.MODID, "textures/gui/icons.png");
        this.isLeft = isLeft;
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
                if(isLeft){

                    mc.renderEngine.bindTexture(ICON);
                    this.drawTexturedModalRect(this.x, this.y, 20, 12, 20, 14);

                }else {

                    mc.renderEngine.bindTexture(ICON);
                    this.drawTexturedModalRect(this.x, this.y, 20, 26, 20, 14);

                }

            }
            else
            {
                if(isLeft){

                    mc.renderEngine.bindTexture(ICON);
                    this.drawTexturedModalRect(this.x, this.y, 0, 12, 20, 14);

                }else {

                    mc.renderEngine.bindTexture(ICON);
                    this.drawTexturedModalRect(this.x, this.y, 0, 26, 20, 14);

                }


            }

            GlStateManager.popMatrix();
        }
    }

}
