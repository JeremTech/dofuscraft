package fr.dofuscraft.dofuscraftcore.gui;

import com.google.gson.*;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.JobInfoParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiDCJobBook extends GuiScreen {

    public static ResourceLocation locationJSON;

    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
    private static final ResourceLocation GUI = new ResourceLocation(References.MODID,"textures/gui/dc_job_book.png");

    private static JsonParser jsonParser = new JsonParser();
    private static JsonElement content;

    private int pageCount = 0;
    private int pageNumber = 0;

    private int xSize, ySize;
    private int guiTop, guiLeft;

    private GuiDCClasseHelp.NextPageButton buttonNextPage;
    private GuiDCClasseHelp.NextPageButton buttonPreviousPage;


    private String idJob;

    public GuiDCJobBook(String id){

        this.idJob = id;
        JobInfoParser parser = new JobInfoParser(idJob);

        if(parser.getNumberOfResources() % 8 == 0 && parser.getNumberOfResources() >8){

            pageCount = parser.getNumberOfResources() / 8;

        }else if(parser.getNumberOfResources() <= 8){

            pageCount = 1;



        }else {

            pageCount = ((parser.getNumberOfResources() - (parser.getNumberOfResources()%8)) / 8) + 1;

        }



    }

    @Override
    public void initGui() {
        super.initGui();

        xSize = 249;
        ySize = 179;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.buttonNextPage = (GuiDCClasseHelp.NextPageButton)this.addButton(new GuiDCClasseHelp.NextPageButton(240, guiLeft + 210, guiTop + 156, true));
        this.buttonPreviousPage = (GuiDCClasseHelp.NextPageButton)this.addButton(new GuiDCClasseHelp.NextPageButton(280, guiLeft + 15, guiTop + 156, false));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();

        int centerX = (this.width / 2) - xSize/2;
        int centerY = (this.height / 2) - ySize/2;

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if(pageNumber == 0){

            fontRenderer.drawString("Page : " + (pageNumber + 1 + (pageNumber * 2))+ "/" + (2* pageCount), guiLeft + 40, guiTop + 160, Color.BLACK.getRGB());
            fontRenderer.drawString("Page : " + (pageNumber + 2 + (pageNumber * 2))+ "/" + (2* pageCount), guiLeft + 160, guiTop + 160, Color.BLACK.getRGB());

        }else {

            fontRenderer.drawString("Page : " + (pageNumber  + (pageNumber * 2))+ "/" + (2* pageCount), guiLeft + 40, guiTop + 160, Color.BLACK.getRGB());
            fontRenderer.drawString("Page : " + (pageNumber  + (pageNumber * 3))+ "/" + (2* pageCount), guiLeft + 160, guiTop + 160, Color.BLACK.getRGB());

        }

        JobInfoParser parser = new JobInfoParser(idJob);

        //region Item Render
        if(pageNumber == pageCount - 1){

            if(parser.getNumberOfResources() % 8 == 0){

                for(int j = 0; j < 2; j++){

                    for(int i = 0; i < 4; i++){

                        GlStateManager.pushMatrix();
                        {


                            GlStateManager.translate(centerX, centerY, 0);
                            GlStateManager.scale(1.45f,1.45f,1.45f);
                            itemRender.renderItemIntoGUI(new ItemStack(Item.getByNameOrId(parser.getResourceInfo((8* pageNumber) + (i + ((j * 4)))).get(0))), 10 + (j*85), 12 + (i*25));

                        }
                        GlStateManager.popMatrix();

                    }

                }

            }else {

                if(parser.getNumberOfResources() % 8 < 5){

                    for(int i = 0; i < parser.getNumberOfResources() % 8; i++){

                        GlStateManager.pushMatrix();
                        {


                            GlStateManager.translate(centerX, centerY, 0);
                            GlStateManager.scale(1.45f,1.45f,1.45f);
                            itemRender.renderItemIntoGUI(new ItemStack(Item.getByNameOrId(parser.getResourceInfo((8* pageNumber) + (i  + 1)).get(0))), 10, 12 + (i*25));

                        }
                        GlStateManager.popMatrix();

                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + parser.getResourceInfo((8* pageNumber) + (i  + 1)).get(1), guiLeft + 40 , guiTop + 20+ (i*36), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.reward") + " " + parser.getResourceInfo((8* pageNumber) + (i  + 1)).get(2), guiLeft + 40, guiTop + 30+ (i*36), Color.BLACK.getRGB());

                    }

                }else {

                    for(int i = 0; i < 4; i++){

                        GlStateManager.pushMatrix();
                        {


                            GlStateManager.translate(centerX, centerY, 0);
                            GlStateManager.scale(1.45f,1.45f,1.45f);
                            itemRender.renderItemIntoGUI(new ItemStack(Item.getByNameOrId(parser.getResourceInfo((8* pageNumber) + (i + 1)).get(0))), 10 , 12 + (i*25));

                        }
                        GlStateManager.popMatrix();

                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + parser.getResourceInfo((8* pageNumber) + (i  + 1)).get(1), guiLeft + 40 , guiTop + 20+ (i*36), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.reward") + " " + parser.getResourceInfo((8* pageNumber) + (i + 1)).get(2), guiLeft + 40, guiTop + 30+ (i*36), Color.BLACK.getRGB());

                    }

                    for(int i = 0; i < ((parser.getNumberOfResources() % 8) - 4); i++){

                        GlStateManager.pushMatrix();
                        {


                            GlStateManager.translate(centerX, centerY, 0);
                            GlStateManager.scale(1.45f,1.45f,1.45f);
                            itemRender.renderItemIntoGUI(new ItemStack(Item.getByNameOrId(parser.getResourceInfo((8* pageNumber) + (i + 5)).get(0))), 95 , 12 + (i*25));

                        }
                        GlStateManager.popMatrix();

                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + parser.getResourceInfo((8* pageNumber) + (i  + 5)).get(1), guiLeft + 165 , guiTop + 20+ (i*36), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.reward") + " " + parser.getResourceInfo((8* pageNumber) + (i + 5)).get(2), guiLeft + 165, guiTop + 30+ (i*36), Color.BLACK.getRGB());

                    }





                }

            }

        }else {

                for (int j = 0; j < 2; j++) {

                    for (int i = 0; i < 4; i++) {

                        GlStateManager.pushMatrix();
                        {


                            GlStateManager.translate(centerX, centerY, 0);
                            GlStateManager.scale(1.45f, 1.45f, 1.45f);
                            itemRender.renderItemIntoGUI(new ItemStack(Item.getByNameOrId(parser.getResourceInfo((8* pageNumber) + (i + ((j * 4) + 1))).get(0))), 10 + (j*85), 12 + (i*25));

                        }
                        GlStateManager.popMatrix();

                        fontRenderer.drawString(I18n.format("gui.label.level") + " " + parser.getResourceInfo((8* pageNumber) + (i + ((j * 4) + 1))).get(1), guiLeft + 40 + (j*125), guiTop + 20+ (i*36), Color.BLACK.getRGB());
                        fontRenderer.drawString(I18n.format("gui.label.reward") + " " + parser.getResourceInfo((8* pageNumber) + (i + ((j * 4) + 1))).get(2), guiLeft + 40 + (j*125), guiTop + 30+ (i*36), Color.BLACK.getRGB());

                    }

                }


        }
        //endregion



        super.drawScreen(mouseX, mouseY, partialTicks);



    }



    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 240)
        {

            if(pageNumber < pageCount - 1){

                pageNumber++;

            }




        }else  if (button.id == 280)
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
                mc.getTextureManager().bindTexture(GuiDCJobBook.BOOK_GUI_TEXTURES);
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

