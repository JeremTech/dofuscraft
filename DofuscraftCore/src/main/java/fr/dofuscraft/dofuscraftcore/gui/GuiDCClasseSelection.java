package fr.dofuscraft.dofuscraftcore.gui;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.gui.buttons.GuiButtonDCClass;
import fr.dofuscraft.dofuscraftcore.network.PacketCommand;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiDCClasseSelection extends GuiScreen
{
    private static final ResourceLocation GUI = new ResourceLocation(References.MODID,"textures/gui/dc_player_classe_selection.png");
    private int xSize, ySize;
    private EntityPlayer player;
    private int guiTop, guiLeft;

    public GuiDCClasseSelection(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        xSize = 168;
        ySize = 64;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButtonDCClass(240, guiLeft + (xSize/4) - (20/2), guiTop + (ySize/2) - (20/2) + 2, new ResourceLocation(References.MODID, "textures/gui/icons/iop_sword.png"), new ResourceLocation(References.MODID, "textures/items/masdaigat_sword.png")));
        this.buttonList.add(new GuiButtonDCClass(250, guiLeft + (xSize/4) + (xSize/4) - (20/2), guiTop + (ySize/2) - (20/2) + 2, new ResourceLocation(References.MODID,"textures/gui/icons/eniripsa.png"), new ResourceLocation(References.MODID,"textures/items/eniripsa.png")));
        this.buttonList.add(new GuiButtonDCClass(260, guiLeft + (xSize/4) + ((xSize/4) * 2) - (20/2), guiTop + (ySize/2) - (20/2) + 2, new ResourceLocation(References.MODID,"textures/gui/icons/feca_shield.png"), new ResourceLocation(References.MODID,"textures/items/feca_shield.png")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        this.mc.getTextureManager().bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        drawString(fontRenderer, I18n.format("gui.class.selection"), guiLeft + (xSize/2) - (fontRenderer.getStringWidth(I18n.format("gui.class.selection")) /2), guiTop + 8, Color.ORANGE.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);

        if(mouseX > guiLeft + (xSize/4) - (20/2)  && mouseY > guiTop + (ySize/2) - (20/2) + 2 && mouseX <guiLeft + (xSize/4) - (20/2) + 20 && mouseY < guiTop + (ySize/2) - (20/2) + 2 + 20)
        {

            List<String> info = Lists.newArrayList("Masdaigat");
            this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);
        }

        if(mouseX > guiLeft + (xSize/4) + (xSize/4) - (20/2)  && mouseY > guiTop + (ySize/2) - (20/2) + 2 && mouseX <guiLeft + (xSize/4) + (xSize/4) - (20/2) + 20 && mouseY < guiTop + (ySize/2) - (20/2) + 2 + 20)
        {

            List<String> info = Lists.newArrayList("Eniripsa");
            this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);
        }

        if(mouseX > guiLeft + (xSize/4) + ((xSize/4) * 2) - (20/2) && mouseY > guiTop + (ySize/2) - (20/2) + 2 && mouseX <guiLeft + (xSize/4) + ((xSize/4) * 2) - (20/2) + 20 && mouseY < guiTop + (ySize/2) - (20/2) + 2 + 20)
        {

            List<String> info = Lists.newArrayList("Feca");
            this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);
        }

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        IClass playerCap = player.getCapability(DCClassProvider.CLASS_CAP, null);

        if (button.id == 240)
        {
           if(!playerCap.getID().equals("masdaigat")){

               Dofuscraftcore.network.sendToServer(new PacketCommand("classe reset " + player.getName() + " " + player.getCapability(DCClassProvider.CLASS_CAP, null).getID()));
               Dofuscraftcore.network.sendToServer(new PacketCommand("classe set " + player.getName() + " masdaigat"));
               this.mc.displayGuiScreen(new GuiDCPlayerInfo((EntityPlayer)Minecraft.getMinecraft().player));

           }else {

               player.sendMessage(new TextComponentString("Vous possédez déjà cette classe"));

           }
        }
        if (button.id == 250)
        {

            if(!playerCap.getID().equals("eniripsa")){

                Dofuscraftcore.network.sendToServer(new PacketCommand("classe reset " + player.getName() + " " + player.getCapability(DCClassProvider.CLASS_CAP, null).getID()));
                Dofuscraftcore.network.sendToServer(new PacketCommand("classe set " + player.getName() + " eniripsa"));
                this.mc.displayGuiScreen(new GuiDCPlayerInfo((EntityPlayer)Minecraft.getMinecraft().player));

            }else {

                player.sendMessage(new TextComponentString("Vous possédez déjà cette classe"));

            }


        }
        if (button.id == 260)
        {

            if(!playerCap.getID().equals("feca")){

                Dofuscraftcore.network.sendToServer(new PacketCommand("classe reset " + player.getName() + " " + player.getCapability(DCClassProvider.CLASS_CAP, null).getID()));
                Dofuscraftcore.network.sendToServer(new PacketCommand("classe set " + player.getName() + " feca"));
                this.mc.displayGuiScreen(new GuiDCPlayerInfo((EntityPlayer)Minecraft.getMinecraft().player));

            }else {

                player.sendMessage(new TextComponentString("Vous possédez déjà cette classe"));

            }

        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }



}
