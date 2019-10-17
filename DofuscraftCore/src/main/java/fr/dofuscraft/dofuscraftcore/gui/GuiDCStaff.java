package fr.dofuscraft.dofuscraftcore.gui;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.gui.buttons.GuiButtonDCRefresh;
import fr.dofuscraft.dofuscraftcore.utils.References;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ForgeVersion;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class GuiDCStaff extends GuiScreen
{
    private static final ResourceLocation DOFUSCRAFT_LOGO = new ResourceLocation(References.MODID, "textures/gui/dofuscraftlogo.png");
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(References.MODID, "textures/gui/main_menu_background.png");
    private static final ResourceLocation SPLASH_TEXTS = new ResourceLocation(References.MODID, "texts/splashes.txt");

    private static final Random RANDOM = new Random();
    public String splashText;

    private int widthCopyright;
    private int widthCopyrightRest;
    private int loopCount = 500;

    public GuiDCStaff()
    {
        this.splashText = "missingno";
        IResource iresource = null;

        try
        {
            List<String> list = Lists.<String>newArrayList();
            iresource = Minecraft.getMinecraft().getResourceManager().getResource(SPLASH_TEXTS);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));
            String s;

            while ((s = bufferedreader.readLine()) != null)
            {
                s = s.trim();

                if (!s.isEmpty())
                {
                    list.add(s);
                }
            }

            if (!list.isEmpty())
            {
                while (true)
                {
                    this.splashText = list.get(RANDOM.nextInt(list.size()));

                    if (this.splashText.hashCode() != 125780783)
                    {
                        break;
                    }
                }
            }
        }
        catch (IOException var8)
        {

        }
        finally
        {
            IOUtils.closeQuietly((Closeable) iresource);
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.widthCopyright = this.fontRenderer.getStringWidth("Copyright Mojang AB & Dofuscraft");
        this.widthCopyrightRest = this.width - this.widthCopyright - 2;

        int j = this.height / 4 + 48;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 50, I18n.format("menu.staff.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 74, I18n.format("menu.staff.multiplayer")));
        this.buttonList.add(new GuiButton(8, this.width / 2 - 200, this.height / 4 + 74, 80, 20, I18n.format("menu.staff.back")));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
        this.buttonList.add(new GuiButtonLanguage(6, this.width / 2 - 124, j + 72 + 12));
        this.buttonList.add(new GuiButtonDCRefresh(7, this.width / 2 + 104, j + 83));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 1)
        {
            this.mc.displayGuiScreen(new GuiWorldSelection(new GuiDCMainMenu()));
        }
        if(button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 4)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5)
        {
            this.mc.shutdown();
        }
        if (button.id == 6)
        {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if(button.id == 8)
        {

            Minecraft.getMinecraft().displayGuiScreen(new GuiDCMainMenu());

        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        // Background texture
        mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        GlStateManager.enableAlpha();

        // Logo texture
        this.mc.getTextureManager().bindTexture(DOFUSCRAFT_LOGO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(this.width / 2 - 150, -30, 0, 0, 1, 1, 300, 160, 1, 1);

        // Splash text
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
        f = f * 100.0F / (float) (this.fontRenderer.getStringWidth(this.splashText) + 32);
        GlStateManager.scale(f, f, f);
        this.drawCenteredString(this.fontRenderer, this.splashText, 0, -8, -256);
        GlStateManager.popMatrix();

        // Strings on the down of the screen (left and right)
        this.drawString(this.fontRenderer, "Copyright Mojang AB & Dofuscraft", this.widthCopyrightRest, this.height - 10, -1);
        this.drawString(this.fontRenderer, "Minecraft 1.12.2", 2, this.height - 30, -1);
        this.drawString(this.fontRenderer, "Forge " + ForgeVersion.getVersion(), 2, this.height - 20, -1);
        this.drawString(this.fontRenderer, "Dofuscraft " + References.VERSION, 2, this.height - 10, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
