package fr.dofuscraft.dofuscraftcore.gui;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import fr.dofuscraft.dofuscraftcore.gui.buttons.GuiButtonDCArrow;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import fr.dofuscraft.dofuscraftcore.input.KeyBindings;
import fr.dofuscraft.dofuscraftcore.network.PacketDCClass;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.recipebook.GuiButtonRecipeTab;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiDCPlayerInfo extends GuiScreen
{
    private static final ResourceLocation GUI = new ResourceLocation(References.MODID,"textures/gui/dc_player_info_gui.png");
    private static final ResourceLocation ICONS = new ResourceLocation(References.MODID,"textures/gui/icons.png");
    private int xSize, ySize;
    private EntityPlayer player;
    private int guiTop, guiLeft;
    private float oldMouseX;
    private float oldMouseY;
    public FontRenderer font = fontRenderer;
    private int pageNumber = 1;
    private int maxPage = 2;

   // private GuiDCTab currentTab = new GuiDCTab(80, new ItemStack(Items.BOOK));

    public GuiDCPlayerInfo(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        xSize = 176;
        ySize = 196;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButton(200, guiLeft + 63, guiTop + 56, 106, 20, I18n.format("gui.classe.reset")));

        this.buttonList.add(new GuiButtonDCArrow(240, guiLeft + 136, guiTop + 83, false));

        this.buttonList.add(new GuiButtonDCArrow(280, guiLeft + 20, guiTop + 83, true));

        /*this.buttonList.add(currentTab);
        currentTab.visible = true;
        currentTab.setPosition(guiLeft -30, guiTop + 7);
        currentTab.setStateTriggered(true);*/

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();



        this.mc.getTextureManager().bindTexture(GUI);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;

        ILevel level = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
        ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);
        IClass playerCap = player.getCapability(DCClassProvider.CLASS_CAP, null);

        PacketDCClass packet = new PacketDCClass(player.getCapability(DCClassProvider.CLASS_CAP, null));
        Dofuscraftcore.network.sendToServer(packet);

        drawString(fontRenderer, I18n.format("gui.label.job") + "s - " + pageNumber + "/" + maxPage, guiLeft + (xSize/2) - (fontRenderer.getStringWidth(I18n.format("gui.label.job") + "s - " + pageNumber + "/" + maxPage) /2), guiTop + 86, Color.WHITE.getRGB());

        this.mc.fontRenderer.drawString(player.getName(), guiLeft + 68, guiTop + 11, Color.WHITE.getRGB());
        if(playerCap.getName() != ""){

            this.mc.fontRenderer.drawString(I18n.format("gui.label.class") + " : " + playerCap.getName(), guiLeft + 68, guiTop + 26, Color.WHITE.getRGB());

        }else {

            this.mc.fontRenderer.drawString(I18n.format("gui.label.class") + " : " + I18n.format("gui.player_info.classe.any"), guiLeft + 68, guiTop + 26, Color.WHITE.getRGB());

        }
        this.mc.fontRenderer.drawString(I18n.format("gui.label.level") + " " + player.experienceLevel, guiLeft + 68, guiTop + 41, Color.WHITE.getRGB());

        //region Affichage metier



            if(pageNumber == 1){

                drawJob(I18n.format("job.alchemist") + " : " + level.getAlchemistLevel(), 30, 107, ModItems.alchemist_book, 10, 107, 30, 117, 30, 117, 101, 5, level.getAlchemistLevel(), sublevels.getAlchemistSubLevel());
                drawJob(I18n.format("job.lumberjack") + " : " + level.getLumberjackLevel(), 30, 127, ModItems.lumberjack_book, 10, 127, 30, 137, 30, 137, 101, 5, level.getLumberjackLevel(), sublevels.getLumberjackSubLevel());
                drawJob(I18n.format("job.miner") + " : " + level.getMinerLevel(), 30, 147, ModItems.miner_book, 10, 147, 30, 157, 30, 157, 101, 5, level.getMinerLevel(), sublevels.getMinerSubLevel());
                drawJob(I18n.format("job.peasant") + " : " + level.getPeasantLevel(),30, 167, ModItems.peasant_book, 10, 167, 30, 177, 30, 177, 101, 5, level.getPeasantLevel(), sublevels.getPeasantSubLevel());

            }else if(pageNumber == 2){

                drawJob(I18n.format("job.jeweler") + " : " + level.getJewelerLevel(), 30, 107, ModItems.jeweler_book, 10, 107, 30, 117, 30, 117, 101, 5, level.getJewelerLevel(), sublevels.getJewelerSubLevel());
                drawJob(I18n.format("job.tailor") + " : " + level.getTailorLevel(), 30, 127, ModItems.tailor_book, 10, 127, 30, 137, 30, 137, 101, 5, level.getTailorLevel(), sublevels.getTailorSubLevel());
                drawJob(I18n.format("job.shoemaker") + " : " + level.getShoeMakerLevel(), 30, 147, ModItems.shoemaker_book, 10, 147, 30, 157, 30, 157, 101, 5, level.getShoeMakerLevel(), sublevels.getShoeMakerSubLevel());

            }

        //endregion
        drawEntityOnScreen(guiLeft + 33, guiTop + 72, 31, (float)(guiLeft + 51) - this.oldMouseX, (float)(guiTop + 75 - 50) - this.oldMouseY, this.mc.player);

        super.drawScreen(mouseX, mouseY, partialTicks);

        //region ToolTips
        if(pageNumber == 1){

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 117, guiLeft + 30 + 101, guiTop + 117 + 5, level.getAlchemistLevel(), sublevels.getAlchemistSubLevel());

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 137, guiLeft + 30 + 101, guiTop + 137 + 5, level.getLumberjackLevel(), sublevels.getLumberjackSubLevel());

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 157, guiLeft + 30 + 101, guiTop + 157 + 5, level.getMinerLevel(), sublevels.getMinerSubLevel());

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 177, guiLeft + 30 + 101, guiTop + 177 + 5, level.getPeasantLevel(), sublevels.getPeasantSubLevel());

        }else if(pageNumber == 2){

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 117, guiLeft + 30 + 101, guiTop + 117 + 5, level.getJewelerLevel(), sublevels.getJewelerSubLevel());

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 137, guiLeft + 30 + 101, guiTop + 137 + 5, level.getTailorLevel(), sublevels.getTailorSubLevel());

            toolTipShow(mouseX, mouseY,guiLeft + 30, guiTop + 157, guiLeft + 30 + 101, guiTop + 157 + 5, level.getShoeMakerLevel(), sublevels.getShoeMakerSubLevel());

        }



        //endregion
    }

    private void drawJob(String text, int textLeft, int textTop,Item icon,int iconLeft,int iconTop,int leftBar1,int topBar1, int leftBar2, int topBar2,int width, int height, int Level, int SubLevel){

        this.mc.fontRenderer.drawString(text, guiLeft + textLeft, guiTop + textTop, Color.BLACK.getRGB());

        itemRender.renderItemIntoGUI(new ItemStack(icon),guiLeft + iconLeft,guiTop + iconTop);

        this.mc.getTextureManager().bindTexture(ICONS);
        drawTexturedModalRect(guiLeft + leftBar1, guiTop + topBar1, 0, 0, width, height);
        drawTexturedModalRect(guiLeft + leftBar2, guiTop + topBar2, 0, 5, xpBar(Level, SubLevel), 7);

    }

    private void toolTipShow(int mouseX, int mouseY, int guiLeft1, int guiTop1, int guiLeft2,int guiTop2,int level, int sublevels){

        if(mouseX > guiLeft1 && mouseY > guiTop1 && mouseX < guiLeft2 && mouseY < guiTop2)
        {
            int sub_level = sublevels;
            int levels = level;
            drawToolTip(mouseX, mouseY, sub_level, levels);
        }

    }


    private void drawToolTip(int mouseX, int mouseY, int player_sub_level, int player_level)
    {
        List<String> info = Lists.newArrayList(player_sub_level + "/" + Math.round((player_level + 1) * player_level * 10)+ " XP");
        this.drawHoveringText(info, (int)mouseX, (int)mouseY, fontRenderer);
    }

    private int xpBar(int level, int sublevel)
    {
        float oneUnit = (float)101 / Math.round((level + 1) * level * 10);
        int currentWidth = (int)(oneUnit * sublevel);

        return currentWidth;
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
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 200)
        {
            this.mc.displayGuiScreen(new GuiDCClassConfirmation(Minecraft.getMinecraft().player));
        }else  if (button.id == 240)
        {

            if(pageNumber < maxPage){

                pageNumber++;

            }

        }else  if (button.id == 280)
        {

            if(pageNumber > 1){

                pageNumber--;

            }

        }/*else if(button.id == 80){

            if(currentTab.isStateTriggered()){

                System.out.println("Test True");
                currentTab.setStateTriggered(false);

            }else {

                System.out.println("Test False");
                currentTab.setStateTriggered(true);

            }


        }*/
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }


    private static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        if(keyCode == KeyBindings.spellKey.getKeyCode()){

            Minecraft.getMinecraft().player.closeScreen();

        }

    }
}
