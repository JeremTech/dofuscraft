package fr.dofuscraft.dofuscraftcore.gui;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiDCClassConfirmation extends GuiScreen
{
    private static final ResourceLocation GUI = new ResourceLocation(References.MODID,"textures/gui/dc_player_info_reset.png");
    private int xSize, ySize;
    private EntityPlayer player;
    private int guiTop, guiLeft;
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;

    public GuiDCClassConfirmation(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        xSize = 176;
        ySize = 100;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButton(210, guiLeft + 10, guiTop + 70, 60, 20, I18n.format("gui.classe.reset.yes")));

        this.buttonList.add(new GuiButton(220, guiLeft + 106, guiTop + 70, 60, 20, I18n.format("gui.classe.reset.no")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        this.mc.getTextureManager().bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int stringWidth = this.fontRenderer.getStringWidth(I18n.format("gui.classe.reset.warning"));

        this.drawCenteredString(this.fontRenderer, I18n.format("gui.classe.reset.warning"), this.width / 2, guiTop + 10, Color.RED.getRGB());

        fontRenderer.drawString(I18n.format("gui.classe.reset.disclamer"), guiLeft + (xSize/2) - (fontRenderer.getStringWidth(I18n.format("gui.classe.reset.disclamer")) /2), guiTop + 25, Color.RED.getRGB());

        fontRenderer.drawString(I18n.format("gui.classe.reset.disclamer.xp"), guiLeft + (xSize/2) - (fontRenderer.getStringWidth(I18n.format("gui.classe.reset.disclamer.xp")) /2), guiTop + 35, Color.RED.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if(button.id == 210)
        {
            this.mc.displayGuiScreen(new GuiDCClasseSelection(Minecraft.getMinecraft().player));
        }

        else if(button.id == 220)
        {
            this.mc.displayGuiScreen(new GuiDCPlayerInfo(Minecraft.getMinecraft().player));
        }

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        if(keyCode == Keyboard.KEY_ESCAPE){



                this.mc.displayGuiScreen(new GuiDCClassConfirmation((EntityPlayer)Minecraft.getMinecraft().player));




        }
    }

}
