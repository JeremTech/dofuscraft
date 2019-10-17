package fr.dofuscraft.dofuscraftcore.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.ClassInfoParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;

import com.google.common.collect.Lists;

import java.util.List;

public class GuiDCClasseHelp extends GuiScreen {

    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
    private static final ResourceLocation GUI = new ResourceLocation(References.MODID,"textures/gui/dc_classes_book.png");

    private static JsonParser jsonParser = new JsonParser();
    private static JsonElement content;

    private int pageCount = 0;
    private int pageNumber = 0;

    private int xSize, ySize;
    private int guiTop, guiLeft;

    private GuiDCClasseHelp.NextPageButton buttonNextPage;
    private GuiDCClasseHelp.NextPageButton buttonPreviousPage;

    private GuiButton[] buttonInfo;

    private String idJob;

    private int alpha = 100;



    public GuiDCClasseHelp(String id){

        this.idJob = id;
        ClassInfoParser parser = new ClassInfoParser(idJob);

        if(parser.getNumberOfResources() % 2 == 0 && parser.getNumberOfResources() > 2){

            pageCount = parser.getNumberOfResources() / 2;

        }
        else if(parser.getNumberOfResources() <= 2)
        {

            pageCount = 1;



        }else {

            pageCount = ((parser.getNumberOfResources() - (parser.getNumberOfResources()%2)) / 2) + 1;

        }



    }

    @Override
    public void initGui() {
        super.initGui();


        xSize = 146;
        ySize = 180;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;


        this.buttonNextPage = (GuiDCClasseHelp.NextPageButton)this.addButton(new GuiDCClasseHelp.NextPageButton(440, guiLeft + 101, guiTop + 154, true));
        this.buttonPreviousPage = (GuiDCClasseHelp.NextPageButton)this.addButton(new GuiDCClasseHelp.NextPageButton(480, guiLeft + 10, guiTop + 154, false));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();

        IClass playerCap = ((EntityPlayer)Minecraft.getMinecraft().player).getCapability(DCClassProvider.CLASS_CAP, null);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 20, 1, xSize, ySize);


        fontRenderer.drawString(playerCap.getName() + " - Spells", guiLeft + (xSize/2) - (fontRenderer.getStringWidth(playerCap.getName() + " - Spells") / 2), guiTop + 17 , Color.BLACK.getRGB());



            fontRenderer.drawString(I18n.format("gui.label.page") + " - " + (pageNumber + 1)+ "/" + pageCount,guiLeft + 67 - (fontRenderer.getStringWidth(I18n.format("gui.label.page") + " - " + (pageNumber + 1)+ "/" + pageCount) /2), guiTop + 156, Color.BLACK.getRGB());

        ClassInfoParser parser = new ClassInfoParser(idJob);


            if(pageNumber == pageCount - 1){

                if(parser.getNumberOfResources() % 2 == 0){

                    for(int i = 0; i < 2; i++){

                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/icons/spells/" + parser.getResourceInfo((2 * pageNumber) + (i + 1)).get(0)));
                        Gui.drawScaledCustomSizeModalRect(guiLeft + 10 , guiTop + 35 + (i * 62), 0, 0, 64, 64, 32, 32, 64, 64);
                        fontRenderer.drawString(I18n.format("gui.label.name") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(1)), guiLeft + 45, guiTop + 35 + (i * 62), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.damage") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(3)), guiLeft + 45, guiTop + 45 + (i * 62), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(2)), guiLeft + 45, guiTop + 55 + (i * 62), Color.BLACK.getRGB());

                    }

                }else {

                    for(int i = 0; i < (parser.getNumberOfResources() - (parser.getNumberOfResources() - (parser.getNumberOfResources() % 2))); i++){

                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/icons/spells/" + parser.getResourceInfo((2 * pageNumber) + (i + 1)).get(0)));
                        Gui.drawScaledCustomSizeModalRect(guiLeft + 10 , guiTop + 35 + (i * 62), 0, 0, 64, 64, 32, 32, 64, 64);
                        fontRenderer.drawString(I18n.format("gui.label.name") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(1)), guiLeft + 45, guiTop + 35 + (i * 62), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.damage") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(3)), guiLeft + 45, guiTop + 45 + (i * 62), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(2)), guiLeft + 45, guiTop + 55 + (i * 62), Color.BLACK.getRGB());

                    }

                }

            }else {

                for(int i = 0; i < 2; i++){

                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/icons/spells/" + parser.getResourceInfo((2 * pageNumber) + (i + 1)).get(0)));
                    Gui.drawScaledCustomSizeModalRect(guiLeft + 10 , guiTop + 35 + (i * 62), 0, 0, 64, 64, 32, 32, 64, 64);
                    fontRenderer.drawString(I18n.format("gui.label.name") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(1)), guiLeft + 45, guiTop + 35 + (i * 62), Color.BLACK.getRGB());
                    fontRenderer.drawString(I18n.format("gui.label.damage") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(3)), guiLeft + 45, guiTop + 45 + (i * 62), Color.BLACK.getRGB());
                    fontRenderer.drawString(I18n.format("gui.label.level") + " " + I18n.format(parser.getResourceInfo((2* pageNumber) + (i + 1)).get(2)), guiLeft + 45, guiTop + 55 + (i * 62), Color.BLACK.getRGB());
                }

            }





            /*List<String> lines = new ArrayList<String>();
            String currentLine = "";
            for(String s : infoText.split(" ")) {
                if(fontRenderer.getStringWidth(currentLine + s + " ") <= 110) {
                    currentLine = currentLine + s + " ";
                } else {
                    lines.add(currentLine);
                    currentLine = "" + s + " ";
                }
            }
            int xScal = 15;

            for(int i = 0; i < lines.size(); i++){

                fontRenderer.drawString(lines.get(i), (width/2) - (fontRenderer.getStringWidth(lines.get(i))/2), guiTop + xScal, Color.BLACK.getRGB());
                xScal += 9;
            }*/




        super.drawScreen(mouseX, mouseY, partialTicks);

        if(parser.getNumberOfResources() % 2 > 0 && pageNumber == pageCount - 1){

            if(mouseX > guiLeft + 10 && mouseY > guiTop + 35 + (0 * 62) && mouseX < guiLeft + 42 && mouseY < guiTop + 35 + (0 * 62) + 32){

                List<String> info = Lists.newArrayList(I18n.format(parser.getResourceInfo((2 * pageNumber) + (0 + 1)).get(4)));
                this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);

            }

        }else {

            for(int i = 0; i < 2; i++){


                if(mouseX > guiLeft + 10 && mouseY > guiTop + 35 + (i * 62) && mouseX < guiLeft + 42 && mouseY < guiTop + 35 + (i * 62) + 32){

                    List<String> info = Lists.newArrayList(I18n.format(parser.getResourceInfo((2 * pageNumber) + (i + 1)).get(4)));
                    this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);

                }

                }

            }


    }



    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 440)
        {

            if(pageNumber < pageCount - 1){

                pageNumber++;

            }




        }else  if (button.id == 480)
        {

            if(pageNumber > 0){

                pageNumber--;

            }

        }
    }



    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }



    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);



    }


    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean isForward;

        public NextPageButton(int buttonId, int x, int y, boolean isForwardIn)
        {
            super(buttonId, x, y, 23, 13, "");
            this.isForward = isForwardIn;
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
            if (this.visible)
            {
                boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(GuiDCClasseHelp.BOOK_GUI_TEXTURES);
                int i = 0;
                int j = 192;

                if (flag)
                {
                    i += 23;
                }

                if (!this.isForward)
                {
                    j += 13;
                }

                this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
            }
        }
    }


}
